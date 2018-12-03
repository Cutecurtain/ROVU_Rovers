package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Circle implements IShape{

    private Point2D point;
    private float radius;

    public Circle(Point2D middlePoint, float radius) {
        this.point = middlePoint;
        this.radius = radius;
    }

    public boolean isPosIn(Point2D point) {
        double dx = Math.abs(this.point.getX() - point.getX());
        double dy = Math.abs(this.point.getY() - point.getY());

        double distance = Math.sqrt(dx*dx + dy*dy);

        return distance <= radius;
    }
}
