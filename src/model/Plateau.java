package model;

import java.util.ArrayList;

public class Plateau {
    //plateau du joueur
    // A faire plus tard, avec les images,affichage, interactions tout ça tout ça
    LigneTuile plancher;
    Model model; // pour envoyer des tuiles dans la défausse
    private int pointDuJoueur;
    private ArrayList<Tuile> mainActuelle;
    public LigneTuile[] ligneDeMotif;
    public Tuile[][] murPlateau;
    protected boolean tourDeJeu;  // Si le joueur est en train de jouer ou non



    public Plateau() {
        tourDeJeu = false;
        plancher = new LigneTuile(7);
        pointDuJoueur = 0;
        mainActuelle = new ArrayList<>();
        ligneDeMotif = new LigneTuile[5];
        murPlateau = new Tuile[5][5];

        for(int i = 0; i<5; i ++){
                ligneDeMotif[i] = new LigneTuile(i+1);
                for(int j= 0; j<5;j++){
                    murPlateau[i][j] = new Tuile(); // on initialise un tableau avec des tuiles vide
                }
        }

    }

    public LigneTuile[] getLigneDeMotif() {
        return ligneDeMotif;
    }
    public boolean isLigneSpecifiqueVide(int ligne){
        return (this.ligneDeMotif[ligne].isVide());
    }

    public boolean isEmplacementLigneSpecifiqueVide(int ligne,int emplacement){

        return(this.ligneDeMotif[ligne].getListeTuiles().size() < emplacement+1);
    }

    public void setLigneDeMotif(LigneTuile[] ligneDeMotif) {
        this.ligneDeMotif = ligneDeMotif;
    }

    public void addPenalite(Tuile tuile){
        if(plancher.getListeTuiles().size() == plancher.getTaille()){
            model.defausseTuile(tuile);
        } else {
            plancher.addTuile(tuile);
        }
    }

    public boolean isTourDeJeu() {
        return tourDeJeu;
    }

    public void setTourDeJeu(boolean tourDeJeu) {
        this.tourDeJeu = tourDeJeu;
    }

    public int getTaillePenalite(){
        return plancher.getTaille();
    }


    //methode pour verifier a chaque tour si un joueur prend des points de pénalité ou non
    // a partir de la taille de la list ou se situe les jetons de penalité
    public void calculPenalite() {
        switch (plancher.getTaille()) {
            case 0:
                break;
            case 1: // en cas de 1 jeton sur la pénalité, on perd 1 point
                if (pointDuJoueur > 0)
                    pointDuJoueur--;
                break;
            case 2:// en cas de 2 jeton sur la pénalité, on perd 2 point
                if (pointDuJoueur > 1)
                    pointDuJoueur -= 2;
                else
                    pointDuJoueur = 0;
                break;
            case 3:// en cas de 3 jeton sur la pénalité, on perd 4 point
                if (pointDuJoueur > 4)
                    pointDuJoueur -= 4;
                else
                    pointDuJoueur = 0;
                break;
            case 4: // en cas de 4 jeton sur la pénalité, on perd 6 point
                if (pointDuJoueur > 6)
                    pointDuJoueur -= 6;
                else
                    pointDuJoueur = 0;
                break;
            case 5:// en cas de 5 jeton sur la pénalité, on perd 8 point
                if (pointDuJoueur > 8)
                    pointDuJoueur -= 8;
                else
                    pointDuJoueur = 0;
                break;
            case 6:// en cas de 6 jeton sur la pénalité, on perd 11 point
                if (pointDuJoueur > 11)
                    pointDuJoueur -= 11;
                else
                    pointDuJoueur = 0;
                break;
            case 7: // en cas de 7 jeton sur la pénalité, on perd 14 point
                if (pointDuJoueur > 14)
                    pointDuJoueur -= 14;
                else
                    pointDuJoueur = 0;
                break;
        }
    }

    public int getPointDuJoueur() {
        return pointDuJoueur;
    }

    public void setPointDuJoueur(int pointDuJoueur) {
        this.pointDuJoueur = pointDuJoueur;
    }

    public void addTuileInMain(Tuile tuile){
        mainActuelle.add(tuile);
    }

    public LigneTuile getPlancher() {
        return plancher;
    }

    public void setPlancher(LigneTuile plancher) {
        this.plancher = plancher;
    }

    public ArrayList<Tuile> getMainActuelle() {
        return mainActuelle;
    }

    public void setMainActuelle(ArrayList<Tuile> mainActuelle) {
        this.mainActuelle = mainActuelle;
    }

    public void clearMainActuelle(){
        mainActuelle.clear();
    }

    public Tuile[][] getMurPlateau() {
        return murPlateau;
    }

    public void setMurPlateau(Tuile[][] murPlateau) {
        this.murPlateau = murPlateau;
    }
}
