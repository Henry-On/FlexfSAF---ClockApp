package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StopwatchSceneController {

    @FXML
    private AnchorPane mainContainer;

    @FXML
    void onChangeSceneToWatch(ActionEvent event) {
        try {
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXMLScenes/watchScene.fxml"));
            Parent root = fxmlLoader.load();
            mainContainer.getChildren().setAll(root);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onChangeSceneToTimer(ActionEvent event) {
        try {
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXMLScenes/timerScene.fxml"));
            Parent root = fxmlLoader.load();
            mainContainer.getChildren().setAll(root);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
