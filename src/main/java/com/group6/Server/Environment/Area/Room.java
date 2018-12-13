package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Room extends Area implements INestable{
    public Room(boolean isPhysical, int rewardPoints, Point2D position) {
        super(isPhysical, rewardPoints, position);
    }

    public Room(boolean isPhysical, int rewardPoints, Point2D position, IShape shape) {
        super(isPhysical, rewardPoints, position, shape);
    }
}