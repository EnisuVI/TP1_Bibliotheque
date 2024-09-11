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

    public List<Livre> livresEmpruntes() throws SQLException {
        String requete = "SELECT * FROM livre INNER JOIN emprunt ON livre.isbn=emprunt.fk_isbn\n" +
                "WHERE date_retour IS NULL ORDER BY titre";
        List<Livre> livresEmpruntes = new ArrayList<>();
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery(requete);
        while (result.next()) {
            String titre = result.getString("titre");
            long isbn = result.getLong("isbn");
            String auteur = result.getString("auteur");
            String editeur = result.getString("editeur");
            int nbPages = result.getInt("nb_pages");
            int date = result.getInt("annee_publication");
            Livre lesLivres = new Livre(isbn, titre, auteur, editeur, nbPages, date);
            livresEmpruntes.add(lesLivres);
        }
        result.close();
        statement.close();
        return livresEmpruntes;
    }

    public List<Livre> livreRecherche(String recherche) throws SQLException {
        String requete = "SELECT * FROM livre WHERE titre LIKE ? OR auteur LIKE ? OR editeur LIKE ?";
        List<Livre> livreRecherche = new ArrayList<>();
        PreparedStatement prepStatement = this.connection.prepareStatement(requete);
        prepStatement.setString(1, "%" + recherche + "%");
        prepStatement.setString(2, "%" + recherche + "%");
        prepStatement.setString(3, "%" + recherche + "%");
        ResultSet result = prepStatement.executeQuery();
        while (result.next()) {
            String titre = result.getString("titre");
            long isbn = result.getLong("isbn");
            String auteur = result.getString("auteur");
            String editeur = result.getString("editeur");
            int nbPages = result.getInt("nb_pages");
            int date = result.getInt("annee_publication");
            Livre livre = new Livre(isbn, titre, auteur, editeur, nbPages, date);
            livreRecherche.add(livre);
            }
        result.close();
        prepStatement.close();
        return livreRecherche;
        }

    public static void main(String[] args) throws SQLException {
        RequeteBiblio connection1 = new RequeteBiblio();
        List<Livre> livres = connection1.biblioAlpha();
        System.out.println(livres);
        List<Usager> utilsateurs = connection1.getUtilisateurs();
        System.out.println(utilsateurs);
        List<Livre> livresEmpruntes = connection1.livresEmpruntes();
        System.out.println(livresEmpruntes);

        List<Livre> livreRecherche = connection1.livreRecherche("Vieux");
        System.out.println(livreRecherche);
    }
}
