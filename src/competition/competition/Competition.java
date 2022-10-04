package competition.competition;

import java.util.*;
import competition.*;

public abstract class Competition {
    protected final List<Competitor> competitors;
    protected Map<Competitor, Integer> results;

    /**
        initialise the attribut competitors with the players given parameter.
        initialise the hashmap results by putting inside of it all the players given in parameter, and assigning a value of zero to their corresponding scores.
     */
    public Comptition(List<Competitor> competitors) {
        this.competitors = competitors;
        this.results = new HashMap<Competitor, Integer>();

        for (Competitor c : competitors){
            results.put(c, 0);
        }
    }

    /**
        returns the list of players in the competition.
        @return the list of players in the competition.
     */
    public List<Competitor> getPlayers() {
        return this.competitors;
    }

    /**
        return the number of players in the competition.
        @return the number of players in the competition.
     */
    public int getNbPlayers() {
        return this.competitors.size();
    }

    public void play() {
        this.play(this.competitors);
    }

    protected abstract void play(List<Competitor> players);
    protected abstract void playMatch(Competitor c1, Competitor c2);

    /**
        returns the hasmap which maps each player in the competiton to his result expressed as an integer, sorted in descending order.
        @return sorted map of the results.
     */
    public Map<Competitor, Integer> ranking(Map<Competitor, Integer> results) {
        return MapUtil.sortByDescendingValue(results);
    }
}