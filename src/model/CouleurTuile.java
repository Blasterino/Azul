package model;

public enum CouleurTuile {
    BLEU("BLEU","imageTuileBleu.png"),
    ROUGE("ROUGE","imageTuileRouge.png"),
    BLANC("BLANC","imageTuileBlanche.png"),
    JAUNE("JAUNE","imageTuileJaune.png"),
    NOIR("NOIR","imageTuileNoir.png"),
    PREMIERJOUEUR("PREMIERJOUEUR","imagePremierJoueur.png");

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
        if(imageTuile == null)
            return "imageTuileVide.png";
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
