package view;

import model.Fabrique;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Vue extends JFrame{
    protected Model model;

    protected JPanel JPanelBase;
    protected JPanel JPanelScore;
    public JPanel JPanelMilieuTable;
    public JPanel JPanelMainJoueur;
    protected JPanel JPanelJeuJoueur;
    protected JPanel JPanelLigneMotif;
    protected JPanel JPanelMur;
    protected JPanel JPanelPenalites;
    protected JLabel JLabelMur;
    private JLabel JLabelLigneMotif;

    public JLabel[] JLabelScores;

    public JPanel[] JPanelFabriques;
    public JButton[][] JButtonTuilesInFabriques;
    public JPanel JPanelMarqueurPremier;
    public JButton JButtonMarqueurPremier;
    public JPanel JPanelCentreTable;

    public JButton[] JButtonMainJoueur;

    public JLabel[] JLabelPenalites;

    public Vue(Model model){
        this.model = model;

        initAttribut();
        setContentPane(JPanelBase);
        addWidgets();

        setSize(1280, 720);
        setTitle("Azul");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initAttribut(){
        //SCORES
        JLabelScores = new JLabel[model.getNombreJoueur()];
        for(int i = 1 ; i <= model.getNombreJoueur() ; i++){
            JLabelScores[i-1] = new JLabel("Score du joueur " + i + " : " + model.getListJoueurs().get(i-1).getPointDuJoueur());
        }

        //MILIEU TABLE
        //Créations des fabriques
        JPanelFabriques = new JPanel[model.getFabriques().length];
        for(int i=0; i < model.getNombreFabriqueGame(); i++)
            JPanelFabriques[i] = new JPanel();

        //Créations des tuiles par fabrique
        JButtonTuilesInFabriques = new JButton[model.getFabriques().length][4];
        for(int i = 0 ; i < JPanelFabriques.length ; i++){
            Fabrique fabrique = model.getFabriques()[i];
            for(int j = 0 ; j < 4 ; j++){
                JButtonTuilesInFabriques[i][j] = new JButton();
                JButtonTuilesInFabriques[i][j].setIcon(new ImageIcon("Resources/" + fabrique.getTuilesOnFabrique().get(j).getCouleurTuile().getImageTuile()));
                JButtonTuilesInFabriques[i][j].setPreferredSize(new Dimension(40, 40));
            }
        }

        //Centre de table
        JPanelCentreTable = new JPanel();
        //Marqueur 1er joueur
        JPanelMarqueurPremier = new JPanel();
        JButtonMarqueurPremier = new JButton("Marqueur 1er joueur");
        //JButtonMarqueurPremier.setIcon(new ImageIcon("Resources/" + model.getTuilePremierJoueur().getCouleurTuile().getImageTuile()));
        //JButtonMarqueurPremier.setPreferredSize(new Dimension(40,40));

        //MAIN DU JOUEUR
        JButtonMainJoueur = new JButton[4];
        for(int i = 1 ; i <= 4 ; i++){
            JButtonMainJoueur[i-1] = new JButton("Tuile " + i);
        }

        //LIGNE MOTIF
        //MUR

        //PENALITES
        JLabelPenalites = new JLabel[model.getNombreJoueur()];
        for(int i = 1 ; i <= model.getNombreJoueur() ; i++){
            JLabelPenalites[i-1] = new JLabel("Pénalités du joueur " + i + " : " + model.getListJoueurs().get(i-1).getTaillePenalite());
        }
    }

    private void addWidgets(){
        //SCORE
        JPanelScore.setLayout(new GridLayout(1, model.getNombreJoueur(), 10, 10));
        for(int i = 0 ; i < model.getNombreJoueur() ; i++){
            JPanelScore.add(JLabelScores[i]);
        }


        //MILIEU TABLE
        //Ajout des tuiles dans les fabriques
        int tampon=0;
        JPanelMilieuTable.setLayout(new GridLayout(3,3,10,10));
        for(int i = 0 ; i < model.getNombreFabriqueGame() ; i++){
            for(int j = 0 ; j < 4 ; j++){
                JPanelFabriques[i].add(JButtonTuilesInFabriques[i][j]);
                tampon++;
            }
        }
        //Ajout des fabriques au centre de la table
        for(int i = 0 ; i < model.getNombreFabriqueGame() ; i++){
            JPanelMilieuTable.add(JPanelFabriques[i]);
        }
        //Centre de table
        //Ajout du marqueur 1er
        JPanelMarqueurPremier.add(JButtonMarqueurPremier);
        JPanelCentreTable.add(new JLabel("Centre de table :"));
        JPanelCentreTable.add(JPanelMarqueurPremier);
        JPanelMilieuTable.add(JPanelCentreTable);


        //MAIN DU JOUEUR
        JPanelMainJoueur.setLayout(new GridLayout(1, 4, 10, 10));
        for(int i = 0 ; i < 4 ; i++){
            JPanelMainJoueur.add(JButtonMainJoueur[i]);
        }

        //LIGNE MOTIFS
        //MUR

        //PENALITES
        JPanelPenalites.setLayout(new GridLayout(1, model.getNombreJoueur(), 10, 10));
        for(int i = 0 ; i < model.getNombreJoueur(); i++){
            JPanelPenalites.add(JLabelPenalites[i]);
        }
    }

    public void display(){
        setVisible(true);
    }

    public void setControlButton(ActionListener cb){
        //CONTROL DES TUILES DU CENTRE DE LA TABLE
        int tampon = 0;
        for(int i = 0 ; i < JPanelFabriques.length ; i++){
            for(int j = 0 ; j < 4 ; j++){
                JButtonTuilesInFabriques[i][j].addActionListener(cb);
                tampon++;
            }
        }

        //CONTROL DU MARQUEUR 1er
        JButtonMarqueurPremier.addActionListener(cb);

        //CONTROL DES TUILES DE LA MAIN DU JOUEUR
        for(int i = 0 ; i < 4 ; i++){
            JButtonMainJoueur[i].addActionListener(cb);
        }
    }
}
