package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Division extends AbstractArea {

    // This will be added onto the room reward
    private int divisionReward;

    private List<Room> rooms;

    public Division(int rewardPoints, Point2D position, List<Room> rooms) {
        super(true, position);
        divisionReward = rewardPoints;
        this.rooms = rooms;
    }

    public Division(Point2D position, List<Room> rooms) {
        this(0, position, rooms);
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
        int total = 0;
        for (Room room : rooms) {
            total += room.collectReward(point);
        }
        return total == 0 ? 0 : total + divisionReward;
    }

    public List<Room> getRooms() {
        return rooms;
    }

}
