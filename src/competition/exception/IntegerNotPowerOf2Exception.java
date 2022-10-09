package competition.exception;

public class IntegerNotPowerOf2Exception extends Exception{

    /**
        Exception thrown when an attempt to get the log2 value of an integer when it is not a power of 2.
        @param msg The error message.
     */
    public IntegerNotPowerOf2Exception(String msg){
        super(msg);
    }
}