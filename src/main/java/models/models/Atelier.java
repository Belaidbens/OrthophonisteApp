package models.models;

import java.time.LocalTime;
import java.util.List;

public class Atelier extends Rendezvous{
    private String thematique ;
    private List<Patient> patients;
    public Atelier(LocalTime heuredebut, LocalTime heurefin, List<Patient> patients,String thematique){
        super(heuredebut,heurefin);
        this.thematique=thematique;
        this.patients=patients;
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

    public void ajouterPatient(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> getPatients() {
        return patients;
    }
    public String getObservation() {
        return observation;
    }
}
