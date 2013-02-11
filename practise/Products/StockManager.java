import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manage the stock in a business.
 * The stock is described by zero or more Products.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StockManager
{
    // A list of the products.
    private ArrayList stock;

    /**
     * Initialise the stock manager.
     */
    public StockManager()
    {
        stock = new ArrayList();
    }

    /**
     * Add a product to the list.
     * @param item The item to be added.
     */
    public void addProduct(Product item)
    {
        if(findProduct(item.getID()) == null){
        stock.add(item);
    }
    else if(findProduct(item.getID()) != null){
        System.out.println("Product already in stock with this ID. addProduct failed.");
    }
}
    
    /**
     * Receive a delivery of a particular product.
     * Increase the quantity of the product by the given amount.
     * @param id The ID of the product.
     * @param amount The amount to increase the quantity by.
     */
    public void delivery(int id, int amount)
    {
        Product product = (Product) findProduct(id);
        product.increaseQuantity(amount);
    }
    
    /**
     * Try to find a product in the stock with the given id.
     * @return The identified product, or null if there is none
     *         with a matching ID.
     */
    public Product findProduct(int id)
    {
        Iterator it = stock.iterator();
        while(it.hasNext()){
            Product product = (Product) it.next();
            if(product.getID() == id){
        return product;
    }
}
    return null;
    }
    
    /**
     * Locate a product with the given ID, and return how
     * many of this item are in stock. If the ID does not
     * match any product, return zero.
     * @param id The ID of the product.
     * @return The quantity of the given product in stock.
     */
    public int numberInStock(int id)
    {
        Product product = (Product) findProduct(id);
        if(product != null){
        return product.getQuantity();
    }
    else{
        System.out.println("Item not found.");
        return 0;
    }
}

    /**
     * Print details of all the products.
     */
    public void printProductDetails()
    {
        Iterator it = stock.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
    }
    
    public void showLowStock(int dangerLevel)
    {
        Iterator it = stock.iterator();
        while(it.hasNext()){
            Product product = (Product) it.next();
            if(product.getQuantity() < dangerLevel){
                System.out.println(product.toString());
            }
        }
    }
    
    public Product findName(String name){
        Iterator it = stock.iterator();
        while(it.hasNext()){
            Product product = (Product) it.next();
            if(product.getName().equals(name)){
                return product;
            }
        }
            System.out.println("Product name not found.");
            return null;
     }
}
