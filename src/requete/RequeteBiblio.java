package requete;

import modele.Livre;
import modele.Usager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequeteBiblio {
    private Connection connection;

    public RequeteBiblio() throws SQLException {
        String urlBDD = "jdbc:mysql://localhost:8889/TP1_Bibliotheque";
        String utilisateur = "root";
        String motDePasse = "root";
        Connection connexionBdd = DriverManager.getConnection(urlBDD, utilisateur, motDePasse);
        this.connection = connexionBdd;
    }

    public List<Livre> biblioAlpha() throws SQLException {
        String requete = "SELECT * FROM livre ORDER BY titre ASC";
        List<Livre> livres = new ArrayList<>();
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery(requete);
        while (result.next()) {
            String titre = result.getString("titre");
            long isbn = result.getLong("isbn");
            String auteur = result.getString("auteur");
            String editeur = result.getString("editeur");
            int nbPages = result.getInt("nb_pages");
            int date = result.getInt("annee_publication");
            Livre livre = new Livre(isbn, titre, auteur, editeur, nbPages, date);
            livres.add(livre);
        }
        result.close();
        statement.close();
        return livres;
    }

        public List<Usager> getUtilisateurs() throws SQLException {
        String requete = "SELECT * FROM usager ORDER BY nom ASC";
        List<Usager> utilisateurs = new ArrayList<>();
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery(requete);
        while (result.next()) {
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            int id_usager = result.getInt("id_usager");
            String adresse = result.getString("adresse");
            Date dateNaissance = result.getDate("date_naissance");
            Usager utilisateur = new Usager(id_usager, nom, prenom, adresse, dateNaissance);
            utilisateurs.add(utilisateur);
        }
        result.close();
        statement.close();
        return utilisateurs;
    }

    public static void main(String[] args) throws SQLException {
        RequeteBiblio connection1 = new RequeteBiblio();
        List<Livre> livres = connection1.biblioAlpha();
        System.out.println(livres);
        List<Usager> utilsateurs = connection1.getUtilisateurs();
        System.out.println(utilsateurs);
    }
}
