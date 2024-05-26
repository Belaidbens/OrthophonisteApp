package models.models;

public class Qst_adt extends Qst_anam {

    private CategAdulte cat;

    Qst_adt(String input,CategAdulte cat) {
        super(input);
        this.cat =cat;
    }
    public String toString(){
        return cat.toString();
    }


}
