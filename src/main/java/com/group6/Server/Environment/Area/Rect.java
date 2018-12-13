package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Rect implements IShape{
    private Point2D point1, point2;

    public Rect(Point2D point1, Point2D point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public boolean isPosIn(Point2D point) {
        boolean withinX;
        boolean withinY;

        if (point1.getX() < point2.getX())
            withinX = point.getX() >= point1.getX() && point.getX() <= point2.getX();
        else
            withinX = point.getX() >= point2.getX() && point.getX() <= point1.getX();

        if (point1.getY() < point2.getY())
            withinY = point.getY() >= point1.getY() && point.getY() <= point2.getY();
        else
            withinY = point.getY() >= point2.getY() && point.getY() <= point1.getY();

        return withinX && withinY;
    }

    public double getHorizontalRadius() {
        return Math.abs(point1.getX() - point2.getX()) / 2;
    }

    public double getVerticalRadius() {
        return Math.abs(point1.getY() - point2.getY()) / 2;
    }

    public Point2D getLocalMiddlePoint() {
        double x = getHorizontalRadius();
        double y = getVerticalRadius();
        if (point1.getX() < point2.getX())
            x += point1.getX();
        else
            x += point2.getX();

        if (point1.getY() < point2.getY())
            y += point1.getY();
        else
            y += point2.getY();

        return new Point2D.Double(x, y);
    }
}
