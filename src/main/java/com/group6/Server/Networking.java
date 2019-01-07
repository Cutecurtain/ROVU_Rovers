package com.group6.Server;

import com.group6.PretendSocket;
import com.group6.RobotRover.Planner;
import com.group6.Server.Robot.IMission;
import com.group6.Server.Robot.IRobot;
import com.group6.Server.Robot.Robot;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Networking extends PretendSocket {

    private static final Networking SINGLETON = new Networking();
    private Map<String, IRobot> robots;

    private Networking() {
        robots = new HashMap<String, IRobot>();
    }

    public static Networking getInstance() {
        return SINGLETON;
    }

    public Map<String, IRobot> getRobots() {
        return robots;
    }

    private boolean connect(Planner planner, double x, double y) {
        if (hasSubscriber(planner.getName()))
            return false;
        addSubscriber(planner);
        IRobot newRobot = new Robot(new Point2D.Double(x, y), planner.getName());
        robots.put(planner.getName(), newRobot);
        return true;
    }

    private boolean update(String id, double x, double y) {
        if (!hasSubscriber(id))
            return false;
        IRobot robot = robots.get(id);
        robot.update(x, y);
        return true;
    }

    private boolean finnishMission(String id) {
        if (!hasSubscriber(id))
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
                return connect(planner, planner.getPosition().getX(), planner.getPosition().getZ());
            case UPDATE:
                return update(planner.getName(), planner.getPosition().getX(), planner.getPosition().getZ());
            case FINNISH_MISSION:
                return finnishMission(planner.getName());
            case FAULT:
                return fault(planner.getName());
        }
        return false;
    }

    public boolean wait(String id) {
        if (!hasSubscriber(id))
            return false;
        getSubscriber(id).waitRover();
        return true;
    }

    public boolean halt(String id) {
        if (!hasSubscriber(id))
            return false;
        getSubscriber(id).haltRover();
        return true;
    }

    public boolean start(String id) {
        if (!hasSubscriber(id))
            return false;
        getSubscriber(id).startRover();
        return true;
    }

    public boolean giveMission(String id, IMission mission) {
        if (!hasSubscriber(id) || !robots.get(id).isAvailable())
            return false;
        robots.get(id).setAvailable(false);
        return getSubscriber(id).addMissionPoint(mission.getMissionPoints());
    }

    public boolean emergencyStop(String id)  {
        if (!hasSubscriber(id))
            return false;
        return getSubscriber(id).emergencyStop();
    }

}
