package com.group6.RobotRover;

import project.AbstractRobotSimulator;
import project.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Planner extends AbstractRobotSimulator {

    private List<Point> missionPoints;

    public Planner(Point position, String name) {
        super(position, name);
        this.missionPoints = new ArrayList<Point>();
    }

    public void addMissionPoint(Point... points) {
        Collections.addAll(this.missionPoints, points);
    }

    public void followPath() {
        for (Point point : missionPoints) {
            super.setDestination(point);
            super.getAgent().performBehavior();
            while (!super.isAtPosition(point));
        }
    }

    @Override
    public String toString() {
        return "Robot " + super.getName();
    }

}
