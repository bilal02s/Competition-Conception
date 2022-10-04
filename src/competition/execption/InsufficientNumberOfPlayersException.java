package competition.exception;

public class InsufficientNumberOfPlayersError extends Exception{
    public InsufficientNumberOfPlayersError(String msg){
        super(msg);
    }
}