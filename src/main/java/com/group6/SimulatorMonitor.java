package com.group6;
import com.group6.RobotRover.Planner;
import project.AbstractSimulatorMonitor;
import simbad.sim.EnvironmentDescription;

import java.util.Set;


public class SimulatorMonitor extends AbstractSimulatorMonitor<Planner> {
    private SimulatorMonitor(Set<Planner> robots, EnvironmentDescription e) {
        super(robots, e);
    }

    public SimulatorMonitor(EnvironmentDescription e) {
        this(PretendSocket.getSubscribers(), e);
        PretendSocket.loadRobots();
    }

    @Override
    public void update(Planner arg0) {
        System.out.println(arg0.getName());
        System.out.println(arg0.getPosition());
    }
}