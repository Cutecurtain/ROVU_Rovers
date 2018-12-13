package com.group6.RobotRover;

public class Movement {

    private double speed;

    private int x, y; //for direction

    public Movement(int dirX, int dirY, double speed, String feature) {

        this.x = dirX;
        this.y = dirY;
        this.speed = speed;

    }

    public void setDirection(int dirX, int dirY){
        x = dirX;
        y = dirY;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }






}
