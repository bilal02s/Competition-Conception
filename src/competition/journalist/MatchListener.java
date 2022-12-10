package competition.journalist;

import java.util.*;
import competition.match.event.MatchEvent;
import competition.journalist.report.Report;

/**
    Observer interface, this object will represents observers of matchs
 */
public interface MatchListener extends EventListener{

    /**
        Handles the event of a match
        @param event the event, A match's event.
     */
    public void handleEvent(MatchEvent event);
}