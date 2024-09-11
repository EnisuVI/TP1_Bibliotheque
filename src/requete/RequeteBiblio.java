package requete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RequeteBiblio {
    private Connection connection;

    public RequeteBiblio() throws SQLException {
        String urlBDD = "jdbc:mysql://localhost:3306/TP1_Bibliotheque";
        String utilisateur = "user";
        String motDePasse = "user";
        Connection connexionBdd = DriverManager.getConnection(urlBDD, utilisateur, motDePasse);
        this.connection = connexionBdd;
    }

}
