package com.group6.Server.Environment;

import com.group6.Server.Environment.Area.IArea;
import simbad.sim.AbstractWall;
import simbad.sim.Boundary;
import simbad.sim.EnvironmentDescription;

import java.awt.geom.Point2D;
import java.util.List;

public interface IEnvironment {

    void addArea(IArea area);

    int logicalReward(Point2D point);

    int physicalReward(Point2D point);

    List<double[]> getVerticalWalls();   // returns: x y1 y2
    List<double[]> getHorizontalWalls(); // returns: y x1 x2

    List<double[]> getVerticalDoors();   // returns: x y
    List<double[]> getHorizontalDoors(); // returns: x y

    Boundary[] createBoundaries();

    List<AbstractWall> createWalls();

    EnvironmentDescription getEnvironmentDescription();

    void addActor(IActor actor);

    boolean isActorInPhysical();

    boolean isActorInLogical();

    void updateAreas();

}
