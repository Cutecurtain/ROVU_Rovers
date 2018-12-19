package com.group6;

import com.group6.RobotRover.Main;
import com.group6.RobotRover.Planner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PretendSocket {

    protected static final char CONNECT = 0;
    protected static final char UPDATE = 1;
    protected static final char FINNISH_MISSION = 2;
    protected static final char FAULT = 3;

    private static final Socket SOCKET = new Socket();

    protected static void addSubscriber(Planner planner) {
        SOCKET.addSubscriber(planner);
    }

    protected static boolean hasSubscriber(String id) {
        return SOCKET.hasSubscriber(id);
    }

    protected static Planner getSubscriber(String id) {
        return SOCKET.getSubscriber(id);
    }

    static Set<Planner> getSubscribers() {
        Set<Planner> set = new HashSet<Planner>();
        set.addAll(SOCKET.getSubscribers().values());
        return set;
    }

    public static void loadRobots() {
        SOCKET.loadRobots();
    }

    private static class Socket {

        private Map<String, Planner> subscribers;

        Socket() {
            subscribers = new HashMap<String, Planner>(); // Just for pretending that we have a connection over the network
        }

        // Here are all the robots, that exist in the simulation, added
        private void loadRobots() {
            Main robot1 = new Main(-2.5,-2.5);
            Main robot2 = new Main(-2.5,2.5);
            Main robot3 = new Main(2.5,2.5);
            Main robot4 = new Main(2.5,-2.5);

            robot1.start();
            robot2.start();
            robot3.start();
            robot4.start();
        }

        void addSubscriber(Planner planner) {
            subscribers.put(planner.getName(), planner);
        }

        boolean hasSubscriber(String id) {
            return subscribers.containsKey(id);
        }

        Planner getSubscriber(String id) {
            return subscribers.get(id);
        }

        Map<String, Planner> getSubscribers() {
            return subscribers;
        }

    }

}
