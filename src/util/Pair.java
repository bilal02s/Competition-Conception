package util;

/**
    Pair data structure, representing a single (key, value) pair
 */
public class Pair<Key, Value>{
    private final Key key;
    private final Value value;

    /**
        Constructs a new pair, with a given key and value 
        @param key the pair's key
        @param value the pair's value
     */
    public Pair(Key key, Value value){
        this.key = key;
        this.value = value;
    }

    /**
        returns the key of this pair
        @return the key of this pair
     */
    public Key getKey(){
        return this.key;
    }

    /**
        returns the value associated of the key of this pair
        @return the value of this pair
     */
    public Value getValue(){
        return this.value;
    }
}