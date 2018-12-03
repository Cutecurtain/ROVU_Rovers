package com.group6.Server.Environment.Area;

import java.awt.*;

public class Rect implements IShape{
    private Point point1, point2;

    public Rect(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public boolean isPosIn(Point point) {
        boolean withinX;
        boolean withinY;

        if (point1.getX() > point2.getX())
            withinX = point.getX() >= point1.getX() && point.getX() <= point2.getX();
        else
            withinX = point.getX() >= point2.getX() && point.getX() <= point1.getX();

        if (point1.getY() > point2.getY())
            withinY = point.getY() >= point1.getY() && point.getY() <= point2.getY();
        else
            withinY = point.getY() >= point2.getY() && point.getY() <= point1.getY();

        return withinX && withinY;
    }
}
