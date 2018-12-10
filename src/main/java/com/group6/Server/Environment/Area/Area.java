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
