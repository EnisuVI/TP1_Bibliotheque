package vue;

import modele.Livre;

import javax.swing.*;
import java.awt.*;

public class PanneauLivres extends JPanel {
    private JLabel etiquetteRecherche;
    private JTextField filtreRecherche;
    private JList<Livre> listeLivres;
    private JScrollPane scrollPane;

    public PanneauLivres(){
        super();
        initComponents();
        initLayout();
    }

    private void initComponents(){
        this.etiquetteRecherche = new JLabel(("Recherche : "));
        this.filtreRecherche = new JTextField(50);
        this.listeLivres = new JList<>();
        this.scrollPane = new JScrollPane(this.listeLivres);
    }

    private void initLayout(){
        this.add(etiquetteRecherche);
        this.add(filtreRecherche);
        this.add(scrollPane);
    }
}
