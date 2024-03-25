package mvc;

import packModele.Promotion;
import packVue.MainWindow;

public class Mvc {


    public static void main(String[] args) {
        Promotion.setPromotion();
        Promotion.Afficher();  
        MainWindow fen=new MainWindow(); 
/*       try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(fen);
            updateComponentTreeUI(fen);
        } catch (Exception ex) {
                       System.out.println(ex.getMessage());
        } 
  */
        fen.setVisible(true);
    }
    
}
