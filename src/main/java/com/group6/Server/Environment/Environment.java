package com.group6.Server.Environment;

import com.group6.Server.Environment.Area.IArea;
import simbad.sim.EnvironmentDescription;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Environment extends EnvironmentDescription implements IEnvironment{

    private List<IArea> areas;

    public Environment() {
        super();
        this.areas = new ArrayList<IArea>();
    }

    public Environment(List<IArea> areas) {
        super();
        this.areas = areas;
    }

    public void addArea(IArea area) {
        this.areas.add(area);
    }

    public int logicalReward(Point2D point) {
        return collectReward(point, false);
    }

    public int physicalReward(Point2D point) {
        return collectReward(point, true);
    }

    private int collectReward(Point2D point, boolean isPhysical) {
        int rewardPoints = 0;
        for (IArea area : areas) {
            if (area.isPhysical() == isPhysical && area.isPosIn(point))
                rewardPoints += area.getRewardPoints();
        }
        return rewardPoints;
    }
}
