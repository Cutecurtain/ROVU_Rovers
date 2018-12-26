package com.group6;


import com.group6.Server.Networking;
import com.group6.Server.ROVU.Controller;
import com.group6.Server.ROVU.Model;
import com.group6.Server.ROVU.View;
import com.group6.Server.Robot.IMission;
import com.group6.Server.Robot.Mission;
import project.AbstractSimulatorMonitor;

import java.awt.geom.Point2D;

public class Main {

    public static void main (String[] args) {


        // Create s server instance
        com.group6.Server.Main server = new com.group6.Server.Main();

        // Start the rovers
        PretendSocket.loadRobots();

        // Start the server
        server.start();

        // Start the simulation
        AbstractSimulatorMonitor controller = new SimulatorMonitor(server.getEnvironment());

        View view = new View();
        Model model = new Model();
        Controller controller1 = new Controller(view, model);
        view.setVisible(true);

        IMission mission1 = new Mission();
        IMission mission2 = new Mission();
        IMission mission3 = new Mission();
        IMission mission4 = new Mission();
        IMission testStrat0 = new Mission();

        // Robot 1 mission
        mission1.addMissionPoint(new Point2D.Double( 2.5, 2.5));
        mission1.addMissionPoint(new Point2D.Double( 2.5, -2.5));
        mission1.chooseStrategy(1, server.getEnvironment());

        // Robot 2 mission
        mission2.addMissionPoint(new Point2D.Double( 2.5, -2.5));
        mission2.addMissionPoint(new Point2D.Double( 2.5, 2.5));
        mission2.addMissionPoint(new Point2D.Double( 2.5, -2.5));
        mission2.addMissionPoint(new Point2D.Double( 2.5, -2.4));
        mission2.addMissionPoint(new Point2D.Double( -2.5, -2.5));
        mission2.chooseStrategy(1, server.getEnvironment());

        // Robot 3 mission
        mission3.addMissionPoint(new Point2D.Double( 2.5, 2.5));
        mission3.addMissionPoint(new Point2D.Double( -3.9, 2.5));
        mission3.chooseStrategy(1, server.getEnvironment());

        // Robot 4 mission
        mission4.addMissionPoint(new Point2D.Double( 2.5, 2.5));
        mission4.addMissionPoint(new Point2D.Double( -1, 2.5));
        mission4.chooseStrategy(1, server.getEnvironment());

        testStrat0.addMissionPoint(new Point2D.Double(2.5, 2.5));
        testStrat0.addMissionPoint(new Point2D.Double(1, 2.5));
        testStrat0.chooseStrategy(0, server.getEnvironment());

        // id starts from ´0´´
        //Networking.getInstance().giveMission("0", mission1);
        //Networking.getInstance().giveMission("1", mission2);
        //Networking.getInstance().giveMission("2", mission3);
        //Networking.getInstance().giveMission("3", mission4);
        Networking.getInstance().giveMission("0", testStrat0);

    }

}
