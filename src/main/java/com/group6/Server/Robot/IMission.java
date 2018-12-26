package com.group6.Server.Robot;

import com.group6.Server.Environment.IEnvironment;

import java.awt.geom.Point2D;
import java.util.List;

public interface IMission {

    List<Point2D> getMissionPoints();

    void addMissionPoint(Point2D point);

    void chooseStrategy(int i, IEnvironment environment);

}
