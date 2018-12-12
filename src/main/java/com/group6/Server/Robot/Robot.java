package com.group6.Server.Robot;

import project.Point;

public class Robot implements IRobot{

    private String id;
    private double x;
    private double y;

    public Robot(Point position, String id) {
        this.id = id;
    }

    public void setMission(IMission mission) {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setAvailable(boolean available) {
        
    }

    public boolean isAvailable() {
        return false;
    }

    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void enteredRoom() {

    }

    private boolean sendMission() {
        return false;
    }

    public String getId() {
        return id;
    }

}
