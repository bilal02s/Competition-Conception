package competition.journalist;

import java.util.*;
import competition.Competitor;
import competition.io.displayer.*;
import competition.journalist.report.*;

/**
    A journalist, diffusing article about match when assisting a competition.
 */
public abstract class Journalist{
    /** the name of this journalist */
    protected String name;
    /** displayer used to display to the terminal the news */
    protected Displayer displayer;
    /** news list will store strings format to be used when diffusing a match result */
    protected List<String> news;

    /** to be used to pick randomly one string out of the news list */
    protected static final Random rand = new Random();

    /**
        Creates a journalist, assigns to it the name given in parameter.
        sets PrintConsole as default displayer.
        @param name the journalist's name
        @param news the list of news format
     */
    public Journalist(String name, List<String> news){
        this.displayer = new PrintConsole();
        this.name = name;
        this.news = news;
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