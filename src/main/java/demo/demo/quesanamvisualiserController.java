package demo.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.models.Orthophoniste;
import models.models.Qst_adt;
import models.models.Qst_anam;
import models.models.Qst_enft;

import java.util.ArrayList;

public class quesanamvisualiserController {
    @FXML
    private ListView<Qst_anam> mylist;
   Orthophoniste ortho;
    public void setOrtho(Orthophoniste ortho) {
        this.ortho = ortho;
    }
 public void HandleEnfant(ActionEvent event){
    ArrayList<Qst_enft> list=ortho.getHolderofanams().getQsts2();
     mylist.getItems().setAll(list);
 }
 public void HandleAdulte(ActionEvent event){
     ArrayList<Qst_adt> list=ortho.getHolderofanams().getQsts1();
     mylist.getItems().setAll(list);
 }



}
