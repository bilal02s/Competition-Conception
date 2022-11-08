package competition.event;

import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;
import competition.displayer.*;

/**
 * factory design pattern, takes care of constructing competitions.
 * @author Bilal el Safah
 */
public class CompetitionFactory{

    /**
        given a string in parameter, the method checks if the name correspond to a type of competition given in this model
        return the corresponding instance in the case of success, null value otherwise.
        the created instance is initialised with the list of players given in parameter, as well as the displayer instance.
        @param name the name of the chosen competition.
        @param players the list of players participating in the competition
        @param displayer Writing results to the displayer
        @return the instance of the chosen competition, null otherwise.
        @throws WrongNumberOfPlayersException in the case when the number of players is not a power of 2 in a tournament.
        @throws InsufficientNumberOfPlayersException in the case when the number of players is inferior to 2 in any competition type.
     */
    public Competition getCompetition(String name, List<Competitor> players, Displayer displayer) throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        if(name == null){
            return null;
        }

        if(name.equalsIgnoreCase("Tournament")){
            return new Tournoi(players, displayer);
        }
        else if(name.equalsIgnoreCase("League")){
            return new Championnat(players, displayer);
        }

        return null;
    }
}