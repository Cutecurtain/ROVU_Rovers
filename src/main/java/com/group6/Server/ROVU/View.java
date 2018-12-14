package com.group6.Server.ROVU;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private JTextField robotNb = new JTextField(10);
    private JPanel jPanel = new JPanel();
    private List<ActionListener> actionListeners = new ArrayList<ActionListener>();

    public void update(Observable observable, Object o) {

    }

    public View() {


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        jPanel.add(robotNb);
        this.add(jPanel);
    }

    public void setButtons(int number) {
        for(int i = 0; i < number; i++) {
            buttons.add(new JButton("Emergency Stop Robot " + i));
            jPanel.add(buttons.get(i));
        }
    }

    public void addButtonListener(ActionListener listener, int i) {
            buttons.get(i).addActionListener(listener);
    }

    public void setRobotNb(int number) {
        robotNb.setText(Integer.toString(number));
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setActionListeners(List<ActionListener> actionListeners) {
        this.actionListeners = actionListeners;
    }

}
