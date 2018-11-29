package com.group6.Server.Environment;

import com.group6.Server.Environment.Area.IArea;

import java.awt.*;
import java.util.List;

public class Environment implements IEnvironment{

    private List<IArea> areas;

    public Environment() {}

    public Environment(List<IArea> areas) {
        this.areas = areas;
    }

    public void addArea(IArea area) {
        this.areas.add(area);
    }

    public int logicalReward(Point point) {
        return collectReward(point, false);
    }

    public int physicalReward(Point point) {
        return collectReward(point, true);
    }

    private int collectReward(Point point, boolean isPhysical) {
        int rewardPoints = 0;
        for (IArea area : areas) {
            if (area.isPhysical() == isPhysical && area.isPosIn(point))
                rewardPoints += area.getRewardPoints();
        }
        return rewardPoints;
    }
}
