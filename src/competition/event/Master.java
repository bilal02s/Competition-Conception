package competition.event;

import competition.*;
import competition.event.*;
import competition.io.displayer.*;
import competition.io.reader.*;
import competition.exception.*;
import competition.match.*;
import java.util.*;

/**
    Master Competition, groups the competitors into a number of pools given by the user.
    select the best players from each pool by the league competition selection for each pool.
    the best players are then confronted together in the final phase in a tournament to determine the winner of the Master.
 */
public class Master extends Competition{
    private List<Championnat> leagues;
    private Tournoi finalTournament;
    private int nbPools;
    private int nbFinalRound;
    private Reader reader;

    /**
        Initialise the reader attribut to a new ScanTerminal instance by default.
        Calls the private method initGroups.
        Throws an error if the number of given competitors is less than 4.
        @param competitors List of participants
        @param displayer Interacting with external source through the displayer (write or read information)
        @throws InsufficientNumberOfPlayersException if the number of players in the list is less than 4.
     */
    public Master(List<Competitor> competitors) throws InsufficientNumberOfPlayersException{
        super(competitors);

        if(competitors.size() < 4){
            throw new InsufficientNumberOfPlayersException("A master must have at least 4 players.");
        }

        this.reader = new ScanTerminal();
        this.getGroupsInformation();
    }

    private void getGroupsInformation(){
        int nbPlayers = this.competitors.size();
        int nbPools;
        int nbFinalRound;

        this.displayer.writeMessage("How many pools do you want to have?");
        nbPools = this.reader.getInputInteger();

        while(nbPlayers % nbPools != 0){
            this.displayer.writeMessage("The number of pools must be a divisor of the total number of competitors.");
            this.displayer.writeMessage("Please chose again a valid number of pools.");
            nbPools = this.reader.getInputInteger();
        }

        this.displayer.writeMessage("The number of players going to the final round must be a power of 2.");
        this.displayer.writeMessage("How many players goes to the final round?");
        nbFinalRound = this.reader.getInputInteger();

        while(!Tournoi.isPowerOf2(nbFinalRound)){
            this.displayer.writeMessage("The number you have entered is not a power of 2.");
            this.displayer.writeMessage("how many players goes to the final round?");
            nbFinalRound = this.reader.getInputInteger();
        }

        this.nbPools = nbPools;
        this.nbFinalRound = nbFinalRound;
    }

    private void initLeagues(){
        assert this.nbPools > 0;
        
        int nbPlayersEachPool = (int) this.competitors.size()/nbPools;
        int indexCompetitors = 0;

        //iterating over the number of pools
        for(int leagueIndex = 0; leagueIndex < this.nbPools; leagueIndex++){
            List<Competitor> leaguePlayers = new ArrayList<Competitor>();

            //iterating over the number of competitors in each pool
            for (int i = 0; i < nbPlayersEachPool; i++){
                leaguePlayers.add(this.competitors.get(indexCompetitors));
                indexCompetitors++;
            }

            Championnat league = new Championnat(leaguePlayers);
            this.leagues.add(league);
        }
    }

    /**
        Sets this master's reader type 
        @param reader A reader type instance 
     */
    public void setReader(Reader reader){
        this.reader = reader;
    }

    /**
        returns this master's reader type instance.
        @return this master's reader instance
     */
    public Reader getReader(){
        return this.reader;
    }

    protected void play(List<Competitor> players){}
}