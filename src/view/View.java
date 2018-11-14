package view;

import model.Model;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {
    Model model;

    public View(Model m) throws HeadlessException {
        this.model = m;
    }

    public void setControlButton(ActionListener cb) {
        //mise en place des différents controller de bouton
    }

    public void initAttribut() {
        // Initialisation des attributs
    }

    public void addWidget() {
        //ajout de "widget" de la vue

    }

    //Affichage de la vue
    public void display(){
        this.setVisible(true);
    }


}