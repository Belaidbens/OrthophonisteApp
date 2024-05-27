package demo.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.models.Adulte;
import models.models.Consultation;
import models.models.Enfant;
import models.models.Orthophoniste;

import java.time.LocalDate;
import java.time.LocalTime;

public class consultationAdulteController {
    Orthophoniste ortho;

    public void setOrtho(Orthophoniste ortho) {
        this.ortho = ortho;
    }

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField tel;
    @FXML
    private TextField lN;
    @FXML
    private TextField adr;
    @FXML
    private TextField prof;
    @FXML
    private TextField dip;
    @FXML
    private DatePicker dN;

    private String hd;

    private String md;

    private  String hf;

    private  String mf;

    private LocalDate dt;

    public void setDt(LocalDate dt) {
        this.dt = dt;
    }





    public void setHd(String hd) {
        this.hd = hd;
    }

    // Setter for md
    public void setMd(String md) {
        this.md = md;
    }

    // Setter for hf
    public void setHf(String hf) {
        this.hf = hf;
    }

    // Setter for mf
    public void setMf(String mf) {
        this.mf = mf;
    }

    public LocalTime getTimeFromHdMd(String hd , String md) {
        if (hd != null && md != null) {
            try {
                int hours = Integer.parseInt(hd);
                int minutes = Integer.parseInt(md);
                return LocalTime.of(hours, minutes);
            } catch (NumberFormatException e) {
                System.err.println("Invalid time format: " + e.getMessage());
            }
        }
        return null; // Return null or handle the case where hd or md is not set or invalid
    }

    public void handlcreatpat(ActionEvent event){

    Adulte adulte = new Adulte(surname.getText(),name.getText(),dN.getValue(),adr.getText(), tel.getText(), null,lN.getText(),dip.getText(),prof.getText());
    ortho.creer_Consultation(adulte,dt,getTimeFromHdMd(hd,md),getTimeFromHdMd(hf,mf),ortho.getAnamneses().get(0));




}










}
