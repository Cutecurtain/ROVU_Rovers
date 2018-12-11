package com.group6.Server;

import com.group6.RobotRover.Planner;
import com.group6.Server.Robot.IRobot;
import com.group6.Server.Robot.Robot;
import project.Point;

import java.util.ArrayList;
import java.util.List;

public class Networking {

    private static final Networking SINGLETON = new Networking();

    private List<IRobot> robots;

    private Networking() {
        robots = new ArrayList<IRobot>();
    }

    public static Networking getInstance() {
        return SINGLETON;
    }

    private void connect(String id, double x, double y) {
        robots.add(new Robot(new Point(x, y), id));
    }

    private void update(String id, double x, double y) {

    }

    private void finnishMission(String id) {

    }

    private void fault(String id) {

    }

    public void sendInstruction(Instruction instruction, Planner planner) {
        switch (instruction) {
            case CONNECT:
                connect(planner.getName(), planner.getPosition().getX(), planner.getPosition().getZ());
                break;
            case UPDATE:
                update(planner.getName(), planner.getPosition().getX(), planner.getPosition().getZ());
                break;
            case FINISH_MISSION:
                finnishMission(planner.getName());
                break;
            case FAULT:
                fault(planner.getName());
                break;
        }
    }

    public enum Instruction {
        CONNECT,
        UPDATE,
        FINISH_MISSION,
        FAULT;
    }

}
