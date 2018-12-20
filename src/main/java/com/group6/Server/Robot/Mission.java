package com.group6.Server.Robot;

import java.awt.geom.Point2D;
import java.util.*;

public class Mission implements IMission{


    private List<Point2D> missionPoints;

    private static ArrayList<Integer> pred;
    private static ArrayList<Double> dist;

    public Mission() {
        this.missionPoints = new ArrayList<Point2D>();
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

    public void chooseStrategy(int i) {
        Strategy strategy = new Strategy(this.missionPoints);
        switch (i) {
            case 1: this.missionPoints = strategy.nearestPath();
            break;
        }
    }


}
