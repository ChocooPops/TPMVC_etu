package packVue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import packControleur.ControleurSuppressionListe;
import packModele.Promotion; 

public class VueListe extends AbstractVue implements Observer{

    private JList liste;
    private JButton btSuppr = new JButton("Supprimer");
    private String [] listEcrivains;  
    private VueFormulaire formulaire; 
    public VueListe(VueFormulaire formulaire) {
        this.formulaire = formulaire; 
        liste = new JList();
        liste.setLayoutOrientation(JList.VERTICAL);
        liste.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   //     liste.setVisibleRowCount(getHeight());
        JScrollPane scrollPane = new JScrollPane(liste);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(scrollPane, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(btSuppr, gc);
       // liste.setPreferredSize(new Dimension (150,500));
        remplissageListe();
        this.pack();
        liste.setVisibleRowCount(this.getHeight()/8);
        new EcouteurSuppr(); 
        new EcouteurClick(); 
        this.pack();
        this.formulaire.addVueListe(this);
    }

    private void remplissageListe() {
        this.listEcrivains = new String[Promotion.getTaille()];
        Promotion.remplirTableau(this.listEcrivains);
        liste.removeAll();
        liste.setListData(listEcrivains);
//        this.pack();
    }

    @Override
    public void update(Observable o, Object o1) {
       remplissageListe(); 
    }
    
    public String getEtudModif(){
        List<String> etu = liste.getSelectedValuesList();
        String[] c = etu.get(0).split(" "); 
        return c[0]; 
    }
    public class EcouteurSuppr{
        public EcouteurSuppr(){
            btSuppr.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<String> etu = liste.getSelectedValuesList(); 
                    ArrayList<String> info = new ArrayList<>(); 
                    for(int i=0; i<etu.size(); i++){
                        String[] c = etu.get(i).split(" "); 
                        info.add(c[0]); 
                    }
                    new ControleurSuppressionListe().control(info); 
                }
            });
        }
    }
    
    public class EcouteurClick{
        public EcouteurClick(){
            liste.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        formulaire.changeBtModif();
                    }else if(evt.getClickCount() == 1){
                        formulaire.changeBtAjout();
                    }
                } 
        }); 
    }
    }
}
