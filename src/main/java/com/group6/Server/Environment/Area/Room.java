package com.group6.Server.Environment.Area;

import java.awt.*;
import java.awt.geom.Point2D;

public class Room extends Area<Rect> {

    private static final Color DEFAULT_COLOR = Color.GRAY;

    private Color color;

    public Room(int rewardPoints, Point2D position, Color color) {
        super(true, rewardPoints, position);
        this.color = color;
    }

    public Room(int rewardPoints, Point2D position) {
        this(rewardPoints, position, DEFAULT_COLOR);
    }

    public Room(int rewardPoints, Point2D position, Rect rect, Color color) {
        super(true, rewardPoints, position, rect);
        this.color = color;
    }

    public Room(int rewardPoints, Point2D position, Rect rect) {
        this(rewardPoints, position, rect, DEFAULT_COLOR);
    }

    public Color getColor() {
        return color;
    }

}