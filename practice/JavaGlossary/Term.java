 

/**
 * This class implements a container which represents a term of
 * the glossary
 * 
 * @author Pablo Romero
 * @version 2004.08.10
 */
public class Term
{
	// The term and its definition
	private String label;
	private String definition;

 	/**
	 * Create a new term
   * @param l  the terms label
   * @param d  the terms definition
   **/
  	public Term(String label, String definition) 
	  {
        this.label = label;
        this.definition = definition;
    }

    /**
     * Accessor for the label
     */
    public String getLabel () 
    {
        return label;
    }

    /**
     * Accessor for the definition
     */
    public String getDefinition () 
    {
        return definition;
    }

}
