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
                Tuile tuileChoisie = new Tuile();
                //Récupération de la tuile choisie
                for(int j = 0 ; j < 4 ; j++){
                    if(e.getSource() == vue.JButtonTuilesInFabriques[i][j]){
                        if(!model.getFabriques()[i].isFabriqueVide()) {
                            tuileChoisie = model.getFabriques()[i].getTuilesOnFabrique().get(j);
                            tuilesRecup.add(tuileChoisie);
                            model.getFabriques()[i].removeTuile(tuileChoisie);
                            vue.JPanelFabriques[i].setVisible(false);
                        }
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
                    model.getFabriques()[i].clearTuilesOnFabrique();
                }

                //Ajout dans la main des tuiles
                int compteur = 0;
                for(Tuile tuile : tuilesRecup){
                    model.getListJoueurs().get(0).addTuileInMain(tuile); // 0 Car on a actuellement que 1 joueurs
                    vue.JButtonMainJoueur[compteur].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                    vue.JButtonMainJoueur[compteur].setText("");
                    compteur++;
                }

                for(int j = 0 ; j < 4 ; j++){
                    vue.JButtonTuilesInFabriques[i][j].setEnabled(false);
                }
                model.setJoueurAvecTuileEnMain(true);

            }


            for(int i = 0 ; i < vue.JButtonMainJoueur.length ; i++){
                if(e.getSource() == vue.JButtonMainJoueur[i]){
                    System.out.println("Tuiles main joueur");
                }
            }


            // partie tuile premier joueur
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


            // de la main vers la ligne de motif
            for(int i=0; i< 5 ; i++){
                for(int j=0; j< i+1; j++){
                    if(e.getSource() == vue.JButtonTuilesLigneMotif[i][j]){
                        if(model.isJoueurAvecTuileEnMain()){
                            int tampon = 0;
                            for(Tuile tuile : model.getListJoueurs().get(0).getMainActuelle()){

                                vue.JButtonTuilesLigneMotif[i][tampon].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                                vue.JButtonTuilesLigneMotif[i][tampon].setText("");


                                tampon++;


                                model.setJoueurAvecTuileEnMain(false);
                            }
                            model.getListJoueurs().get(0).getMainActuelle().clear();
                            for(int n=0; n<4; n++){
                                vue.JButtonMainJoueur[n].setIcon(null);
                                vue.JButtonMainJoueur[n].setText("Tuile "+ n);
                            }

                            for(int p = 0 ; p < model.getNombreFabriqueGame() ; p++){
                                for(int q = 0 ; q < 4 ; q++) {
                                    vue.JButtonTuilesInFabriques[p][q].setEnabled(true);
                                }
                            }

                        }
                    }
                }
            }




        }

    }
}