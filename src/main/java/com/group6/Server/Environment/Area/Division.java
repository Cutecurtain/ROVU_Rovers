package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;
import java.util.List;

public class Division extends AbstractArea {

    // This will be added onto the room reward
    private int divisionReward;

    private List<Room> rooms;

    public Division(int rewardPoints, List<Room> rooms) {
        super(true, null);
        divisionReward = rewardPoints;
        this.rooms = rooms;
    }

    public Division(Point2D position, List<Room> rooms) {
        this(0, rooms);
    }

    public Point2D[] getEdges() {
        double x1 = Double.MAX_VALUE;
        double x2 = Double.MIN_VALUE;
        double y1 = Double.MAX_VALUE;
        double y2 = Double.MIN_VALUE;
        for (Room room : rooms) {
            Point2D[] edges = room.getEdges();
            if (edges[0].getX() < x1)
                x1 = edges[0].getX();
            if (edges[1].getX() > x2)
                x2 = edges[1].getX();
            if (edges[0].getY() < y1)
                y1 = edges[0].getY();
            if (edges[1].getY() > y2)
                y2 = edges[1].getY();
        }
        return new Point2D[]{new Point2D.Double(x1, y1), new Point2D.Double(x2, y2)};
    }

    public boolean isPosIn(Point2D point) {
        for (Room room : rooms) {
            if (room.isPosIn(point))
                return true;
        }
        return false;
    }

    public int collectReward(Point2D point) {
        int total = divisionReward;
        for (Room room : rooms) {
            total += room.collectReward(point);
            if (total > divisionReward) // Logically, you can only be in one room at a time
                return total;
        }
        return 0;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public Point2D getPosition() {
        return getEdges()[0];
    }

}
