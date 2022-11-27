package competition.journalist;

import competition.Competitor;
import competition.io.displayer.*;

public abstract class Journalist{
    private String name;
    private Displayer displayer;

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
        
     */
    public abstract void PrintReport(Competitor c1, Competitor c2, int winner);
}