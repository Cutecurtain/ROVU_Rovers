package com.group6.RobotRover;

import project.AbstractRobotSimulator;
import project.Point;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Planner extends AbstractRobotSimulator {

    private List<Point> missionPoints;

    private Iterator<Point> missionIterator;

    private Point currentGoal;

    private boolean available;

    public Planner(Point position, String name) {
        super(position, name);
        this.missionPoints = new ArrayList<Point>();
        this.missionIterator = this.missionPoints.iterator();
        this.currentGoal = super.getPosition();
        this.available = false;
    }

    public boolean addMissionPoint(Point2D... points) {
        if (!available)
            return false;
        Point[] newPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++)
            newPoints[i] = new Point(points[i].getX(), points[i].getY());
        Collections.addAll(this.missionPoints, newPoints);
        this.missionIterator = missionPoints.iterator();
        return true;
    }

    public void followPath() {
        if (super.isAtPosition(currentGoal) && missionIterator.hasNext())
            currentGoal = missionIterator.next();
        available = !missionIterator.hasNext();
        super.setDestination(currentGoal);
    }

    public void halt() {
        available = false;
        super.setDestination(super.getPosition());
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Robot " + super.getName();
    }

}
