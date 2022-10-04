package competition.exception;

public class WrongNumberOfPlayersException extends Exception{
    public WrongNumberOfPlayersException(String msg){
        super(msg);
    }
}