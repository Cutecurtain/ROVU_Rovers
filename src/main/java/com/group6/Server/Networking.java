package com.group6.Server;

import com.group6.RobotRover.Planner;
import com.group6.Server.Robot.IMission;
import com.group6.Server.Robot.IRobot;
import com.group6.Server.Robot.Robot;
import project.Point;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Networking {

    private static final Networking SINGLETON = new Networking();

    private Map<String, IRobot> robots;
    private Map<String, Planner> subscribers; // Just for pretending that we have a connection over the network

    private Networking() {
        robots = new HashMap<String, IRobot>();
        subscribers = new HashMap<String, Planner>();
    }

    public static Networking getInstance() {
        return SINGLETON;
    }

    public Map<String, IRobot> getRobots() {
        return robots;
    }

    private boolean connect(String id, double x, double y) {
        if (subscribers.containsKey(id))
            return false;
        IRobot newRobot = new Robot(new Point(x, y), id);
        robots.put(id, newRobot);
        return true;
    }

    private boolean update(String id, double x, double y) {
        if (!subscribers.containsKey(id))
            return false;
        IRobot robot = robots.get(id);
        robot.update(x, y);
        return true;
    }

    private boolean finnishMission(String id) {
        // TODO
        return false;
    }

    private boolean fault(String id) {
        // TODO
        return false;
    }

    public boolean request(Instruction instruction, Planner planner) {
        switch (instruction) {
            case CONNECT:
                String id = planner.getName();
                if (connect(id, planner.getPosition().getX(), planner.getPosition().getZ())) {
                    subscribers.put(id, planner);
                }
                return false;
            case UPDATE:
                return update(planner.getName(), planner.getPosition().getX(), planner.getPosition().getZ());
            case FINISH_MISSION:
                return finnishMission(planner.getName());
            case FAULT:
                return fault(planner.getName());
        }
        return false;
    }

    public boolean paus(String id, int seconds) {
        if (!subscribers.containsKey(id))
            return false;
        // TODO
        return true;
    }

    public boolean giveMission(String id, IMission mission) {
        if (!subscribers.containsKey(id))
            return false;
        Point2D[] missionPoints = (Point2D[]) mission.getMissionPoints().toArray();
        subscribers.get(id).addMissionPoint(missionPoints);
        return true;
    }

    public boolean sendInstruction(String id) {
        // TODO
        return false;
    }

    public enum Instruction {
        CONNECT,
        UPDATE,
        FINISH_MISSION,
        FAULT;
    }

}
