package com.group6.Server;

import com.group6.PretendSocket;
import com.group6.RobotRover.Planner;
import com.group6.Server.Robot.IMission;
import com.group6.Server.Robot.IRobot;
import com.group6.Server.Robot.Robot;
import project.Point;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Networking extends PretendSocket {

    private static final Networking SINGLETON = new Networking();
    private Robot robot1 = new Robot(new Point(1,2), "0");
    private Robot robot2 = new Robot(new Point(1,2), "1");
    private Map<String, IRobot> robots;
    private Map<String, Planner> subscribers; // Just for pretending that we have a connection over the network

    private Networking() {
        robots = new HashMap<String, IRobot>();
        robots.put(robot1.getId(), robot1);
        robots.put(robot2.getId(), robot2);
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
        if (!subscribers.containsKey(id))
            return false;
        robots.get(id).setAvailable(true);
        return true;
    }

    private boolean fault(String id) {
        // TODO
        return false;
    }

    public boolean request(char instruction, Planner planner) {
        switch (instruction) {
            case CONNECT:
                String id = planner.getName();
                if (connect(id, planner.getPosition().getX(), planner.getPosition().getZ())) {
                    subscribers.put(id, planner);
                }
                return false;
            case UPDATE:
                return update(planner.getName(), planner.getPosition().getX(), planner.getPosition().getZ());
            case FINNISH_MISSION:
                return finnishMission(planner.getName());
            case FAULT:
                return fault(planner.getName());
        }
        return false;
    }

    public boolean halt(String id, int seconds) {
        if (!subscribers.containsKey(id))
            return false;
        subscribers.get(id).haltRover();
        return true;
    }

    public boolean giveMission(String id, IMission mission) {
        if (!subscribers.containsKey(id) || !robots.get(id).isAvailable())
            return false;
        robots.get(id).setAvailable(false);
        Point2D[] missionPoints = (Point2D[]) mission.getMissionPoints().toArray();
        return subscribers.get(id).addMissionPoint(missionPoints);
    }

}
