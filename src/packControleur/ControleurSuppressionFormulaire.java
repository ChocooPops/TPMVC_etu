package packControleur;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import packModele.Promotion;
import packVue.MainWindow;

public class ControleurSuppressionFormulaire extends AbstractControlleur {

    @Override
    public void control(ArrayList<String> numero) {
        if(numero.get(0) != null && !numero.get(0).equals("") && !numero.get(0).equals(" ")){
            Promotion.suppr(numero.get(0));
            MainWindow.getPromotion().notifyObservateur();
        }else{
            JOptionPane.showMessageDialog(null, "Le champ est vide", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }
}