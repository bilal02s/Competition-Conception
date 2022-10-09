package competition.exception;

/**
 * Exception thrown when a number of players is incompatible with a competition type.
 */
public class WrongNumberOfPlayersException extends Exception{

    /**
        Exception thrown when an attempt to create a tournament given a list of players that does not match a classical format.
        (the number of players is not a power of 2).
        @param msg The error message.
     */
    public WrongNumberOfPlayersException(String msg){
        super(msg);
    }
}