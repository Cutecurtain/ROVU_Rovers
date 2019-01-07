package com.group6.Server.Robot;

import com.group6.Server.Environment.IEnvironment;

import java.awt.geom.Point2D;
import java.util.*;

class Strategy {

    private List<Point2D> missionPoints;

    Strategy(List<Point2D> missionPoints) {
        this.missionPoints = missionPoints;
    }

    List<Point2D> throughDoors(IEnvironment environment) {
        if (missionPoints.size() <= 1)
            return missionPoints;

        List<double[]> verticalWalls = environment.getVerticalWalls();
        List<double[]> horizontalWalls = environment.getHorizontalWalls();

        List<Point2D> doors = new ArrayList<>();
        for (double[] door : environment.getVerticalDoors())
            doors.add(new Point2D.Double(door[0], door[1]));
        for (double[] door : environment.getHorizontalDoors())
            doors.add(new Point2D.Double(door[0], door[1]));

        Queue<Path> pq = new PriorityQueue<>();
        pq.add(new Path(missionPoints.get(0)));

        for (int i = 1; i < missionPoints.size(); ) {
            if (pq.peek() == null)
                break;
            Path shortest = pq.poll();
            Point2D current = shortest.getPath().get(shortest.getPath().size() - 1);
            Point2D goal = missionPoints.get(i);

            if (current == goal) {
                if (missionPoints.size() == ++i)
                    return shortest.getPath();
                goal = missionPoints.get(i);
                pq = new PriorityQueue<>();
            }

            List<Path> possiblePaths = pathDoors(shortest, goal, doors, verticalWalls, horizontalWalls);

            if (possiblePaths.size() > 0)
                pq.addAll(possiblePaths);
        }
        return null;
    }

    private List<Path> pathDoors(Path path, Point2D goal, List<Point2D> doors, List<double[]> vWalls, List<double[]> hWalls) {
        List<Path> newPaths = new ArrayList<>();
        Point2D current = path.getPath().get(path.getPath().size() - 1);

        if (!isInSameRoom(current, goal, vWalls, hWalls)) {
            for (Point2D door : doors) {
                if (!path.visited(door) && isInSameRoom(current, door, vWalls, hWalls)) {
                    path.visit(door);
                    Path newPath = new Path(path);
                    newPath.add(door);
                    newPaths.addAll(pathDoors(newPath, goal, doors, vWalls, hWalls));
                }
            }
        } else {
            path.visit(goal);
            path.add(goal);
            newPaths.add(path);
        }

        return newPaths;
    }

