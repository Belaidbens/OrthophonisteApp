package demo.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.models.Orthophoniste;

public class anamtestController
{
  private Orthophoniste ortho;
    public void setOrtho(Orthophoniste ortho) {
        this.ortho = ortho;
    }
    @FXML
    public void HandleAnam(ActionEvent event){
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anamnese.fxml"));
            Parent root = loader.load();
            anamneseController anam=loader.getController();//pour pouvoir faire passer un objet de inscrire a la scene dashboard
             anam.setOrtho(ortho);
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



}
