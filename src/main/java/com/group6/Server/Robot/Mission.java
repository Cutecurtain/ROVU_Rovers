package com.group6.Server.Robot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mission implements IMission{

    private List<Point> missionPoints;

    public Mission() {
        this.missionPoints = new ArrayList<Point>();
    }

    public Mission(List<Point> missionPoints) {
        this.missionPoints = missionPoints;
    }

    public List<Point> getMissionPoints() {
        return null;
    }
}
