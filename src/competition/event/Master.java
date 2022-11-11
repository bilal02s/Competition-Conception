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
    private int firstNplayers;
    private int bestNplayers;
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

    /**
        Retrieves information about the distribution of pools from the external environment using the reader instance.
     */
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

        this.choseNfirstNbestPlayers();
    }

    /**
        calculate the firstNplayers and bestNplayers following a certain logic explained in the code in  more details.
     */
    private void choseNfirstNbestPlayers(){
        assert this.nbPools > 0;
        assert this.nbFinalRound > 0;

        /*
            since we want to pick the winners from each pool to the final round in an equal way,
            we will pick all the first players from each pool to the final round, if we need more players, we will add all the second players from each pool and so on,
            we call it the first n players (designating the first players or the first two players or first three players...),
            if we need more players to the final round but it is less than the number of pool we face a problem.
            thus we will be picking the best n players to complete the missing number for the final round (best third players from the pools for example).
            following the logic the best n players is less than the number of pools and is equal to the rest of the division of nbfinalround and nbpools.
            hence, the firstNplayers designate that we will be picking the first n players from each pool for the final round
            and the bestNplayers designate the number of missing players to the final round.
            
        */
        this.firstNplayers = (int) (this.nbFinalRound/this.nbPools);
        this.bestNplayers = (int) (this.nbFinalRound % this.nbPools);
    }

    /**
        Verifies that the attributs nbPools is initialised to a correct value.
        iterate over the number of pools all while creating the list of corresponding players and instantiating league competitions.
        and then add the instance to the list of leagues.
        if the instantiation of a league fails for any reason, an exception is thrown.
        @throws CanNotCreateCompetitionException if the instantiation of a league fails
     */
    private void initLeagues(){
        assert this.nbPools > 0;
        assert this.competitors.size() % this.nbPools == 0;
        
        int nbPlayersEachPool = (int) (this.competitors.size()/this.nbPools);
        int indexCompetitors = 0;

        //iterating over the number of pools
        for(int leagueIndex = 0; leagueIndex < this.nbPools; leagueIndex++){
            List<Competitor> leaguePlayers = new ArrayList<Competitor>();

            //iterating over the number of competitors in each pool
            for (int i = 0; i < nbPlayersEachPool; i++){
                leaguePlayers.add(this.competitors.get(indexCompetitors));
                indexCompetitors++;
            }

            Championnat league;
            try{//in case any exception occurs in the instantiation of the league, it is to be thrown as a runtime exception.
                league = new Championnat(leaguePlayers);
            }
            catch(InsufficientNumberOfPlayersException e){
                throw new CanNotCreateCompetitionException(e.getMessage());
            }

            this.leagues.add(league);
        }
    }

    /**
        Displays through the displayer all the organized pools, and the competitor's names inside of each pool.
     */
    private void displayPools(){
        assert this.nbPools > 0;

        int poolCounter = 1;

        this.displayer.writeMessage("All competitors are separated into " + this.nbPools + " pools.");
        
        for(Championnat league : this.leagues){
            this.displayer.writeMessage("Pools " + poolCounter + " contains:");
            
            for (Competitor competitor : league.getPlayers()){
                this.displayer.writeMessage(competitor.getName());
            }
            this.displayer.writeMessage("");
        }
        this.displayer.writeMessage("");
    }

    /**
        This method will play all the already initialised leagues one by one, all by displaying the league being played and the corresponding result.
     */
    private void playLeagues(){
        int leagueCounter = 1;

        for(Championnat league : this.leagues){
            this.displayer.writeMessage("League number " + leagueCounter + " will be starting");
            league.play();
            league.displayRanking();
            this.displayer.writeMessage("");
        }
        this.displayer.writeMessage("");
    }

    private Competitor[] pickFirstNPlayers(Championnat league, int firstNplayers){
        Competitor[] firstPlayers = new Competitor[firstNplayers];
        
        return firstPlayers;
    }

    private void choseFinalRoundCompetitors(){

    }

    public void play(){
        this.initLeagues();
        this.displayPools();
        this.playLeagues();
        this.choseFinalRoundCompetitors();
        //this.play()
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