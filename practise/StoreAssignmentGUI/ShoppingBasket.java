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
    private ArrayList<Product> itemList;
    private Formatter formatter;

    /**
     * Create a new ShoppingBasket
   **/
    public ShoppingBasket()
    {
        itemList = new ArrayList<Product>();
        formatter = new Formatter();
    }
    
    
    public void empty()
    {
        itemList.clear();
    }
    
    public boolean isEmpty()
    {
        boolean empty = true;
        for (Product item : itemList){
            if (item != null)
                empty = false;
        }
        return empty;
    }
    
    
    public void add(Product product, int quantity)
    {
        if (!itemExists(product.getLabel()))
            itemList.add(new ShoppingItem(product.getLabel(), product.getPrice(), quantity));
        else
            getItem(product.getLabel()).addQuantity(quantity);
        }
            
            
    
    public boolean itemExists(String name)
    {
        for (Product item : itemList){
            if (item.getLabel().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public boolean removeItem(String name)
    {
        boolean existed = false;
        if (!itemExists(name)){
            return existed;
        }
        
        Product deletedItem = null;

        for (Product item : itemList){
            if (item.getLabel().equals(name)){
                deletedItem = item;
                existed = true;
            }
        }
        itemList.remove(deletedItem);
        return existed;
    }
    
    public int getQuantity(String label)
    {
        for (Product item : itemList){
            if (item.getLabel().equals(label)){
                return ((ShoppingItem) item).getQuantity();
            }
        }
        return 0;
    }
    
    public int getTotal()
    {
        int total = 0;
        for (Product item : itemList){
            total += ((ShoppingItem) item).getTotal();
        }
        return total;
    }
    
    public ArrayList<Product> getItems()
    {
        return itemList;
    }
    
    public ShoppingItem getItem(String label){
        for (Product item : itemList){
            if (item.getLabel().equals(label)){
                return (ShoppingItem) item;
            }
        }
        return null;
    }
    
   // public String toString()
   // {
    //    String string = "";
    //    string += "\n" + formatter.rightJustify("Product name:", 20) + formatter.rightJustify("Price (p):", 12) 
    //    + formatter.rightJustify("Quantity:", 12) + formatter.rightJustify("Total (p):", 12);
    //    int total = 0;
    //    for (Product item : itemList){
    //        string += "\n" + formatter.rightJustify(item.getLabel(), 20) + formatter.rightIntJustify(item.getPrice(), 12) 
    //        + formatter.rightIntJustify((ShoppingItem) item).getQuantity(), 12) + formatter.rightIntJustify(item.getTotal(), 12);
    //        total += item.getTotal();
    //    }
    //    string += "\n\n    Total to pay (p): " + total;
   //     return string;
   // }
    

}
