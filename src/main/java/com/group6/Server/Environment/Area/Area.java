package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Area<S extends IShape> extends AbstractArea{

    private S shape;
    private int rewardPoints;

    public Area(boolean isPhysical, int rewardPoints, Point2D position) {
        super(isPhysical, position);
        this.rewardPoints = rewardPoints;
    }

    public Area(boolean isPhysical, int rewardPoints, Point2D position, S shape) {
        this(isPhysical, rewardPoints, position);
        this.shape = shape;
    }

    public Point2D[] getEdges() {
        if (shape == null)
            return null;

        Point2D[] edges = new Point2D[2];
        Point2D localMiddlePoint = shape.getLocalMiddlePoint();
        double horizontalRadius = shape.getHorizontalRadius();
        double verticalRadius = shape.getVerticalRadius();
        edges[0] = new Point2D.Double(super.getPosition().getX(), super.getPosition().getY());
        edges[1] = new Point2D.Double(super.getPosition().getX() + localMiddlePoint.getX() + 2*horizontalRadius,
                                     super.getPosition().getY() + localMiddlePoint.getY() + 2*verticalRadius);
        return edges;
    }

    public void addShape(S shape) {
        this.shape = shape;
    }

    public boolean isPosIn(Point2D point) {
        if (shape == null)
            return false;

        if (point.getX() < super.getPosition().getX() || point.getY() < super.getPosition().getY())
            return false;

        double withinX = point.getX() - super.getPosition().getX();
        double withinY = point.getY() - super.getPosition().getY();

        return shape.isPosIn(new Point2D.Double(withinX, withinY));
    }

    public int collectReward(Point2D point) {
        if (isPosIn(point))
            return rewardPoints;
        return 0;
    }

}
