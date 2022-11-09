package competition.io.reader;

/**
    Reader interface.
 */
public interface Reader{

    /**
        Checks the existence of a new token.
        @return true if there is a new token to be read
     */
    public boolean hasNext();

    /**
        returns the next token as a string
        @return the next string
     */
    public String getInputString();

    /**
        return the next token as an integer
        @return the next integer
     */
    public int getInputInteger();
}