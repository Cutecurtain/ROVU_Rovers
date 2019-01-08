package com.group6.Server.Environment;

import com.group6.Server.Environment.Area.*;
import simbad.sim.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Environment implements IEnvironment {

    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    private static final float EDGE_DISTANCE = 2;

    private EnvironmentDescription environmentDescription;

    private List<IArea> areas;

    private List<double[]> verticalWalls;
    private List<double[]> horizontalWalls;

    private List<double[]> verticalDoors;
    private List<double[]> horizontalDoors;

    private Color color;

    private List<IActor> actors;

    private List<RoomQueue> roomQueues;

    public Environment(List<IArea> areas, Color boundaryColor) {
        this.environmentDescription = new EnvironmentDescription();
        this.areas = new ArrayList<>();
        this.verticalWalls = new ArrayList<>();
        this.horizontalWalls = new ArrayList<>();
        this.verticalDoors = new ArrayList<>();
        this.horizontalDoors = new ArrayList<>();
        this.color = boundaryColor;
        this.actors = new ArrayList<>();
        this.roomQueues = new ArrayList<>();
        // To make sure queues are instantiated
        for (IArea area : areas)
            addArea(area);
    }

    public Environment(List<IArea> areas) {
        this(areas, TRANSPARENT);
    }

    public Environment(Color boundaryColor) {
        this(new ArrayList<>(), boundaryColor);
    }

    public Environment() {
        this(TRANSPARENT);
    }

    public void addArea(IArea area) {
        this.areas.add(area);
        if (area instanceof Room)
            roomQueues.add(new RoomQueue((Room) area));
        else if (area instanceof Division) {
            for (Room room : ((Division) area).getRooms())
                roomQueues.add(new RoomQueue(room));
        }
    }

    public int logicalReward(Point2D point) {
        return collectReward(point, false);
    }

    public int physicalReward(Point2D point) {
        return collectReward(point, true);
    }

    public List<double[]> getVerticalWalls() {
        return verticalWalls;
    }

    public List<double[]> getHorizontalWalls() {
        return horizontalWalls;
    }

    public List<double[]> getVerticalDoors() {
        return verticalDoors;
    }

    public List<double[]> getHorizontalDoors() {
        return horizontalDoors;
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
                bottom + EDGE_DISTANCE, environmentDescription, color);
        // x2
        boundaries[1] = new HorizontalBoundary(right + EDGE_DISTANCE,
                top - EDGE_DISTANCE,
                bottom + EDGE_DISTANCE, environmentDescription, color);
        // y1
        boundaries[2] = new VerticalBoundary(bottom + EDGE_DISTANCE,
                left - EDGE_DISTANCE,
                right + EDGE_DISTANCE, environmentDescription, color);
        // y2
        boundaries[3] = new VerticalBoundary(top - EDGE_DISTANCE,
                left - EDGE_DISTANCE,
                right + EDGE_DISTANCE, environmentDescription, color);

        // Don't know if this is needed
        environmentDescription.setWorldSize(Math.max(right - left, bottom - top) + 3);

        return boundaries;
    }

    public List<AbstractWall> createWalls() {
        List<AbstractWall> walls = new ArrayList<>();

        for (IArea area : areas) {
            if (area instanceof Room) // Is this a good idea?
                walls.addAll(getWalls((Room) area));
            else if (area instanceof Division) {
                for (Room room : ((Division) area).getRooms())
                    walls.addAll(getWalls(room));
            }
        }

        initWalls();
        initDoors();

        return walls;
    }

    public EnvironmentDescription getEnvironmentDescription() {
        return environmentDescription;
    }

    public void addActor(IActor actor) {
        actors.add(actor);
    }

    public boolean isActorInPhysical() {
        return isActorInTypeArea(true);
    }

    public boolean isActorInLogical() {
        return isActorInTypeArea(false);
    }

    private boolean isActorInTypeArea(boolean physical) {
        for (IArea area : areas) {
            if (area.isPhysical() == physical) {
                for (IActor actor : actors) {
                    if (area.isPosIn(actor.getPosition()))
                        return true;
                }
            }
        }
        return false;
    }

    public void updateAreas() {
        for (RoomQueue roomQueue : roomQueues) {
            for (IActor actor : actors)
                roomQueue.addActor(actor);
            roomQueue.updateQueue();
        }
    }

    private List<AbstractWall> getWalls(Room room) {
        // x1, x2, y1 ,y2
        List<AbstractWall> abstractWalls = new ArrayList<>();
        Point2D[] edges = room.getEdges();

        double yWallLength = (edges[1].getX() - edges[0].getX());
        double xWallLength = (edges[1].getY() - edges[0].getY());

        double[] doorX1 = room.getDoorX1();
        double[] doorX2 = room.getDoorX2();
        double[] doorY1 = room.getDoorY1();
        double[] doorY2 = room.getDoorY2();

        double x12 = xWallLength - doorX1[0] - doorX1[1];
        double x22 = xWallLength - doorX2[0] - doorX2[1];
        double y12 = yWallLength - doorY1[0] - doorY1[1];
        double y22 = yWallLength - doorY2[0] - doorY2[1];

        // x1 1
        if (doorX1[0] > 0) {
            abstractWalls.add(new HorizontalWall((float) edges[0].getX(),
                    (float) edges[0].getY(),
                    (float) (edges[0].getY() + doorX1[0]), environmentDescription, room.getColor()));
        }
        // x1 2
        if (x12 > 0) {
            abstractWalls.add(new HorizontalWall((float) edges[0].getX(),
                    (float) edges[1].getY(),
                    (float) (edges[1].getY() - x12), environmentDescription, room.getColor()));
        }

        // x2 1
        if (doorX2[0] > 0) {
            abstractWalls.add(new HorizontalWall((float) edges[1].getX(),
                    (float) edges[0].getY(),
                    (float) (edges[0].getY() + doorX2[0]), environmentDescription, room.getColor()));
        }
        // x2 2
        if (x22 > 0) {
            abstractWalls.add(new HorizontalWall((float) edges[1].getX(),
                    (float) edges[1].getY(),
                    (float) (edges[1].getY() - x22), environmentDescription, room.getColor()));
        }


        // y1 1
        if (doorY1[0] > 0) {
            abstractWalls.add(new VerticalWall((float) edges[0].getY(),
                    (float) edges[0].getX(),
                    (float) (edges[0].getX() + doorY1[0]), environmentDescription, room.getColor()));
        }
        // y1 2
        if (y12 > 0) {
            abstractWalls.add(new VerticalWall((float) edges[0].getY(),
                    (float) edges[1].getX(),
                    (float) (edges[1].getX() - y12), environmentDescription, room.getColor()));
        }

        // y2 1
        if (doorY2[0] > 0) {
            abstractWalls.add(new VerticalWall((float) edges[1].getY(),
                    (float) edges[0].getX(),
                    (float) (edges[0].getX() + doorY2[0]), environmentDescription, room.getColor()));
        }
        if (y22 > 0) {
            // y2 2
            abstractWalls.add(new VerticalWall((float) edges[1].getY(),
                    (float) edges[1].getX(),
                    (float) (edges[1].getX() - y22), environmentDescription, room.getColor()));
        }
        return abstractWalls;
    }

    private void initWalls() {
        for (IArea area : areas) {
            if (area instanceof Room) {
                initRoom((Room) area);
            } else if (area instanceof Division) {
                List<Room> rooms = ((Division) area).getRooms();
                for (Room room : rooms)
                    initRoom(room);
            }
        }
        verticalWalls = removeDupes(verticalWalls);
        horizontalWalls = removeDupes(horizontalWalls);
    }

    private void initRoom(Room room) {
        Point2D[] edges = room.getEdges();
        double x1 = edges[0].getX();
        double x2 = edges[1].getX();
        double y1 = edges[0].getY();
        double y2 = edges[1].getY();
        verticalWalls.add(new double[]{x1, y1, y2});
        verticalWalls.add(new double[]{x2, y1, y2});
        horizontalWalls.add(new double[]{y1, x1, x2});
        horizontalWalls.add(new double[]{y2, x1, x2});
    }

    private void initDoors() {
        for (IArea area : areas) {
            if (area instanceof Room) {
                initDoor((Room) area);
            } else if (area instanceof Division) {
                for (Room room : ((Division) area).getRooms())
                    initDoor(room);
            }
        }
        verticalDoors = removeDupes(verticalDoors);
        horizontalDoors = removeDupes(horizontalDoors);
    }

    private void initDoor(Room room) {
        Point2D[] edges = room.getEdges();
        if (room.getDoorX1()[1] > 0) {
            double x = edges[0].getX();
            double y = edges[0].getY() + room.getDoorX1()[0] + (room.getDoorX1()[1] / 2);
            verticalDoors.add(new double[]{x, y});
        }
        if (room.getDoorX2()[1] > 0) {
            double x = edges[1].getX();
            double y = edges[0].getY() + room.getDoorX2()[0] + (room.getDoorX2()[1] / 2);
            verticalDoors.add(new double[]{x, y});
        }
        if (room.getDoorY1()[1] > 0) {
            double y = edges[0].getY();
            double x = edges[0].getX() + room.getDoorY1()[0] + (room.getDoorY1()[1] / 2);
            horizontalDoors.add(new double[]{x, y});
        }
        if (room.getDoorY2()[1] > 0) {
            double y = edges[1].getY();
            double x = edges[0].getX() + room.getDoorY2()[0] + (room.getDoorY2()[1] / 2);
            horizontalDoors.add(new double[]{x, y});
        }
    }

    private List<double[]> removeDupes(List<double[]> list) {
        List<double[]> dupeFree = new ArrayList<>();
        for (double[] item1 : list) {
            boolean exists = false;
            for (double[] item2 : dupeFree) {
                if (Arrays.equals(item1, item2)) {
                    exists = true;
                    break;
                }
            }
            if (!exists)
                dupeFree.add(item1);
        }
        return dupeFree;
    }

    private int collectReward(Point2D point, boolean isPhysical) {
        int rewardPoints = 0;
        for (IArea area : areas) {
            if (area.isPhysical() == isPhysical)
                rewardPoints += area.collectReward(point);
        }
        return rewardPoints;
    }

    private class RoomQueue {

        private Room room;

        private BlockingQueue<IActor> actorQueue;

        RoomQueue(Room room) {
            this.room = room;
            actorQueue = new LinkedBlockingQueue<>();
        }

        void addActor(IActor actor) {
            if (!actorQueue.contains(actor) && !room.isPosIn(actor.getPosition()))
                return;
            if (!actorQueue.isEmpty())
                actor.halt();
            actor.sleep();
            actorQueue.offer(actor);
        }

        void updateQueue() {
            if (actorQueue.isEmpty())
                return;
            if (!room.isPosIn(actorQueue.peek().getPosition())) {
                actorQueue.poll();
                if (!actorQueue.isEmpty())
                    actorQueue.peek().start();
            }

        }

    }

}
