package controller;

import model.Model;
//import view.View;
import view.Vue;

public class ControlGroup {

    private Model model;
    private Vue vue;

    private ControlButton controlButton;

    // Controle des différents controller pour respecter le mvc
    public ControlGroup(Model model) {
        // par défaut j'ai mis 3 joueurs pour ne pas s'embêter
        /*Model m = new Model(3);
        View v = new View(m);
        ControlButton cb = new ControlButton(v, m);
        v.display();*/
        this.model = model;

        vue = new Vue(model);

        controlButton = new ControlButton(model, vue);

        vue.display();
    }
}

