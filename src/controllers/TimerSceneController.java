package controllers;
import javafx.application.Application;
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

public class TimerSceneController {

    private boolean running;
    private Long elapsedTime = 0L;
    private long startTime;

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
            }
        });
    }

    @FXML
    void onBtnPlay(ActionEvent event) {

        if( running ) {
            stopStopwatch();
            return;  
        }  
        
        startTimer ();

    }

    void startTimer ( ) {

        if( !running ) startTime = System.currentTimeMillis() - elapsedTime;
        setRunningState(true);
        
        timerThread = new Thread(()->{
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

        timerThread.start();

    }

    void stopStopwatch ( ) {

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

        return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, (millis % 1000)/10);
    }

    @FXML
    void onResetStopwatch ( ) {

        elapsedTime = 0L;
        setRunningState(false);

        updateUICounter(formatTime(elapsedTime));

    }

    private void setRunningState ( boolean running ) {

        this.running = running;
        btnPlay.setText( running ? "Pause" : "Start" );

    }



}
