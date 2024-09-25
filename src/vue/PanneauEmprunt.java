package vue;

import requete.RequeteBiblio;

import javax.swing.*;
import java.awt.*;

public class PanneauEmprunt extends JPanel {
    private RequeteBiblio requete;
    public static final Color COLORFOND = new Color(30, 30, 42);

    public PanneauEmprunt(RequeteBiblio requete) {
        super();
        this.requete = requete;
        this.setBackground(COLORFOND);
    }
}
