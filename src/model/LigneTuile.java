package model;

import java.util.ArrayList;
import java.util.List;

public class LigneTuile {

    List<Tuile> listeTuiles;
    int taille;

    public LigneTuile(int taille) {
        this.taille = taille;
        this.listeTuiles = new ArrayList<>();
    }

    public int addTuile(Tuile tuile){

        if(listeTuiles.size() == taille){
            return -1;
        } else {
            listeTuiles.add(tuile);
            return 0;
        }
    }

    public int removeTuile(int index){
        if(index > listeTuiles.size()-1 || index < 0) {
            return -1;
        } else {
            listeTuiles.remove(index);
            return 0;
        }
    }

    public boolean isVide(){
        return listeTuiles.isEmpty();
    }

    public List<Tuile> getListeTuiles() {
        return listeTuiles;
    }

    public void setListeTuiles(List<Tuile> listeTuiles) {
        this.listeTuiles = listeTuiles;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }


    public boolean isLigneTuilePleine(){
        return this.getTaille() == this.getListeTuiles().size();
    }

    @Override
    public String toString() {
        return "LigneTuile{" +
                "listeTuiles=" + listeTuiles +
                ", taille=" + taille +
                '}';
    }
}
