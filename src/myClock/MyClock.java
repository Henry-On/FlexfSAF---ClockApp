package myClock;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class MyClock implements IClockMethods {


    int hours, minutes, seconds;
    String amPm;
    boolean is24HrsFormat = false;

    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    LocalDateTime currentDateTime = LocalDateTime.now();

    String timezone;

    public MyClock () {

        DateTimeFormatter formatAsHour = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter formatAsMinute = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter formatAsSecond = DateTimeFormatter.ofPattern("ss");
        DateTimeFormatter formatAsAmPm = DateTimeFormatter.ofPattern("a");
        
        this.amPm = LocalDateTime.now().format(formatAsAmPm);

        // get the different parts of current time
        String hours = LocalDateTime.now().format(formatAsHour);
        String minutes = LocalDateTime.now().format(formatAsMinute);
        String seconds = LocalDateTime.now().format(formatAsSecond);

        // cast the time parts into integer
        this.hours = Integer.parseInt(hours);
        this.minutes = Integer.parseInt(minutes);
        this.seconds = Integer.parseInt(seconds);

    }

    // set the clock to desired time
    public void setTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getTime() {

        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss a");
        String timeAsString = LocalDateTime.now().format(formatTime);
        
        return timeAsString;
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }


}
