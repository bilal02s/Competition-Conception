package competition;

import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;

public class Main{

    /**
        given a string in parameter, the method checks if the name correspond to a type of competition given in this model
        return a boolean value indicating wether the name is correct or not
        @param competitionName the name of the chosen competition
        @return True if the competition type exist, false otherwise.
     */
    private static boolean checkCompetitionName(String competitionName){
        return competitionName.equals("Tournament") || competitionName.equals("League");
    }

    public static void main(String args[]) throws Exception{
        //creating the list of players, initially emtpy.
        List<Competitor> players = new ArrayList<Competitor>();

        //checking if the user have chosen the correct competition name. throw exception otherwise.
        if (! Main.checkCompetitionName(args[0])){
            throw new Exception("wrong competition name entered");
        }

        //building the list of players
        for (int i = 1; i < args.length; i++){
            players.add(new Competitor(args[i]));
        }

        //selecting the type of competition
        Competition competition = (args[0].equals("Tournament")) ? new Tournoi(players) : new Championnat(players);

        //launching all the matchs
        competition.play();

        //retrieve the rankings
        Map<Competitor, Integer> ranks = competition.ranking();

        for (Competitor c : ranks.keySet()){
            System.out.println(c + " - " + ranks.get(c));
        }
    }
}