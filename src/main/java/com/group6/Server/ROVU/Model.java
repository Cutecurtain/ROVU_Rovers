package com.group6.Server.ROVU;

import java.util.Observable;

public class Model extends Observable {

    private int robotID;

    public void selectRobot(int robotID) {
        this.robotID = robotID;
    }

    public int getRobotID() {
        return robotID;
    }
}
