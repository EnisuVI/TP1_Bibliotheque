package vue;

import javax.swing.*;
import java.awt.*;

public class FenetreBiblio extends JFrame {
    private PanneauLivres panneauLivres;
    private PanneauEmprunt panneauEmprunt;
    public static final Color COLORFOND = new Color(30, 30, 42);

     public FenetreBiblio() {
         super();
         initFenetre();
         initComponents();
         initLayout();
         this.setVisible(true);
         this.setBackground(COLORFOND);
     }

    private void initFenetre() {
        this.setSize(1000, 800);
        this.setTitle("Bibliothèque");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initComponents(){
         this.panneauLivres = new PanneauLivres();
         this.panneauEmprunt = new PanneauEmprunt();
    }

    private void initLayout(){
         GridLayout layout = new GridLayout(2,1);
         this.setLayout(layout);
         this.add(panneauLivres);
         this.add(panneauEmprunt);
    }

    public static void main(String[] args) {
        FenetreBiblio fenetreBiblio = new FenetreBiblio();
    }
}
