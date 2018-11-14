package controller;

import model.Model;
import view.View;

public class ControlGroup {

    // Controle des différents controller pour respecter le mvc
    public ControlGroup() {
        Model m = new Model();
        View v = new View(m);
        ControlButton cb = new ControlButton(v, m);

        v.display();
    }
}

