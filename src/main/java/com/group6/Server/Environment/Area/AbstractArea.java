package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

abstract class AbstractArea implements IArea {

    private boolean isPhysical;

    private Point2D position;

    AbstractArea(boolean isPhysical, Point2D position) {
        this.isPhysical = isPhysical;
        this.position = position;
    }

    public Point2D getPosition() {
        return position;
    }

    public boolean isPhysical() {
        return isPhysical;
    }

}
