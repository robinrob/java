import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Write a description of class input here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InputReader
{

    private BufferedReader reader;

    public InputReader()
    {
        reader = new BufferedReader(new InputStreamReader(System.in));;
    }
    
    public String getInput()
    {
        System.out.println("> ");
        String inputLine = readInputLine();
        
        return inputLine;
    }
    
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
