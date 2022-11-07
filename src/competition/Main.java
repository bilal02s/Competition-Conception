package competition;

import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;
import competition.displayer.*;

/**
 * The main program that launchs a competition with its participants.
 */
public class Main{

    /**
        receives the competition type and the competitors in the args parameter as strings.
        picks the corresponding competition type and instanciate it, and gives it the list of player after building it.
        then launchs all the matchs between players, and prints the result to the terminal.
        @param args the argument given in the terminal, expecting a competition name such as tournament or league, followed by the names of players.
     */
    public static void main(String args[]) {
        //creating the list of players, initially emtpy.
        List<Competitor> players = new ArrayList<Competitor>();

        //initialise the factory instance.
        CompetitionFactory factory = new CompetitionFactory();

        //building the list of players
        for (int i = 1; i < args.length; i++){
            players.add(new Competitor(args[i]));
        }

        //checking if the user have chosen the required number of players for the chosen competition type. 
        //the factory's method will throw exception otherwise.
        Competition competition;
        try {
            //getting the correct type of match using the factory design pattern.
            competition = factory.getCompetition(args[0], players, new PrintConsole());
        }
        catch(Exception e){
            System.out.println("Please enter the required number of players in the competition and try again");
            return;
        }

        if (competition == null){
            System.out.println("Please select a correct competition type and try again");
            return;
        }

        //launching all the matchs
        competition.play();

        //retrieve the rankings
        Map<Competitor, Integer> ranks = competition.ranking();

        for (Competitor c : ranks.keySet()){
            System.out.println(c + " - " + ranks.get(c));
        }
    }
}