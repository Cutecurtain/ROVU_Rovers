package com.group6.RobotRover;

public class UltraSonic implements IProximitySensor{

    private float distance;

    private float calculateDistance() {
        return 10;
    }

    public void setDistance() {
        distance = calculateDistance();
    }

    public float getDistance() {
        setDistance();
        return distance;
    }


}
