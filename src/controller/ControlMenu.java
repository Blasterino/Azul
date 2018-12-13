package controller;

import model.Model;
import view.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenu implements ActionListener {

    private Model model;
    private Vue vue;
    private ControlButton controlButton;

    public ControlMenu(Model model, Vue vue){
        this.model = model;
        this.vue = vue;

        vue.setControlMenu(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == vue.JButtonNbDeuxJoueurs){
            model.setJeuEnCours(true);
            model.setNombreJoueur(2);
            creerJeu();

        }
        if (e.getSource() == vue.JButtonNbTroisJoueurs){
            model.setJeuEnCours(true);
            model.setNombreJoueur(3);
            creerJeu();
        }
        if (e.getSource() == vue.JButtonNbQuatreJoueurs){
            model.setJeuEnCours(true);
            model.setNombreJoueur(4);
            creerJeu();
        }
        if (e.getSource() == vue.JButtonRegles){
            vue.creatingRulesFrame();
        }
        if (e.getSource() == vue.JButtonRetourMenu){
            vue.creatingMenuFrame();
        }
        if (e.getSource() == vue.JButtonQuitter){
            vue.dispose();
        }
    }

    public void creerJeu(){
        vue.updateVueNbJoueurs();
        controlButton = new ControlButton(model, vue);
        vue.creatingGameFrame();
        vue.JPanelBase.updateUI();
    }
}
