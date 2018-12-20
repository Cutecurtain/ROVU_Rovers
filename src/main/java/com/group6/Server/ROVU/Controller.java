package com.group6.Server.ROVU;

import com.group6.Server.Networking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private View view;
    private Model model;
    private Networking networking = Networking.getInstance();
    private List<ButtonListener> buttonListeners = new ArrayList<ButtonListener>();

    public Controller(View view, Model model) {

        this.view = view;
        this.model = model;
        model.setRobotNb(networking.getRobots().size());
        view.setRobotNb(model.getRobotNb());
        view.setButtons(model.getRobotNb());
        for(int i = 0; i < networking.getRobots().size(); i++) {
            buttonListeners.add(new ButtonListener());
            this.view.addButtonListener(buttonListeners.get(i), i);
        }
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            networking.getRobots().get(Character.toString(s.charAt((s.length() - 1))));
            networking.emergencyStop("0");
            emergencyStop();
        }

        public boolean emergencyStop() {
            model.removeRobot();
            view.setRobotNb(model.getRobotNb());
            return true;
        }

    }
}
