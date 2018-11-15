package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Plateau {
    //plateau du joueur
    // A faire plus tard, avec les images,affichage, interactions tout ça tout ça
    ArrayList<Tuile> penalite;
    int pointDuJoueur;


    public Plateau() {
        penalite = new ArrayList<>();
        pointDuJoueur = 0;
    }


    //methode pour verifier a chaque tour si un joueur prend des points de pénalité ou non
    // a partir de la taille de la list ou se situe les jetons de penalité
    public void calculPenalite() {
        switch (penalite.size()) {
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



}
