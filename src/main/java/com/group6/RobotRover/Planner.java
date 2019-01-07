package com.group6.RobotRover;

import project.AbstractRobotSimulator;
import project.Point;

import javax.vecmath.Vector3d;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Planner extends AbstractRobotSimulator {

    private static final long DEFAULT_SLEEP_TIME = 2000;

    private List<Point> missionPoints;

    //private Iterator<Point> missionIterator;

    private Stack<Point> missionStack;

    private Point currentGoal;

    private ProximitySensorFactory proximitySensorFactory;

    private Point tempGoal;

    private boolean available;

    private boolean sleeping;

    private boolean halted;

    private boolean stopped;

    private long sleepTime;


    public Planner(Point position, String name) {
        super(position, name);
        super.setDestination(super.getPosition());
        this.missionPoints = new ArrayList<Point>();
        //this.missionIterator = this.missionPoints.iterator();
        this.missionStack = new Stack<Point>();

        this.currentGoal = position;
        this.available = false;
        this.sleeping = false;
        this.halted = false;
        this.stopped = false;
        this.sleepTime = DEFAULT_SLEEP_TIME;
        this.proximitySensorFactory = new ProximitySensorFactory();

    }

    public boolean addMissionPoint(List<Point2D> missionPoints) {
        /*if (!available)
            return false;*/
        this.missionPoints = new ArrayList<Point>();
        for (int i = 0; i < missionPoints.size(); i++)
            this.missionPoints.add(new Point(missionPoints.get(i).getX(), missionPoints.get(i).getY()));
        for (int i = missionPoints.size() - 1; i > -1; i--) {
            this.missionStack.push(this.missionPoints.get(i));
        }
        //this.missionIterator = this.missionPoints.iterator();
        return true;
    }

    public void followPath() {
        if (this.isAtPosition(currentGoal)) {
            if (missionStack.size() > 0)
                currentGoal = missionStack.pop();
            super.setDestination(currentGoal);
        }
        available = missionStack.size() > 2;
        if (super.checkObstacle()) {
            proximitySensorFactory.getSensor("laser");
        }
    }

    void sleep() throws InterruptedException {
        super.setDestination(super.getPosition());
        Thread.sleep(sleepTime);
        super.setDestination(currentGoal);
        sleeping = false;
    }

    public boolean emergencyStop() {
        available = false;
        super.setDestination(super.getPosition());
        stopped = true;
        return true;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public boolean isHalted() {
        return halted;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void haltRover() {
        super.setDestination(super.getPosition());
        this.halted = true;
    }

    public void waitRover() {
        this.sleeping = true;
    }

    public void startRover() {
        super.setDestination(currentGoal);
        this.halted = false;
    }

    // In order to increase the margin
    @Override
    public boolean isAtPosition(Point dest) {
        if (dest == null)
            throw new NullPointerException("The destination cannot be null");
        Vector3d position = this.getAgent().getPosition();
        return Math.abs(dest.getZ() - position.z) <= 0.2 && Math.abs(dest.getX() - position.x) <= 0.2;

    }

    @Override
    public String toString() {
        return "Robot " + super.getName();
    }


}
