package util;

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

    public Key getKey(){
        return this.key;
    }

    public Value getValue(){
        return this.value;
    }
}