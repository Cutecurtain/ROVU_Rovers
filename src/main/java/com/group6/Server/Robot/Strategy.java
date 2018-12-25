package com.group6.Server.Robot;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

class Strategy {

    private List<Point2D> missionPoints;

    Strategy(List<Point2D> missionPoints) {
        this.missionPoints = missionPoints;
    }

    List<Point2D> nearestPath() {
        List<Point2D> orderedPoints = new ArrayList<Point2D>();
        List<Point2D> pointsVisited = new ArrayList<Point2D>();
        orderedPoints.add(0, missionPoints.get(0));
        pointsVisited.add(0, missionPoints.get(0));
        double distance = 100;


        if(missionPoints.size() == 1) {
            return this.missionPoints;
        }

        for(int i = 0; i < missionPoints.size(); i++) {
            double newDistance;
            double x1 = missionPoints.get(i).getX();
            double y1 = missionPoints.get(i).getY();
            int count = 0;

            for(int l = 0; l < pointsVisited.size(); l++) {
                if(visited(pointsVisited, missionPoints, l)) {
                    count++;
                }
            }

            for(int l = count; l < missionPoints.size(); l++) {
                double x2 = missionPoints.get(l).getX();
                double y2 = missionPoints.get(l).getY();
                newDistance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
                if(newDistance < distance && l > 2) {
                    distance = newDistance;
                    pointsVisited.add(missionPoints.get(l));
                    orderedPoints.add(l-1, missionPoints.get(l));
                } else {
                    pointsVisited.add(missionPoints.get(l));
                    orderedPoints.add(l, missionPoints.get(l));
                }
            }



        }
        return orderedPoints;
    }

    boolean visited(List<Point2D> pointsVisited, List<Point2D> missionPoint, int l) {
        for(int i = 0; i < missionPoint.size(); i++) {
            if(pointsVisited.get(l) == missionPoint.get(i)) {
                return true;
            }
        } return false;
    }
}



