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
        if (e.getSource() == vue.JButtonJouerMenu){
            System.out.println("appui bouton jouer");
            vue.creatingGameFrame();
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

        if(e.getSource() instanceof JButton){
            for(int i = 0 ; i < vue.JButtonTuilesInFabriques.length ; i++){
                //ArrayList<Tuile> tuilesRecup = new ArrayList<>(4);
                Tuile tuileChoisie = null;
                //Récupération de la tuile choisie
                for(int j = 0 ; j < 4 ; j++){
                    if(e.getSource() == vue.JButtonTuilesInFabriques[i][j]){
                        if(!model.getFabriques()[i].isFabriqueVide()) {
                            tuileChoisie = model.getFabriques()[i].getTuilesOnFabrique().get(j);
                            //tuilesRecup.add(tuileChoisie);
                            model.getFabriques()[i].removeTuile(tuileChoisie);
                            vue.JPanelFabriques[i].setVisible(false);
                        }
                    }
                }

                //Prise des tuiles dans la main du joueur
                if(tuileChoisie != null){
                    model.getFabriques()[i].prendreTuile(tuileChoisie, model.getCentreTable(), model.getListJoueurs().get(0));
                    //Ajout dans la main des tuiles
                    for(int j = 0 ; j < model.getListJoueurs().get(0).getMainActuelle().size() ; j++){
                        //vue.JButtonMainJoueur[j].setIcon(new ImageIcon("Resources/" + model.getListJoueurs().get(0).getMainActuelle().get(i).getCouleurTuile().getImageTuile()));
                        //vue.JButtonMainJoueur[j].setText("");
                        JLabel JLabelMainJoueur = new JLabel();
                        JLabelMainJoueur.setIcon(new ImageIcon("Resources/" + model.getListJoueurs().get(0).getMainActuelle().get(j).getCouleurTuile().getImageTuile()));
                        JLabelMainJoueur.setPreferredSize(new Dimension(40, 40));
                        vue.JPanelMainJoueur.add(JLabelMainJoueur);

                    }
                    model.setJoueurAvecTuileEnMain(true);

                    //Disposition dans le centre de table
                    for(int j = 0 ; j < model.getCentreTable().getTuilesOnCentreTable().size() ; j++){
                        JButton JButtonCentreTable = new JButton();
                        JButtonCentreTable.setIcon(new ImageIcon("Resources/" + model.getCentreTable().getTuilesOnCentreTable().get(j)));
                        vue.JPanelCentreTable.add(JButtonCentreTable);
                    }

                    //Désactivation des fabriques
                    for(int j = 0 ; j < 4 ; j++){
                        vue.JButtonTuilesInFabriques[i][j].setEnabled(false);
                    }
                }


                /*if(!tuilesRecup.isEmpty()){
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
                }*/

                /*int compteur = 0;
                for(Tuile tuile : tuilesRecup){
                    model.getListJoueurs().get(0).addTuileInMain(tuile); // 0 Car on a actuellement que 1 joueurs
                    vue.JButtonMainJoueur[compteur].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                    vue.JButtonMainJoueur[compteur].setText("");
                    compteur++;
                }*/
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
                vue.JLabelPlancher[model.getListJoueurs().get(0).getPlancher().getTaille()].setText("Marqueur du premier joueur");
                vue.JPanelMarqueurPremier.setVisible(false);

            }


            // de la main vers la ligne de motif
            for(int i=0; i< 5 ; i++){
                for(int j=0; j< i+1; j++){
                    if(e.getSource() == vue.JButtonTuilesLigneMotif[i][j]){
                        if(model.isJoueurAvecTuileEnMain()){

                            int tampon = 0;
                            for(Tuile tuile : model.getListJoueurs().get(0).getMainActuelle()){

                                if(model.getListJoueurs().get(0).isEmplacementLigneSpecifiqueVide(i,tampon) && model.isJoueurAvecTuileEnMain()){

                                    if(tuile.equals(model.getTuilePremierJoueur())){

                                        model.getListJoueurs().get(0).addPenalite(model.getTuilePremierJoueur());
                                        vue.JLabelPlancher[model.getListJoueurs().get(0).getPlancher().getTaille()].setText("Marqueur du premier joueur");
                                    } else {

                                        if(tampon > i){
                                            vue.JLabelPlancher[model.getListJoueurs().get(0).getPlancher().getListeTuiles().size()].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                                            model.getListJoueurs().get(0).addPenalite(model.getListJoueurs().get(0).getMainActuelle().get(tampon));
                                        } else {

                                            vue.JButtonTuilesLigneMotif[i][tampon].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                                            vue.JButtonTuilesLigneMotif[i][tampon].setText("");

                                            model.getListJoueurs().get(0).ligneDeMotif[i].getListeTuiles().add(tuile);
                                        }

                                    }

                                    tampon++;
                                }
                                else {
                                    System.out.println("Vous ne pouvez pas");
                                }
                            }
                            model.setJoueurAvecTuileEnMain(false);
                            model.getListJoueurs().get(0).getMainActuelle().clear();

                            // on supprime l'affichage de la main

                            vue.JPanelMainJoueur.removeAll();


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