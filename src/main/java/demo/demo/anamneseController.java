package demo.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.models.Orthophoniste;

public class anamneseController {
    private  Orthophoniste ortho;

    public void setOrtho(Orthophoniste ortho) {
        this.ortho = ortho;
    }

    public void Handlecreateques(ActionEvent event){
      try {
          // Load the second page
          FXMLLoader loader = new FXMLLoader(getClass().getResource("questionanam.fxml"));
          Parent root = loader.load();
          questionanamController quesAnam=loader.getController();//pour pouvoir faire passer un objet de inscrire a la scene dashboard
          quesAnam.setOrtho(ortho);
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
    public void HandleVisualiserAnam(ActionEvent event){
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("quesanamvisualiser.fxml"));
            Parent root = loader.load();
            quesanamvisualiserController visAnam=loader.getController();//pour pouvoir faire passer un objet de inscrire a la scene dashboard
            visAnam.setOrtho(ortho);
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
    public void HandleCreerAnam(ActionEvent event){
        try {
            // Load the second page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anamneseCreate.fxml"));
            Parent root = loader.load();
            anamneseCreateController visAnam=loader.getController();//pour pouvoir faire passer un objet de inscrire a la scene dashboard
            visAnam.setOrtho(ortho);
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
