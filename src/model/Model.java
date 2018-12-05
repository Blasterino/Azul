package model;

import java.util.ArrayList;

public class Model {
    boolean joueurAvecTuileEnMain;
    int nombreJoueur;
    ArrayList<Plateau> listJoueurs;

    Fabrique[] fabriques;
    CentreTable centreTable;
    Sac sacPioche;
    Sac defausse;
    Tuile tuilePremierJoueur;


    public Fabrique fabrique;

    public Model(int nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
        listJoueurs = new ArrayList<>();
        for(int i = 0; i < nombreJoueur ; i ++){
            Plateau joueur = new Plateau();
            listJoueurs.add(joueur);
        }
        //variable utilisée
        joueurAvecTuileEnMain = false;
        // Création pioche et défausse
        sacPioche= new Sac();
        defausse= new Sac();
        // Création centre de la table
        centreTable = new CentreTable();
        //Création fabriques
        fabriques = new Fabrique[getNombreFabriqueGame()];
        //Remplissage de la pioche
        sacPioche.remplirPaquetDebutDePartie();
        //Initialisation des fabriques
        initialisationFabriques();
        //Initialisation du centre
        centreTable.addTuile(new Tuile(CouleurTuile.PREMIERJOUEUR));

    }

    //Création des fabriques en fonction du nombre de joueurs
    public void initialisationFabriques(){
        for(int i = 0 ; i < getNombreFabriqueGame() ; i++){
            Fabrique fabrique = new Fabrique();
            for(int j = 0 ; j < 4 ; j++){
                fabrique.addTuile(getSacPioche().piocherUneTuileDuSac());
            }
            fabriques[i] = fabrique;
        }
    }

    //On effectue ici toutes les différentes étapes à faire lors d'un tours de jeux
    public void tourDeJeux(){
        //On vérifie la pénalité des joueurs à chaque fin de tours
        for (Plateau joueur : listJoueurs ) {
            joueur.calculPenalite();
        }

    }

    public int getNombreJoueur() {
        return nombreJoueur;
    }

    public ArrayList<Plateau> getListJoueurs() {
        return listJoueurs;
    }

    public int getNombreFabriqueGame(){
        if(nombreJoueur == 2){
            return 5;
        }
        if(nombreJoueur == 3){
            return 7;
        }
        if(nombreJoueur == 4){
            return 9;
        }
        return 0;
    }

    public Fabrique[] getFabriques() {
        return fabriques;
    }

    public CentreTable getCentreTable() {
        return centreTable;
    }

    public Sac getDefausse() {
        return defausse;
    }

    public Tuile getTuilePremierJoueur() {
        return tuilePremierJoueur;
    }

    public Sac getSacPioche() {
        return sacPioche;
    }

    public void setSacPioche(Sac sacPioche) {
        this.sacPioche = sacPioche;
    }

    public boolean isJoueurAvecTuileEnMain() {
        return joueurAvecTuileEnMain;
    }

    public void setJoueurAvecTuileEnMain(boolean joueurAvecTuileEnMain) {
        this.joueurAvecTuileEnMain = joueurAvecTuileEnMain;
    }

}
