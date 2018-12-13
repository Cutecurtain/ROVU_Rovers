package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Room extends Area<Rect> {

    public Room(int rewardPoints, Point2D position) {
        super(true, rewardPoints, position);
    }

    public Room(int rewardPoints, Point2D position, Rect rect) {
        super(true, rewardPoints, position, rect);
    }

}