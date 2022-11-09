package competition.event;

import competition.*;
import competition.event.*;
import competition.io.displayer.*;
import competition.exception.*;
import competition.match.*;
import java.util.*;

public class Master extends Competition{
    private List<Championnat> leagues;
    private Tounoi finalTournament;
    private Scanner scanner;

    /**
        @param competitors List of participants
        @param displayer Interacting with external source through the displayer (write or read information)
        @throws InsufficientNumberOfPlayersException if the number of players in the list is less than 4.
     */
    public Master(List<Competitor> competitors) throws InsufficientNumberOfPlayersException{
        super(competitors);

        if(competitors.size() < 4){
            throw new InsufficientNumberOfPlayersException("A master must have at least 4 players.");
        }

        this.scanner = new Scanner(System.in);
        this.initGroups();
    }

    private void initGroups(){
        
    }
}