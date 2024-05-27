package demo.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dashboardController {
    private Orthophoniste ortho;
    private ArrayList<Orthophoniste> orthos = new ArrayList<>();
    Gson gson;

    public void setOrtho(Orthophoniste ortho) {
        this.ortho = ortho;
    }

    public void HandleRdv(ActionEvent event) {
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Rendez-vous.fxml"));
            Parent root = loader.load();
            rendezvousController list = loader.getController();
            list.setOrtho(ortho);


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

    public void HandleList(ActionEvent event) {
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listpatient.fxml"));
            Parent root = loader.load();
            listpatientController list = loader.getController();
            list.setOrtho(ortho);


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

    public void HandleTest(ActionEvent event) {
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anamtest.fxml"));
            Parent root = loader.load();
            anamtestController test = loader.getController();
            test.setOrtho(ortho);


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

    private void loadUsers() {
        try (FileReader reader = new FileReader("C:\\Users\\hp\\OneDrive\\Documents\\demo\\src\\main\\java\\demo\\demo\\people.json")) {
            Type userListType = new TypeToken<List<Orthophoniste>>() {
            }.getType();
            orthos = gson.fromJson(reader, userListType);
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


