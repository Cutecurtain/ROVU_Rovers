package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Circle implements IShape{

    private Point2D point;
    private double radius;

    public Circle(Point2D middlePoint, float radius) {
        this.point = middlePoint;
        this.radius = radius;
    }

    public boolean isPosIn(Point2D point) {
        double dx = this.point.getX() - point.getX();
        double dy = this.point.getY() - point.getY();

        double distance = Math.sqrt(dx*dx + dy*dy);

        return distance <= radius;
    }

    public double getHorizontalRadius() {
        return radius;
    }

    public double getVerticalRadius() {
        return radius;
    }

    public Point2D getLocalMiddlePoint() {
        return new Point2D.Double(point.getX(), point.getY());
    }
}
