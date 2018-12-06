package com.group6.RobotRover;

public class Laser implements IProximitySensor{

    private float distance;

    private float calculateDistance() {
        return 10;
    }

    public void setDistance() {
        distance = calculateDistance();
    }

    public float getDistance() {
        distance = calculateDistance();
        return distance;
    }

}
