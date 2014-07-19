import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

/**
 * This class implements a shopping basket with functionality to 
 * access and maintain a list of shopping items
 * 
 * @author Pablo Romero
 * @version 2004.08.28
 */
public class ShoppingBasket implements Serializable
{
  // the list of items to be bought
    private ArrayList<ShoppingItem> itemList;
    private Formatter formatter;

    /**
     * Create a new ShoppingBasket
   **/
    public ShoppingBasket()
    {
        itemList = new ArrayList<ShoppingItem>();
        formatter = new Formatter();
    }
    
    
    public boolean isEmpty()
    {
        boolean empty = true;
        for (ShoppingItem item : itemList){
            if (item != null)
                empty = false;
        }
        return empty;
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
    
    public ArrayList<ShoppingItem> getItems()
    {
        return itemList;
    }
    
    public ShoppingItem getItem(String label){
        for (ShoppingItem item : itemList){
            if (item.getLabel().equals(label)){
                return item;
            }
        }
        return null;
    }
    
    public String toString()
    {
        String string = "";
        string += "\n" + formatter.rightJustify("Product name:", 20) + formatter.rightJustify("Price (p):", 12) 
        + formatter.rightJustify("Quantity:", 12) + formatter.rightJustify("Total (p):", 12);
        int total = 0;
        for (ShoppingItem item : itemList){
            string += "\n" + formatter.rightJustify(item.getLabel(), 20) + formatter.rightIntJustify(item.getPrice(), 12) 
            + formatter.rightIntJustify(item.getQuantity(), 12) + formatter.rightIntJustify(item.getTotal(), 12);
            total += item.getTotal();
        }
        string += "\n\n    Total to pay (p): " + total;
        return string;
    }
    

}
