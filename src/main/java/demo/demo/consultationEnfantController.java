package demo.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class consultationEnfantController {
    private Orthophoniste ortho;

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
    private String hf;
    private String mf;
    private LocalDate dt;

    // Setter for dt
    public void setDt(LocalDate dt) {
        this.dt = dt;
    }

    // Setter for hd
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

    // Method to retrieve and set the LocalDate from DatePicker dN
    public void initialize() {
        dN.valueProperty().addListener((observable, oldValue, newValue) -> setDt(newValue));
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
