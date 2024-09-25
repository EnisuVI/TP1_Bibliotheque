package modele;

import java.util.Date;

public class Usager {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private Date dateNaissance;

    public Usager(int id, String nom, String prenom, String adresse, Date dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usager{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }

}
