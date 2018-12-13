package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Area implements IArea{

    private IShape shape;
    private boolean isPhysical;
    private int rewardPoints;

    private Point2D position;

    public Area(boolean isPhysical, int rewardPoints, Point2D position) {
        this.isPhysical = isPhysical;
        this.rewardPoints = rewardPoints;
        this.position = position;
    }

    public Area(boolean isPhysical, int rewardPoints, Point2D position, IShape shape) {
        this(isPhysical, rewardPoints, position);
        this.shape = shape;
    }

    public Point2D getPosition() {
        return position;
    }

    public Point2D[] getEdges() {
        Point2D[] edges = new Point2D[2];
        Point2D localMiddlePoint = shape.getLocalMiddlePoint();
        double horizontalRadius = shape.getHorizontalRadius();
        double verticalRadius = shape.getVerticalRadius();
        edges[0] = new Point2D.Double(position.getX(), position.getY());
        edges[1] = new Point2D.Double(position.getX() + localMiddlePoint.getX() + 2*horizontalRadius,
                                     position.getY() + localMiddlePoint.getY() + 2*verticalRadius);
        return edges;
    }

    public void addShape(IShape shape) {
        this.shape = shape;
    }

    public boolean isPosIn(Point2D point) {
        if (point.getX() < position.getX() || point.getY() < position.getY())
            return false;

        double withinX = point.getX() - position.getX();
        double withinY = point.getY() - position.getY();

        return shape.isPosIn(new Point2D.Double(withinX, withinY));
    }

    public boolean isPhysical() {
        return isPhysical;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }


}
