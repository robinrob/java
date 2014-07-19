import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements a shopping basket with functionality to 
 * access and maintain a list of shopping items
 * 
 * @author Pablo Romero
 * @version 2004.08.28
 */
public class ShoppingBasket
{
  // the list of items to be bought
    private ArrayList<ShoppingItem> itemList;

    /**
     * Create a new ShoppingBasket
   **/
    public ShoppingBasket()
    {
        itemList = new ArrayList<ShoppingItem>();
    }
    
    public boolean addItem(String name, int price, int quantity)
    {
        if (!itemExists(name)){      
            itemList.add(new ShoppingItem(name, price, quantity));
            return true;
        }
        return false;
    }
    
    public boolean itemExists(String name)
    {
        for (ShoppingItem item : itemList){
            if (item.getLabel().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteItem(String name)
    {
        boolean existed = false;
        if (!itemExists(name)){
            return existed;
        }
        
        ShoppingItem deletedItem = null;
        for (ShoppingItem item : itemList){
            if (item.getLabel().equals(name)){
                deletedItem = item;
                existed = true;
            }
        }
        itemList.remove(deletedItem);
        return existed;
    }
    
    public int getTotal()
    {
        int total = 0;
        for (ShoppingItem item : itemList){
            total += item.getTotal();
        }
        return total;
    }

}
