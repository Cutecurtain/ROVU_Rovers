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
        List<Point2D> missionPoints = new ArrayList<>();
        List<Point2D> results ;

        missionPoints.add(new Point2D.Double(1,1));

        missionPoints.add(new Point2D.Double(1,3));
        missionPoints.add(new Point2D.Double(2,20));

        missionPoints.add(new Point2D.Double(2,6));
        missionPoints.add(new Point2D.Double(1,3));
        missionPoints.add(new Point2D.Double(2,2));

        Mission mission = new Mission();
        mission.addMissionPoint(new Point2D.Double( 2.5, -2.5));
        mission.addMissionPoint(new Point2D.Double( 2.5, 2.5));
        mission.addMissionPoint(new Point2D.Double( 2.5, -2.5));
        mission.addMissionPoint(new Point2D.Double( 2.5, -2.4));
        mission.addMissionPoint(new Point2D.Double( -2.5, -2.5));
        //mission.chooseStrategy(1);

        System.out.print(mission.toString());

        //Strategy strategy = new Strategy(missionPoints);

        //results = strategy.nearestPath();


        //System.out.println(results);
    }





}