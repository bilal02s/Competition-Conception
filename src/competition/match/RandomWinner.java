package competition.match;

import java.util.*;
import competition.*;
import competition.match.*;

public class RandomWinner implements Match{
    public Competitor playMatch(Competitor c1, Competitor c2){
        Random rand =  new Random();
        int choice = rand.nextInt(2);

        return (choice == 1) ? c2 : c1;
    }
}