package demo.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import models.models.Orthophoniste;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.*;

public class listpatientController implements Initializable {

    @FXML
    private ListView<Orthophoniste> mylist;
    private Orthophoniste ortho;
    private  List<Orthophoniste> orthos=new ArrayList<Orthophoniste>() ;

    public void setOrtho(Orthophoniste ortho){
        this.ortho=ortho;
    }

    public void HandlePrisEnCharge(ActionEvent event){
        orthos.add(ortho);
       List<Orthophoniste> list=   orthos;
        ObservableList<Orthophoniste> observablePatients = FXCollections.observableArrayList(list);
        mylist.setItems(observablePatients);
    }
    public void HandleEnAttente(ActionEvent event){
        orthos.add(ortho);
        List<Orthophoniste> list=   orthos;
        ObservableList<Orthophoniste> observablePatients = FXCollections.observableArrayList(list);
        mylist.setItems(observablePatients);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*orthos.add(ortho);
        List<Orthophoniste> list=   orthos;
        ObservableList<Orthophoniste> observablePatients = FXCollections.observableArrayList(list);
        mylist.setItems(observablePatients);
       // mylist.getItems().addAll();*/
    }
}