    private boolean isInSameRoom(Point2D current, Point2D goal, List<double[]> vWalls, List<double[]> hWalls) {
        double leftToCurrent = -Double.MAX_VALUE;
        double rightToCurrent = Double.MAX_VALUE;
        double leftToGoal = -Double.MAX_VALUE;
        double rightToGoal = Double.MAX_VALUE;

        for (double[] vWall : vWalls) {
            if (isBetween(current.getY(), vWall[1], vWall[2])) {
                if (vWall[0] < current.getX() && vWall[0] > leftToCurrent)
                    leftToCurrent = vWall[0];
                else if (vWall[0] > current.getX() && vWall[0] < rightToCurrent)
                    rightToCurrent = vWall[0];
                else if (vWall[0] == current.getX()) {
                    leftToCurrent = vWall[0];
                    rightToCurrent = vWall[0];
                }
            }
            if (isBetween(goal.getY(), vWall[1], vWall[2])) {
                if (vWall[0] < goal.getX() && vWall[0] > leftToGoal)
                    leftToGoal = vWall[0];
                else if (vWall[0] > goal.getX() && vWall[0] < rightToGoal)
                    rightToGoal = vWall[0];
                else if (vWall[0] == goal.getX()) {
                    leftToGoal = vWall[0];
                    rightToGoal = vWall[0];
                }
            }
        }

        double topToCurrent = -Double.MAX_VALUE;
        double bottomToCurrent = Double.MAX_VALUE;
        double topToGoal = -Double.MAX_VALUE;
        double bottomToGoal = Double.MAX_VALUE;

        for (double[] hWall : hWalls) {
            if (isBetween(current.getX(), hWall[1], hWall[2])) {
                if (hWall[0] < current.getY() && hWall[0] > topToCurrent)
                    topToCurrent = hWall[0];
                else if (hWall[0] > current.getY() && hWall[0] < bottomToCurrent)
                    bottomToCurrent = hWall[0];
                else if (hWall[0] == current.getY()) {
                    topToCurrent = hWall[0];
                    bottomToCurrent = hWall[0];
                }
            }
            if (isBetween(goal.getX(), hWall[1], hWall[2])) {
                if (hWall[0] < goal.getY() && hWall[0] > topToGoal)
                    topToGoal = hWall[0];
                else if (hWall[0] > goal.getY() && hWall[0] < bottomToGoal)
                    bottomToGoal = hWall[0];
                else if (hWall[0] == goal.getY()) {
                    topToGoal = hWall[0];
                    bottomToGoal = hWall[0];
                }
            }
        }

        // Check if two doors the the same room
        if ((leftToCurrent == rightToCurrent && leftToGoal == rightToGoal) ||
                (leftToCurrent == rightToCurrent && topToGoal == bottomToGoal) ||
                (topToCurrent == bottomToCurrent && leftToGoal == rightToGoal) ||
                (topToCurrent == bottomToCurrent && topToGoal == bottomToGoal))
            return true;

        // Check if one of the points is a door to the room the other point is in
        if (topToCurrent == topToGoal && bottomToCurrent == bottomToGoal) {
            if ((leftToCurrent == rightToCurrent && (leftToCurrent == leftToGoal || leftToCurrent == rightToGoal)) ||
                    (leftToGoal == rightToGoal && (leftToGoal == leftToCurrent || leftToGoal == rightToCurrent)))
                return true;
        } else if (leftToCurrent == leftToGoal && rightToCurrent == rightToGoal) {
            if ((topToCurrent == bottomToCurrent && (topToCurrent == topToGoal || topToCurrent == bottomToGoal)) ||
                    (topToGoal == bottomToGoal && (topToGoal == topToCurrent || topToGoal == bottomToCurrent)))
                return true;
        }

        // Check if the points are inside the same room
        return leftToCurrent == leftToGoal && rightToCurrent == rightToGoal
                && topToCurrent == topToGoal && bottomToCurrent == bottomToGoal;
    }

    private boolean isBetween(double d, double v1, double v2) {
        return v1 < v2 ? d >= v1 && d <= v2 : d >= v2 && d <= v1; // A closed interval
    }

    List<Point2D> nearestPath() {
        List<Point2D> orderedPoints = new ArrayList<>();
        List<Point2D> pointsVisited = new ArrayList<>();
        orderedPoints.add(0, missionPoints.get(0));
        pointsVisited.add(0, missionPoints.get(0));
        double distance = 100;


        if (missionPoints.size() == 1) {
            return this.missionPoints;
        }

        for (int i = 0; i < missionPoints.size(); i++) {
            double newDistance;
            double x1 = missionPoints.get(i).getX();
            double y1 = missionPoints.get(i).getY();
            int count = 0;

            for (int l = 0; l < pointsVisited.size(); l++) {
                if (visited(pointsVisited, missionPoints, l)) {
                    count++;
                }
            }

            for (int l = count; l < missionPoints.size(); l++) {
                double x2 = missionPoints.get(l).getX();
                double y2 = missionPoints.get(l).getY();
                newDistance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
                if (newDistance < distance && l > 2) {
                    distance = newDistance;
                    pointsVisited.add(missionPoints.get(l));
                    orderedPoints.add(l - 1, missionPoints.get(l));
                } else {
                    pointsVisited.add(missionPoints.get(l));
                    orderedPoints.add(l, missionPoints.get(l));
                }
            }


        }
        return orderedPoints;
    }

    boolean visited(List<Point2D> pointsVisited, List<Point2D> missionPoint, int l) {
        for (Point2D point2D : missionPoint) {
            if (pointsVisited.get(l) == point2D) {
                return true;
            }
        }
        return false;
    }

    private class Path implements Comparable<Path> {
        private List<Point2D> path;
        private Set<Point2D> visited;

        Path(Point2D start) {
            path = new ArrayList<>();
            path.add(start);
            visited = new HashSet<>();
        }

        Path(Path toClone) {
            path = new ArrayList<>(toClone.path);
            visited = new HashSet<>(toClone.visited);
        }

        void add(Point2D point) {
            path.add(point);
        }

        void visit(Point2D point) {
            visited.add(point);
        }

        boolean visited(Point2D point) {
            return visited.contains(point);
        }

        List<Point2D> getPath() {
            return path;
        }

        @Override
        public int compareTo(Path path) {
            return this.path.size() - path.path.size();
        }
    }
}



