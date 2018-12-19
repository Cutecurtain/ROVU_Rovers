package com.group6.Server.Robot;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Strategy {

    private List<Point2D> missionPoints;


    Strategy(List<Point2D> missionPoints) {

        this.missionPoints = missionPoints;

    }

        List<Point2D> nearestPath() {
        missionPoints = new ArrayList<Point2D>();
        List<Point2D> orderedPoints = new ArrayList<Point2D>();
        List<Point2D> pointsVisited = new ArrayList<Point2D>();
        orderedPoints.add(0, missionPoints.get(0));
        pointsVisited.add(0, missionPoints.get(0));
        double distance = 100;

        if(missionPoints.size() == 1) {
            return missionPoints;
        }

        for(int i = 0; i < missionPoints.size(); i++) {

            double newDistance;
            double x1 = missionPoints.get(i).getX();
            double y1 = missionPoints.get(i).getY();


            for(int l = 0; l < missionPoints.size(); l++) {

                if(visited(pointsVisited, missionPoints, l) && l < missionPoints.size()) {
                    l++;
                }
                if(x1 != missionPoints.get(l).getX() && y1 != missionPoints.get(l).getY()) {
                    double x2 = missionPoints.get(l).getX();
                    double y2 = missionPoints.get(l).getY();
                    newDistance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));

                    if(newDistance < distance) {
                        distance = newDistance;
                        orderedPoints.add(i+1, missionPoints.get(l));
                        pointsVisited.add(i, missionPoints.get(l));
                    }
                }


            }
        }
        return orderedPoints;
    }

    private boolean visited(List<Point2D> pointsVisited, List<Point2D> missionPoints, int l) {
        for(int i = 0; i < pointsVisited.size(); i++) {
            if(pointsVisited.get(i) == missionPoints.get(l)) {
                return true;
            }
        } return false;
    }


}
