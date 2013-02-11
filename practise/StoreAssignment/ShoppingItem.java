/**
 * This class implements a container which represents an item of
 * the shopping list
 * 
 * @author Pablo Romero
 * @version 2004.08.10
 */
public class ShoppingItem
{
	// The item, its price and number of instances
	private String label;
	private int price;
  private int number;

 	/**
	 * Create a new item
   * @param l  the items label
   * @param d  the items price
   * @param n  number of items
   **/
  	public ShoppingItem(String l, int p, int n) 
	  {
        label = l;
        price = p;
        number = n;
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

    /**
     * Accessor for the number of items
     */
    public int getNumber () 
    {
        return number;
    }
    
    public int getTotal()
    {
        return price*number;
    }

}
