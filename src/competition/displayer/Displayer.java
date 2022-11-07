package competition.displayer;

/**
    Displayer Interface.
 */
public interface Displayer{

    /**
        Writes the received message in the parameter to the appropriate output stream.
        @param msg the message to be written.
     */
    public void writeMessage(String msg);
}