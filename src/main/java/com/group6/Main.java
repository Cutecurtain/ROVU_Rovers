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

        mission1.addMissionPoint(new Point2D.Double(-1,2.5));
        mission1.addMissionPoint(new Point2D.Double( -2.5, -2.5));
        mission1.addMissionPoint(new Point2D.Double( 2.5, -2.5));
        mission1.addMissionPoint(new Point2D.Double( 2.5, 2.5));
        mission1.chooseStrategy(1);

        mission2.addMissionPoint(new Point2D.Double(2.5,2.5));
        mission2.addMissionPoint(new Point2D.Double( -2.5, 2.5));



        Networking.getInstance().giveMission("0", mission1);
        //Networking.getInstance().giveMission("1", mission2);



    }

}
