package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public interface IArea {

    boolean isPosIn(Point2D point);
    boolean isPhysical();
    int getRewardPoints();
}
