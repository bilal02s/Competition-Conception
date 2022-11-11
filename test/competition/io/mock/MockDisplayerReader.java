package competition.io.mock;

import competition.io.reader.*;
import competition.io.displayer.*;

/**
    this Mock is used to test the competitions in which some user inputs are required
 */
public class MockDisplayerReader implements Displayer, Reader{
    private String lastReceivedMessage;

    /**
        stock the printed message to a variable to be used later when asked for an input.
        @param msg The printed message
     */
    public void writeMessage(String msg){
        this.lastReceivedMessage = msg;
    }

    /**
        Force value to true, there is always a token to be given.
        @return true
     */
    public boolean hasNext(){
        return true;
    }

    /**
        Force value to an empty string, we have no use of this method
        @return an empty string
     */
    public String getInputString(){
        return "";
    }

    /**
        Force the integer to be returned to some predetermined value depending on the last received message.
        @return a predetermined int used for testing purposes
     */
    public int getInputInteger(){
        if("How many pools do you want to have?".equals(this.lastReceivedMessage)){
            return 4;
        }
        else if("How many players goes to the final round?".equals(this.lastReceivedMessage)){
            return 4;
        }

        return -1;
    }
}