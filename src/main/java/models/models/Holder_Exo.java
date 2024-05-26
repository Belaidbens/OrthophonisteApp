package models.models;

import java.util.ArrayList;

public class Holder_Exo {

    private ArrayList<Exercice> qsts = new ArrayList<>();
    private int nb_ele;
    public void add_exo(Exercice qst) {
        qsts.add(qst);
        nb_ele++;
    }
    public void afficher_exos() {
        for (Exercice qst : qsts) {
            System.out.println(qst);
        }
    }
    public int taille() {
        return nb_ele;
    }
    public Exercice QstAtindex (int i) {
        if(i>=0 && i<nb_ele) {

            return qsts.get(i);
        }
        else {
            return null;
        }
    }

}
