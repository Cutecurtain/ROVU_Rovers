package com.group6.Server.ROVU;

import com.group6.Server.Networking;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private JTextField robotNb = new JTextField(10);


    public void update(Observable observable, Object o) {

    }

    public View() {

        JPanel jPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        jPanel.add(robotNb);
        Networking networking = Networking.getInstance();
        for(int i = 0; i < networking.getRobots().size(); i++) {
            buttons.add(new JButton("Robot" + i));
            jPanel.add(buttons.get(i));
        }


        this.add(jPanel);
    }

    public void addButtonListener(ActionListener listener) {
        for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(listener);
        }
    }

    public int getRobotNb() {
        return Integer.parseInt(robotNb.getText());
    }

    public void setRobotNb(int number) {
        robotNb.setText(Integer.toString(number));
    }


}
