package demo.demo;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.models.*;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.FileWriter;
import java.io.IOException;
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

public class loginController {
        @FXML
        private Label errorLabel;
        @FXML
        private TextField usernameField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private Button loginButton;
        @FXML
        private TextField motdepasseField;
        @FXML
        private TextField emailField;
        private ArrayList<Orthophoniste> orthos=new ArrayList<>();
        private  Orthophoniste ortho;
       private Gson gson;
        @FXML
        private void handleLogin(ActionEvent event) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
                gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
                gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
                gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer());
                gson = gsonBuilder.create();
                // Récupérer les données des champs d'inscription
                String motdepasse = motdepasseField.getText();
                String email = emailField.getText();
                loadUsers();

                if (isUserValid(email, motdepasse)) {
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
                } else {
                        errorLabel.setText("Invalid username or password");
                        errorLabel.setVisible(true);
                }



        }
                private void loadUsers() {
                        try (FileReader reader = new FileReader("C:\\Users\\hp\\OneDrive\\Documents\\demo\\src\\main\\java\\demo\\demo\\people.json")) {
                                Type userListType = new TypeToken<List<Orthophoniste>>() {}.getType();

                                 orthos = gson.fromJson(reader,userListType );
                               //orthos.add(ortho);

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

        private boolean isUserValid(String email, String motdepasse) {

                for (Orthophoniste user : orthos) {

                        if (user.getMail().equals(email) && user.getMotdepasse().equals(motdepasse)) {
                                this.ortho=user;
                                return true;
                        }
                }

                return false;
        }


}
