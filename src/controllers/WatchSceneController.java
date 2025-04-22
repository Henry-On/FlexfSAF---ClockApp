package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WatchSceneController {

    @FXML
    private Label watchDisplayLabel;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private volatile boolean running = true;

    @FXML
    public void initialize() {
        // Called after FXML fields are injected
        startClockThread();
    }

    private void startClockThread() {
        
        Thread clockThread = new Thread(() -> {
            while (running) {
                String currentTime = LocalDateTime.now().format(formatter);

                // Update UI safely on JavaFX Application Thread
                Platform.runLater(() -> watchDisplayLabel.setText(currentTime));

                try {
                    Thread.sleep(1000); // wait 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // restore interrupt flag
                    break;
                }
            }
        });

        clockThread.setDaemon(true); // ensures thread stops when app closes
        clockThread.start();
    }

    // Optional: a method to stop the clock if needed
    public void stopClock() {
        running = false;
    }
}
