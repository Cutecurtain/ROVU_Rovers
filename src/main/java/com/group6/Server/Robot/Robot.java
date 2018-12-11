package com.group6.Server.Robot;

import project.Point;

public class Robot implements IRobot{

    private String id;

    public Robot(Point position, String id) {
        this.id = id;
    }

    public void setMission(IMission mission) {

    }

    public double getX() {
        return 0;
    }

    public double getY() {
        return 0;
    }

    public void update(int x, int y) {

    }

    public void enteredRoom() {

    }

    private boolean sendMission() {
        return false;
    }

    private void update(double x, double y) {

    }

}
