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

    private Controller controller = new Controller();
    private Robot robot = new Robot();


    @Before
    public void setUp() {

    }


    @Test
    public void testGiveMission() {

        assertTrue(mission.giveMission() && robot.hasMission);
    }

    @Test
    public void collectRewardPointsLogical() {
        robot.moveToLogical();
        assertTrue(robot.rewardPoints > 0);
    }

    @Test
    public void collectRewardPointsPhysical() {
        robot.moveToPhysical();
        assertTrue(robot.rewardPoints > 0);
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