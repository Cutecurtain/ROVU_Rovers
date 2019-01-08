package com.group6.RobotRover;

import project.Point;

public class Main {

    private static final Networking NETWORKING = Networking.getInstance();

    private static int nextID = 0;

    private Planner planner;

    private double x, z;

    public Main(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public void start() {
        if (connect()) {
            Thread thread = new Thread(() -> {
                try {
                    main();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    private boolean connect() {
        planner = new Planner(new Point(x, z), Integer.toString(nextID++));
        if (!NETWORKING.connect(planner)) {
            nextID--;
            return false;
        }
        return true;
    }

    private void main() throws InterruptedException {
        while (!planner.isStopped()) {
            if (planner.isSleeping()) {
                planner.sleep();
            } else {
                if (!planner.isHalted())
                    planner.followPath();
                updateServer();
            }
        }
    }

    private void updateServer() {
        NETWORKING.update(planner);
        if (planner.isAvailable())
            NETWORKING.finishMission(planner);
    }

}
