package competition.journalist;

import util.Pair;
import competition.*;
import competition.journalist.*;
import competition.journalist.report.*;
import competition.io.displayer.*;

public class ReportResultsJournalist extends Journalist{

    public ReportResultsJournalist(String name){
        super(name);
    }

    public void printReport(Report report){
        Pair<Competitor, Integer> firstPair = report.getFirstCompetitorScore();
        Pair<Competitor, Integer> secondPair = report.getSecondCompetitorScore();

        String comment = String.format(this.getName() + " : %s score is %d, %s score is %d", 
            firstPair.getKey(), 
            firstPair.getValue(), 
            secondPair.getKey(), 
            secondPair.getValue());

        this.displayer.writeMessage(comment);
        this.displayer.writeMessage("");
    }
}