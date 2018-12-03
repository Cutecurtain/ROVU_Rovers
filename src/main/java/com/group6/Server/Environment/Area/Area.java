package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Area implements IArea{

    private IShape shape;
    private boolean isPhysical;
    private int rewardPoints;

    public Area(boolean isPhysical, int rewardPoints) {
        this.isPhysical = isPhysical;
        this.rewardPoints = rewardPoints;
    }

    public Area(boolean isPhysical, int rewardPoints, IShape shape) {
        this(isPhysical, rewardPoints);
        this.shape = shape;
    }

    public void addShape(IShape shape) {
        this.shape = shape;
    }

    public boolean isPosIn(Point2D point) {
        return shape.isPosIn(point);
    }

    public boolean isPhysical() {
        return isPhysical;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }


}
