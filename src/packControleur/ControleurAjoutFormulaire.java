
package packControleur;

import java.util.ArrayList;
import javax.swing.JOptionPane;
/*import static jdk.internal.joptsimple.internal.Messages.message;*/
import packModele.Etudiant;
import packModele.Promotion;
import packVue.MainWindow;

public class ControleurAjoutFormulaire extends AbstractControlleur {

    public ControleurAjoutFormulaire(){

    }
    @Override
    public void control(ArrayList<String> numero) {
        boolean op = true; 
        for(String info : numero){
            if(info.equals("") || info.equals(" ") || info.equals("") || info == null){
                op = false; 
            }
        }
        if(op){
            Promotion.ajout(new Etudiant(numero.get(0), numero.get(1), numero.get(2), numero.get(3), numero.get(4)));
            MainWindow.getPromotion().notifyObservateur();
        }else{
            JOptionPane.showMessageDialog(null, "Informations invalides", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }
}
