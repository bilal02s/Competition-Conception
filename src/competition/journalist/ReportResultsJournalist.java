package competition.journalist;

import util.Pair;
import java.util.*;
import competition.*;
import competition.journalist.*;
import competition.journalist.report.*;
import competition.io.displayer.*;

/**
    the ReportResultsJournalist will model a standard behaviour journalist that will receive the news and then comment it out with a single string displayed to the terminal.
 */
public class ReportResultsJournalist extends Journalist{

    /**
        construct a journalist who's job is to diffuse news about matchs
        @param name this journalist's name
        @param news the list of news format
     */
    public ReportResultsJournalist(String name, List<String> news){
        super(name, news);
    }

    /**
        this method will print to the console a message/news corresponding the report it receives in parameter for a given match.
        @param report the match's report
     */
    protected void printReport(Report report){
        //retireve the pairs
        Pair<Competitor, Integer> firstPair = report.getFirstCompetitorScore();
        Pair<Competitor, Integer> secondPair = report.getSecondCompetitorScore();

        //retrieve randomly the news sentence to diffuse
        String format = this.news.get(Journalist.rand.nextInt(this.news.size()));

        //building the string to display
        String comment = String.format(this.getName() + format, //" : %s score is %d, %s score is %d", 
            firstPair.getKey(), 
            firstPair.getValue(), 
            secondPair.getKey(), 
            secondPair.getValue());

        //displaying the message
        this.displayer.writeMessage(comment);
    }
}