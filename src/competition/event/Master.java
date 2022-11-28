package competition.event;

import competition.*;
import competition.event.*;
import competition.io.displayer.*;
import competition.journalist.*;
import competition.io.reader.*;
import competition.exception.*;
import competition.match.*;
import java.util.*;
import util.*;

/**
    Master Competition, groups the competitors into a number of pools given by the user.
    select the best players from each pool by the league competition selection for each pool.
    the best players are then confronted together in the final phase in a tournament to determine the winner of the Master.
 */
public class Master extends Competition{
    private CompetitionFactory factory;
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
        @throws InsufficientNumberOfPlayersException if the number of players in the list is less than 4.
     */
    public Master(List<Competitor> competitors) throws InsufficientNumberOfPlayersException{
        super(competitors);

        if(competitors.size() < 3){
            throw new InsufficientNumberOfPlayersException("A master must have at least 4 players.");
        }

        this.factory = new CompetitionFactory();
        this.leagues = new ArrayList<Championnat>();
        this.reader = new ScanTerminal();
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

        while(nbPools <= 0 || nbPools == nbPlayers || nbPlayers % nbPools != 0){
            this.displayer.writeMessage("The number of pools must be a divisor of the total number of competitors.");
            this.displayer.writeMessage("Please chose again a valid number of pools.");
            nbPools = this.reader.getInputInteger();
        }

        this.displayer.writeMessage("The number of players going to the final round must be less than the total number of players and must be a power of 2.");
        this.displayer.writeMessage("How many players goes to the final round?");
        nbFinalRound = this.reader.getInputInteger();

        while(nbFinalRound <= 1 || !Tournoi.isPowerOf2(nbFinalRound) || nbFinalRound >= nbPlayers){
            this.displayer.writeMessage("The number you have entered is not a power of 2.");
            this.displayer.writeMessage("Please chose again how many players goes to the final round?");
            nbFinalRound = this.reader.getInputInteger();
        }

        this.nbPools = nbPools;
        this.nbFinalRound = nbFinalRound;

        this.choseNfirstNbestPlayers(this.nbPools, this.nbFinalRound);
    }

    /**
        Creates a competition using a factory object.
        initialise the competition created with the necessary setup before returning it.
        @return competition type instance
        @throws CanNotCreateCompetitionException if the instantiation of a competition fails
     */
    private Competition getCompetition(String name, List<Competitor> competitors){
        Competition competition;
        
        try{//in case any exception occurs in the instantiation of the league, it is to be thrown as a runtime exception.
            competition = this.factory.getCompetition(name, competitors);
        }
        catch(Exception e){
            throw new CanNotCreateCompetitionException(e.getMessage());
        }

        competition.setDisplayer(this.getDisplayer());
        competition.setMatch(this.getMatch());

        return competition;
    }

