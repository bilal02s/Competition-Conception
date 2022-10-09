package competition.match;

import java.util.*;
import competition.*;
import competition.match.*;

public class RandomWinner implements Match{

    /**
        plays a match between the two competitors given in parameter.
        return one competitor of the two, the returned one is the winner.
        The winner is selected randomly.
        @param c1 The first competitor.
        @param c2 The second competitor.
        @return The winner out of the two competitors.
     */
    public Competitor playMatch(Competitor c1, Competitor c2){
        Random rand =  new Random();
        int choice = rand.nextInt(2);

        return (choice == 1) ? c2 : c1;
    }
}