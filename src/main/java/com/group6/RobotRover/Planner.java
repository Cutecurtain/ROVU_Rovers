package com.group6.RobotRover;

import project.AbstractRobotSimulator;
import project.Point;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Planner extends AbstractRobotSimulator {

    private static final long DEFAULT_HALT_TIME = 2000;

    private List<Point> missionPoints;

    //private Iterator<Point> missionIterator;

    private Stack<Point> missionStack;

    private Point currentGoal;

    private ProximitySensorFactory proximitySensorFactory;

    private Point tempGoal;

    private boolean available;

    private boolean halted;

    private boolean stopped;

    private long haltTime;


    public Planner(Point position, String name) {
        super(position, name);
        super.setDestination(super.getPosition());
        this.missionPoints = new ArrayList<Point>();
        for(int i = missionPoints.size(); i > 0; i++) {
            this.missionStack.push(this.missionPoints.get(i));
        }

        this.currentGoal = position;
        this.available = false;
        this.halted = false;
        this.stopped = false;
        this.haltTime = DEFAULT_HALT_TIME;
        this.proximitySensorFactory = new ProximitySensorFactory();
        this.proximitySensorFactory.getSensor("laser");
    }

    public boolean addMissionPoint(List<Point2D> missionPoints) {
        if (!available)
            return false;
        this.missionPoints = new ArrayList<Point>();
        for (int i = 0; i < missionPoints.size(); i++)
            this.missionPoints.add(new Point(missionPoints.get(i).getX(), missionPoints.get(i).getY()));
        for(int i = missionPoints.size(); i > 0; i++) {
            this.missionStack.push(this.missionPoints.get(i));
        }
        return true;
    }

    public void followPath() {
        if (super.isAtPosition(currentGoal) && !missionStack.empty())
            currentGoal = missionStack.pop();
        available = !missionStack.empty();
        super.setDestination(currentGoal);
        if(super.checkObstacle()) {

        }
    }

    void halt() throws InterruptedException {
        super.setDestination(super.getPosition());
        Thread.sleep(haltTime);
        halted = false;
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

    public boolean isHalted() {
        return halted;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void haltRover(long millis) {
        this.halted = true;
        this.haltTime = millis;
    }

    public void haltRover() {
        haltRover(DEFAULT_HALT_TIME);
    }

    @Override
    public String toString() {
        return "Robot " + super.getName();
    }



}
