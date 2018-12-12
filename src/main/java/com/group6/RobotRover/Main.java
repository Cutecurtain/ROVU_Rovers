package com.group6.RobotRover;

public class Main {

    private static final Networking NETWORKING = Networking.getInstance();

    private static int nextID = 0;

    private Planner planner;

    public Main() {}

    public void start() {
        if (connect()) {
            // TODO create new thread for the main loop method
        }
    }

    private boolean connect() {
        planner = new Planner(null, Integer.toString(nextID++));
        if (!NETWORKING.connect(planner)) {
            nextID--;
            return false;
        }
        return true;
    }

    private void main() throws InterruptedException {
        while (true) {
            if (planner.isHalted()) {
                planner.halt();
            } else {
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
