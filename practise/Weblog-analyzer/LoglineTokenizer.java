import java.util.StringTokenizer;

/**
 * Break up line from a web server log file into
 * its separate fields.
 * Currently, the log file is assumed to contain simply
 * integer date and time information.
 * 
 * @author David J. Barnes and Michael Kolling.
 * @version 2001.12.31
 */
public class LoglineTokenizer
{
    /**
     * Construct a LogLineAnalyzer
     */
    public LoglineTokenizer()
    {
    }

    /**
     * Tokenize a log line. Place the integer values from
     * it into an array. The length of the array must match
     * the number of tokens on the line.
     *
     * @param logline The line to be tokenized.
     * @param dataLine Where to store the values.
     */
    public void tokenize(String logline, int[] dataLine)
    {
        StringTokenizer tokens = new StringTokenizer(logline);
        if(tokens.countTokens() == dataLine.length) {
            String number = "";
            try{
                StringTokenizer tokenizer = new StringTokenizer(logline);
                int numTokens = tokenizer.countTokens();
                for(int i = 0; i < numTokens; i++) {
                    number = tokenizer.nextToken();
                    dataLine[i] = Integer.parseInt(number);
                }
            }
            catch(NumberFormatException e) {
                System.out.println("Badly formatted number: " + number);
                System.out.println("In log line: " + logline);
            }
        }
        else {
            System.out.println("Invalid log line: " + logline);
        }
    }
}
