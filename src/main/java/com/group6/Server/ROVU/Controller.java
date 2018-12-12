package com.group6.Server.ROVU;

import com.group6.Server.Networking;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    Networking networking = Networking.getInstance();

      public boolean emergencyStop() {

          return true;
    }

}
