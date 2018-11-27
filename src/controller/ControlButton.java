package controller;

import model.CouleurTuile;
import model.Model;
import model.Tuile;
//import view.View;
import view.Vue;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ControlButton implements ActionListener {

    /*View view;
    Model model;

    public ControlButton(View v, Model m) {
        this.view = v;
        this.model = m;
    }*/

    /*public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            // contenu de ce que doit faire le bouton lorsque l'on clique dessus
            if(e.getSource() == view.tuilePremierJoueur){
                System.out.println("Tuile premier joueur");
            }
            for (JButton tuilebutton : view.tuilesFabriques) {
                if (e.getSource() == tuilebutton) {
                    System.out.println(tuilebutton);
                }

            }
        }
    }*/

    private Model model;
    private Vue vue;

    public ControlButton(Model model, Vue vue){
        this.model = model;
        this.vue = vue;

        vue.setControlButton(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() instanceof JButton){
            for(int i = 0 ; i < vue.JButtonTuilesInFabriques.length ; i++){
                ArrayList<Tuile> tuilesRecup = new ArrayList<>(4);
                //Récupération de la tuile choisie
                for(int j = 0 ; j < 4 ; j++){
                    if(e.getSource() == vue.JButtonTuilesInFabriques[i][j]){
                        Tuile tuileChoisie = model.getFabriques()[i].getTuilesOnFabrique().get(j);
                        tuilesRecup.add(tuileChoisie);
                        model.getFabriques()[i].removeTuile(tuileChoisie);
                        vue.JPanelFabriques[i].setVisible(false);
                    }
                }

                //Récupération des tuiles de la même couleur et de celles restantes
                if(!tuilesRecup.isEmpty()){
                    ArrayList<Tuile> tuilesRestantes = new ArrayList<>();
                    for(int j = 0 ; j < 3 ; j++){
                        if(tuilesRecup.get(0).getCouleurTuile() == model.getFabriques()[i].getTuilesOnFabrique().get(j).getCouleurTuile()){
                            tuilesRecup.add(model.getFabriques()[i].getTuilesOnFabrique().get(j));
                        }
                        tuilesRestantes.add(model.getFabriques()[i].getTuilesOnFabrique().get(j));
                    }

                    for(Tuile tuile : tuilesRestantes){
                        JButton tuileRest = new JButton();
                        tuileRest.setIcon(new ImageIcon(tuile.getCouleurTuile().getImageTuile()));
                        vue.JPanelCentreTable.add(tuileRest);
                    }
                }

                //Ajout dans la main des tuiles
                int compteur = 0;
                for(Tuile tuile : tuilesRecup){
                    model.getListJoueurs().get(0).addTuileInMain(tuile);
                    vue.JButtonMainJoueur[compteur].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                    vue.JButtonMainJoueur[compteur].setText("");
                    compteur++;
                }

                model.getFabriques()[i].clearTuilesOnFabrique();
                for(int j = 0 ; j < 4 ; j++){
                    vue.JButtonTuilesInFabriques[i][j].setEnabled(false);
                }

            }

            for(int i = 0 ; i < vue.JButtonMainJoueur.length ; i++){
                if(e.getSource() == vue.JButtonMainJoueur[i]){
                    System.out.println("Tuiles main joueur");
                }
            }

            if(e.getSource() == vue.JButtonMarqueurPremier){
                System.out.println("Marqueur 1er");
                model.getListJoueurs().get(0).addPenalite(new Tuile(CouleurTuile.PREMIERJOUEUR));
                model.getListJoueurs().get(0).calculPenalite();
                System.out.println(model.getListJoueurs().get(0).getTaillePenalite());
                for(int i = 1 ; i <= model.getNombreJoueur() ; i++){
                    vue.JLabelPenalites[i-1].setText("Pénalités du joueur " + i + " : " + model.getListJoueurs().get(i-1).getTaillePenalite());
                }
                vue.JPanelMarqueurPremier.setVisible(false);
                vue.JPanelMainJoueur.add(new JLabel("Marqueur 1er joueur"));
            }
        }
    }
}
