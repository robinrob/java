import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Class InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is then returned as a String.
 * 
 * @author     Michael Kolling and David J. Barnes
 * @version    0.1  (31.Oct.2001)
 */
public class InputReader
{
    private BufferedReader reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new BufferedReader(new InputStreamReader(System.in));;
    }

    /**
     * Read a line of text from standard input (the text terminal), and
     * return it as a String.
     *
     * @return  A String typed by the user.
     */
    public String getInput() 
    {
        System.out.print("> ");                // print prompt
        String inputLine = readInputLine();
        inputLine = inputLine.trim().toLowerCase();
        return inputLine;
    }
    
    public HashSet geTInput()
    {
        System.out.print("Your message > ");
        String inputLine = readInputLine().trim().toLowerCase();
        
        String[] wordArray = inputLine.split(" ");
        
        //add words from array into hashset
        HashSet words = new HashSet();
        for(int i=0; i < wordArray.length; i++){
            words.add(wordArray[i]);
        }
        return words;
    }
    


    /**
     * Read one line of input and return it as a String. 
     *
     * @return  A String representing the input, or an empty String 
     *          if an error occurs.
     */
    private String readInputLine()
    {
        String line = "";

        try {
            line = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("Read error: " + exc.getMessage());
        }
        return line;
    }
}
