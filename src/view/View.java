package view;

import controller.ControlButton;
import model.Model;
import model.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class View extends JFrame {
    Model model;

  //  JButton[] boutonBijoux = new JButton[8][8];
    JButton[] fabrique;


    JLabel penalite, score;
    ArrayList<JLabel> listScoreJoueurView, listPenaliteJoueurView;

    JPanel fullView, middle;

    protected ActionListener actionListener;



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
        actionListener = new ControlButton(this, model);
        fabrique =  new JButton[model.getNombreFabriqueGame()];
        fullView = new JPanel();
        listPenaliteJoueurView = new ArrayList<>();
        listScoreJoueurView = new ArrayList<>();

        for(Plateau joueurs : model.getListJoueurs()){
            score = new JLabel(String.valueOf(joueurs.getPointDuJoueur()));
            listScoreJoueurView.add(score);
        }

        for(Plateau joueurs : model.getListJoueurs()){
            // penalite = new JLabel(String.valueOf(joueurs.getPointDePenaliteActuel()));
            penalite = new JLabel("Missing value");
            listPenaliteJoueurView.add(penalite);
        }
    }





    public void addWidget() {
        int compteursNbJoueurs = 0; // Variable tampon
        final JPanel top = new JPanel();
        for(JLabel scoreJoueur : listScoreJoueurView){
            compteursNbJoueurs++;
            top.add(new JLabel("Score Joueur" + compteursNbJoueurs + " :"));
            top.add(scoreJoueur);

            top.add(Box.createHorizontalStrut(50));
        }


        middle = new JPanel(new GridLayout(3,3,100,100));
        for (int i = 0; i < model.getNombreFabriqueGame(); i++) {
            fabrique[i] = new JButton();
            fabrique[i].addActionListener(actionListener); // ajouter l'écouteur aux
            fabrique[i].setIcon(new ImageIcon("Resources/ImageFabrique.png"));
            middle.add(fabrique[i]);
        }


        JPanel bottom = new JPanel();
        compteursNbJoueurs=0;
        for(JLabel penaliteJoueur : listPenaliteJoueurView){
            compteursNbJoueurs++;
            bottom.add(new JLabel("Pénalité du joueur" + compteursNbJoueurs+": "));
            bottom.add(penaliteJoueur);
            bottom.add(Box.createHorizontalStrut(50));
        }
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.X_AXIS));
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS));
        root.add(top);
        root.add(middle);
        root.add(bottom);
        this.setContentPane(root);

    }

    //Affichage de la vue
    public void display(){
        this.setVisible(true);
    }


}