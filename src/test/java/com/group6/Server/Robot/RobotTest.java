package com.group6.Server.Robot;

import com.group6.RobotRover.FaultHandler;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class RobotTest {

    private Mission mission;
    private Robot robot;
    private FaultHandler faultHandler;

    @Before
    public void setUp() {
        Mission mission = new Mission();
        Robot robot = new Robot();
        FaultHandler faultHandler = new FaultHandler();
    }


    @Test
    public void faultHandler() {

        assertTrue(faultHandler().notify);
    }

    @Test
    public void testCompleteMission() {

        assertTrue(robot.completeMission);
    }




}