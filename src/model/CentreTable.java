package model;

import java.util.ArrayList;

public class CentreTable {

    public ArrayList<Tuile> tuilesOnCentreTable;

    public CentreTable(){
        tuilesOnCentreTable = new ArrayList<>();
    }

    public ArrayList<Tuile> getTuilesOnCentreTable() {
        return tuilesOnCentreTable;
    }

    public void setTuilesOnCentreTable(ArrayList<Tuile> tuilesOnCentreTable) {
        this.tuilesOnCentreTable = tuilesOnCentreTable;
    }

    public void addTuile(Tuile tuile){
        tuilesOnCentreTable.add(tuile);
    }

    public void removeTuile(Tuile tuile){
        tuilesOnCentreTable.remove(tuile);
    }

    public void clearTuiles(){
        tuilesOnCentreTable.clear();
    }

    public void prendreTuile(Tuile tuile, Plateau joueur){
        joueur.addTuileInMain(tuile);
        removeTuile(tuile);

        ArrayList<Tuile> tuilesAEnlever = new ArrayList<>();
        for(Tuile tuile1 : tuilesOnCentreTable){
            if(tuile.getCouleurTuile() == tuile1.getCouleurTuile()){
                joueur.addTuileInMain(tuile1);
                tuilesAEnlever.add(tuile1);
            }
        }

        for(Tuile tuile1 : tuilesAEnlever){
            removeTuile(tuile1);
        }
    }
}
