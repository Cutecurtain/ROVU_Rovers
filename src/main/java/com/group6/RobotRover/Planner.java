package com.group6.RobotRover;

import project.AbstractRobotSimulator;
import project.Point;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Planner extends AbstractRobotSimulator {

    private List<Point> missionPoints;

    public Planner(Point position, String name) {
        super(position, name);
        this.missionPoints = new ArrayList<Point>();
    }

    public void addMissionPoint(Point2D... points) {
        Point[] newPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++)
            newPoints[i] = new Point(points[i].getX(), points[i].getY());
        Collections.addAll(this.missionPoints, newPoints);
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
