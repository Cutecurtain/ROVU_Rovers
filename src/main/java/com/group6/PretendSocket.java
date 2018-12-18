package com.group6;

import com.group6.RobotRover.Main;
import com.group6.RobotRover.Planner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PretendSocket {

    protected static final char CONNECT = 0;
    protected static final char UPDATE = 1;
    protected static final char FINNISH_MISSION = 2;
    protected static final char FAULT = 3;

    private static Socket socket = new Socket();

    protected static void addSubscriber(Planner planner) {
        socket.addSubscriber(planner);
    }

    protected static boolean hasSubscriber(String id) {
        return socket.hasSubscriber(id);
    }

    protected static Planner getSubscriber(String id) {
        return socket.getSubscriber(id);
    }

    static Set<Planner> getSubscribers() {
        return new HashSet<Planner>(socket.getSubscribers().values());
    }

    private static class Socket {

        private Map<String, Planner> subscribers;

        Socket() {
            subscribers = new HashMap<String, Planner>(); // Just for pretending that we have a connection over the network
            loadRobots();
        }

        // Here are all the robots, that exist in the simulation, added
        private void loadRobots() {
            Main robot1 = new Main(1,3);
            Main robot2 = new Main(3,1);
            robot1.start();
            robot2.start();
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
