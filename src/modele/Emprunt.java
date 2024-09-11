package modele;

import java.util.Date;

public class Emprunt {
    private int id;
    private Date dateEmprunt;
    private Date dateRetour;
    private Livre livre;
    private Usager usager;

    public Emprunt(int id, Date dateEmprunt, Date dateRetour, Livre livre, Usager usager) {
        this.id = id;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.livre = livre;
        this.usager = usager;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                ", livre=" + livre +
                ", usager=" + usager +
                '}';
    }

}
