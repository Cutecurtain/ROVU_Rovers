package com.group6;
import com.group6.RobotRover.Planner;
import com.group6.Server.Environment.IEnvironment;
import project.AbstractSimulatorMonitor;
import simbad.sim.EnvironmentDescription;

import java.util.Set;


public class SimulatorMonitor extends AbstractSimulatorMonitor<Planner> {
    private SimulatorMonitor(Set<Planner> robots, EnvironmentDescription e) {
        super(robots, e);
    }

    public SimulatorMonitor(IEnvironment environment) {
        this(PretendSocket.getSubscribers(), environment.getEnvironmentDescription());
    }

    @Override
    public void update(Planner arg0) {
        System.out.println(arg0.getName());
        System.out.println(arg0.getPosition());
    }
}