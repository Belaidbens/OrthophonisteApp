package models.models;

import java.util.ArrayList;
import java.util.*;

public class FicheSuivi {
    private TreeSet<Objective> objectives;

    public TreeSet<Objective> getObjectives() {
        return objectives;
    }

    public ArrayList[] getNotes() {
        return notes;
    }

    private ArrayList[] notes;

    public FicheSuivi(TreeSet<Objective> objectives, ArrayList[] notes) {
        this.objectives = objectives;
        this.notes = notes;
    }

    public void setObjs( TreeSet<Objective> objs) {
        this.objectives.addAll(objs);  //adds the objectives to the "Fichier de suivi"
    }


}
