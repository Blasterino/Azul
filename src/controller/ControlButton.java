package controller;

import model.Model;
import model.Tuile;
import view.View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlButton extends JFrame implements ActionListener {
    View view;
    Model model;



    public ControlButton(View v, Model m) {
        this.view = v;
        this.model = m;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            // contenu de ce que doit faire le bouton lorsque l'on clique dessus

            for (JButton tuilebutton : view.tuile) {
                if (e.getSource() == tuilebutton) {
                    System.out.println(tuilebutton);
                }

            }
        }

    }
}
