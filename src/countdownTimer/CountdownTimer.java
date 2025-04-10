package countdownTimer;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {

    private int remainingTime;
    private Timer timer;
    public boolean running = false;

    private TimerTask countdownTimerTask = new TimerTask() {

        @Override
        public void run() {
            if (remainingTime > 0) {
                updateTime();
            } else {
                System.out.println("Time's up!");
                CountdownTimer.this.stop();
            }
        }
    };

    Scanner scanner = new Scanner(System.in);
    int seconds;

    public int getRemainingTime() {
        return this.remainingTime;
    }

    public CountdownTimer(int seconds) {
        this.remainingTime = seconds;
    }

    public void start() {
        this.running = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(countdownTimerTask, 0, 1000);
    }

    public void stop () {
        if(this.running) this.running = false;
        timer.cancel();
    }

    private void updateTime() {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        System.out.println("Remaining Time: " + minutes + ":" + seconds);
        remainingTime--;
    }

}
