package competition.event;

import competition.*;
import competition.event.*;
import competition.displayer.*;
import competition.exception.*;
import competition.match.*;
import java.util.*;

public class Master extends Competition{
    private Scanner scanner;

    /**
        @param competitors List of participants
        @param displayer Writing results to the displayer
        @throws InsufficientNumberOfPlayersException if the number of players in the list is less than 4.
     */
    public Master(List<Competitor> competitors, Displayer displayer) throws InsufficientNumberOfPlayersException{
        super(competitors, displayer);

        if(competitors.size() < 4){
            throw new InsufficientNumberOfPlayersException("A master must have at least 4 players.");
        }

        this.scanner = new Scanner(System.in);
        this.initGroups();
    }

    private void initGroups(){
        
    }
}