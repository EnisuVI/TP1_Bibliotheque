package vue;

import requete.RequeteBiblio;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class FenetreBiblio extends JFrame {
    private PanneauLivres panneauLivres;
    private PanneauEmprunt panneauEmprunt;
    private RequeteBiblio requete;
    public static final Color COLORFOND = new Color(30, 30, 42);

     public FenetreBiblio() {
         super();
         initConnexionBDD();
         initFenetre();
         initComponents();
         initLayout();
         this.setVisible(true);
         this.setBackground(COLORFOND);
     }

     private void initConnexionBDD(){
         try{
             this.requete = new RequeteBiblio();
         } catch(SQLException ex){
             JOptionPane.showMessageDialog(
                     this,
                     "message",
                     "titre",
                     JOptionPane.ERROR_MESSAGE
             );
             this.dispose();
             System.exit(1);
         }
     }
    private void initFenetre() {
        this.setSize(1000, 800);
        this.setTitle("Bibliothèque");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initComponents(){
         this.panneauLivres = new PanneauLivres(requete);
         this.panneauEmprunt = new PanneauEmprunt(requete);
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
