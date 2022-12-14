package util;

import static org.junit.Assert.*;
import org.junit.*;
import util.Pair;

public class PairTest{

    /**
        test getKey, the same instance
     */
    @Test 
    public void testGetKey(){
        String key = "toto";
        Pair pair = new Pair<String, Integer>(key, 3);

        assertSame(key, pair.getKey());
    }

    /**
        test getValue, the same instance
     */
    @Test 
    public void testGetValue(){
        String key = "toto";
        Pair pair = new Pair<String, Integer>(key, 3);

        assertSame(3, pair.getValue());
    }

    /**
        test getKey and getValue should return the same original instance.
     */
    @Test 
    public void testGetKeyValue(){
        String key = "toto";
        String value = "3";
        Pair pair = new Pair<String, String>(key, value);

        assertSame(key, pair.getKey());
        assertSame(value, pair.getValue());
    }
}