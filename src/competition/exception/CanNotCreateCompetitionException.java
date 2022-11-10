package competition.exception;

/**
    Exception when an attempt to create a competition fails, and the client can not do anything to recover from the exception.
 */
public class CanNotCreateCompetitionException extends RuntimeException{

    /**
        Exception thrown when an object fails to create a competition.
     */
    public CanNotCreateCompetitionException(String msg){
        super(msg);
    }
}