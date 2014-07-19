/**
 * This class implements a container which represents an item of
 * the shopping list
 * 
 * @author Pablo Romero
 * @version 2004.08.10
 */
public class ShoppingItem extends Product
{
	// The item, its price and number of instances
    private int quantity;

 	/**
	 * Create a new item
   * @param l  the items label
   * @param d  the items price
   * @param n  number of items
   **/
  	public ShoppingItem(String label, int price, int quantity) 
	  {
        super(label, price);
        this.quantity = quantity;
    }

    /**
     * Accessor for the number of items
     */
    public int getQuantity () 
    {
        return quantity;
    }

    /**
     * Returns the total of the price times the number of items
     */
    public int getTotal () 
    {
        return price * quantity;
    }
    
    public void changeQuantity(int newQuantity){
        quantity = newQuantity;
    }

}
