package com.group6.RobotRover;

import project.AbstractRobotSimulator;
import project.Point;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Planner extends AbstractRobotSimulator {

    private static final long DEFAULT_HALT_TIME = 2000;

    private List<Point> missionPoints;

    private Iterator<Point> missionIterator;

    private Point currentGoal;

    private boolean available;

    private boolean halted;

    private boolean stopped;

    private long haltTime;

    public Planner(Point position, String name) {
        super(position, name);
        super.setDestination(super.getPosition());
        this.missionPoints = new ArrayList<Point>();
        this.missionIterator = this.missionPoints.iterator();
        this.currentGoal = super.getPosition();
        this.available = false;
        this.halted = false;
        this.stopped = false;
        this.haltTime = DEFAULT_HALT_TIME;
    }

    public boolean addMissionPoint(List<Point2D> missionPoints) {
        if (!available)
            return false;
        this.missionPoints = new ArrayList<Point>();
        for (int i = 0; i < missionPoints.size(); i++)
            this.missionPoints.add(new Point(missionPoints.get(i).getX(), missionPoints.get(i).getY()));
        this.missionIterator = this.missionPoints.iterator();
        return true;
    }

    public void followPath() {
        if (super.isAtPosition(currentGoal) && missionIterator.hasNext())
            currentGoal = missionIterator.next();
        available = !missionIterator.hasNext();
        super.setDestination(currentGoal);
    }

    public void halt() throws InterruptedException {
        super.setDestination(super.getPosition());
        Thread.sleep(haltTime);
    }

    public boolean emergencyStop() throws InterruptedException {
        available = false;
        halt();
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
