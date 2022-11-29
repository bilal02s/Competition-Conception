package competition.journalist;

import static org.junit.Assert.*;
import org.junit.*;
import competition.journalist.report.*;
import competition.journalist.*;

public class JournalistTest{
    Journalist journalist;
    String name;
    List<String> news;

    protected abstract Journalist getJournalist(String name, List<String> news);

    @Before 
    public void init(){
        this.name = "test";
        this.news = new ArrayList<String>();
        this.journalist = this.getJournalist(this.name, this.news);
    }

    /**
        test if getName will return the correct attributed name during the construction.
     */
    @Test
    public void testGetName(){
        assertEquals(this.name, player.getName());
        assertFalse("Test".equals(player.getName()));
    }

    /**
        test if toString will return the name of the journalist.
     */
    @Test
    public void testToString(){
        assertEquals(this.name, player.getName());
        assertFalse("Boo".equals(player.getName()));
    }
}