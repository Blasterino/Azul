package view;

import model.Fabrique;
import model.Model;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Vue extends JFrame{
    protected Model model;

    public JPanel JPanelBase;
    protected JPanel JPanelScore;
    public JPanel JPanelMilieuTable;
    public JPanel JPanelMainJoueur;
    protected JPanel JPanelJeuJoueur;
    protected JPanel JPanelLigneMotif;
    protected JPanel JPanelMur;
    protected JPanel JPanelPenalites;
    protected JLabel JLabelMur;
    private JLabel JLabelLigneMotif;
    public JPanel JPanelPlancher;

    public JLabel[] JLabelScores,JLabelPlancher;

    public JPanel[] JPanelFabriques;
    public JButton[][] JButtonTuilesInFabriques,JButtonTuilesLigneMotif;
    public List<JButton> JButtonTuilesCentre;
    public JPanel JPanelMarqueurPremier;
    public JButton JButtonMarqueurPremier;
    public JPanel JPanelCentreTable;

    //public JButton[] JButtonMainJoueur;

    public JLabel[] JLabelPenalites;

    public JButton JButtonJouerMenu;
    public JButton JButtonRegles;
    public JButton JButtonQuitter;
    public JButton JButtonRetourMenu;
    public JLabel JLabelRegles;
    public JPanel JPanelMenu;
    public JPanel JPanelRules;
    public JFrame newGameFrame;
    public JFrame newRulesFrame;


    public Vue(Model model){
        this.model = model;
        initAttribut();
        addWidgets();
        creatingMenuFrame();
    }

    private void initAttribut(){
        //MENU
        JButtonJouerMenu = new JButton("Jouer");
        JButtonRegles = new JButton("Regles");
        JButtonQuitter = new JButton("Quitter");
        JButtonRetourMenu = new JButton("Retour au menu");
        JLabelRegles = new JLabel("ici les regles");
        JPanelMenu = new JPanel();
        JPanelRules = new JPanel();
        //je l'ai mis en commentaire car c'est pas la peine, pas besoin d'instancier c'est déjà fait dans le .form
        //JPanelPenalites = new JPanel();
        JButtonTuilesCentre = new ArrayList<>();
        //SCORES
        JLabelScores = new JLabel[model.getNombreJoueur()];
        for(int i = 1 ; i <= model.getNombreJoueur() ; i++){
            JLabelScores[i-1] = new JLabel("Score du joueur " + i + " : " + model.getListJoueurs().get(i-1).getPointDuJoueur());
        }

        //MILIEU TABLE
        //Créations des fabriques
        JPanelFabriques = new JPanel[model.getFabriques().length];
        for(int i=0; i < model.getNombreFabriqueGame()+1; i++)
            JPanelFabriques[i] = new JPanel();

        //Créations des tuiles par fabrique
        JButtonTuilesInFabriques = new JButton[model.getFabriques().length][4];
        for(int i = 0 ; i < JPanelFabriques.length ; i++){
           System.out.println(String.valueOf(i)+" fois");
            for(int j = 0 ; j < model.getFabriques()[i].getTuilesOnFabrique().size() ; j++){

                JButtonTuilesInFabriques[i][j] = new JButton();
                JButtonTuilesInFabriques[i][j].setIcon(new ImageIcon("Resources/" + model.getFabriques()[i].getTuilesOnFabrique().get(j).getCouleurTuile().getImageTuile()));
             //   JButtonTuilesInFabriques[i][j].setIcon(new ImageIcon("Resources/" + fabrique.getTuilesOnFabrique().get(j).getCouleurTuile().getImageTuile()));
                JButtonTuilesInFabriques[i][j].setPreferredSize(new Dimension(40, 40));
            }
        }


        //Centre de table
        JPanelCentreTable = new JPanel();
        //Marqueur 1er joueur
        JPanelMarqueurPremier = new JPanel();
        JButtonMarqueurPremier = new JButton();
        JButtonMarqueurPremier.setIcon(new ImageIcon("Resources/" + model.getFabriques()[JPanelFabriques.length-1].getTuilesOnFabrique().get(0).getCouleurTuile().getImageTuile()));
        JPanelCentreTable.add(JButtonMarqueurPremier);
        //JButtonMarqueurPremier.setIcon(new ImageIcon("Resources/" + model.getTuilePremierJoueur().getCouleurTuile().getImageTuile()));
        //JButtonMarqueurPremier.setPreferredSize(new Dimension(40,40));

        //MAIN DU JOUEUR
        /*JButtonMainJoueur = new JButton[4];
        for(int i = 1 ; i <= 4 ; i++){
            JButtonMainJoueur[i-1] = new JButton("Tuile " + i);
        }*/

        //LIGNE MOTIF*
        JButtonTuilesLigneMotif = new JButton[5][5];

        for(int i=0; i<5;i++){
            for(int j=0; j<i+1;j++) {
                JButtonTuilesLigneMotif[i][j] = new JButton("ligne : " + (i+1) + " tuiles : " + (j+1));


            }
        }
        //MUR

        //PLANCHER
        //En comm car déjà fait dans l'ui
        JPanelPlancher = new JPanel();
        JLabelPlancher = new JLabel[7];
        for(int i = 0; i < 7; i++){
            JLabelPlancher[i] = new JLabel("jlabel plancher "+i);
            JPanelPlancher.add(JLabelPlancher[i]);
        }

        //PENALITES
        JLabelPenalites = new JLabel[model.getNombreJoueur()];
        for(int i = 1 ; i <= model.getNombreJoueur() ; i++){
            JLabelPenalites[i-1] = new JLabel("Taille du plancher de  " + i + " : " + model.getListJoueurs().get(i-1).getPlancher().getListeTuiles().size());
        }
    }

    private void addWidgets(){
        //MENU
        JPanelMenu.setLayout(new GridLayout(3,1,50,50));
        JPanelMenu.add(JButtonJouerMenu);
        JPanelMenu.add(JButtonRegles);
        JPanelMenu.add(JButtonQuitter);

        JPanelRules.add(JLabelRegles);
        JPanelRules.add(JButtonRetourMenu);
        //SCORE
        JPanelScore.setLayout(new GridLayout(1, model.getNombreJoueur(), 10, 10));
        for(int i = 0 ; i < model.getNombreJoueur() ; i++){
            JPanelScore.add(JLabelScores[i]);
        }


        //MILIEU TABLE

        //Ajout des tuiles dans les fabriques
        JPanelMilieuTable.setLayout(new GridLayout(3,3,10,10));
        for(int i = 0 ; i < model.getNombreFabriqueGame() ; i++){
            for(int j = 0 ; j < 4 ; j++){
                JPanelFabriques[i].add(JButtonTuilesInFabriques[i][j]);
            }
        }

        //ajout du panel centre table
        //JPanelCentreTable.add(JButtonTuilesCentre.get(0));
        JPanelFabriques[JPanelFabriques.length-1].add(JPanelCentreTable);

        //Ajout des fabriques au centre de la table
        for(int i = 0 ; i < model.getNombreFabriqueGame()+1 ; i++){
            JPanelMilieuTable.add(JPanelFabriques[i]);
        }
        //Centre de table
        /*//Ajout du marqueur 1er
        JPanelMarqueurPremier.add(JButtonMarqueurPremier);
        JPanelCentreTable.add(new JLabel("Centre de table :"));*/





        //MAIN DU JOUEUR
        JPanelMainJoueur.setLayout(new GridLayout(1, 4, 10, 10));
        /*for(int i = 0 ; i < 4 ; i++){
            JPanelMainJoueur.add(JButtonMainJoueur[i]);
        }*/

        //LIGNE MOTIFS

        JPanelLigneMotif.setLayout(new GridLayout(5,1));
        for(int i= 0; i <5; i++){
            JPanel JPanelTemporaire = new JPanel();
            for(int j=0; j<i+1;j++) {
                JPanelTemporaire.setLayout(new GridLayout(5,5,10,10));
                JPanelTemporaire.add(JButtonTuilesLigneMotif[i][j]);
                JPanelTemporaire.setAlignmentX(JScrollPane.RIGHT_ALIGNMENT);
                JPanelTemporaire.setLayout(new BoxLayout(JPanelTemporaire,BoxLayout.X_AXIS));
            }
            JPanelLigneMotif.add(JPanelTemporaire);
            JPanelLigneMotif.setLayout(new BoxLayout(JPanelLigneMotif,BoxLayout.Y_AXIS));
        }
       // JPanelLigneMotif.setAlignmentX(JScrollPane.TOP_ALIGNMENT);



        //MUR

        //PLANCHER

        JPanelPlancher.setLayout(new GridLayout(1,7));
        for(int i = 0; i < 7; i++){
            JPanel JpanelTemp = new JPanel();
            JpanelTemp.add(JLabelPlancher[i]);
            JPanelPlancher.add(JpanelTemp);
        }
        //PENALITES
        JPanelPenalites.setLayout(new GridLayout(1, model.getNombreJoueur(), 10, 10));
        for(int i = 0 ; i < model.getNombreJoueur(); i++){
            JPanelPenalites.add(JLabelPenalites[i]);
        }



    }

    public void display(){
        setVisible(true);
    }
    public void creatingGameFrame(){
        newGameFrame = new JFrame();
        newGameFrame.add(JPanelBase);
        System.out.println(getContentPane());
        setContentPane(JPanelBase);
        setSize(1280, 720);
        setTitle("Azul");
        setDefaultCloseOperation(newGameFrame.EXIT_ON_CLOSE);
        System.out.println(getContentPane());
    }
    public void  creatingRulesFrame(){
        newRulesFrame = new JFrame();
        newRulesFrame.add(JPanelRules);
        setContentPane(JPanelRules);
        setSize(1280, 720);
        setTitle("Regles Azul");
        setDefaultCloseOperation(newRulesFrame.EXIT_ON_CLOSE);
    }
    public void creatingMenuFrame(){
        add(JPanelMenu);
        setContentPane(JPanelMenu);
        setSize(1280, 720);
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setControlButton(ActionListener cb){
        //CONTROL DES TUILES DU CENTRE DE LA TABLE
        int tampon = 0;
        for(int i = 0 ; i < JPanelFabriques.length-1 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                JButtonTuilesInFabriques[i][j].addActionListener(cb);
                tampon++;
            }
        }

        //CONTROL DU MARQUEUR 1er
        JButtonMarqueurPremier.addActionListener(cb);

        //CONTROL DES TUILES DE LA MAIN DU JOUEUR

        for(int i= 0; i <5; i++){
            for(int j=0; j<i+1;j++)
                JButtonTuilesLigneMotif[i][j].addActionListener(cb);
        }
        JButtonJouerMenu.addActionListener(cb);
        JButtonRegles.addActionListener(cb);
        JButtonRetourMenu.addActionListener(cb);
        JButtonQuitter.addActionListener(cb);
    }


}
