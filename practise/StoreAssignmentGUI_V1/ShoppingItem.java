/**
 * This class implements a container which represents an item in
 * the shopping basket.  It assosciates a product with a quantity.
 * 
 * @author Robin Smith
 * @version 2009.01.18
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
    public int getQuantity() 
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
    
    public void addQuantity(int newQuantity){
        quantity += newQuantity;
    }
    

}
