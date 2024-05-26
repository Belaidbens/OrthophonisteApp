package demo.demo;

import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.models.*;

public class inscrireController {
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField motdepasseField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField emailField;
    private Gson gson;
    private ArrayList<Orthophoniste> orthos=new ArrayList<Orthophoniste>();
    Orthophoniste ortho;
   private GsonBuilder gsonBuilder;


    @FXML
    private void handleInscription(ActionEvent event) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer());
        gson = gsonBuilder.create();
        // Récupérer les données des champs d'inscription
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String motdepasse = motdepasseField.getText();
        String adresse = adresseField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();

        // Créer un objet Patient ou Orthophoniste (ou un autre modèle en fonction de votre application)
         ortho = new Orthophoniste(nom, prenom, adresse,email, telephone, motdepasse);
          loadUsers();
        orthos.add(ortho);
        // Sauvegarder les données dans un fichier JSON
        try (FileWriter writer = new FileWriter("C:\\Users\\hp\\OneDrive\\Documents\\demo\\src\\main\\java\\demo\\demo\\people.json")) {
            gson.toJson(orthos, writer);
            System.out.println("Inscription réussie pour : " + nom + " " + prenom);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            dashboardController dash=loader.getController();//pour pouvoir faire passer un objet de inscrire a la scene dashboard
            dash.setOrtho(ortho);
            // Get the current stage
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Afficher un message ou naviguer vers une autre page
    }
    private void loadUsers() {
        try (FileReader reader = new FileReader("C:\\Users\\hp\\OneDrive\\Documents\\demo\\src\\main\\java\\demo\\demo\\people.json")) {
            Type userListType = new TypeToken<List<Orthophoniste>>() {}.getType();

            orthos = gson.fromJson(reader, userListType);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}