package view;

import model.Model;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {
    Model model;
    JPanel fullView;

    public View(Model m) throws HeadlessException {
        //Mise en place de la vue avec toutes les méthodes
        this.model = m;
        initAttribut();
        addWidget();
        setSize(1280, 720);
        setTitle("Azul");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setControlButton(ActionListener cb) {
        //mise en place des différents controller de bouton
        // exemple         jbbutton.addActionListener(cb);

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