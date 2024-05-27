package demo.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static models.models.Type.ADULTE;
import static models.models.Type.ENFANT;

public class anamneseCreateController implements Initializable {

    @FXML
    private Label succes;
    @FXML
    private ComboBox<String> myType;
    @FXML
    private ListView<Qst_anam> mylist;
    @FXML
    private TextField enonce;
    private ArrayList<Orthophoniste> orthos=new ArrayList<Orthophoniste>();
    Orthophoniste ortho;
    private ArrayList<Qst_anam> quest=new ArrayList<Qst_anam>();
    public void setOrtho(Orthophoniste ortho) {
        this.ortho = ortho;
    }
    Type type=null;
   private ArrayList<Qst_anam> ques_choisi;

    public void HandleTest(ActionEvent event){
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anamtest.fxml"));
            Parent root = loader.load();
            anamtestController anamtest=loader.getController();//pour pouvoir faire passer un objet de inscrire a la scene dashboard
            anamtest.setOrtho(ortho);
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void HandleDisConnect(ActionEvent event){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer());
        Gson gson = gsonBuilder.create();
        int i = 0;
        for(Orthophoniste orthoo : orthos){
            if (orthoo.getMotdepasse()==ortho.getMotdepasse() && orthoo.getMail()==ortho.getMail()){
                i=orthos.indexOf(orthoo);
                break;
            }
        }
        orthos.add(i,ortho);
        try (FileWriter writer = new FileWriter("C:\\Users\\hp\\OneDrive\\Documents\\demo\\src\\main\\java\\demo\\demo\\people.json")) {

            gson.toJson(orthos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void HandleAnamAdulte(ActionEvent event){
        ques_choisi.clear();
        ArrayList<Qst_anam> list1 = new ArrayList<Qst_anam>(ortho.getHolderofanams().getQsts1());
        mylist.getItems().setAll(list1);
        System.out.println(list1);
        type=ADULTE;
    }
    public void HandleAnamEnfant(ActionEvent event){
        ques_choisi.clear();
        ArrayList<Qst_anam> list2 = new ArrayList<Qst_anam>(ortho.getHolderofanams().getQsts2());
        mylist.getItems().setAll(list2);
        type=ENFANT;

    }
    public void handleGetSelectedItems(ActionEvent event) {
        ObservableList<Qst_anam> selectedItems = mylist.getSelectionModel().getSelectedItems();


        if (type != null) {


            if (ortho.create_Amn(type, ques_choisi) != null) {
                succes.setText("Anamnèse ajoutée avec succès.");
                succes.setStyle("-fx-text-fill: #008000;"); // Green color for success
                succes.setVisible(true);
            } else {
                succes.setText("Erreur: Anamnese vide pas de questions");
                succes.setStyle("-fx-text-fill: #FF0000;"); // Red color for error
                succes.setVisible(true);

            }
        }
        ques_choisi.clear();

    }
public void ajouterques_choisi(ActionEvent event){
        for( Qst_anam ques : ques_choisi){
            System.out.println(ques.toString());
        }
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ques_choisi=new ArrayList<Qst_anam>();
        succes.setVisible(false);
        mylist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Qst_anam>() {
            @Override
            public void changed(ObservableValue<? extends Qst_anam> observableValue, Qst_anam qstAnam, Qst_anam t1) {

                ques_choisi.add(mylist.getSelectionModel().getSelectedItem());
                System.out.println(mylist.getSelectionModel().getSelectedItem().toString());
            }
        });
    }
}
