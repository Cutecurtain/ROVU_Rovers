package com.group6.Server.Robot;

import com.group6.RobotRover.FaultHandler;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class RobotTest {

    private Mission mission;
    private Robot robot = new Robot();
    private FaultHandler faultHandler;

    @Before
    public void setUp() {

    }


    @Test
    public void faultHandler() {

        assertTrue(faultHandler.notify());
    }

    @Test
    public void testCompleteMission() {

        assertTrue(robot.completeMission());
    }




}