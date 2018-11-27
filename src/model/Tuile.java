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

    @Override
    public String toString() {
        return "Tuile{" +
                "couleurTuile=" + couleurTuile +
                '}';
    }
}

