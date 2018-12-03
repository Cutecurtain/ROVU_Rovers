package com.group6.Server.Environment;

import java.awt.geom.Point2D;

public interface IEnvironment {

    int logicalReward(Point2D point);

    int physicalReward(Point2D point);

}
