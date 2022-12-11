package competition.io.mock;

import competition.io.displayer.*;

/**
    This mock will prevent printing to console, contains a counter, and will increment it
    everytime the method writeMessage is called.
 */
public class MockDisplayer implements Displayer{
    int nbCallMethod;

    /**
        initialise the nbCallMethod to 0.
     */
    public MockDisplayer(){
        this.nbCallMethod = 0;
    }

    /**
        method to be tested, if it is called or not, each call will increment the counter by 1.
        @param msg a message
     */
    public void writeMessage(String msg){
        this.nbCallMethod++;
    }

    /**
        returns the number of call to the targeted method
        @return the number of times the targeted method was called
     */
    public int getNbCall(){
        return this.nbCallMethod;
    }
}