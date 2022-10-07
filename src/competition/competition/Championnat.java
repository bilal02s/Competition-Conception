package competition.competition;

import java.util.*;
import competition.*;
import competition.exception.*;

public class Championnat extends Competition {
    public Championnat(List<Competitor> players) throws InsufficientNumberOfPlayersException {
        super(players);
    }

    /**
        initialises the initial conditions of play : matchs distribution across players.
        plays the matchs between players.
        in this type of competition each player will play twice against all other players.
        @param players list of players that will participate in the competition
     */
    protected void play(List<Competitor> players){
        for (Competitor c1 : players){
            for(Competitor c2 : players){
                if (! c1.equals(c2)){
                    this.playMatch(c1, c2);
                }
            }
        }
    }
}