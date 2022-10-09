package competition;

/**
 * Creates a competitor that participates in a competition.
 */
public class Competitor {
    private String name;

    /**
        initialise the name of the player to the name given in parameter.
        @param name the name given to the player
     */
    public Competitor(String name){
        this.name = name;
    }

    /**
        returns the name of the player.
        @return the attribut name of the player.
     */
    public String getName(){
        return this.name;
    }

    /**
        receives an object o, and verifies if this object is the same as this current instance, by checking if the names are equal.
        @param o the object to be tested.
        @return True if the two objects are equal, False otherwise.
     */
    public boolean equals(Object o){
        if(o instanceof Competitor){
            Competitor other = (Competitor) o;
            return this.getName().equals(other.getName());
        }

        return false;
    }

    /**
        returns a String describing the instance, which is the name of the player.
        @return the name of the player.
     */
    public String toString(){
        return this.name;
    }
}