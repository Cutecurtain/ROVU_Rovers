package com.group6.Server.Robot;

import com.group6.Server.Environment.Area.AreaFactory;
import com.group6.Server.Environment.Area.Division;
import com.group6.Server.Environment.Area.IArea;
import com.group6.Server.Environment.Area.Room;
import com.group6.Server.Environment.Environment;
import com.group6.Server.Environment.IEnvironment;
import junit.framework.Assert;
import org.junit.Test;
import simbad.sim.AbstractWall;
import simbad.sim.Boundary;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MissionTest {

    private Mission mission;

    private IEnvironment environment;

    public void setUp() {
        List<Room> rooms = new ArrayList<>();

        // Example room 1 - S001
        Room room1 = AreaFactory.createRoom(20, -5, -5, 5, Color.BLUE);
        rooms.add(room1);

        // Example room 2 - S002
        Room room2 = AreaFactory.createRoom(20, 0, -5, 5, Color.BLUE);
        rooms.add(room2);

        // Example room 3 - S003
        Room room3 = AreaFactory.createRoom(20, -5, 0, 2,5, Color.BLUE);
        rooms.add(room3);

        // Example room 4 - S004
        Room room4 = AreaFactory.createRoom(20, -2, 0, 2,5, Color.BLUE);
        rooms.add(room4);

        // Example room 5 - C001
        Room room5 = AreaFactory.createRoom(10, 0, 0, 5, Color.red);
        rooms.add(room5);

        room1.setX2Door(5/4);
        room1.setY2Door(5/4);

        room2.setX1Door(5/4);
        room2.setY2Door(5/4);

        room3.setX2Door(5/4);

        room4.setX1Door(5/4);
        room4.setX2Door(5/4);

        room5.setX1Door(5/4);
        room5.setY1Door(5/4);

        // A Division with all the rooms
        IArea division = new Division(0, rooms);

        // Our Environment
        environment = new Environment();

        // Add the created division to the environment
        environment.addArea(division);

        // Create the boundaries and the walls
        Boundary[] boundaries = environment.createBoundaries();
        List<AbstractWall> walls = environment.createWalls();

        mission = new Mission();
        mission.addMissionPoint(new Point2D.Double(2.5, -2.5));
        mission.addMissionPoint(new Point2D.Double(2.5, 2.5));
        mission.addMissionPoint(new Point2D.Double(-3.9, 2.5));
    }

    @Test
    public void chooseStrategy() {
        setUp();
        System.out.println("Before: " + mission.toString());
        Point2D first1 = mission.getMissionPoints().get(0);
        Point2D last1 = mission.getMissionPoints().get(2);
        mission.chooseStrategy(0, environment);
        System.out.println("After: "+ mission.toString());
        Point2D first2 = mission.getMissionPoints().get(0);
        Point2D last2 = mission.getMissionPoints().get(2);
        Assert.assertFalse(first1.equals(first2) && last1.equals(last2) && mission.getMissionPoints().size() > 3);
    }
}