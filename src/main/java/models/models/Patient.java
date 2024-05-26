package models.models;

import java.time.*;
import java.util.*;
public  abstract class Patient {
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String adresse;
    private String telephone;
    private String email;
    private String LieuDeNaissance;

    private Dossier doc;
    public Patient(String nom, String prenom, LocalDate dateDeNaissance, String adresse, String telephone, String email, String LieuDeNaissance) {

        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.LieuDeNaissance=LieuDeNaissance;
        }
        public Dossier getDoc(){
          return this.doc;
        }
    public void SetDoc(Dossier doc){
        this.doc=doc;
    }
        public String getNom(){
        return this.nom;
        }
        public String getPrenom(){
        return this.prenom;
       }
       public int getAge(){
        LocalDate today=LocalDate.now();
        Period period=Period.between(dateDeNaissance,today);
        return period.getYears();
       }

}
