package model;

import java.util.ArrayList;

public class Fabrique {
    // petit rond sur lequels on pose les fabrique
    ArrayList<Tuile> tuileOnFabrique;

    public Fabrique() {
        tuileOnFabrique = new ArrayList<>();
    }

    public ArrayList<Tuile> getTuileOnFabrique() {
        return tuileOnFabrique;
    }

    public void setTuileOnFabrique(ArrayList<Tuile> tuileOnFabrique) {
        this.tuileOnFabrique = tuileOnFabrique;
    }
}
