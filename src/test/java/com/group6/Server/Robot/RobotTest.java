package com.group6.Server.Robot;

import com.group6.RobotRover.FaultHandler;
import org.junit.Before;
import org.junit.Test;

public class RobotTest {

    private Mission mission;

    private Robot robot = new Robot(null, null);

    private FaultHandler faultHandler;

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




}