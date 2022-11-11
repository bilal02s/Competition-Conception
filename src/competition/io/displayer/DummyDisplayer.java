package competition.io.displayer;

import competition.io.displayer.Displayer;


/**
    This class is used to replace real displayers when testing, to avoid printing unnecessary information.
    Also used when we want not to display anything in the program.
 */
public class DummyDisplayer implements Displayer{

    /**
        This method does nothing with the given message, essentially rendering it useless.
     */
    public void writeMessage(String msg){}
}