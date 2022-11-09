package competition.io.reader;

import java.util.Scanner;
import java.util.InputMismatchException;
import competition.io.reader.Reader;

/**
    Serves as a way to input data through scanning the terminal, implementing the reader interface
 */
public class ScanTerminal implements Reader{
    private Scanner scanner;

    /**
        Initialising the scanner attribut with a new scanner instance scanning input from the standard input.
     */
    public ScanTerminal(){
        this.scanner = new Scanner(System.in);
    }

    /**
        Returns true if this scanner has another token in its input. This method may block while waiting for input to scan. The scanner does not advance past any input.
        @return true if and only if this scanner has another token
     */
    public boolean hasNext(){
        return this.scanner.hasNext();
    }

    /**
        returns the next token scanned from the scanner as a String
        @return the next token
     */
    public String getInputString(){
        return this.scanner.next();
    }

    /**
        return the next token scanned from the scanner as an integer
        @return the next integer
     */
    public int getInputInteger() {
        int value = -1;
        boolean integerFound = false;

        while(!integerFound){
            try{    
                value = this.scanner.nextInt();
                integerFound = true;
            }
            catch(InputMismatchException e){
                this.scanner.nextLine();
                System.out.println("Please enter an integer");
            }
        }
        
        
        return value;
    }
}