 

/**
 * This class implements a container which represents the distance
 * between two words. It is used to record the edit distance between
 * a main and a secondary word.
 * 
 * @author Pablo Romero
 * @version 2004.08.10
 */
public class WordDistance
{
	// The two words to be compared
	private String word1;
	private String word2;

	// Their edit distance
  private int distance;

 	/**
	 * Create a new WordDistance
   * @param a  main word
   * @param b  word to be compared
   * @param ed  their edit distance
   **/
  	public WordDistance(String Word1, String Word2, int edit_distance) 
	  {
        word1 = Word1;
        word2 = Word2;
        distance = edit_distance;
    }
    


    /**
     * Accessor for the main word
     */
    public String from() 
    {
        return word1;
    }

    /**
     * Accessor for the secondary word
     */
    public String to() 
    {
        return word2;
    }

    /**
     * Accessor for the edit distance
     */
    public int getDistance () 
    {
        return distance;
    }

}
