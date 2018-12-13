package com.group6.Server.Environment;

import simbad.sim.AbstractWall;
import simbad.sim.Boundary;

import java.awt.geom.Point2D;
import java.util.List;

public interface IEnvironment {

    int logicalReward(Point2D point);

    int physicalReward(Point2D point);

    Boundary[] createBoundaries();

    List<AbstractWall> createWalls();

}
