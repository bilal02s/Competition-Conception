package competition.competition;

import java.util.*;
import competition.*;

public class Championnat extends Competition {
    public Map<Competitor, Competitor> play() {
        Map<Competitor, Competitor> distribution = new HashMap<Competitor, Competitor>();

        for (Competitor c1 : this.competitors){
            for (Competitor c2 : this.competitors){
                distribution.put(c1, c2);
            }
        }

        return distribution;
    }
}