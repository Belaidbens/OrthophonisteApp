package models.models;

import java.util.ArrayList;

public class Epreuve {
    private String observation;
    private ArrayList<Test> tests;
    private  ArrayList<CompteRendu> compteRendus;

    public String getObservation() {
        return observation;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public ArrayList<CompteRendu> getCompteRendus() {
        return compteRendus;
    }

    public Epreuve( ArrayList<Test> tests, ArrayList<CompteRendu> compteRendus) {
        this.tests = tests;
        this.compteRendus = compteRendus;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
