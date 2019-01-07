package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Composition<S extends IShape> implements IShape{

    private List<S> shapes;

    public Composition() {
        this.shapes = new ArrayList<>();
    }

    public Composition(List<S> shapes) {
        this.shapes = shapes;
    }

    public Composition(S shape) {
        this();
        this.shapes.add(shape);
    }

    public boolean isPosIn(Point2D point) {
        for (IShape shape : shapes) {
            if (shape.isPosIn(point))
                return true;
        }
        return false;
    }

    public double getHorizontalRadius() {
        double furthest = Double.MIN_VALUE;
        for (IShape shape : shapes) {
            double right = shape.getLocalMiddlePoint().getX() + shape.getHorizontalRadius();
            if (right > furthest)
                furthest = right;
        }
        return (furthest - getLocalMiddlePoint().getX());
    }

    public double getVerticalRadius() {
        double furthest = Double.MIN_VALUE;
        for (IShape shape : shapes) {
            double bottom = shape.getLocalMiddlePoint().getY() + shape.getVerticalRadius();
            if (bottom > furthest)
                furthest = bottom;
        }
        return (furthest - getLocalMiddlePoint().getY());
    }

    public Point2D getLocalMiddlePoint() {
        double minLeft = Double.MAX_VALUE;
        double maxRight = Double.MIN_VALUE;
        double minTop = Double.MAX_VALUE;
        double maxBottom = Double.MIN_VALUE;

        for (IShape shape : shapes) {
            double left = shape.getLocalMiddlePoint().getX() - shape.getHorizontalRadius();
            double right = shape.getLocalMiddlePoint().getX() + shape.getHorizontalRadius();
            double top = shape.getLocalMiddlePoint().getY() - shape.getVerticalRadius();
            double bottom = shape.getLocalMiddlePoint().getY() + shape.getVerticalRadius();
            if (left < minLeft)
                minLeft = left;
            if (right > maxRight)
                maxRight = right;
            if (top < minTop)
                minTop = top;
            if (bottom > maxBottom)
                maxBottom = bottom;
        }
        return new Point2D.Double((maxRight - minLeft) / 2, (maxBottom - minTop) / 2);
    }


}
