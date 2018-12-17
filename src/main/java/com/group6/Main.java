package com.group6;

import com.group6.RobotRover.Planner;
import com.group6.Server.Environment.Area.AreaFactory;
import com.group6.Server.Environment.Area.Division;
import com.group6.Server.Environment.Area.IArea;
import com.group6.Server.Environment.Area.Room;
import com.group6.Server.Environment.Environment;
import com.group6.Server.ROVU.Controller;
import com.group6.Server.ROVU.Model;
import com.group6.Server.ROVU.View;
import project.AbstractSimulatorMonitor;
import project.Point;
import simbad.sim.AbstractWall;
import simbad.sim.Boundary;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main (String[] args) throws InterruptedException {

        View view = new View();
        Model model = new Model();
        Controller controller1 = new Controller(view, model);
        view.setVisible(true);

        /*
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
        */

        // A list of rooms
        List<Room> rooms = new ArrayList<Room>();

        AreaFactory areaFactory = new AreaFactory();


        // Example room 1
        rooms.add(areaFactory.createRoom(1, -5,-5, 5, Color.red));

        // Example room 2
        rooms.add(areaFactory.createRoom(2, 0,-5, 5, Color.BLUE));

        // Example room 3
        rooms.add(areaFactory.createRoom(3, -5,0, 5, Color.GREEN));
        //rooms.add(new Room(3, new Point2D.Double(-5,0), new Rect(new Point2D.Double(0,0), new Point2D.Double(5,5))));

        // Example room 4
        rooms.add(areaFactory.createRoom(4, 0,0, 5));

        // A Division with all the rooms
        IArea division = new Division(0, rooms);

        // Our Environment
        Environment e = new Environment();

        // Add the created division to the environment
        e.addArea(division);

        // Create the boundaries and the walls
        Boundary[] boundaries = e.createBoundaries();
        List<AbstractWall> walls = e.createWalls();

        Set<Planner> robots = new HashSet<Planner>();
        Planner robot1 = new Planner(new Point(0, 0), "Robot 1");
        Planner robot2 = new Planner(new Point(1, 3), "Robot 2");

        robots.add(robot1);
        robots.add(robot2);

        AbstractSimulatorMonitor controller = new SimulatorMonitor(robots, e);

    }

}
