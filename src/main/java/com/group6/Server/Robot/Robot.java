package com.group6.Server.Robot;

import com.group6.RobotRover.Lights;
import com.group6.Server.Networking;
import project.Point;

public class Robot implements IRobot{

    private String id;
    private double x;
    private double y;
    private Point position;
    private Lights lights;

    private boolean available;

    public Robot(Point position, String id) {
        this.position = position;
        this.id = id;
        this.available = true;
        this.lights = new Lights();
    }

    public void setMission(IMission mission) {
        Networking.getInstance().giveMission(id, mission);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point getPosition() {
        return position;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void enteredRoom() {
        Networking.getInstance().halt(id);
    }

    private boolean sendMission(IMission mission) {
        return Networking.getInstance().giveMission(id, mission);
    }

    public String getId() {
        return id;
    }

    public void turnOnLights(int lightId) {

        switch(lightId) {
            case 1: lights.turnOnLights();
            case 2: lights.turnOnHazardLights();
            break;
        }

    }

}
