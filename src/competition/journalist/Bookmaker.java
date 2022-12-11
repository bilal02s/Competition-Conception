package competition.journalist;

import java.util.*;
import java.lang.Math;
import competition.journalist.Journalist;
import competition.journalist.report.*;
import competition.match.State;
import competition.*;

/**
    keeping track of competitor's quotes and their variation from match to match.
 */
public class Bookmaker extends Journalist{
    /** a map, mapping quotations to competitors */
    protected Map<Competitor, Integer> quotations;
    /** the initial value of quotations for new competitors */
    protected int initValue;
    /** the delta value added or substracted to the competitor's quotation, after a win or loss */
    protected int deltaValue;

    /**
        contructs a new bookmaker, declare the quotations hashmap.
        @param name this bookmaker's name
        @param news the list of news format
     */
    public Bookmaker(String name, List<String> news){
        super(name, news);

        this.quotations = new HashMap<Competitor, Integer>();
        this.initValue = 1;
        this.deltaValue = 1;
    }

    /**
        Receives a report, computes the quotation of the competitors figuring in the report and modifies the map of this bookmaker accordingly.
        new players will have a quotation of 1 by default.
        @param report a report of a given match
     */
    private void computeNewQuotation(Report report){
        //retrieve players
        Competitor winner = report.getWinner();
        Competitor loser = report.getLoser();

        //init their quotation if they are new
        if (!this.quotations.containsKey(winner)){
            this.quotations.put(winner, this.initValue);
        }
        if (!this.quotations.containsKey(loser)){
            this.quotations.put(loser, this.initValue);
        }

        //if the match is draw dont change anything
        if (report.getMatchState() == State.DRAW){
            return;
        }

        //compute new quotation
        int winnerQuotation = Math.max(1, this.quotations.get(winner) - this.deltaValue);
        int loserQuotation = this.quotations.get(loser) + this.deltaValue;

        //put results in the map
        this.quotations.put(winner, winnerQuotation);
        this.quotations.put(loser, loserQuotation);
    }

    /**
        this method will print to the console a message/news corresponding the report it receives in parameter for a given match.
        @param report the match's report
     */
    protected void printReport(Report report){
        //retireve the players
        Competitor winner = report.getWinner();
        Competitor loser = report.getLoser();

        //retrieve randomly the news sentence to diffuse
        String format = this.news.get(Journalist.rand.nextInt(this.news.size()));

        //compute new quotations
        this.computeNewQuotation(report);
        int firstCompetitorQuotation = this.quotations.get(winner);
        int secondCompetitorQuotation = this.quotations.get(loser);

        //building the string to display
        String comment = String.format(this.getName() + format, //" : %s score is %d, %s score is %d", 
            winner, 
            firstCompetitorQuotation, 
            loser, 
            secondCompetitorQuotation);

        //displaying the message
        this.displayer.writeMessage(comment);
    }

    /**
        returns this bookmaker's quotations.
        @return this bookmaker's quotations.
     */
    public Map<Competitor, Integer> getQuotations(){
        return this.quotations;
    }

    /**
        Sets the init value quotation of this bookmaker
        @param initValue the new initial quotation value
     */
    public void setInitValue(int initValue){
        this.initValue = initValue;
    }

    /**
        Returns the value of which new competitor's quotations are initialised
        @return quotation's initial value
     */
    public int getInitValue(){
        return this.initValue;
    }

    /**
        Sets the delta change value of this bookmaker
        @param deltaValue the new delta value
     */
    public void setDeltaValue(int deltaValue){
        this.deltaValue = deltaValue;
    }

    /**
        Returns the delta value, which is the value added or subtracted from competitor's quotation
        @return this bookmaker's deltaValue
     */
    public int getDeltaValue(){
        return this.deltaValue;
    }
}