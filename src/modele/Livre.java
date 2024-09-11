package modele;

import java.util.Date;

public class Livre {
    private long isbn;
    private String titre;
    private String auteur;
    private String editeur;
    private int nbPages;
    private int anneePublication;

    public Livre(long isbn, String titre, String auteur, String editeur, int nbPages, int anneePublication) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.nbPages = nbPages;
        this.anneePublication = anneePublication;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "isbn=" + isbn +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", editeur='" + editeur + '\'' +
                ", nbPages=" + nbPages +
                ", anneePublication=" + anneePublication +
                '}';
    }

}
