package com.group6.Server;

import com.group6.Server.Environment.Area.AreaFactory;
import com.group6.Server.Environment.Area.Division;
import com.group6.Server.Environment.Area.IArea;
import com.group6.Server.Environment.Area.Room;
import com.group6.Server.Environment.Environment;
import com.group6.Server.Environment.IEnvironment;
import com.group6.Server.Robot.IRobot;
import simbad.sim.AbstractWall;
import simbad.sim.Boundary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final long PROCEDURE_WAIT_TIME = 20000;

    private enum Procedure {
        A, B
    }

    private IEnvironment environment;

    private Procedure procedure;

    public void start() {
        createEnvironment();
        for (IRobot robot : Networking.getInstance().getRobots().values())
            environment.addActor(robot);

        Thread thread = new Thread() {
            @Override
            public void run() {
                main();
            }
        };
        thread.start();
    }

    public IEnvironment getEnvironment() {
        return environment;
    }

    private void createEnvironment() {
        List<Room> rooms = new ArrayList<Room>();

        AreaFactory areaFactory = new AreaFactory();

        // Example room 1
        rooms.add(areaFactory.createRoom(1, -5, -5, 5, Color.red));

        // Example room 2
        rooms.add(areaFactory.createRoom(2, 0, -5, 5, Color.BLUE));

        // Example room 3

        rooms.add(areaFactory.createRoom(3, -5, 0, 5, Color.BLUE));
        //rooms.add(new Room(3, new Point2D.Double(-5,0), new Rect(new Point2D.Double(0,0), new Point2D.Double(5,5))));

        // Example room 4
        rooms.add(areaFactory.createRoom(4, 0, 0, 5, 2, Color.BLUE));


        // Example room 5
        rooms.add(areaFactory.createRoom(4, 0, 3, 5, 2, Color.BLUE));

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
        long tStart = System.currentTimeMillis();
        while (true) {
            // ToDo all the server stuff
            environment.updateAreas();

            long tEnd = System.currentTimeMillis();
            long tDelta = tEnd - tStart;
            if (tDelta >= PROCEDURE_WAIT_TIME) {
                if (changeProcedure())
                    runProcedure();
                tStart = System.currentTimeMillis();
            }
        }
    }

    private void runProcedure() {
        switch (procedure) {
            case A:
                procedureA();
                break;
            case B:
                procedureB();
                break;
        }
    }

    private boolean changeProcedure() {
        switch (procedure) {
            case A:
                return setProcedureB();
            case B:
                return setProcedureA();
        }
        return setProcedureA() || setProcedureB();
    }


    private boolean setProcedureA() {
        if (environment.isActorInPhysical()) {
            procedure = Procedure.A;
            return true;
        }
        return false;
    }

    private boolean setProcedureB() {
        if (environment.isActorInLogical()) {
            procedure = Procedure.B;
            return true;
        }
        return false;
    }

    private void procedureA() {
        for (IRobot robot : Networking.getInstance().getRobots().values())
            robot.giveRewardPoints(environment.physicalReward(robot.getPosition()));
    }

    private void procedureB() {
        for (IRobot robot : Networking.getInstance().getRobots().values())
            robot.giveRewardPoints(environment.logicalReward(robot.getPosition()));
    }


}
