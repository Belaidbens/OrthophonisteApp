package demo.demo;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.IOException;
public class rendezvousController {
    @FXML
    private ComboBox<String> myComboboxType;
    @FXML
    private void initialize() {
        // Ajoutez les éléments à la ComboBox
        myComboboxType.getItems().addAll("Consultation","Seance De Suivi" ,"Atelier De Groupe");
    }
    @FXML
    private void GoToChoixType(ActionEvent event) {

        String choix = myComboboxType.getValue();

        if (choix != null) {

            // Chargez la page correspondante en fonction du choix

            String fxmlFile = "";

            switch (choix) {

                case "Consultation":

                    fxmlFile = "consultation.fxml";

                    break;

                case "Seance De Suivi":

                    fxmlFile = "suivi.fxml";

                    break;

                case "Atelier De Groupe":

                    fxmlFile = "atelier.fxml";

                    break;



            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

            try {

                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

                Scene scene = new Scene(loader.load());

                stage.setScene(scene);

                stage.show();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }
}
