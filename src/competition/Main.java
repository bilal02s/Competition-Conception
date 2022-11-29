package competition;

import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;
import competition.journalist.*;

/**
 * The main program that launchs a competition with its participants.
 */
public class Main{
    private static List<String> news = new ArrayList<String>();

    /**
        adding values to the static attribut news, to be used in the main
     */
    private static void buildNews(){
        Main.news.add(" : %s score is %d, %s score is %d");
        Main.news.add(" : %s actual quotation is %d | %s actual quotation is %d");
    }

    /**
        receives the competition type and the competitors in the args parameter as strings.
        picks the corresponding competition type and instanciate it, and gives it the list of player after building it.
        then launchs all the matchs between players, and prints the result to the terminal.
        @param args the argument given in the terminal, expecting a competition name such as tournament or league, followed by the names of players.
     */
    public static void main(String args[]) {
        //init news
        Main.buildNews();

        //creating the list of players, initially emtpy.
        List<Competitor> players = new ArrayList<Competitor>();

        //declare the factory instance.
        CompetitionFactory factory = new CompetitionFactory();

        //declare the list of journalists.
        List<Journalist> journalists = new ArrayList<Journalist>();
        journalists.add(new ReportResultsJournalist("FIFA", Main.news.subList(0, 1)));
        journalists.add(new Bookmaker("ParisonsSport", Main.news.subList(1, 2)));

        //building the list of players
        for (int i = 1; i < args.length; i++){
            players.add(new Competitor(args[i]));
        }

        //checking if the user have chosen the required number of players for the chosen competition type. 
        //the factory's method will throw exception otherwise.
        Competition competition;
        try {
            //getting the correct type of match using the factory design pattern.
            competition = factory.getCompetition(args[0], players);
        }
        catch(Exception e){
            System.out.println("Please enter the required number of players in the competition and try again");
            return;
        }

        if (competition == null){
            System.out.println("Please select a correct competition type and try again");
            return;
        }

        //adding all the journalist to the competition
        for(Journalist journalist : journalists){
            competition.addJournalist(journalist);
        }

        //launching all the matchs
        competition.play();

        //display the rankings
        competition.displayRanking();
    }
}