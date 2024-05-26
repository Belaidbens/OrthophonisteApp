package models.models;

import java.util.ArrayList;

public class Exercice {
    private String consigne;
    private String material;

    public String getConsigne() {
        return consigne;
    }

    public String getMaterials() {
        return material;
    }

    public Exercice(String consigne, String materials) {
        this.consigne = consigne;
        this.material = materials;
    }
}
