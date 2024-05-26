package models.models;

import java.util.ArrayList;

public class BilanOrthophonique {
    private boolean premier;
    private ArrayList<Epreuve> epreuves;
    private Diagnostic diagnostic;
    private ProjectTherapeutique projectTherapeutique;

    public boolean isPremier() {
        return premier;
    }

    public ArrayList<Epreuve> getEpreuves() {
        return epreuves;
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public ProjectTherapeutique getProjectTherapeutique() {
        return projectTherapeutique;
    }

    public BilanOrthophonique(ArrayList<Epreuve> epreuves, Diagnostic diagnostic, ProjectTherapeutique projectTherapeutique) {
        this.epreuves = epreuves;
        this.diagnostic = diagnostic;
        this.projectTherapeutique = projectTherapeutique;
    }
}