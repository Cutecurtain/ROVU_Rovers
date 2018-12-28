package com.group6.Server.Robot;

import com.group6.Server.Environment.IEnvironment;

import java.awt.geom.Point2D;
import java.util.*;

public class Mission implements IMission{


    private List<Point2D> missionPoints;

    private static ArrayList<Integer> pred;
    private static ArrayList<Double> dist;

    public Mission() {
        this.missionPoints = new ArrayList<Point2D>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("< i: [x, y] >\n");
        int i = 0;
        for (Point2D point : missionPoints)
            sb.append(i++ + ": [" + point.getX() + ", " + point.getY() + "]\n");
        return sb.toString();
    }

    public Mission(List<Point2D> missionPoints) {
        this.missionPoints = missionPoints;
    }

    public List<Point2D> getMissionPoints() {
        return missionPoints;
    }

    public void addMissionPoint(Point2D point) {
        missionPoints.add(point);
    }

    public void chooseStrategy(int i, IEnvironment environment) {
        Strategy strategy = new Strategy(this.missionPoints);
        switch (i) {
            case 0: this.missionPoints = strategy.throughDoors(environment);
            break;
            case 1: this.missionPoints = strategy.nearestPath();
            break;
        }
    }


}
