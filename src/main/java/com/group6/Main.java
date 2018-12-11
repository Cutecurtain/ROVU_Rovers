package com.group6;

import java.util.HashSet;
import java.util.Set;

import com.group6.RobotRover.Planner;
import project.Point;
import project.RobotAgent;
import project.AbstractSimulatorMonitor;
import project.AbstractRobotSimulator;
import simbad.gui.Simbad;
import simbad.sim.*;

import java.awt.Color;

public class Main {

    public static void main (String[] args) throws InterruptedException {

        // Just the standard example

        EnvironmentDescription e = new EnvironmentDescription();
        Color color = Color.GRAY;

        Boundary w1 = new HorizontalBoundary(-5.0f, -5.0f, 5.0f, e, color);
        Boundary w2 = new HorizontalBoundary(5.0f, -5.0f, 5.0f, e, color);
        Boundary w3 = new VerticalBoundary(5.0f, -5.0f, 5.0f, e, color);
        Boundary w4 = new VerticalBoundary(-5.0f, -5.0f, 5.0f, e, color);

        AbstractWall roomWall1 = new HorizontalWall(-1f, 4.5f, 3.5f, e, color);
        AbstractWall roomWall2 = new HorizontalWall(-4.5f, 4.5f, 1f, e, color);
        AbstractWall roomWall3 = new VerticalWall(4.5f, -4.5f, -1f, e, color);
        AbstractWall roomWall4 = new VerticalWall(1f, -4.5f, -1f, e, color);

        Set<Planner> robots = new HashSet<Planner>();
        Planner robot1 = new Planner(new Point(0, 0), "Robot 1");
        Planner robot2 = new Planner(new Point(1, 3), "Robot 2");

        robots.add(robot1);
        robots.add(robot2);

        AbstractSimulatorMonitor controller = new SimulatorMonitor(robots, e);

    }

}
