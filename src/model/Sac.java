package model;

import java.util.ArrayList;
import java.util.Collections;

public class Sac {
    ArrayList<Tuile> contenuSac;
    static final int nombreDeTuileMax = 100;
    static final int nombreDeTuileMaxParCouleur = 20;

    public Sac(){
        contenuSac = null;
    }

    public Sac(ArrayList<Tuile> contenuSac) {
        this.contenuSac = contenuSac;
    }

    public ArrayList<Tuile> getContenuSac() {
        return contenuSac;
    }

    // J'initialise mon sac de tuille
    public ArrayList<Tuile> remplirPaquetDebutDePartie(){
        //Pour chaque couleur dans mon enumerations
                for (CouleurTuile couleur : CouleurTuile.values()){
                    //je vérifie que ce n'est pas une couleur "premier joueur" pour ne pas en creer 20
                    if(couleur != CouleurTuile.PREMIERJOUEUR) {
                        for (int j = 0; j < getNombreDeTuileMaxParCouleur(); j++) {
                            // Je creer 20 tuiles de la couleur séléctionné
                            Tuile tuileColore = new Tuile(couleur);
                            contenuSac.add(tuileColore);
                        }
                    }
                } // ce qui me donne un total de 100 tuile
        // on mélange le sac
        Collections.shuffle(contenuSac);

        return contenuSac;
    }

    public void setContenuSac(ArrayList<Tuile> contenuSac) {
        this.contenuSac = contenuSac;
    }

    public static int getNombreDeTuileMax() {
        return nombreDeTuileMax;
    }

    public static int getNombreDeTuileMaxParCouleur() {
        return nombreDeTuileMaxParCouleur;
    }
}
