
package packModele;

public class Etudiant {
    private String numero; 
    private String prenom; 
    private String nom; 
    private String bac; 
    private String departement; 
    
    public Etudiant(String numero, String prenom, String nom, String bac, String departement){
        this.numero = numero; 
        this.prenom = prenom; 
        this.nom = nom; 
        this.bac = bac; 
        this.departement = departement; 
    }
    
    public String getNumero(){
        return this.numero; 
    }
    public String getPrenom(){
        return this.prenom; 
    }
    public String getNom(){
        return this.nom; 
    }
    public String getBac(){
        return this.bac; 
    }
    public String getDepartement(){
        return this.departement; 
    }
    
    public void setNumero(String numero){
        this.numero = numero; 
    }
    public void setPrenom(String prenom){
        this.prenom = prenom; 
    }
    public void setNom(String nom){
        this.nom = nom; 
    }
    public void setBac(String bac){
        this.bac = bac; 
    }
    public void setDepartement(String dept){
        this.departement = dept; 
    }
}
