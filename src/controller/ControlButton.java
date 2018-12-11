package controller;

import model.CentreTable;
import model.CouleurTuile;
import model.Model;
import model.Tuile;
//import view.View;
import view.Vue;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
            vue.creatingGameFrame();
            vue.JPanelBase.updateUI();
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
                Tuile tuileChoisie = null;
                //Récupération de la tuile choisie
                for(int j = 0 ; j < 4 ; j++){
                    if(e.getSource() == vue.JButtonTuilesInFabriques[i][j]){
                        if(!model.getFabriques()[i].isFabriqueVide()) {
                            tuileChoisie = model.getFabriques()[i].getTuilesOnFabrique().get(j);
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
                    //On enlève tout ce qu'il y a dans l'affichage pour éviter les doublons
                    vue.JPanelCentreTable.removeAll();
                    //On remet le texte et le marqueur
                    vue.JPanelCentreTable.add(new JLabel("Centre de table : "));
                    vue.JPanelCentreTable.add(vue.JButtonMarqueurPremier);
                    for(int j = 0 ; j < model.getCentreTable().getTuilesOnFabrique().size() ; j++){
                        //condition pour éviter d'avoir le marqueur en doublon ou trop petit à cause du Dimension
                        if(model.getCentreTable().getTuilesOnFabrique().get(j).getCouleurTuile() != CouleurTuile.PREMIERJOUEUR){
                            JButton JButtonCentreTable = new JButton();
                            JButtonCentreTable.setIcon(new ImageIcon("Resources/" + model.getCentreTable().getTuilesOnFabrique().get(j).getCouleurTuile().getImageTuile()));
                            JButtonCentreTable.setPreferredSize(new Dimension(40, 40));
                            JButtonCentreTable.addActionListener(this);
                            vue.JPanelCentreTable.add(JButtonCentreTable);
                        }
                    }

                    //Désactivation des fabriques
                    for(int j = 0 ; j < 4 ; j++){
                        vue.JButtonTuilesInFabriques[i][j].setEnabled(false);
                    }
                }
            }

            //CENTRE TABLE VERS MAIN
            //Parcours des tuiles sur le centre de table
            for(int i = 0 ; i < model.getCentreTable().getTuilesOnFabrique().size() ; i++){
                Tuile tuileChoisie = null;
                //si l'action a été effectuée sur une tuile du centre de table (hors marqueur), on la récupère
                if(e.getSource() != vue.JButtonMarqueurPremier && e.getSource() == vue.JPanelCentreTable.getComponent(i+1)){
                    tuileChoisie = model.getCentreTable().getTuilesOnFabrique().get(i);
                }

                //si on a pu récupérer une tuile, on récupère toutes les autres de la même couleur
                if(tuileChoisie != null){
                    model.getCentreTable().prendreTuile(tuileChoisie ,model.getListJoueurs().get(0));
                    System.out.println(model.getCentreTable());
                    //Ajout dans la main des tuiles
                    for(int j = 0 ; j < model.getListJoueurs().get(0).getMainActuelle().size() ; j++){
                        JLabel JLabelMainJoueur = new JLabel();
                        JLabelMainJoueur.setIcon(new ImageIcon("Resources/" + model.getListJoueurs().get(0).getMainActuelle().get(j).getCouleurTuile().getImageTuile()));
                        JLabelMainJoueur.setPreferredSize(new Dimension(40, 40));
                        vue.JPanelMainJoueur.add(JLabelMainJoueur);
                    }
                    model.setJoueurAvecTuileEnMain(true);
                }
            }

            //Redisposition du centre de la table
            //On enlève tout ce qu'il y a dans l'affichage pour éviter les doublons
            vue.JPanelCentreTable.removeAll();
            //On remet le texte et le marqueur
            vue.JPanelCentreTable.add(new JLabel("Centre de table : "));
            vue.JPanelCentreTable.add(vue.JButtonMarqueurPremier);
            for(int j = 0 ; j < model.getCentreTable().getTuilesOnFabrique().size() ; j++){
                //condition pour éviter d'avoir le marqueur en doublon ou trop petit à cause du Dimension
                if(model.getCentreTable().getTuilesOnFabrique().get(j).getCouleurTuile() != CouleurTuile.PREMIERJOUEUR){
                    JButton JButtonCentreTable = new JButton();
                    JButtonCentreTable.setIcon(new ImageIcon("Resources/" + model.getCentreTable().getTuilesOnFabrique().get(j).getCouleurTuile().getImageTuile()));
                    JButtonCentreTable.setPreferredSize(new Dimension(40, 40));
                    JButtonCentreTable.addActionListener(this);
                    vue.JPanelCentreTable.add(JButtonCentreTable);
                }
            }

            // MAIN VERS LIGNE MOTIF
            for(int i=0; i< 5 ; i++){
                for(int j=0; j< i+1; j++){
                    if(e.getSource() == vue.JButtonTuilesLigneMotif[i][j]){
                        if(model.isJoueurAvecTuileEnMain()){
                            int tampon = 0;
                            for(Tuile tuile : model.getListJoueurs().get(0).getMainActuelle()){
                                // Si la ligne est vide, on pose les tuiles sans reflechir
                                if(model.getListJoueurs().get(0).isEmplacementLigneSpecifiqueVide(i,tampon) && model.isJoueurAvecTuileEnMain()){
                                    //si on prend la tuile du premier joueur, on prend penalité
                                    if(tuile.equals(model.getTuilePremierJoueur())){

                                        model.getListJoueurs().get(0).addPenalite(model.getTuilePremierJoueur());
                                        vue.JLabelPlancher[model.getListJoueurs().get(0).getPlancher().getTaille()].setText("Marqueur du premier joueur");
                                    } else {

                                      //Si on dépasse la taille max de la ligne de motif : on doit en mettre dans le plancher
                                      if(model.getListJoueurs().get(0).ligneDeMotif[i].getTaille() == model.getListJoueurs().get(0).ligneDeMotif[i].getListeTuiles().size()){
                                          model.getListJoueurs().get(0).getPlancher().addTuile(tuile);
                                          vue.JLabelPenalites[model.getListJoueurs().get(0).getPlancher().getListeTuiles().size()-1].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                                          vue.JLabelPenalites[model.getListJoueurs().get(0).getPlancher().getListeTuiles().size()-1].setPreferredSize(new Dimension(40, 40));

                                      }else{ // sinon on les ajoute normalement dans la ligne de motif
                                          vue.JButtonTuilesLigneMotif[i][tampon].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                                          vue.JButtonTuilesLigneMotif[i][tampon].setText("");
                                          model.getListJoueurs().get(0).ligneDeMotif[i].getListeTuiles().add(tuile);
                                      }
                                    }

                                    tampon++;
                                }

                                // Si des tuiles sont déjà présentes et possédent la même couleurs
                                else if(model.getListJoueurs().get(0).ligneDeMotif[i].getListeTuiles().get(tampon).getCouleurTuile() == tuile.getCouleurTuile()) {
                                   // System.out.println("Vous ne pouvez pas");
                                    //"Même couleur, on ajoute"
                                        tampon = model.getListJoueurs().get(0).ligneDeMotif[i].getListeTuiles().size();
                                        vue.JButtonTuilesLigneMotif[i][tampon].setIcon(new ImageIcon("Resources/" + tuile.getCouleurTuile().getImageTuile()));
                                        vue.JButtonTuilesLigneMotif[i][tampon].setText("");
                                        model.getListJoueurs().get(0).ligneDeMotif[i].getListeTuiles().add(tuile);

                                    tampon++;
                                }else{
                                    System.out.println("Vous ne pouvez pas : Pas les mêmes couleurs ");
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
        vue.JPanelBase.updateUI();
        vue.JPanelRules.updateUI();

    }
}