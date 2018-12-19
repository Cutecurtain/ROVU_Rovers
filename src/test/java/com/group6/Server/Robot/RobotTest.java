package com.group6.Server.Robot;

import com.group6.RobotRover.FaultHandler;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;





public class RobotTest {


    private Mission mission;

    private Robot robot = new Robot(null, null);

    private FaultHandler faultHandler;

    // test case for checking strategy

    @Before
    public void setUp() {

    }


    @Test
    public void faultHandler() {

        //assertTrue(faultHandler.notify());
    }

    @Test
    public void testCompleteMission() {

        //assertTrue(robot.completeMission());
    }

    @Test

    public void testStrategy(){
        List<Point2D> missionPoints = new ArrayList<Point2D>();
        List<Point2D> results ;

        missionPoints.add(new Point2D.Double(1,1));
        missionPoints.add(new Point2D.Double(1,3));
        missionPoints.add(new Point2D.Double(2,20));
        missionPoints.add(new Point2D.Double(2,6));

        Strategy strategy = new Strategy(missionPoints);

        results = strategy.nearestPath(missionPoints);


        System.out.println(results);
    }





}