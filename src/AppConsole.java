import java.util.Scanner;

import timer.CountdownTimer;
import watch.Watch;
import stopwatch.Stopwatch;

class AppConsole {

    public static void main(String[] args) {
    
        System.out.println("ClockApp, select mode: ");
        int mode;

        Scanner scanner = new Scanner(System.in);

        while (true) {
    
            System.out.print("[1 => Watch, 2 => Stopwatch, 3 => Timer, 4 => Alarm, 0 => Exit App] Your Answer: ");
            String selectedMode = scanner.nextLine().trim().toUpperCase();
            String answer;

            try {

                mode = Integer.parseInt(selectedMode); 

                if( mode == 0 ) {
                    break;
                }
                else if( mode == 1 ) {

                    System.out.println("[MODE : Watch]");
                    
                    Watch watch = new Watch();
                    watch.setVisibility(true);

                    // create the thread
                    Thread watchThread = new Thread(watch);
                    watchThread.start();
                    Thread.sleep(500);

                    System.out.println("Watch Commands: Press 'ENTER' key to exit");
                    answer = scanner.nextLine().toUpperCase().trim();

                    if(answer.equals("")) {

                        watch.setVisibility(false);
                        mode = 100;
                        continue;

                    }

                }
                else if( mode == 2 ) {

                    System.out.println("[MODE : Stopwatch]");

                    Stopwatch stopwatch = new Stopwatch();
                    Thread stopwatchThread = new Thread (stopwatch);
                    stopwatchThread.start();
                    Thread.sleep(500);

                    String command = "";

                    do {
                        System.out.println("Stopwatch commands: START, EXIT");
                        System.out.print("Enter command: ");
                        command = scanner.nextLine().trim().toUpperCase();

                        if(!command.equals("START") && !command.equals("EXIT")) {
                            System.out.println("Invalid Command");
                        }

                        if(command.equals("START")) {
                            stopwatch.start();
                            break;
                        }
                        if(command.equals("EXIT")) {
                            Stopwatch.closeStopwatch = true;
                            break;
                        }
                        
                    } while(!command.equals("START"));
                    
                    while ( !Stopwatch.closeStopwatch ) {

                        System.out.println("Stopwatch commands: START, STOP, RESET, EXIT");
                        System.out.print("Enter command: ");
                        command = scanner.nextLine().trim().toUpperCase();
            
                        switch (command) {
                            case "START":
                                stopwatch.start();
                                break;
                            case "STOP":
                                stopwatch.stop();
                                break;
                            case "RESET":
                                stopwatch.reset();
                                break;
                            case "EXIT":
                                System.out.println("Exiting stopwatch...");
                                stopwatch.stop();
                                Stopwatch.closeStopwatch = true;
                                break;
                            default:
                                System.out.println("Invalid command! Use: STOP, RESET, or EXIT.");
                        }
                    }
                    
                }
                else if( mode == 3 ) {

                    System.out.println("[MODE : Timer]");
                    boolean runTimer = true;
                        
                    do {

                        if (!runTimer) break;

                        System.out.print("Enter time in seconds: ");
                        int timeInSeconds = Integer.parseInt(scanner.nextLine());

                        CountdownTimer countdownTimer = new CountdownTimer(timeInSeconds);
                        countdownTimer.start();

                        System.out.println("Timer Commands: START, EXIT");
                        System.out.println("Press 'ENTER' key to close");
                        answer = scanner.nextLine().trim();

                        if(answer.equals("")) {
                            countdownTimer.stop();
                            System.out.println("Timer stopped");
                            continue;
                        }
                        if(answer.equals("START")) {
                            countdownTimer.stop();
                            continue;
                        }
                        if(answer.equals("EXIT")) {
                            runTimer = false;
                            break;
                        }

                    } while (!answer.equals(""));

                    continue;

                }
                else if( mode == 100 ) {

                    continue;

                }
                else {
                    System.out.println("Invalid selection");
                }

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }

        scanner.close();
        System.out.println("App closed");

    }

}
