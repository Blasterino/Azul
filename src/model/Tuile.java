package model;

public class Tuile {
    CouleurTuile couleurTuile;

    public Tuile() {
        couleurTuile = null;
    }

    public Tuile(CouleurTuile couleurTuile) {
        this.couleurTuile = couleurTuile;
    }

    public CouleurTuile getCouleurTuile() {
        return couleurTuile;
    }

    public void setCouleurTuile(CouleurTuile couleurTuile) {
        this.couleurTuile = couleurTuile;
    }

    public boolean isColorNull(){
        if(this.couleurTuile ==null )
            return true;
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Tuile{" +
                "couleurTuile=" + couleurTuile +
                '}';
    }
}

