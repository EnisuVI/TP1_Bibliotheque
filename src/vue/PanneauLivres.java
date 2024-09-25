package vue;

import modele.Livre;
import requete.RequeteBiblio;

import javax.swing.*;
import java.awt.*;

public class PanneauLivres extends JPanel {
    private JLabel etiquetteRecherche;
    private JTextField filtreRecherche;
    private JList<Livre> listeLivres;
    private JScrollPane scrollPane;
    private RequeteBiblio requete;
    public static final Color COLORFOND = new Color(30, 30, 42);
    public static final Color COLORTEXT = new Color(241,241,241);

    public PanneauLivres(RequeteBiblio requete){
        super();
        initComponents();
        initLayout();
        this.requete = requete;
        this.setBackground(COLORFOND);
    }

    private void initComponents(){
        this.etiquetteRecherche = new JLabel(("Recherche : "));
        this.filtreRecherche = new JTextField(50);
        this.listeLivres = new JList<>();
        this.scrollPane = new JScrollPane(this.listeLivres);
        etiquetteRecherche.setForeground(COLORTEXT);
        etiquetteRecherche.setFont(Font.getFont("Papyrus"));
        listeLivres.setFont(Font.getFont("Papyrus"));
    }

    private void initLayout(){
        JPanel panneauHaut = new JPanel();
        JPanel panneau1 = new JPanel();
        JPanel panneau2 = new JPanel();
        JPanel panneau3 = new JPanel();
        panneauHaut.setBackground(COLORFOND);
        panneau1.setBackground(COLORFOND);
        panneau2.setBackground(COLORFOND);
        panneau3.setBackground(COLORFOND);
        panneau2.add(this.etiquetteRecherche);
        panneau2.add(this.filtreRecherche);
        GridLayout gridLayout = new GridLayout(3,1);
        panneauHaut.setLayout(gridLayout);
        panneauHaut.add(panneau1);
        panneauHaut.add(panneau2);
        panneauHaut.add(panneau3);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.add(this.scrollPane, BorderLayout.CENTER);
        this.add(panneauHaut, BorderLayout.NORTH);
        this.add(Box.createHorizontalStrut(100), BorderLayout.EAST);
        this.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
    }
}
