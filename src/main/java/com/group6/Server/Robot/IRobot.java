package com.group6.Server.Robot;

import java.awt.geom.Point2D;

public interface IRobot {

    void setMission(IMission mission);

    Point2D getPosition();

    void setAvailable(boolean available);

    boolean isAvailable();

    void update(double x, double y);

    void enteredRoom();

    void giveRewardPoints(int reward);

    int getRewardPoints();

}
