package competition.io.displayer;

import competition.io.displayer.Displayer;


/**
    This class is used to replace real displayers when testing, to avoid printing unnecessary information.
 */
public class DummyDisplayer implements Displayer{

    /**
        This method does nothing with the given message, essentially rendering it useless.
     */
    public void writeMessage(String msg){}
}