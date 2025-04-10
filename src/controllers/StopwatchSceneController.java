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

    private Stage stage;

    private void setMainStage(Stage stageName) {
        this.stage = stageName;
    }

	public void setMainWindow(Stage primaryStage) {
        this.stage = primaryStage;
	}

    @FXML
    void onChangeSceneToWatch(ActionEvent event) {
        try {
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXMLScenes/watchScene.fxml"));
            Parent root = fxmlLoader.load();
            WatchSceneController controller = fxmlLoader.getController();
            controller.setMainStage(stage);

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onChangeSceneToTimer(ActionEvent event) {
        try {
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXMLScenes/timerScene.fxml"));
            Parent root = fxmlLoader.load();
            TimerSceneController controller = fxmlLoader.getController();
            controller.setMainWindow(stage);

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
