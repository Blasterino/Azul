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
    public JButton[] tuile,tuilesFabriques;
    public JButton tuilePremierJoueur;

    JPanel[] fabriques;
    JLabel penalite, score;
    ArrayList<JLabel> listScoreJoueurView, listPenaliteJoueurView;

    JPanel fullView, middle, centreTable;

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
        for (JButton tuileButton : tuile)
            tuileButton.addActionListener(cb);
    }

    public void initAttribut() {
        // Initialisation des attributs
        actionListener = new ControlButton(this, model);
        fabriques =  new JPanel[model.getNombreFabriqueGame()];
        centreTable = new JPanel();
        tuile =  new JButton[4];
        tuilesFabriques = new JButton[4*model.getNombreFabriqueGame()];
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
            fabriques[i] = new JPanel();
           // fabrique[i].addActionListener(actionListener); // ajouter l'écouteur aux
            fabriques[i].setBackground(Color.orange);
            for(int j=0;j<4; j++){
                tuile[j] = new JButton();
                tuile[j].addActionListener(actionListener);
                //j'ajoute sur chaque bouton (tuiles) l'image de la tuile adéquat
                tuile[j].setIcon(new ImageIcon("Resources/"+model.getSacPioche().piocherUneTuileDuSac().getCouleurTuile().getImageTuile()));
                fabriques[i].add(tuile[j]);
                tuilesFabriques[4*i+j] = tuile[j];
            }

            middle.add(fabriques[i]);
        }
        //création du milieu de la table
        centreTable.setBackground(Color.red);
        tuilePremierJoueur = new JButton();
        tuilePremierJoueur.setIcon(new ImageIcon( "Resources/"+model.getTuilePremierJoueur().getCouleurTuile().getImageTuile()));
        tuilePremierJoueur.addActionListener(actionListener);
        centreTable.add(tuilePremierJoueur);
        middle.add(centreTable);


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