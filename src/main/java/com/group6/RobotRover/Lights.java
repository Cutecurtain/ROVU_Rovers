package com.group6.RobotRover;

public class Lights {

    private boolean lightsOn;
    private boolean hazardLightsOn;

    public Lights() {
        this.lightsOn = false;
        this.hazardLightsOn = false;
    }

    public boolean turnOnLights() {

        return(lightsOn = true);
    }

    public boolean turnOnHazardLights() {
        return(hazardLightsOn = true);
    }

    public boolean isLightsOn() {
        return lightsOn;
    }


    public boolean isHazardLightsOn() {
        return hazardLightsOn;
    }
}
