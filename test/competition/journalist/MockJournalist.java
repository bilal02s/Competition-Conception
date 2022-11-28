package competition.journalist;

import competition.journalist.*;

/**
    mock journalist, used to test if the call to the method printReport happened or not.
 */
public class MockJournalist extends Journalist{
    private int nbMethodCall;

    /**
        sets the attribut nbMethodCall to 0.
        @param name the name of this journalist
     */
    public MockJournalist(String name){
        super(name);
        this.nbMethodCall = 0;
    }

    /**
        returns the number of times the method targeted have been called
        @return the number of times the targeted method have been called
     */
    public int getNbCall(){
        return this.nbMethodCall;
    }

    /**
        this mock targets this method, to test how many times it has been called.
        each time it is called, the counter is incremented by 1.
        @param report we have no use of the report
     */
    public void printReport(Report report){
        this.nbMethodCall += 1;
    }
}