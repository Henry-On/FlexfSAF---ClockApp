package modes.watch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import myClock.MyClock;

public class Watch extends MyClock implements Runnable {

    boolean visibilty = true;
    public String timeString;

    @Override
    public void run ( ) {

        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        while ( true ) {

            this.timeString = LocalDateTime.now().format(formatTime);
        
            if( this.visibilty == true ) System.out.print("\rTime: " + timeString);
            try {       
                timeString = LocalDateTime.now().format(formatTime);        
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        
    }
    
    public void setVisibility(boolean visibilty) {
        this.visibilty = visibilty;
    }

    public void showDate() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        String dateString = LocalDate.now().format(formatDate);
        System.out.println(dateString);
    }

}
