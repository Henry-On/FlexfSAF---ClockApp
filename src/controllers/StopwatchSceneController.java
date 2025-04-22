package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stopwatch.Stopwatch;

public class StopwatchSceneController {

    private boolean running = false;
    private static long startTime = 0;
    private static long elapsedTime = 0;

    Thread stopwatchThread;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private Label lblShowCounter;

    @FXML
    private Button playButton;

    @FXML
    private Button btnResetStopwatch;

    @FXML
    void onRunStopwatch(ActionEvent event) {

        if( running ) {
            stopStopwatch();
            return;  
        }  
        
        startStopwatch();

    }

    void startStopwatch ( ) {

        if(!running) startTime = System.currentTimeMillis() - elapsedTime;
        setRunningState(true);
        
        stopwatchThread = new Thread(()->{
            while (running) {
                try {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    updateUICounter( formatTime(elapsedTime) );
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        stopwatchThread.start();

    }

    void stopStopwatch ( ) {

        setRunningState(false);

    }

    void updateUICounter ( String time ) {

        Platform.runLater(()-> {
            lblShowCounter.setText(time);
        });

    }

    
    String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds %= 60;
        minutes %= 60;

        return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, (millis % 1000)/10);
    }

    @FXML
    void onResetStopwatch ( ) {

        elapsedTime = 0;
        startTime = 0;
        setRunningState(false);

        updateUICounter(formatTime(elapsedTime));

    }

    private void setRunningState ( boolean running ) {

        this.running = running;
        playButton.setText( running ? "Pause" : "Start" );

    }

}
