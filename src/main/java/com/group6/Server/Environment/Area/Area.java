package com.group6.Server.Environment.Area;

public class Area implements IArea{

    private IShape shape;
    private boolean physical;

    @Override
    public boolean isPosIn() {
        return false;
    }

    @Override
    public boolean isPhysical() {
        return false;
    }

    @Override
    public int getRewardPoints() {
        return 0;
    }


}