    /**
        calculate the firstNplayers and bestNplayers following a certain logic explained in the code in  more details.
     */
    private void choseNfirstNbestPlayers(int nbPools, int nbFinalRound){
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
        this.firstNplayers = (int) (nbFinalRound/nbPools);
        this.bestNplayers = (int) (nbFinalRound % nbPools);
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

            Championnat league = (Championnat) this.getCompetition("league", leaguePlayers);
            this.leagues.add(league);
        }
    }

    /**
        Displays through the displayer all the organized pools, and the competitor's names inside of each pool.
     */
    private void displayPools(){
        int poolCounter = 1;

        this.displayer.writeMessage("All competitors are separated into " + this.leagues.size() + " pools.");
        
        for(Championnat league : this.leagues){
            StringBuilder str = new StringBuilder("Pool " + poolCounter + " contains: ");
            poolCounter++;
            
            for (Competitor competitor : league.getPlayers()){
                str.append(competitor.getName());
                str.append(" ");
            }
            this.displayer.writeMessage(str.toString());
        }
        this.displayer.writeMessage("");
    }

    /**
        This method will play all the already initialised leagues one by one, all by displaying the league being played and the corresponding result.
        and then copy the results of the league into the master's results.
     */
    private void playLeagues(){
        int leagueCounter = 1;

        for(Championnat league : this.leagues){
            this.displayer.writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            this.displayer.writeMessage("League number " + leagueCounter + " will be starting");
            this.displayer.writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            leagueCounter++;
            league.play();
            league.displayRanking();
            this.copyResults(league);
            this.displayer.writeMessage("");
        }
    }

    /**
        Given a league and the n-th rank, determines the first n-th players who had highest scores
        in the given league, returns the result as a list of competitors.
        @param league the league we wish to determine the winners from
        @param firstNplayers the n-th rank we wish to conserve
        @return the list of competitors of the league belonging from the first to the n-th rank
     */
    private List<Competitor> pickFirstNPlayers(Championnat league, int firstNplayers){
        assert league.getNbPlayers() > firstNplayers;

        int counter = 0;
        List<Competitor> firstPlayers = new ArrayList<Competitor>();
        Map<Competitor, Float> ranking = league.ranking();

        for (Competitor competitor : ranking.keySet()){
            if (counter >= firstNplayers){
                break;
            }
            firstPlayers.add(competitor);
            counter ++;
        }        

        return firstPlayers;
    }

    /**
        Picks a number of competitors belonging to the firstNplayer + 1 rank from each league.
        the number of competitors to pick from is equal to bestNplayers.
        (for example we want to pick the best 2 players of the third rank, the method will 
        pick the competitors of the third rank from each pool and then filter out the best 2 of them 
        depending on their scores in their corresponding league)
        @return the list of competitors who are the best from a specific rank
     */
    private List<Competitor> pickBestNPlayers(){
        List<Competitor> bestNplayers = new ArrayList<Competitor>();
        Map<Competitor, Float> toPickFrom = new HashMap<Competitor, Float>();

        //if we do not need any best n players, then return an empty list
        if (this.bestNplayers == 0){
            return bestNplayers;
        }

        //iterate over all leagues
        for (Championnat league : this.leagues){
            int counter = 0;
            Map<Competitor, Float> ranking = league.ranking();

            //iterate over all players sorted by scores in each league
            for (Competitor competitor : ranking.keySet()){
                //pick the player in the n-th rank
                if(counter == firstNplayers){
                    toPickFrom.put(competitor, ranking.get(competitor));
                    break;
                }
                counter++;
            }
        }

        //sort the players in the n-th rank
        toPickFrom = MapUtil.sortByDescendingValue(toPickFrom);

        int counter = 0;
        //iterate over players in the n-th rank to chose only the number that we want.
        for (Competitor competitor : toPickFrom.keySet()){
            if (counter >= this.bestNplayers){
                break;
            }
            bestNplayers.add(competitor);
            counter++;
        }

        return bestNplayers;
    }

    /**
        After playing all the leagues, this method choose the winners from each league that will play 
        in the final phase of the master.
        @return the list of competitors playing the final phase.
     */
    private List<Competitor> choseFinalRoundCompetitors(){
        List<Competitor> finalCompetitors = new ArrayList<Competitor>();

        for (Championnat league : this.leagues){
            finalCompetitors.addAll(this.pickFirstNPlayers(league, this.firstNplayers));
        }

        finalCompetitors.addAll(this.pickBestNPlayers());
        assert finalCompetitors.size() == this.nbFinalRound;

        return finalCompetitors;
    }

    /**
        Iterate over the results of the given competition, and copies it to this master's results.
        @param competition the competition from which it will copy the results
     */
    private void copyResults(Competition competition){
        Map<Competitor, Float> ranking = competition.ranking();

        for(Competitor competitor : ranking.keySet()){
            float oldScore = this.results.get(competitor);
            this.results.put(competitor, oldScore + ranking.get(competitor));
        }
    }


    /**
        Instanciate the tournament instance, displays important information, then plays the tournament.
        @param finalCompetitors the competitors playing the final tournament in the master.
        @throws CanNotCreateCompetitionException if the instantiation of the tournament fails
     */
    protected void play(List<Competitor> finalCompetitors){
        this.finalTournament = (Tournoi) this.getCompetition("tournament", finalCompetitors);

        //display some information about the final tournament
        this.displayer.writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.displayer.writeMessage("We will proceed to the final round.");
        this.displayer.writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.displayer.writeMessage("The competitors facing each other in the last tournament are :");
        for (Competitor competitor : finalCompetitors){
           this.displayer.writeMessage(competitor.getName());
        }
        this.displayer.writeMessage("");

        this.finalTournament.play();
        this.finalTournament.displayRanking();
        this.displayer.writeMessage("");
    }

    /**
        Executes all the necessary steps to setup the different competitions inside the master,
        organize the players, the leagues, play the leagues, display results and compute the winners
        of each phase, running the final tournament to determine the ultimate winner of the master.
     */
    public void play(){
        this.displayer.writeMessage("|********************  Welcome to the Master  ********************|");
        this.getGroupsInformation();
        this.initLeagues();
        this.displayPools();
        this.playLeagues();
        List<Competitor> finalCompetitors = this.choseFinalRoundCompetitors();
        this.play(finalCompetitors);
        this.copyResults(this.finalTournament);
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

    /**
        Returns the list of leagues attribut
        @return this list of leagues
     */
    public List<Championnat> getLeagues(){
        return this.leagues;
    }

    /**
        Returns the final tournament
        @return the final tournament
     */
    public Tournoi getFinalTournament(){
        return this.finalTournament;
    }

    /**
        Returns the attribut nbPools
        @return nbPools
     */
    public int getNbPools(){
        return this.nbPools;
    }

    /**
        Returns the attribut nbFinalRound
        @return nbFinalRound
     */
    public int getNbFinalRound(){
        return this.nbFinalRound;
    }

    /**
        Returns the attribut firstNplayers
        @return firstNplayers
     */
    public int getFirstNPlayers(){
        return this.firstNplayers;
    }

    /**
        Returns the attribut bestNplayers
        @return bestNplayers
     */
    public int getbestNPlayers(){
        return this.bestNplayers;
    }
}