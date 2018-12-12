package com.group6.RobotRover;

import com.group6.PretendSocket;

public class Networking extends PretendSocket {

    private static final Networking SINGLETON = new Networking();

    private com.group6.Server.Networking server; // Pretend network connection

    private Networking() {
        server = com.group6.Server.Networking.getInstance();
    }

    public static Networking getInstance() {
        return SINGLETON;
    }

    public boolean connect(Planner planner) {
        return server.request(CONNECT, planner);
    }

    public boolean update(Planner planner) {
        return server.request(UPDATE, planner);
    }

    public boolean finishMission(Planner planner) {
        return server.request(FINNISH_MISSION, planner);
    }

    public boolean fault(Planner planner) {
        return server.request(FAULT, planner);
    }
    
}
