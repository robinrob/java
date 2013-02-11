import java.util.HashSet;

/**
 * This class implements a technical support system. It is the top
 * level class in this project. The support system communicates via
 * text input/output in the text terminal.
 * 
 * This class uses an object of class InputReader to read input from
 * the user, and an object of class Responder to generate responses.
 * It contains a loop that repeatedly reads input and generates output
 * until the users wants to leave.
 * 
 * @author     Michael Kolling and David J. Barnes
 * @version    0.1 (1.Feb.2002)
 */
public class SupportSystem
{
    private InputReader reader;
    private Responder responder;
    
    /**
     * Creates a technical support system.
     */
    public SupportSystem()
    {
        reader = new InputReader();
        responder = new Responder();
    }

    /**
     * Start the technical support system. This will print a welcome
     * message and enter into a dialog with the user, until the user
     * ends the dialog.
     */
    public void start()
    {
        boolean finished = false;

        printWelcome();

        while(!finished) {
            HashSet input = reader.geTInput();
            
            if(input.contains("bye")) {
                finished = true;
            }

            else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
        printGoodbye();
    }
    
    
    private void printWelcome()
    {
        System.out.println("Welcome to the DodgySoft Technical Support System.");
        System.out.println();
        System.out.println("Please tell us about your problem.");
        System.out.println("We will assist you with any problem you might have.");
        System.out.println("Please type 'bye' to exit our system.");
    }
    
    
    private void printGoodbye()
    {
        System.out.println("Nice talking to you. Bye...");
    }
    
    
    
  
   
    

    
    private void printMateWelcome()
    {
        System.out.println("----------------------------------");
        System.out.println("");
        System.out.println("Hello, i'm your Mate.");
        System.out.println();
        System.out.println("Please tell me about what you got up to last night, mate.");
        System.out.println("Please type 'fuck this' to end this conversation.");
    }
    
    private void printMateGoodbye()
    {
        System.out.println("Fuck off you twat.");
    }
    
    /**
     * Print a good-bye message to the screen.
     */

}
