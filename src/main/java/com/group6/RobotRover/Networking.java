package com.group6.RobotRover;

public class Networking {

    private static final Networking SINGLETON = new Networking();

    private com.group6.Server.Networking server; // Pretend network connection

    private Networking() {
        server = com.group6.Server.Networking.getInstance();
    }

    public static Networking getInstance() {
        return SINGLETON;
    }

    public boolean connect(Planner planner) {
        return server.request(com.group6.Server.Networking.Instruction.CONNECT, planner);
    }

    public boolean update(Planner planner) {
        return server.request(com.group6.Server.Networking.Instruction.UPDATE, planner);
    }

    public boolean finishMission(Planner planner) {
        return server.request(com.group6.Server.Networking.Instruction.FINISH_MISSION, planner);
    }

    public boolean fault(Planner planner) {
        return server.request(com.group6.Server.Networking.Instruction.FAULT, planner);
    }
    
}
