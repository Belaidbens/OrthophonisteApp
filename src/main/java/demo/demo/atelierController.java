package demo.demo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class atelierController {

    @FXML
    private TextField numeroDossierField;

    @FXML
    private Button addButton;

    @FXML
    private ListView<String> patientListView;

    // Liste observable pour stocker les patients
    private ObservableList<String> patientList;

    @FXML
    private void initialize() {
        // Initialiser la liste des patients
        patientList = FXCollections.observableArrayList();
        patientListView.setItems(patientList);
    }

    // Méthode pour ajouter un patient à la liste
    @FXML
    private void handleAddPatient(ActionEvent event) {
        String numeroDossier = numeroDossierField.getText();
        if (numeroDossier != null && !numeroDossier.trim().isEmpty()) {
            patientList.add("Patient avec numéro de dossier: " + numeroDossier);
            numeroDossierField.clear(); // Effacer le champ de texte après l'ajout
        }
    }
}
