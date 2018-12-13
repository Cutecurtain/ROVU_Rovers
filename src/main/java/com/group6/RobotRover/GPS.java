package com.group6.RobotRover;

import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;

public class GPS implements Observer {
    public void update(Observable observable, Object o) {

    }

    public Point2D getPosition(){
            return new Point2D.Float(0,0);
        }
}
