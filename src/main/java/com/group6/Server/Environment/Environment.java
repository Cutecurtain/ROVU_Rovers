package com.group6.Server.Environment;

import com.group6.Server.Environment.Area.*;
import com.group6.Server.Robot.IRobot;
import simbad.sim.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Environment implements IEnvironment{

    private static final Color TRANSPARENT = new Color(0,0,0,0);

    private static final float EDGE_DISTANCE = 0.5f;

    private EnvironmentDescription environMentDescription;

    private List<IArea> areas;

    private Color color;

    private List<ActorInArea> actorsInAreas;

    public Environment(List<IArea> areas, Color boundaryColor) {
        this.environMentDescription = new EnvironmentDescription();
        this.areas = areas;
        this.color = boundaryColor;
        this.actorsInAreas = new ArrayList<ActorInArea>();
    }

    public Environment(List<IArea> areas) {
        this(areas, TRANSPARENT);
    }

    public Environment(Color boundaryColor) {
        this(new ArrayList<IArea>(), boundaryColor);
    }

    public Environment() {
        this(TRANSPARENT);
    }

    public void addArea(IArea area) {
        this.areas.add(area);
    }

    public int logicalReward(Point2D point) {
        return collectReward(point, false);
    }

    public int physicalReward(Point2D point) {
        return collectReward(point, true);
    }

    public Boundary[] createBoundaries() {
        // x1, x2, y1, y2
        Boundary[] boundaries = new Boundary[4];

        float left = Float.MAX_VALUE;
        float right = Float.MIN_VALUE;
        float top = Float.MAX_VALUE;
        float bottom = Float.MIN_VALUE;
        for (IArea area : areas) {
            Point2D[] edges = area.getEdges();
            if (edges[0].getX() < left)
                left = (float) edges[0].getX();
            if (edges[1].getX() > right)
                right = (float) edges[1].getX();
            if (edges[0].getY() < top)
                top = (float) edges[0].getY();
            if (edges[1].getY() > bottom)
                bottom = (float) edges[1].getY();
        }

        // x1
        boundaries[0] = new HorizontalBoundary(left - EDGE_DISTANCE,
                                               top - EDGE_DISTANCE,
                                               bottom + EDGE_DISTANCE, environMentDescription, color);
        // x2
        boundaries[1] = new HorizontalBoundary(right + EDGE_DISTANCE,
                                               top - EDGE_DISTANCE,
                                               bottom + EDGE_DISTANCE, environMentDescription, color);
        // y1
        boundaries[2] = new VerticalBoundary(bottom + EDGE_DISTANCE,
                                             left - EDGE_DISTANCE,
                                             right + EDGE_DISTANCE, environMentDescription, color);
        // y2
        boundaries[3] = new VerticalBoundary(top - EDGE_DISTANCE,
                                             left - EDGE_DISTANCE,
                                             right + EDGE_DISTANCE, environMentDescription, color);

        // Don't know if this is needed
        environMentDescription.setWorldSize(Math.max(right-left, bottom - top));

        return boundaries;
    }

    public List<AbstractWall> createWalls() {
        List<AbstractWall> walls = new ArrayList<AbstractWall>();

        for (IArea area : areas) {
            if (area instanceof Room) // Is this a good idea?
                walls.addAll(getWalls((Room) area));
            else if (area instanceof Division) {
                for (Room room : ((Division) area).getRooms())
                    walls.addAll(getWalls(room));
            }
        }

        return walls;
    }

    public EnvironmentDescription getEnvironmentDescription() {
        return environMentDescription;
    }

    public void addActor(IRobot robot) {
        actorsInAreas.add(new ActorInArea(robot));
    }

    public void updateAreas() {
        for (ActorInArea actorInArea : actorsInAreas)
            actorInArea.hasEnteredNewRoom();
    }

    private List<AbstractWall> getWalls(Room room) {
        // x1, x2, y1 ,y2
        List<AbstractWall> abstractWalls = new ArrayList<AbstractWall>();
        Point2D[] edges = room.getEdges();

        double xWallLength = (edges[1].getX() - edges[0].getX()) * 3/8;
        double yWallLength = (edges[1].getY() - edges[0].getY()) * 3/8;

        // x1 1
        abstractWalls.add(new HorizontalWall((float) edges[0].getX(),
                                             (float) edges[0].getY(),
                                             (float) (edges[0].getY() + yWallLength), environMentDescription, room.getColor()));
        // x1 2
        abstractWalls.add(new HorizontalWall((float) edges[0].getX(),
                                             (float) edges[1].getY(),
                                             (float) (edges[1].getY() - yWallLength), environMentDescription, room.getColor()));

        // x2 1
        abstractWalls.add(new HorizontalWall((float) edges[1].getX(),
                                             (float) edges[0].getY(),
                                             (float) (edges[0].getY() + yWallLength), environMentDescription, room.getColor()));
        // x2 2
        abstractWalls.add(new HorizontalWall((float) edges[1].getX(),
                                             (float) edges[1].getY(),
                                             (float) (edges[1].getY() - yWallLength), environMentDescription, room.getColor()));


        // y1 1
        abstractWalls.add(new VerticalWall((float) edges[0].getY(),
                                           (float) edges[0].getX(),
                                           (float) (edges[0].getX() + xWallLength), environMentDescription, room.getColor()));
        // y1 2
        abstractWalls.add(new VerticalWall((float) edges[0].getY(),
                                           (float) edges[1].getX(),
                                           (float) (edges[1].getX() - xWallLength), environMentDescription, room.getColor()));

        // y2 1
        abstractWalls.add(new VerticalWall((float) edges[1].getY(),
                                           (float) edges[0].getX(),
                                           (float) (edges[0].getX() + xWallLength), environMentDescription, room.getColor()));
        // y2 2
        abstractWalls.add(new VerticalWall((float) edges[1].getY(),
                                           (float) edges[1].getX(),
                                           (float) (edges[1].getX() - xWallLength), environMentDescription, room.getColor()));
        return abstractWalls;
    }

    private int collectReward(Point2D point, boolean isPhysical) {
        int rewardPoints = 0;
        for (IArea area : areas) {
            if (area.isPhysical() == isPhysical)
                rewardPoints += area.collectReward(point);
        }
        return rewardPoints;
    }

    private class ActorInArea {
        private IRobot robot;
        private List<IArea> activeAreas;

        ActorInArea(IRobot robot) {
            this.robot = robot;
            findAreas();
        }

        void hasEnteredNewRoom() {
            List<IArea> previous = activeAreas;
            findAreas();
            for (IArea area : activeAreas) {
                if (area instanceof Room) {
                    if (!previous.contains(area)) {
                        robot.enteredRoom();
                        return;
                    }
                }
            }
        }

        private void findAreas() {
            activeAreas = new ArrayList<IArea>();
            for (IArea area : areas) {
                if (area instanceof Division)
                    inDivision((Division) area);
                else if (area.isPosIn(robot.getPosition()))
                    activeAreas.add(area);
            }
        }

        private void inDivision(Division division) {
            for (Room room : division.getRooms()) {
                if (room.isPosIn(robot.getPosition()))
                    activeAreas.add(room);
            }
        }

    }

}
