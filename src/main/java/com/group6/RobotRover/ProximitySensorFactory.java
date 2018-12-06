package com.group6.RobotRover;

public class ProximitySensorFactory {

    public IProximitySensor getSensor(String sensorType) {

        if(sensorType == null) {
            return null;
        }

        if(sensorType.equals("laser")) {
            return new Laser();
        } else if(sensorType.equals("ultrasonic")) {
            return new UltraSonic();
        }

        return null;

    }


}
