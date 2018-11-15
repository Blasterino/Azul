package model;

import java.util.ArrayList;

public class Model {
    int nombreJoueur;
    ArrayList<Plateau> listJoueurs;
    ArrayList<Fabrique> listFabrique;
    Fabrique centreTable;
    Sac defausse;
    Tuile tuilePremierJoueur;

    public Model(int nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
        // On creer la defausse en tant que Sac
        defausse= new Sac();
        // On creer le centre de la table en tant qu'une fabrique, pas la peine de creer une autre class
        centreTable = new Fabrique();
        // creation de la tuile du premier joueur
        tuilePremierJoueur = new Tuile(CouleurTuile.PREMIERJOUEUR);
        listJoueurs = new ArrayList<>();
        // et on place la tuile du premier joueur au centre de la table
        centreTable.getTuileOnFabrique().add(tuilePremierJoueur);
        for(int i = 0; i < nombreJoueur ; i ++){
            Plateau joueur = new Plateau();
            listJoueurs.add(joueur);
        }

    }


    // On place le nombre de fabrique suivant le nombre de joueurs
    public void initialisationNombreFabrique(){
        if(nombreJoueur == 2){
            for(int i=0; i<5 ; i++){
                Fabrique fabrique = new Fabrique();
                listFabrique.add(fabrique);
            }
        }
        if(nombreJoueur == 3){
            for(int i=0; i<7 ; i++){
                Fabrique fabrique = new Fabrique();
                listFabrique.add(fabrique);
            }
        }
        if(nombreJoueur == 4){
            for(int i=0; i<9 ; i++){
                Fabrique fabrique = new Fabrique();
                listFabrique.add(fabrique);
            }
        }

    }

    //On effectue ici toutes les différentes étapes à faire lors d'un tours de jeux
    public void tourDeJeux(){




        //On vérifie la pénalité des joueurs à chaque fin de tours
        for (Plateau joueur : listJoueurs ) {
            joueur.calculPenalite();
        }

    }







}
