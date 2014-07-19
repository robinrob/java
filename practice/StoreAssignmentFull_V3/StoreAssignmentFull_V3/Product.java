import java.io.Serializable;

/**
 * This class implements a container which represents a product of
 * the catalogue
 * 
 * @author Pablo Romero
 * @version 2004.08.10
 */
public class Product implements Serializable
{
	// The product and its price
	private String label;
	protected int price;
	private String description = "";

 	/**
	 * Create a new product
   * @param l  the products label
   * @param d  the products price
   **/
   
   public Product(String label, int price)
   {
       this.label = label;
       this.price = price;
    }
    
  	public Product(String label, int price, String description) 
	  {
        this.label = label;
        this.price = price;
        this.description = description;
    }

    /**
     * Accessor for the label
     */
    public String getLabel () 
    {
        return label;
    }

    /**
     * Accessor for the price
     */
    public int getPrice () 
    {
        return price;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void addDescription(String description)
    {
        this.description = description;
    }
    
    public String toString(){
        return ("Name: " + label + "\n" + "Price: " + price + "\n" 
        + "Description: " + description);
    }
    

}
