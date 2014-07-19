import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements a catalogue with functionality to support the
 * search for products.
 * 
 * @author Pablo Romero
 * @version 2004.08.28
 */
public class Catalogue
{

    private ArrayList<Product> productList;

    /**
     * Create a new Catalogue
   **/
    public Catalogue() 
      {
        productList = new ArrayList<Product>();
        addProducts();
    }

    /**
     * This method adds products to the productList field. It is a simple 
     * solution. The sophisticated version should add products from a text
     * file
     */
    private void addProducts () 
    {
        addProduct("allbran cereal",250);
        addProduct("baked beans",120);
        addProduct("corn flakes cereal",250);
        addProduct("doughnuts (packet of 4)",65);
        addProduct("fresh milk",58);
        addProduct("orange juice",70);
        addProduct("olive oil",320);
        addProduct("peanut butter",137);
    }

    /**
     * This method adds a new product to the productList field
     * @param product  the new product to be added
     * @param price  the products price
     * @return true if the product didn't exist and could be added,
     *         false otherwise
     */
    public boolean addProduct(String product, int price) 
    {
        boolean found = true;
        if (!productExists(product)){
            productList.add(new Product(product, price));
            found = false;
        }     
        return !found;
    }
    
    public boolean productExists(String name)
    {
        for (Product product : productList){
            if (product.getLabel().equals(name)){ 
                return true; 
            }
        }
        return false;
    }
        
     
    public int getProductPrice(String name)
    {
        for (Product product : productList){
            if (product.getLabel().equals(name)){
                return product.getPrice();
            }
        }
        return 0;
    }
    
    public ArrayList<String> getProducts()
    {
        ArrayList<String> productNames = new ArrayList<String>();
        for (Product product : productList){
            productNames.add(product.getLabel());
        }
        return productNames;
    }
            
    
    
}
