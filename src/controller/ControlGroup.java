package controller;

import model.Model;
import view.View;

public class ControlGroup {

    // Controle des différents controller pour respecter le mvc
    public ControlGroup() {
        // par défaut j'ai mis 3 joueurs pour ne pas s'embêter
        Model m = new Model(3);
        View v = new View(m);
        ControlButton cb = new ControlButton(v, m);

        v.display();
    }
}

