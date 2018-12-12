package com.group6.Server.ROVU;

import com.group6.Server.Networking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private View view;
    private Model model;
    private Networking networking = Networking.getInstance();

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        model.setRobotNb(networking.getRobots().size());
        view.setRobotNb(model.getRobotNb());

        this.view.addButtonListener(new ButtonListener());
    }



      public boolean emergencyStop() {

          return true;
    }

    class ButtonListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            model.removeRobot();
            view.setRobotNb(model.getRobotNb());
        }
    }

}
