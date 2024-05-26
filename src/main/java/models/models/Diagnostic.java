package models.models;

import java.util.ArrayList;

public class Diagnostic {
    private String nom;
    private ArrayList<Trouble> troubles;

    public Diagnostic(ArrayList<Trouble> troubles) {
        this.troubles = troubles;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Trouble> getTrouble() {
        return troubles;
    }
}
