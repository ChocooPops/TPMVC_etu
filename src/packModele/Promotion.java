package packModele;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.jfree.data.general.DefaultPieDataset;
import packVue.AbstractVue;
import packVue.VueCamembertChart;
import packVue.VueListe;

public class Promotion extends Observable {
    private static ArrayList<Etudiant> promotion = new ArrayList<>(); 

    private static boolean EtudiantExistant(String numero){
        boolean op = false; 
        for(Etudiant e : promotion){
            if(e.getNumero().equals(numero)){
                op = true; 
            }
        }
        return op; 
    }
   
    public static void setPromotion(){
        try{
             FileInputStream file = new FileInputStream("src/data/promoBUT.csv"); 
             Scanner scanner= new Scanner(file); 
             while(scanner.hasNextLine()){
                 String[] etudiant = scanner.nextLine().split(";"); 
                 Promotion.ajout(new Etudiant(etudiant[0],etudiant[1], etudiant[2], etudiant[4], etudiant[3]));
             }
        }catch(IOException e){
             e.printStackTrace();
         }   
    }
    public static void Afficher(){
        String s = " "; 
        for(Etudiant e : promotion){
            s = s + e.getNumero() + " : "  + e.getNom() + " " + e.getPrenom() + " " +  e.getDepartement() + " " +  e.getBac(); 
            s = s + '\n'; 
        }
        System.out.println(s);
    }
    public static void ajout(Etudiant etudiant){
       if(!EtudiantExistant(etudiant.getNumero())){
           Promotion.promotion.add(etudiant);
       }else{
           JOptionPane.showMessageDialog(null, "Numero de l'élève déjà existant", "Avertissement", JOptionPane.WARNING_MESSAGE);
       }
    }
   
    public static void suppr(String num){
        if(EtudiantExistant(num)){
           Etudiant etu = null; 
           for(Etudiant e : promotion){
               if(e.getNumero().equals(num)){
                   etu = e; 
               }
           }
           Promotion.promotion.remove(etu);
       }else{
            JOptionPane.showMessageDialog(null, "Numero de l'élève inexistant", "Avertissement", JOptionPane.WARNING_MESSAGE);
       }
    }
   
    public static Etudiant getEtudiantById(int i){
        return Promotion.promotion.get(i); 
    }
    public static ArrayList<Etudiant> getPromotion(){
       return Promotion.promotion; 
    }
    public static int getTaille(){
        return Promotion.promotion.size(); 
    }
    
    
        //Methode pour l'HISTOGRAMME; 
    public static double[][] setDataHistogramme(){
      double[][] data = new double[][]{{Promotion.getBacG(), Promotion.getBacT(), Promotion.getBacA(), Promotion.getBacPro()}, {}};  
      return data; 
    } 
    
    private static int getBacG(){
        int nbG = 0; 
        for(Etudiant e : promotion){
            if(e.getBac().equals("G")){
                nbG ++; 
            }
        }
        return nbG; 
    }
    private static int getBacT(){
        int nbT = 0; 
        for(Etudiant e : promotion){
            if(e.getBac().equals("T")){
                nbT ++; 
            }
        }
        return nbT; 
    }
    private static int getBacA(){
        int nbA = 0; 
        for(Etudiant e : promotion){
            if(e.getBac().equals("A")){
                nbA ++; 
            }
        }
        return nbA; 
    }
    private static int getBacPro(){
        int nbPro = 0; 
        for(Etudiant e : promotion){
            if(e.getBac().equals("Pro")){
                nbPro ++; 
            }
        }
        return nbPro; 
    }
    
        //Methode pour le Camembert; 
    public static void setResult(DefaultPieDataset result){
        for(int i=0; i<Promotion.getNbDepartement(); i++){
            result.setValue(Promotion.getDepartementById(i),Promotion.getNbEtudiantByDepartement(i));
        }
    }
    private static ArrayList<String> getListeDepartement(){
        ArrayList<String> nomDept = new ArrayList<>();
        for(Etudiant e : promotion){
            boolean op = true; 
            for(String dept : nomDept){
                if(e.getDepartement().equals(dept)){
                    op = false; 
                }
            }
            if(op){
                nomDept.add(e.getDepartement()); 
            }
        }
        return nomDept; 
    }
    private static int getNbDepartement(){
        return Promotion.getListeDepartement().size(); 
    }
    private static String getDepartementById(int i){
        return Promotion.getListeDepartement().get(i); 
    }
    private static int getNbEtudiantByDepartement(int i){
        int nbEtudiant = 0; 
        String departement = Promotion.getListeDepartement().get(i); 
        for(Etudiant e : promotion){
            if(e.getDepartement().equals(departement)){
                 nbEtudiant ++; 
            }
        }
        return nbEtudiant; 
    }
    
        //Methode pour la liste d'élève; 
    public static void remplirTableau(String [] tab){
        for(int i = 0; i<Promotion.promotion.size(); i++){
            tab[i] = Promotion.promotion.get(i).getNumero() + " - " + Promotion.promotion.get(i).getPrenom() + " " + Promotion.promotion.get(i).getNom() + " (" + Promotion.promotion.get(i).getDepartement() + ")"; 
        }
    }
    
    public void addObvservateur(Observer obs){
        this.addObserver(obs);
    }
    public void removeObservateur(Observer obs){
        this.removeObservateur(obs);
    }
    public void notifyObservateur(){ 
        setChanged(); 
        this.notifyObservers();
    }
    
    
    public static void mdofif(String n, String num, String nom, String prenom, String bac, String dept){
         for(Etudiant e : promotion){
             if(e.getNumero().equals(n)){
                 e.setPrenom(prenom);
                 e.setNom(nom);
                 e.setBac(bac);
                 e.setNumero(num);
                 e.setDepartement(dept);
             }
             break; 
         }
    }
}
