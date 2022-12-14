package competition.exception;

/**
 * Exception representing not enough players to start a competition. 
 */
public class InsufficientNumberOfPlayersException extends Exception{

    /**
        Exception throws when an attempt to create a competition with not enough participants.
        @param msg The error message.
     */
    public InsufficientNumberOfPlayersException(String msg){
        super(msg);
    }
}