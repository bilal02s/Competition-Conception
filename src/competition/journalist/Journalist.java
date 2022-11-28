package competition.journalist;

import competition.Competitor;
import competition.io.displayer.*;
import competition.journalist.report.*;

public abstract class Journalist{
    protected String name;
    protected Displayer displayer;

    /**
        Creates a journalist, assigns to it the name given in parameter.
        sets PrintConsole as default displayer.
        @param name the journalist's name
     */
    public Journalist(String name){
        this.name = name;
        this.displayer = new PrintConsole();
    }

    /**
        Returns the name of this journalist.
        @return this journalist's name
     */
    public String getName(){
        return this.name;
    }

    /**
        Provides a string representation of this journalist.
        @return this journalist's name
     */
    public String toString(){
        return this.name;
    }

    /**
        After receiving the report, prints to the console the news that will be diffused to the press.
        @param report the match's report, containing the score, the winner and the loser.
     */
    public abstract void printReport(Report report);
}