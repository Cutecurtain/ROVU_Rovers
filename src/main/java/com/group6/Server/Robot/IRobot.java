package com.group6.Server.Robot;

public interface IRobot {

    void setMission(IMission mission);

    double getX();
    double getY();

    void update(double x, double y);

    void enteredRoom();

}
