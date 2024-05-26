package models.models;

public class Objective {
    private String nom;
    private EnumTerm term;
    private boolean chekced;;
    public String getNom() {
        return nom;
    }

    public EnumTerm getTerm() {
        return term;
    }

    public Objective(String nom, EnumTerm term) {
        this.nom = nom;
        this.term = term;
    }
    public void checkobj() {
        chekced = true;
    }
    public void uncheck(){
        chekced = false;
    }

    public String toString() {
        return "Objectif : "+nom + " Terme : "+term.toString() + " checked "+ chekced;
    }
}
