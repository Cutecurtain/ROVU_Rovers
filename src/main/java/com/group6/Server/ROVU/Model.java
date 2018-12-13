package com.group6.Server.ROVU;

import java.util.Observable;

public class Model extends Observable {

    private int robotNb;
    private Position position;

    public void removeRobot() {
        robotNb--;
    }

    class Position {
        double x;
        double y;

        public Position(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public void setRobotNb(int size) {
        this.robotNb = size;
    }

    public int getRobotNb() {
        return robotNb;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
