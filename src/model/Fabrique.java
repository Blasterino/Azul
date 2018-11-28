package model;

import java.util.ArrayList;

//Petit rond sur lequel on pose les tuiles
public class Fabrique {
    public ArrayList<Tuile> tuilesOnFabrique;

    public Fabrique() {
        tuilesOnFabrique = new ArrayList<>(4);
    }

    public ArrayList<Tuile> getTuilesOnFabrique() {
        return tuilesOnFabrique;
    }

    public void setTuilesOnFabrique(ArrayList<Tuile> tuiles){
        tuilesOnFabrique = tuiles;
    }

    public void addTuile(Tuile tuile){
        tuilesOnFabrique.add(tuile);
    }

    public void removeTuile(Tuile tuile){
        tuilesOnFabrique.remove(tuile);
    }

    public boolean isFabriqueVide(){
        return tuilesOnFabrique.isEmpty();
    }

    public void clearTuilesOnFabrique(){
        tuilesOnFabrique.clear();
    }


}
