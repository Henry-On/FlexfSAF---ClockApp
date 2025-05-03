package modes.timer;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import myClock.MyClock;

public class CountdownTimer extends MyClock {

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

    @Override
    public void start() {
        this.running = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(countdownTimerTask, 0, 1000);
    }

    @Override
    public void stop () {
        if(this.running) this.running = false;
        timer.cancel();
    }

    private void updateTime() {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        System.out.print("\rRemaining Time: " + minutes + ":" + seconds);
        remainingTime--;
    }

}
