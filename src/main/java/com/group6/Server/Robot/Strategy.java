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

        Queue<Path> pq = new PriorityQueue<Path>();
        pq.add(new Path(missionPoints.get(0)));

        for (int i = 1; i < missionPoints.size(); ) {
            if (pq.peek() == null)
                return null;
            Path shortest = pq.poll();
            Point2D current = shortest.getPath().get(shortest.getPath().size() - 1);
            Point2D goal = missionPoints.get(i);

            if (current == goal)
                goal = missionPoints.get(++i);

            List<Path> possiblePaths = pathDoors(shortest, goal, doors, verticalWalls, horizontalWalls);

            if (possiblePaths.size() > 0)
                pq.addAll(possiblePaths);

        }

        return pq.peek() == null ? null : pq.poll().getPath();
    }

    private List<Path> pathDoors(Path path, Point2D goal, List<Point2D> doors, List<double[]> vWalls, List<double[]> hWalls) {
        List<Path> newPaths = new ArrayList<>();
        Point2D current = path.getPath().get(path.getPath().size() - 1);

        if (isWallsBetween(current, goal, vWalls, hWalls)) {
            for (Point2D door : doors) {
                if (!path.visited(door) && !isWallsBetween(current, door, vWalls, hWalls)) {
                    path.visit(door);
                    Path newPath = new Path(path);
                    newPath.add(door);
                    newPaths.addAll(pathDoors(newPath, goal, doors, vWalls, hWalls));
                }
            }
        } else {
            newPaths.add(path);
        }

        return newPaths;
    }

    private boolean isWallsBetween(Point2D current, Point2D goal, List<double[]> vWalls, List<double[]> hWalls) {
        for (double[] vWall : vWalls) {
            if (isBetween(vWall[0], current.getX(), goal.getX())) {
                if (isBetween(current.getY(), vWall[1], vWall[2]) && isBetween(goal.getY(), vWall[1], vWall[2])) {
                    return true;
                }
            }
        }

        for (double[] hWall : hWalls) {
            if (isBetween(hWall[0], current.getY(), goal.getY())) {
                if (isBetween(current.getX(), hWall[1], hWall[2]) && isBetween(goal.getX(), hWall[1], hWall[2])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBetween(double d, double v1, double v2) {
        return v1 < v2 ? d > v1 && d < v2 : d > v2 && d < v1; // An open interval
    }

    List<Point2D> nearestPath() {
        List<Point2D> orderedPoints = new ArrayList<Point2D>();
        List<Point2D> pointsVisited = new ArrayList<Point2D>();
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
        for (int i = 0; i < missionPoint.size(); i++) {
            if (pointsVisited.get(l) == missionPoint.get(i)) {
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
            return path.path.size() - this.path.size();
        }
    }
}



