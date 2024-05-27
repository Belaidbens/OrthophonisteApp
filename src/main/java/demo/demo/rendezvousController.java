package demo.demo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.javafx.iio.ImageLoader;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.models.*;

import javax.imageio.IIOParam;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class rendezvousController {
    private int i;
    @FXML
    private ComboBox<String> myComboboxType;
    private Orthophoniste ortho;

    @FXML
    private TextField hd;
    @FXML
    private TextField md;
    @FXML
    private TextField hf;
    @FXML
    private TextField mf;
    @FXML
    private DatePicker dt;


    @FXML
    private void initialize() {
        // Ajoutez les éléments à la ComboBox
        myComboboxType.getItems().addAll("Consultation Adulte", "Consultation Enfant", "Seance De Suivi", "Atelier De Groupe");
    }

    private void szwitchscene(int i) {
        switch (i) {
            case 0:


        }

    }

    @FXML
    private void GoToChoixType(ActionEvent event) throws IOException {
        String choix = myComboboxType.getValue();
        System.out.println(choix);

        if (choix != null) {
            FXMLLoader loader = new FXMLLoader();
            Parent root = null;

            // Determine the FXML file and the corresponding controller based on the choice
            switch (choix) {
                case "Consultation Enfant":
                    loader = new FXMLLoader(getClass().getResource("consultationEnfant.fxml"));
                    root = loader.load();
                    consultationEnfantController childController = loader.getController();
                    childController.setOrtho(ortho);
                    childController.setHd(hd.getText());
                    childController.setHf(hf.getText());
                    childController.setMd(md.getText());
                    childController.setMf(mf.getText());
                    childController.setDt(dt.getValue());
                    break;
                case "Consultation Adulte":
                    loader = new FXMLLoader(getClass().getResource("consultationAdulte.fxml"));
                    root = loader.load();
                    consultationAdulteController adultController = loader.getController();
                    adultController.setOrtho(ortho);
                    adultController.setHd(hd.getText());
                    adultController.setHf(hf.getText());
                    adultController.setMd(md.getText());
                    adultController.setMf(mf.getText());
                    adultController.setDt(dt.getValue());
                    break;
                case "Seance De Suivi":
                    loader = new FXMLLoader(getClass().getResource("suivi.fxml"));
                    root = loader.load();
                    suiviController suiviController = loader.getController();
                    suiviController.setOrtho(ortho);
                    break;

                case "Atelier De Groupe":
                    loader = new FXMLLoader(getClass().getResource("atelier.fxml"));
                    root = loader.load();
                    atelierController atelierController = loader.getController();
                    atelierController.setOrtho(ortho);
                    break;



                default:
                    // Handle unexpected values, if any
                    System.err.println("Unexpected value: " + choix);
                    return; // Exit the method since we have an unexpected value
            }

            // Set the new scene and show it
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    public void setOrtho(Orthophoniste ortho) {
        this.ortho=ortho;
    }
    Gson gson;
    private ArrayList<Orthophoniste> orthos=new ArrayList<Orthophoniste>();
    private void loadUsers() {
        try (FileReader reader = new FileReader("C:\\Users\\hp\\OneDrive\\Documents\\demo\\src\\main\\java\\demo\\demo\\people.json")) {
            java.lang.reflect.Type userListType = new TypeToken<List<Orthophoniste>>() {}.getType();

            orthos = gson.fromJson(reader,userListType );
            //orthos.add(ortho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void HandleDisConnect(ActionEvent event) {
        int i = -1; // Initialize index to -1
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer());
        gson = gsonBuilder.create();
        loadUsers();

        // Find the index of the current ortho in the list
        for (Orthophoniste orthoo : orthos) {
            if (orthoo.getMotdepasse().equals(ortho.getMotdepasse()) && orthoo.getMail().equals(ortho.getMail())) {
                i = orthos.indexOf(orthoo);
                break;
            }
        }

        // If ortho is found, remove it before adding the new one
        if (i != -1) {
            orthos.remove(i);
        }

        // Add the disconnected ortho at the beginning of the list
        orthos.add(0, ortho);

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
}
