package com.group6.Server.Robot;

import com.group6.RobotRover.Lights;
import com.group6.Server.Networking;

import java.awt.geom.Point2D;

public class Robot implements IRobot{

    private String id;
    private double x;
    private double y;
    private Point2D position;
    private Lights lights;

    private boolean available;

    private int rewardPoints;

    public Robot(Point2D position, String id) {
        this.position = position;
        this.id = id;
        this.available = true;
        this.lights = new Lights();
        this.rewardPoints = 0;
    }

    public void setMission(IMission mission) {
        Networking.getInstance().giveMission(id, mission);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void update(double x, double y) {
        position.setLocation(x, y);
    }

    public void sleep() {
        Networking.getInstance().wait(id);
    }

    public void halt() {
        Networking.getInstance().halt(id);
    }

    public void start() {
        Networking.getInstance().start(id);
    }

    public void giveRewardPoints(int reward) {
        rewardPoints += reward;
    }

    public int getRewardPoints() {
        return rewardPoints;
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
