package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WatchSceneController {

    private boolean AMPM_MODE = false;

    @FXML
    private Label watchDisplayLabel;

    private final DateTimeFormatter _24HrsFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter _12HrsFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    private volatile boolean running = true;

    @FXML
    public void initialize() {
        // Called after FXML fields are injected
        startClockThread();
    }

    @FXML
    public void toggle24HrsMode (ActionEvent event) {
        this.AMPM_MODE = !this.AMPM_MODE;
    }

    private void startClockThread() {
        
        Thread clockThread = new Thread(() -> {
            
            while (running) {

                DateTimeFormatter currentTimeTimeFormatter = AMPM_MODE ? _12HrsFormatter : _24HrsFormatter;

                String currentTime = LocalDateTime.now().format(currentTimeTimeFormatter);

                // Update UI safely on JavaFX Application Thread
                // JavaFX doesnot allow direct updating of the UI from background thread, hence @Platform.runLater()
                Platform.runLater(() -> watchDisplayLabel.setText(currentTime));

                try {
                    long millisUntilNextSecond = 1000 - (System.currentTimeMillis() % 1000);
                    Thread.sleep(millisUntilNextSecond);
                    // Thread.sleep(1000); // wait 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // restore interrupt flag
                    break;
                }
            }
        });

        clockThread.setDaemon(true); // ensures thread stops when app closes
        clockThread.start();
    }

    public void stopClock() {
        running = false;
    }

}
