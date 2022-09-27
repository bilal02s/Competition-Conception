package competition.competition;

public abstract class Competition {
    protected final List<Competitor> competitors;
    protected Map<Competitor, Integer> results;

    public Comptition(List<Competitor> competitors) {
        this.competitors = competitors;
        this.results = new HashMap<Competitor, Integer>();

        for (Competitor c : competitors){
            results.put(c, 0);
        }
    }


}