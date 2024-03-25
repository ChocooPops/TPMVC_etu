package packControleur;

import java.util.ArrayList;
import packModele.Promotion;
import packVue.MainWindow;

public class ControleurSuppressionListe extends AbstractControlleur {
    
    @Override
    public void control(ArrayList<String> numero) {
        if(numero != null){
             for(String num : numero){
                 Promotion.suppr(num);
             }
            MainWindow.getPromotion().notifyObservateur();
            System.out.println("Les eleves : " + numero + "ont ete supprime");
        }
    }
}
