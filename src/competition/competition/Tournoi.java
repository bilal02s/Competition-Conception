package competition.competition;

import java.util.*;
import java.lang.Math;
import competition.*;
import competition.exception.*;

public class Tournoi extends Competition {

    /**
        in the construction of the class, the number of players given in parameter must be a power of 2.
        otherwise an exception is raised.
        @throws WrongNumberOfPlayersException if the number of players is not a power of 2.
     */
    public Tournoi (List<Competitor> players) throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException {
        super(players);

        if (! Tournoi.isPowerOf2(players.size())) {
            throw new WrongNumberOfPlayersException("the number of players in this competition is not a power of 2");
        }
    }

    /**
        this method is static since it does not depend on any instance.
        this method checks if the integer given in parameter is a power of two or not, return a boolean as answer.
        @param x the integer to be checked.
        @return True is the number is a power of two, False otherwise.
     */
    public static boolean isPowerOf2(int x) {
        //if a number is a power of two then it is expressed as a series of zeros as bit and then a one on the nth bit, thus x-1 will be series of 1 and then a zero on the nth bit
        //then doing a bitwise and operation will result a zero if the number is a power of 2 and different then zero otherwise.
        return (x & (x-1)) == 0 ? true : false;
    }

    /**
        receives and integer that is a power of 2 in parameter.
        returns the value for which, when 2 is powered by this value will be equal to the integer given in parameter.
        @param x integer, power of 2.
        @return the value of the log2(x).
        @throws IntegerNotPowerOf2Exception
     */
    public static int getPowerOf2(int x) throws IntegerNotPowerOf2Exception{
        if (! Tournoi.isPowerOf2(x)) {
            throw new IntegerNotPowerOf2Exception("the given integer is not a power of 2");
        }
        return (int) (Math.log(x)/Math.log(2));
    }

    /**
        initialises the initial conditions of play : matchs distribution across players.
        plays the matchs between players.
        in this type of competition each player are paired two by two, and then winners will play against winners.
        @param players list of players that will participate in the competition
     */
    protected void play(List<Competitor> players) {
        //verify if we have the correct number of players, else exit the method.
        //in this type of competition the number of players is a power of 2 : 2**x, the x value corresponds to the number of rounds played.
        int rounds;
        try {
            rounds = Tournoi.getPowerOf2(players.size());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        for (int round = 1; round <= rounds; round++){
            //we create a list which contains the players that will play against each other in the current round.
            List<Competitor> currentRoundPlayers = players;
            //we create a list in which we will add the winners from the first round, so that they will play in the next round
            List<Competitor> nextRoundPlayers = new ArrayList<Competitor>(); 

            Iterator itr = currentRoundPlayers.iterator();
            while(itr.hasNext()){
                //since there is "a power of 2" number of players, if there is at least one player, then there is for sure another one to play against
                //so we can take from the iterator two players at once without the risk of raising an error.
                Competitor c1 = (Competitor) itr.next();
                Competitor c2 = (Competitor) itr.next();
                Competitor winner = this.playMatch(c1, c2);
                nextRoundPlayers.add(winner);
            }

            currentRoundPlayers = nextRoundPlayers;
            nextRoundPlayers = new ArrayList<Competitor>();
        }
    }
}