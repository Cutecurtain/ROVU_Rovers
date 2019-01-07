package com.group6.RobotRover;

import org.junit.Before;
import org.junit.Test;
import project.Point;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class PlannerTest {

    private Planner planner = new Planner(new Point(0,0), "Test");

    @Before
    public void setUp() {

    }

    @Test
    public void addMissionPoint() {
        List<Point2D> missionPoints = new ArrayList<>();
        missionPoints.add(new Point2D.Double(1,1));
        missionPoints.add(new Point2D.Double(2,2));
        missionPoints.add(new Point2D.Double(3,3));
        planner.addMissionPoint(missionPoints);
        //assertTrue(planner.addMissionPoint(missionPoints));
    }

    @Test
    public void followPath() {
        boolean oldValue = planner.isAvailable();
        planner.followPath();
        assertFalse(oldValue == planner.isAvailable());
    }

    @Test
    public void emergencyStop() {
        boolean oldValue = planner.isStopped();
        planner.emergencyStop();
        assertFalse(oldValue == planner.isStopped());
    }

    @Test
    public void haltRover() {
        boolean oldValue = planner.isHalted();
        planner.haltRover();
        assertFalse(oldValue == planner.isHalted());
    }

}