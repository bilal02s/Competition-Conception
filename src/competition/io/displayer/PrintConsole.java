package competition.io.displayer;

import competition.io.displayer.Displayer;

/**
    Used to write messages to the standard output.
 */
public class PrintConsole implements Displayer{

    /**
        Prints the message received in parameter to the terminal through a call to System.out.println
        @param msg the message to be printed.
     */
    public void writeMessage(String msg){
        System.out.println(msg);
    }
    
}