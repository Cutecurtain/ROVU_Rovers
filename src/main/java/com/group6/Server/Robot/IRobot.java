package com.group6.Server.Robot;

public interface IRobot {

    void setMission(IMission mission);

    double getX();
    double getY();

    void update(int x, int y);

    void enteredRoom();

}
