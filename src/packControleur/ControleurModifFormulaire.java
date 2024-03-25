
package packControleur;

import java.util.ArrayList;
import packModele.Promotion;
import packVue.MainWindow;


public class ControleurModifFormulaire extends AbstractControlleur{

    @Override
    public void control(ArrayList<String> i) {
        Promotion.mdofif(i.get(0), i.get(1), i.get(2), i.get(3), i.get(4), i.get(5));
        MainWindow.getPromotion().notifyObservateur();
    }
    
}
