package com.group6.Server.Environment.Area;

import java.awt.*;
import java.awt.geom.Point2D;

public class AreaFactory {

    public static Room createRoom(int rewardPoints, int posX, int posY, double size, Color color) {
        return new Room(rewardPoints, new Point2D.Double(posX,posY), new Rect(new Point2D.Double(0,0), new Point2D.Double(size,size)), color);
    }

    public static Room createRoom(int rewardPoints, int posX, int posY, double size) {
        return new Room(rewardPoints, new Point2D.Double(posX,posY), new Rect(new Point2D.Double(0,0), new Point2D.Double(size,size)));
    }

}
