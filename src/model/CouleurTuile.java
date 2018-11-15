package model;

public enum CouleurTuile {
    BLEU("BLEU","imageTuileBleu"),
    ROUGE("ROUGE","imageTuileRouge"),
    VERT("VERT","imageTuileVert"),
    JAUNE("JAUNE","imageTuileJaune"),
    NOIR("NOIR","imageTuileNoir"),
    PREMIERJOUEUR("PREMIERJOUEUR","imagePremierJoueur");

    String couleurTuile;
    String imageTuile;

    CouleurTuile(String couleurTuile) {
        this.couleurTuile = couleurTuile;
    }

    CouleurTuile(String couleurTuile, String imageTuile) {
        this.couleurTuile = couleurTuile;
        this.imageTuile = imageTuile;
    }

    public String getImageTuile() {
        return imageTuile;
    }

    public void setImageTuile(String imageTuile) {
        this.imageTuile = imageTuile;
    }

    public String getCouleurTuile() {
        return couleurTuile;
    }

    @Override
    public String toString() {
        return "CouleurTuile{" +
                "couleurTuile='" + couleurTuile + '\'' +
                '}';
    }

    public void setCouleurTuile(String couleurTuile) {
        this.couleurTuile = couleurTuile;
    }
}
