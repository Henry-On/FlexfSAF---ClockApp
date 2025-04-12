package controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import watch.Watch;

public class WatchSceneController implements Runnable {

    public Watch watch;

    @FXML
    Label watchDisplayLabel;

    @FXML
    AnchorPane mainContainer;

    @Override
    public void run() {

        // start running the time
        watch = new Watch();
        watch.setVisibility(true);

        // create the thread
        Thread watchThread = new Thread(watch);
        watchThread.start();

        while (true) {
                    
            try {
        
                this.setSceneTime(watch.timeString);

                // runs every 1 second
                Thread.sleep(1000);

            } catch (Exception e) {

                System.out.println(e);

            }

        }

    }


    public void setSceneTime(String time) {

        /**
         * JavaFX does not allow UI element from a background thread
         * JavaFX only allows UI updates from the JavaFX Application Thread
         * Platform.runLater() schedules the update to run safely on the JavaFX Application Thread
         */
        Platform.runLater(()->{
            this.watchDisplayLabel.setText(time);
        });    
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

    @FXML
    void onChangeSceneToStopwatch(ActionEvent event) {
        try {
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXMLScenes/stopwatchScene.fxml"));
            Parent root = fxmlLoader.load();
            mainContainer.getChildren().setAll(root);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
