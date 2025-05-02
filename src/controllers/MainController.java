package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController {

    @FXML
    Pane modeContainer;

    @FXML
    public Button buttonWatch;

    @FXML
    Button buttonTimer;

    @FXML
    Button buttonStopwatch;

    Parent watch;
    Parent stopwatch;
    Parent timer;
    Parent alarm;
    
    @FXML
    public void initialize() {

        try{
            // load all views
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../FXMLScenes/timerScene.fxml"));
            timer = loader1.load();

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../FXMLScenes/watchScene.fxml"));
            watch = loader2.load();

            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("../FXMLScenes/stopwatchScene.fxml"));
            stopwatch = loader3.load();

            // FXMLLoader alarmLoader = new FXMLLoader(getClass().getResource("../FXMLScenes/alarmScene.fxml"));
            // alarm = alarmLoader.load();

            modeContainer.getChildren().addAll(watch, timer, stopwatch);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    

    public void showStartView ( ) {

        this.addActiveClass(buttonWatch);
        this.onChangeSceneToWatch();
        
    }

    @FXML
    public void onChangeSceneToWatch() {
        this.addActiveClass(buttonWatch);
        showView(watch);
    }
    
    @FXML
    void onChangeSceneToTimer(ActionEvent event) {
        this.addActiveClass(buttonTimer);
        showView(timer);
    }

    @FXML
    void onChangeSceneToStopwatch(ActionEvent event) {
        this.addActiveClass(buttonStopwatch);
        showView(stopwatch);
    }

    private void addActiveClass (Button button) {

        buttonWatch.getStyleClass().remove("active-button");
        buttonStopwatch.getStyleClass().remove("active-button");
        buttonTimer.getStyleClass().remove("active-button");
        button.getStyleClass().add("active-button");

    }

    private void showView(Node viewToShow) {
        for (Node view : modeContainer.getChildren()) {
            view.setVisible(view == viewToShow);
        }
    }

}
