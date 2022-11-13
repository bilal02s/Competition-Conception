package competition.io.mock;

import java.util.*;
import competition.io.reader.*;
import competition.io.displayer.*;
import competition.io.mock.MockDisplayerReaderException;

/**
    this Mock is used to test the competitions in which some user inputs are required
 */
public class MockDisplayerReader implements Displayer, Reader{
    private Map<String, String> answers; //the first string correspond to a question, the second correspond to an answer.
    private String lastReceivedMessage;

    /**
        Initialise a hasmap to be used to save input answers to questions that might be asked later.
     */
    public MockDisplayerReader(){
        this.answers = new HashMap<String, String>();
    }

    private String getAnswer(String question){
        for (String str : this.answers.keySet()){
            if (str.equals(question)){
                return this.answers.get(str);
            }
        }

        return null;
    }

    /**
        stock the printed message to a variable to be used later when asked for an input.
        @param msg The printed message
     */
    public void writeMessage(String msg){
        this.lastReceivedMessage = msg;
    }

    /**
        Force value to true, there is always a token to be given.
        @return true
     */
    public boolean hasNext(){
        return true;
    }

    /**
        returns the string answer saved in the hashmap (answers) corresponding to the question present 
        in the last received message
        @return the answer to the last received message
        @throws MockDisplayerReaderException if there was no answer set to the last received message
     */
    public String getInputString(){
        String answer = this.getAnswer(this.lastReceivedMessage);

        if (answer == null){
            throw new MockDisplayerReaderException("answer not found");
        }

        return answer;
    }

    /**
        returns the integer answer saved in the hashmap (answers) corresponding to the question present
        in the last received message.
        @return the integer answer to the last received message
        @throws MockDisplayerReaderException if there was no answer set that is an integer to the last received message
     */
    public int getInputInteger(){
        int answer;
        try{
            answer = Integer.parseInt(this.getAnswer(this.lastReceivedMessage));
        }
        catch(Exception e){
            throw new MockDisplayerReaderException("Answer is not an integer, or answer not found");
        }

        return answer;
    }

    /**
        Puts inside the hashmap a combination of question and answer, 
        so that when asked a question through the call to writeMessage, 
        the question in saved in the attribut lastReceivedMessage, and then we use it
        to retrieve the desired answer from the hashmap when asked an input by the call to
        getInputInteger or getInputString, simulating a user answering a question.
     */
    public void putAnswer(String question, String answer){
        this.answers.put(question, answer);
    }
}