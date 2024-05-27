package models.models;

import java.util.ArrayList;

public class Holder_anam {
    private ArrayList<Qst_adt> qsts1 = new ArrayList<>();
    private ArrayList<Qst_enft> qsts2 = new ArrayList<>();
    private int nb_ele1;
    private int nb_ele2;
    public ArrayList<Qst_adt> getQsts1(){
        return qsts1;
    }
    public ArrayList<Qst_enft> getQsts2() {
        return qsts2;
    }

    public void add_qst(Qst_anam qst, Type type) {
        switch(type) {
            case ADULTE:
                qsts1.add((Qst_adt) qst);
                nb_ele1++;
                break; // Break to prevent fall-through
            case ENFANT:
                qsts2.add((Qst_enft) qst);
                nb_ele2++;
                break; // Break to prevent fall-through
            default:
                // Optionally, handle other types or add a default case
                break; // Break is optional for the default case but good practice
        }

    }
    public void afficher_qsts(Type type) {
        switch(type) {
            case ADULTE:
                for (Qst_anam qst : qsts1) {
                    System.out.println(qst);
                }
                break; // Add break to prevent fall-through
            case ENFANT:
                for (Qst_anam qst : qsts2) {
                    System.out.println(qst);
                }
                break; // Add break to prevent fall-through
            default:
                // Optionally, handle other types or add a default case
                break; // Break is optional for the default case but good practice
        }



    }
    public int taille(Type type) {
        switch(type) {
            case ADULTE:
                return nb_ele1;
            case ENFANT:
                return nb_ele2;
            default:
                return -1;
        }
    }
    public Qst_anam QstAtindex (int i,Type type) {
        switch(type) {
            case ADULTE:
                if (i >= 0 && i < nb_ele1) {
                    return qsts1.get(i);
                }
                break;
            case ENFANT:
                if (i >= 0 && i < nb_ele2) {
                    return qsts2.get(i);
                }
                break;
            default:
                return null; // Handle other types if necessary
        }
        return null;
    }
}
