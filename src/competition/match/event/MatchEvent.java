package competition.match.event;

import java.util.*;
import competition.event.Competition;
import competition.journalist.report.Report;

/**
    MatchEvent is the event that occurs at the end of a match
 */
public class MatchEvent extends EventObject{
    /** this event holds the report of a match */
    protected Report report;

    /**
        Constructs an event that hold the report as information about a match.
        @param source The competition source that emitted the event
        @param report The information to transmit in this event
     */
    public MatchEvent(Competition source, Report report){
        super(source);
        
        this.report = report;
    }

    /**
        Returns the information transmitted with this event, the report.
        @return a report.
     */
    public Report getReport(){
        return this.report;
    }
}