package competition.event;

import java.util.*;
import java.lang.Math;
import competition.*;
import competition.exception.*;
import competition.io.displayer.*;
import competition.journalist.report.*;
import competition.journalist.*;
import competition.match.State;

/**
 * Tournoi representing a tournament.
 */
public class Tournoi extends Competition {

    /**
        in the construction of the class, the number of players given in parameter must be a power of 2, and superior to 1.
        otherwise an exception is raised.
        @param players List of participants
        @throws InsufficientNumberOfPlayersException if the number of players in the list is less than 2.
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
        @throws IntegerNotPowerOf2Exception If the integer given in parameter is not a power of 2.
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
        //in this type of competition the number of players is a power of 2 : 2**x, the x value corresponds to the number of rounds to be played.
        //example: if there are 4 players, 4 = 2**2, then there will be 2 rounds in the tournament.
        int rounds;
        try {
            rounds = Tournoi.getPowerOf2(players.size());
        }
        catch (Exception e){
            this.displayer.writeMessage(e.getMessage());
            return;
        }

        //we create a list which contains the players that will play against each other in the first round, aka all players.
        List<Competitor> currentRoundPlayers = new ArrayList<Competitor>(players);
        //we create a list in which we will add the winners from the first round, so that they will play in the next round
        List<Competitor> nextRoundPlayers = new ArrayList<Competitor>(); 

        for (int round = 1; round <= rounds; round++){
            Iterator itr = currentRoundPlayers.iterator();
            while(itr.hasNext()){
                //since there is "a power of 2" number of players, if there is at least one player, then there is for sure another one to play against
                //so we can take from the iterator two players at once without the risk of raising an error.
                Competitor c1 = (Competitor) itr.next();
                Competitor c2 = (Competitor) itr.next();
                Report report = this.playMatch(c1, c2);

                //if the match is a tie, go into tie breaker, keep playing until there is a victory.
                while(report.getMatchState() == State.DRAW){
                    this.displayer.writeMessage("The last match was a tie, we will proceed to a tie breaker match!");
                    report = this.playMatch(c1, c2);
                }

                this.updateRanking(report);
                Competitor winner = report.getWinner();
                nextRoundPlayers.add(winner);
            }

            //next round players will become the current players for the next round.
            currentRoundPlayers.clear();
            currentRoundPlayers.addAll(nextRoundPlayers);
            //next round players will become an empty list, to be filled in the next round.
            nextRoundPlayers.clear();
        }
    }
}