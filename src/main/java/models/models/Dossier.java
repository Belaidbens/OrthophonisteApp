package models.models;

import java.util.ArrayList;

public class Dossier {
    private int numero;
    private static int index = 0;
    private ArrayList<Rendezvous> rendezvous = new ArrayList<>();
    private ArrayList<BilanOrthophonique> bilanOrthophoniques = new ArrayList<>();
    private ArrayList<FicheSuivi> ficheSuivis = new ArrayList<>();

    public Dossier(Rendezvous rdv) {
        rendezvous.add(rdv);
        this.numero = index++;
    }

    public void addRendezVous(Rendezvous rendezvous) {
        this.rendezvous.add(rendezvous);
    }
    public void addBilan(BilanOrthophonique bilan) {
        this.bilanOrthophoniques.add(bilan);
    }
    public void addFichSuivi(FicheSuivi fsv) {
        this.ficheSuivis.add(fsv);
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(){
        numero=index;
        Dossier.index++;
    }

    public ArrayList<Rendezvous> getRendezvous() {
        return rendezvous;
    }

    public ArrayList<BilanOrthophonique> getBilanOrthophoniques() {
        return bilanOrthophoniques;
    }

    public ArrayList<FicheSuivi> getFicheSuivis() {
        return ficheSuivis;
    }

    public void addFicheSuivi(FicheSuivi fiche) {
        this.ficheSuivis.add(fiche);
    }
}
