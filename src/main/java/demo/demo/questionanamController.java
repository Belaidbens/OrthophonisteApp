package demo.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import models.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class questionanamController implements Initializable {

    @FXML
    private Label succes;
    @FXML
    private ComboBox<String> myType;
    @FXML
    private ComboBox<String> myCategorie;
    @FXML
    private TextField enonce;
  private ArrayList<Orthophoniste> orthos=new ArrayList<Orthophoniste>();

    private final String[] CategorieTab1 = {
            "structure familiale", "dynamique familiale", "antécédents familiaux",
            "conditions natales", "developpement psychomoteur", "developement langagier",
            "caractères & comportements"
    };
    private final String[] CategorieTab2={
            "histoire de sa maladie", "suivi medical"
    };
    private final String[] TypeTab = {"Adulte", "Enfant"};

    @FXML
   /* private void initialize() {
        // Add elements to the ComboBox myType
        myType.getItems().addAll(TypeTab);

        // Add event listener to the ComboBox myType
        myType.setOnAction(event -> updateCategorie());

        // Initialize the elements of myCategorie based on the initial value of myType (if available)
        if (myType.getValue() != null) {
            updateCategorie();
        }
    }*/

    private void updateCategorie() {
        // Clear the current items of myCategorie
        myCategorie.getItems().clear();

        // Add appropriate items to myCategorie based on the value of myType
        if ("Adulte".equals(myType.getValue())) {
            myCategorie.getItems().addAll("histoire de sa maladie", "suivi medical");
        } else if ("Enfant".equals(myType.getValue())) {
            myCategorie.getItems().addAll(CategorieTab1);
        }
    }

    private Orthophoniste ortho;

    public void setOrtho(Orthophoniste ortho) {
        this.ortho = ortho;
    }

    @FXML
    public void handleAjouterQuestAnam(ActionEvent event) {
        int i;
        String categorie = myCategorie.getValue();
        int j = Arrays.asList(TypeTab).indexOf(myType.getValue());
        System.out.println(j);
        if (j < 0) {
            System.err.println("Type not found in TypeTab");
            succes.setText("Erreur: Type non trouvé.");
            succes.setStyle("-fx-text-fill: #FF0000;"); // Red color for error
            succes.setVisible(true); // Make the label visible
            return;
        }
        Type typee = Type.values()[j];
        if(j==0){
            i = Arrays.asList(CategorieTab2).indexOf(categorie);

        }else{
            i = Arrays.asList(CategorieTab1).indexOf(categorie);
        }
        System.out.println(i);
        if (i < 0) {
            System.err.println("Categorie not found in CategorieTab");
            succes.setText("Erreur: Catégorie non trouvée.");
            succes.setStyle("-fx-text-fill: #FF0000;"); // Red color for error
            succes.setVisible(true); // Make the label visible
            return;
        }
        succes.setText("Question ajoutée avec succès.");
        succes.setStyle("-fx-text-fill: #008000;"); // Green color for success
        succes.setVisible(true);
        ortho.AddQstToHoldAnam(enonce.getText(), typee, i);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add elements to the ComboBox myType
        myType.getItems().addAll(TypeTab);
        succes.setVisible(false);
        // Add event listener to the ComboBox myType
        myType.setOnAction(event -> updateCategorie());

        // Initialize the elements of myCategorie based on the initial value of myType (if available)
        if (myType.getValue() != null) {
            updateCategorie();
        }
    }
    public void HandleTest(ActionEvent event){
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anamtest.fxml"));
            Parent root = loader.load();
            anamtestController anamtest=loader.getController();//pour pouvoir faire passer un objet de inscrire a la scene dashboard
            anamtest.setOrtho(ortho);
            // Get the current stage
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
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
        gsonBuilder.registerTypeAdapter(LocalDate .class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalTime .class, new LocalTimeSerializer());
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
            //anamtestController test=loader.getController();
            // test.setOrtho(ortho);


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
}
