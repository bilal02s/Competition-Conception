package competition.exception;

public class WrongNumberOfPlayersError extends Exception{
    public WrongNumberOfPlayersError(String msg){
        super(msg);
    }
}