package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TimerSceneController {

    private boolean running;
    private long startTime;
    private long elapsedTime = 0L;
    private long uSetTime;
    
    private Thread timerThread;

    @FXML 
    AnchorPane mainContainer;

    @FXML 
    private TextField txtSetHour;

    @FXML 
    private TextField txtSetMin;

    @FXML 
    private TextField txtSetSec;

    @FXML 
    private Label lblShowTImer;

    @FXML
    private Button btnPlay;

    @FXML
    public void initialize() {

        restrictToMaxValue(txtSetHour, 24);
        restrictToMaxValue(txtSetMin, 60);
        restrictToMaxValue(txtSetSec, 60);

    }

    private void restrictToMaxValue(TextField textField, int maxValue) {

        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            // If input is not a number, reset to old value
            if (!newValue.matches("\\d*")) {
                textField.setText(oldValue);
                return;
            }

            // If number is greater than 60, reset to old value
            if (!newValue.isEmpty()) {

                try {
                    int value = Integer.parseInt(newValue);
                    if (value > 60) {
                        textField.setText(oldValue);
                    }
                } catch (NumberFormatException e) {
                    textField.setText(oldValue);
                }

                // enable @btnPlay button
                if(btnPlay.isDisabled()) btnPlay.setDisable(false);
            }
            else {

                // enable @btnPlay button
                if(!btnPlay.isDisabled()) btnPlay.setDisable(true);

            }
        });
    }

    @FXML
    void onBtnPlay(ActionEvent event) {

        if( running ) {
            stopTimer();
            return;  
        }  
        
        startTimer ();

    }

    void startTimer() {

        try {
            long hours = txtSetHour.getText().isEmpty() ? 0L : Long.parseLong(txtSetHour.getText());
            long minutes = txtSetMin.getText().isEmpty() ? 0L : Long.parseLong(txtSetMin.getText());
            long seconds = txtSetSec.getText().isEmpty() ? 0L : Long.parseLong(txtSetSec.getText());

            uSetTime = (hours * 3600 + minutes * 60 + seconds) * 1000; // converts the set time to milliseconds

        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    
        if (uSetTime < 1) return;
    
        setRunningState(true);
        startTime = System.currentTimeMillis() - elapsedTime;
    
        timerThread = new Thread(() -> {
            while (running) {
                try {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    long remaining = uSetTime - elapsedTime;
    
                    if (remaining <= 0) {
                        TimerSceneController.this.onResetTimer();
                        break;
                    }
    
                    updateUICounter(formatTime(remaining));
                    Thread.sleep(100); // sleep for 100ms to reduce CPU usage
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    
        timerThread.start();
    }
    

    void stopTimer ( ) {

        setRunningState(false);

    }

    void updateUICounter ( String time ) {

        Platform.runLater(()-> {
            lblShowTImer.setText(time);
        });

    }

    
    String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds %= 60;
        minutes %= 60;

        if(hours > 0) {
            return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, (millis % 1000)/10);
        }
        else if(minutes > 0) {
            return String.format("%02d:%02d:%02d", minutes, seconds, (millis % 1000)/10);
        }

        return String.format("%02d:%02d",  seconds, (millis % 1000)/10);
        
    }

    @FXML
    void onResetTimer ( ) {

        elapsedTime = 0L;
        setRunningState(false);

        updateUICounter("00:00");

    }

    private void setRunningState ( boolean running ) {

        this.running = running;
        Platform.runLater(() -> {
            btnPlay.setText(running ? "Pause" : "Start");
        });

    }
}
