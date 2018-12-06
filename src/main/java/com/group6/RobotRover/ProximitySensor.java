package com.group6.RobotRover;

import java.util.Observable;

public class ProximitySensor extends Observable {

    private float distance;
    private String sensorType;
    IProximitySensor proximitySensor;

    public ProximitySensor(int sensorType) {

        switch (sensorType) {
            case 1: proximitySensor = new UltraSonic();
            break;
            case 2: proximitySensor = new Laser();
            break;
            default: System.out.print("invalid choice for proximity sensor");
            break;
        }
    }

    public ProximitySensor() {

    }


    public float getDistance() {

        return proximitySensor.getDistance();
    }


}
