package com.group6.Server.ROVU;

import com.group6.RobotRover.FaultHandler;
import com.group6.Server.Environment.IEnvironment;
import com.group6.Server.Robot.Mission;
import com.group6.Server.Robot.Robot;
import org.junit.Before;
import org.junit.Test;


import java.awt.*;

import static junit.framework.Assert.assertTrue;

public class ControllerTest {

    private Mission mission;
    private Controller controller;
    private FaultHandler faultHandler;
    private Robot robot;


    @Before
    public void setUp() {
        Mission mission = new Mission();
        Robot robot = new Robot();
        FaultHandler faultHandler = new FaultHandler();
        Controller controller = new Controller();
    }


    @Test
    public void testGiveMission() {

        assertTrue(mission.giveMission && robot.hasMission);
    }

    @Test
    public void collectRewardPointsLogical() {
        tank.moveToLogical();
        assertTrue(tank.rewardPoints > 0);
    }

    @Test
    public void collectRewardPointsPhysical() {
        tank.moveToPhysical();
        assertTrue(tank.rewardPoints > 0);
    }


    @Test
    public void changeProcedure() {

        assertTrue(robot.changeProcedure);
    }

    @Test
    public void emergencyStop() {
        assertTrue(controller.emergencyStop());

    }







}