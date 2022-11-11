package competition.event;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;
import competition.io.reader.*;
import competition.io.displayer.*;
import competition.match.mock.MockMatch;
import competition.io.mock.MockDisplayerReader;

public class MasterTest extends CompetitionTest{
    private Master master;

    protected Competition createComp(List<Competitor> competitors) throws InsufficientNumberOfPlayersException{
        Master master = new Master(competitors);
        MockDisplayerReader mock = this.initMock();
        master.setReader(mock);
        master.setDisplayer(mock);
        return master;
    }

    private Master createMaster(List<Competitor> competitors) throws InsufficientNumberOfPlayersException{
        return (Master) this.createComp(competitors);
    }

    private MockDisplayerReader initMock(){
        MockDisplayerReader mock = new MockDisplayerReader();
        mock.putAnswer("How many pools do you want to have?", "2");
        mock.putAnswer("How many players goes to the final round?", "2");
        return mock;
    }

    @Before 
    public void init(){
        super.init();
        
        try{
            this.comp = this.createComp(this.joueurs);
        }
        catch(Exception e){
            fail();
        }
    }

    @Before 
    public void initMaster() {
        this.joueurs = new ArrayList<Competitor>();
        this.joueurs.add(new Competitor("toto"));
        this.joueurs.add(new Competitor("tata"));
        this.joueurs.add(new Competitor("tutu"));
        this.joueurs.add(new Competitor("tati"));

        try{
            this.master = new Master(this.joueurs);
        }
        catch(Exception e){
            fail();
        }

        //prevent printing to console
        MockDisplayerReader IO = new MockDisplayerReader();
        this.master.setDisplayer(IO);
        this.master.setReader(IO);
    }

    /**
        tests if the method setReader, sets the new Reader instance, and if getReader returns the new setted instance.
     */
    @Test 
    public void testGetSetReader(){
        Reader reader = new ScanTerminal();
        assertFalse(reader == this.master.getReader());

        this.master.setReader(reader);
        assertTrue(reader == this.master.getReader());
    }

    /**
        the constructor assign to the attribut reader by default an instance of ScanTerminal.
     */
    @Test 
    public void testReaderDefaultValue() throws InsufficientNumberOfPlayersException{
        List<Competitor> joueurs = new ArrayList<Competitor>();
        joueurs.add(new Competitor("toto"));
        joueurs.add(new Competitor("tata"));
        joueurs.add(new Competitor("tutu"));
        joueurs.add(new Competitor("tati"));
        //Master master = new Master(this.joueurs);

        //assertTrue(master.getReader() instanceof ScanTerminal);
    }

    @Test 
    public void testPlay(){}
}