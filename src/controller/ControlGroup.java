package controller;

import model.Model;
//import view.View;
import view.Vue;

public class ControlGroup {

    private Model model;
    private Vue vue;

    private ControlButton controlButton;
    private ControlMenu controlMenu;

    // Controle des différents controller pour respecter le mvc
    public ControlGroup(Model model) {
        this.model = model;

        vue = new Vue(model);

        controlMenu = new ControlMenu(model, vue);
        //controlButton = new ControlButton(model, vue);

        vue.display();
    }
}

