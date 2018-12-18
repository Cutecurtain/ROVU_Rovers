package com.group6.Server;

import com.group6.Server.Environment.Area.AreaFactory;
import com.group6.Server.Environment.Area.Division;
import com.group6.Server.Environment.Area.IArea;
import com.group6.Server.Environment.Area.Room;
import com.group6.Server.Environment.Environment;
import simbad.sim.AbstractWall;
import simbad.sim.Boundary;
import simbad.sim.EnvironmentDescription;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private Environment environment;

    public void start() {
        createEnvironment();

        Thread thread = new Thread() {
            @Override
            public void run() {
                //main();
            }
        };
        thread.start();
    }

    public EnvironmentDescription getEnvironment() {
        return environment;
    }

    private void createEnvironment() {
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
        environment = new Environment();

        // Add the created division to the environment
        environment.addArea(division);

        // Create the boundaries and the walls
        Boundary[] boundaries = environment.createBoundaries();
        List<AbstractWall> walls = environment.createWalls();
    }

    private void main() {
        while (true) {
            // Do something
        }
    }

    private void procedureA() {

    }

    private void procedureB() {

    }


}
