package model;

import java.util.ArrayList;

public class CentreTable extends Fabrique{

    public CentreTable(){
        super();
        tuilesOnFabrique = new ArrayList<>();
    }

    public void prendreTuile(Tuile tuile, Plateau joueur){
        joueur.addTuileInMain(tuile);
        removeTuile(tuile);

        ArrayList<Tuile> tuilesAEnlever = new ArrayList<>();
        for(Tuile tuile1 : tuilesOnFabrique){
            if(tuile.getCouleurTuile() == tuile1.getCouleurTuile()){
                joueur.addTuileInMain(tuile1);
                tuilesAEnlever.add(tuile1);
            }
        }

        for(Tuile tuile1 : tuilesAEnlever){
            removeTuile(tuile1);
        }
    }

    @Override
    public String toString() {
        return "CentreTable{" +
                "tuilesOnFabrique=" + tuilesOnFabrique +
                '}';
    }
}
