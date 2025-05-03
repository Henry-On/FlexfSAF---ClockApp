package modes.stopwatch;

import java.util.Scanner;
import myClock.MyClock;

public class Stopwatch extends MyClock implements Runnable {

    private static long startTime = 0;
    private static long elapsedTime = 0;
    public static boolean running = false;
    public static boolean closeStopwatch = false;

    Scanner scanner = new Scanner(System.in);
    String command;

    public void run() {

        // System.out.println("Simple Stopwatch - Commands: START, STOP, RESET, EXIT");

    }

    public void start() {

        Stopwatch.closeStopwatch = false; 

        if (!running) {
            startTime = System.currentTimeMillis() - elapsedTime; // Resume from paused time
            running = true;
            System.out.println("Stopwatch started...");
        } else {
            System.out.println("Stopwatch is already running!");
        }
    }

    public void stop() {
        Stopwatch.closeStopwatch = false; 
        if (running) {
            elapsedTime = System.currentTimeMillis() - startTime;
            running = false;
            System.out.println("Stopwatch stopped. Time elapsed: " + formatTime(elapsedTime));
        } else {
            System.out.println("Stopwatch is not running!");
        }
    }

    public void reset() {
        Stopwatch.closeStopwatch = false; 
        elapsedTime = 0;
        startTime = 0;
        running = false;
        System.out.println("Reset completed.");
    }

    public String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds %= 60;
        minutes %= 60;
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis % 1000);
    }
}
