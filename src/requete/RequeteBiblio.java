package requete;

import modele.Emprunt;
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

    //Question 6
    public List<Livre> biblioAlpha() throws SQLException {
        String requete = "SELECT * FROM livre ORDER BY titre ASC";
        List<Livre> livres = new ArrayList<>();
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery(requete);
        while (result.next()) {
            String titre = result.getString("titre");
            String isbn = result.getString("isbn");
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

    //Question 7
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

    //Question 8
    public List<Livre> livresEmpruntes() throws SQLException {
        String requete = "SELECT * FROM livre INNER JOIN emprunt ON livre.isbn=emprunt.fk_isbn\n" +
                "WHERE date_retour IS NULL ORDER BY titre";
        List<Livre> livresEmpruntes = new ArrayList<>();
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery(requete);
        while (result.next()) {
            String titre = result.getString("titre");
            String isbn = result.getString("isbn");
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

    // Question 9
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
            String isbn = result.getString("isbn");
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

        //Question 10
        public List<Usager> personneRecherche(String recherche) throws SQLException {
        String requete = "SELECT * FROM usager WHERE nom LIKE ? OR prenom LIKE ?";
        List<Usager> personneRecherche = new ArrayList<>();
        PreparedStatement prepStatement = this.connection.prepareStatement(requete);
        prepStatement.setString(1, "%" + recherche + "%");
        prepStatement.setString(2, "%" + recherche + "%");
        ResultSet result = prepStatement.executeQuery();
        while (result.next()) {
            int id_usager = result.getInt("id_usager");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adresse = result.getString("adresse");
            Date date_naissance = result.getDate("date_naissance");
            Usager usager = new Usager(id_usager, nom, prenom, adresse, date_naissance);
            personneRecherche.add(usager);
            }
        result.close();
        prepStatement.close();
        return personneRecherche;
        }

        //Question 11
        public List<Emprunt> enRetard(int rechercheDate) throws SQLException {
        String requete = "SELECT * FROM emprunt WHERE DATEDIFF(CURRENT_DATE, date_emprunt) >= ? AND date_retour IS NULL";
        List<Emprunt> enRetard = new ArrayList<>();
        PreparedStatement prepStatement = this.connection.prepareStatement(requete);
        prepStatement.setInt(1, rechercheDate);
        ResultSet result = prepStatement.executeQuery();
        while (result.next()) {
            int id_emprunt = result.getInt("id_emprunt");
            Date date_emprunt = result.getDate("date_emprunt");
            Date date_retour = result.getDate("date_retour");
            Emprunt emprunt = new Emprunt(id_emprunt, date_emprunt, date_retour, null, null);
            enRetard.add(emprunt);
            }
        result.close();
        prepStatement.close();
        return enRetard;
        }

        //Question 12
        /**public List<Livre> rendreLivre(String titre) throws SQLException {
        String requete = "INSERT INTO livre(titre) VALUES (?)";
        Statement statement = this.connection.createStatement();
        statement.setString(1, titre);
        int nbRows = statement.executeUpdate(requete);
        System.out.println(nbRows + " enregistrement(s) ajouté(s)");
        statement.close();
        }*/

        //Question 12
        public void giveBackLivre(Livre livre) throws SQLException {

        if (livre == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être null");
        }

        String requete = "UPDATE emprunt SET date_retour = ? WHERE fk_isbn = ? AND date_retour IS NULL";
        PreparedStatement prepStatement =
                this.connection.prepareStatement(requete);
        prepStatement.setDate(1, new Date(System.currentTimeMillis()));
        prepStatement.setString(2, livre.getIsbn());
        prepStatement.executeUpdate();
        prepStatement.close();
    }
        //Question 13
        public void emprunterLivre(Livre livre, Usager usager) throws SQLException {

            if (livre == null) {
                throw new IllegalArgumentException("Le livre ne peut pas être null");
            }

             if (usager == null) {
                throw new IllegalArgumentException("L'usager ne peut pas être null");
            }

            String requete = "INSERT INTO emprunt (date_emprunt, fk_id_usager, fk_isbn) VALUES (?,?,?)";
            PreparedStatement prepStatement =
                this.connection.prepareStatement(requete);
            prepStatement.setDate(1, new Date(System.currentTimeMillis()));
            prepStatement.setInt(2, usager.getId());
            prepStatement.setString(3, livre.getIsbn());
            prepStatement.executeUpdate();
            prepStatement.close();
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
        List<Usager> personneRecherche = connection1.personneRecherche("Chantal");
        System.out.println(personneRecherche);
        List<Emprunt> enRetard = connection1.enRetard(20);
        System.out.println(enRetard);

            Livre unLivre = null;
            for (Livre livre : livres) {
                if (livre.getIsbn().equals("9782846820820")) {
                    unLivre = livre;
                    break;
                }
            }
            assert unLivre != null;
            System.out.println(unLivre);
            connection1.giveBackLivre(unLivre);

            Livre unAutreLivre = null;
            Usager unUsager = null;
            for (Livre livre : livres) {
                if (livre.getIsbn().equals("9782846820820")) {
                    unAutreLivre = livre;
                    break;
                }
            }
            for (Usager usager : utilsateurs) {
                if (usager.getId() == 1) {
                    unUsager = usager;
                    break;
                }
            }
            assert unAutreLivre != null;
            assert unUsager != null;
            connection1.emprunterLivre(unAutreLivre, unUsager);

    }

}
