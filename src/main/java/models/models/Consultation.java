package models.models;

import java.time.*;

import java.util.*;

public class Consultation extends Rendezvous {
   private  String nom;
    private String prenom;
    private int age;
    private String name;
    private Anamnese amnese;
    public Consultation(LocalTime heuredebut, LocalTime heurefin,String nom,String prenom,int age,Anamnese amnese){
        super(heuredebut,heurefin);
        this.amnese=amnese;
    }
    public LocalTime getHeuredebut(){
        return heuredebut;
    }
    public LocalTime getHeurefin(){
        return heurefin;
    }
    public void modifier(LocalTime heuredebut, LocalTime heurefin) {
        this.heuredebut= heuredebut;
        // this.type = type; pour consultation
        this.heurefin = heurefin;
    }
    public String consulterDetails() {
        return " Heure de Debut: " + heuredebut+ ", Heure de Fin: " + heurefin + ", Observation: " + observation + ", Patient:" + patients;
        //", Durée: " + duree + ", Patient: " + patient.getNom() +
    }
    public void ajouterObservation(String observation) {
        this.observation = observation;
    }
    public String getObservation() {
        return observation;
    }

}
