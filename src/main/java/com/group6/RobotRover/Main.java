package com.group6.RobotRover;

public class Main {

    private static final Networking NETWORKING = Networking.getInstance();

    private static final long DEFAULT_HALT_TIME = 2000;

    private static int nextID = 0;

    private Planner planner;

    private boolean halted;
    private long haltTime;

    public Main() {
        halted = false;
    }

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
            if (halted) {
                planner.halt(haltTime);
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

    public void setHalted(boolean halted, long millis) {
        this.halted = halted;
        this.haltTime = millis;
    }

    public void setHalted(boolean halted) {
        setHalted(halted, DEFAULT_HALT_TIME);
    }

}
