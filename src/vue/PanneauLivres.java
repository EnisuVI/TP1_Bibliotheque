package vue;

import modele.Livre;
import requete.RequeteBiblio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;

public class PanneauLivres extends JPanel {
    private JLabel etiquetteRecherche;
    private JTextField filtreRecherche;
    private JList<Livre> listeLivres;
    private JScrollPane scrollPane;
    private RequeteBiblio requete;
    public static final Color COLORFOND = new Color(30, 30, 42);
    public static final Color COLORTEXT = new Color(241,241,241);

    public class MyListener implements KeyListener {
        private PanneauLivres panneauLivres;

        public MyListener(PanneauLivres panneauLivres) {
            this.panneauLivres = panneauLivres;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            panneauLivres.getLivre(text);
        }
    }

    public PanneauLivres(RequeteBiblio requete){
        super();
        this.requete = requete;
        initComponents();
        initLayout();
        this.setBackground(COLORFOND);
    }

    private void getLivre(String recherche){
        try {
            List<Livre> elements = requete.livreRecherche(recherche);
            DefaultListModel<Livre> model = new DefaultListModel<>();
            for (Livre livre : elements) {
                model.addElement(livre);
            }
            listeLivres.setModel(model);
        } catch(SQLException ex){
            System.out.println("Erreur.");
        }
    }
    private void initComponents(){
        this.etiquetteRecherche = new JLabel(("Recherche : "));
        this.filtreRecherche = new JTextField(50);
        this.filtreRecherche.addKeyListener(new MyListener(this));
        this.listeLivres = new JList<>();
        this.scrollPane = new JScrollPane(this.listeLivres);
        etiquetteRecherche.setForeground(COLORTEXT);
        etiquetteRecherche.setFont(Font.getFont("Papyrus"));
        listeLivres.setFont(Font.getFont("Papyrus"));
        getLivre("");
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
