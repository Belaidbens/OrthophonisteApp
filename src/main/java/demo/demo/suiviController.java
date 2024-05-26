package demo.demo;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;

public class suiviController {

    @FXML
    private ComboBox<String> myType;

    // Méthode d'initialisation appelée après que le fichier FXML a été chargé
    @FXML
    private void initialize() {
        // Ajoutez les éléments à la ComboBox
        myType.getItems().addAll("En Ligne", "Présentiel");
    }

    // Méthode pour gérer l'événement de changement de scène
    @FXML
    private void handleChangeScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("")); // Remplacez "nextScene.fxml" par le nom de votre fichier FXML cible
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
