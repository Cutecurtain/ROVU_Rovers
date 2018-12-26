package com.group6.Server.Robot;

import com.group6.Server.Environment.IActor;

public interface IRobot extends IActor {

    void setMission(IMission mission);

    void setAvailable(boolean available);

    boolean isAvailable();

    void update(double x, double y);

    void giveRewardPoints(int reward);

    int getRewardPoints();

}
