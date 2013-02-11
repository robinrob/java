/**
 * This class implements a container which represents a product of
 * the catalogue
 * 
 * @author Pablo Romero
 * @version 2004.08.10
 */
public class Product
{
	// The product and its price
	private String label;
	private int price;

 	/**
	 * Create a new product
   * @param l  the products label
   * @param d  the products price
   **/
  	public Product(String l, int p) 
	  {
        label = l;
        price = p;
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

}
