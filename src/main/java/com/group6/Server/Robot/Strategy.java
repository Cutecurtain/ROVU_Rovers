package com.group6.Server.Robot;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Strategy {

    List<Point2D> missionPoints;

    Strategy(List<Point2D> missionPoints) {

        this.missionPoints = missionPoints;

    }

    void nearestPath(List<Point2D> points) {
        ArrayList<Point2D> pointsVisited = new ArrayList<Point2D>();
        missionPoints.set(0, points.get(0));
        pointsVisited.set(0, points.get(0));

        for(int i = 0; i < points.size(); i++) {

            double distance = 0;
            double newDistance;
            double x1 = points.get(i).getX();
            double y1 = points.get(i).getY();


            for(int l = 0; i < points.size(); i++) {
                for(int m = 0; m < pointsVisited.size(); m++) {
                    if(missionPoints.get(m) == points.get(l)) {
                        l++;
                        break;
                    }
                }

                double x2 = points.get(l).getX();
                double y2 = points.get(l).getY();
                newDistance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));

                if(distance > newDistance) {
                    distance = newDistance;
                    missionPoints.set(i, points.get(l));
                }
            }
        }

    }

    List<Point2D> getMissionPoints() {
        return missionPoints;
    }


}
