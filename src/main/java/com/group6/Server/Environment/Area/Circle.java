package com.group6.Server.Environment.Area;

import java.awt.*;

public class Circle implements IShape{

    private Point point;
    private float radius;

    public Circle(Point middlePoint, float radius) {
        this.point = middlePoint;
        this.radius = radius;
    }

    public boolean isPosIn(Point point) {
        float dx = (float) Math.abs(this.point.getX() - point.getX());
        float dy = (float) Math.abs(this.point.getY() - point.getY());

        float distance = (float) Math.sqrt(dx*dx + dy*dy);

        return distance <= radius;
    }
}
