package models.models;

import java.util.ArrayList;

public class Holder_Qst {

    private ArrayList<Question> qsts = new ArrayList<>();


    private int nb_ele;

    public void add_qst(Question qst) {
        qsts.add(qst);
        nb_ele++;


    }
    public void afficher_qsts() {

        for (Question qst : qsts) {
            System.out.println(qst);
        }


    }
    public int taille() {
        return nb_ele;
    }
    public Question QstAtindex (int i) {

        if(i>=0 && i<nb_ele) {
            return qsts.get(i);
        }
        else {
            return null;
        }

    }


}
