package competition.displayer;

import competition.displayer.Displayer;

public class PrintConsole implements Displayer{

    /**
        Prints the message received in parameter to the terminal through a call to System.out.println
        @param msg the message to be printed.
     */
    public void writeMessage(String msg){
        System.out.println(msg);
    }
    
}