import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.awt.image.BufferedImage;

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
    private PhraseGenerator phraseGenerator;
    private Formatter formatter;

    /**
     * Create a new Catalogue
   **/
    public Catalogue() 
      {
        productList = new ArrayList<Product>();
        phraseGenerator = new PhraseGenerator();
        formatter = new Formatter();
    }

    /**
     * This method adds products to the productList field. It is a simple 
     * solution. The sophisticated version should add products from a text
     * file
     */

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
            phraseGenerator.addTermWordsToLexicon(product);
            found = false;
        }     
        return !found;
    }
    
    public boolean addProduct(String product, int price, String description, BufferedImage image) 
    {
        boolean found = true;
        if (!productExists(product)){
            productList.add(new Product(product, price, description, image));
            phraseGenerator.addTermWordsToLexicon(product);
            found = false;
        }     
        return !found;
    }    
    
    public boolean addProduct(Product product)
    {
        boolean found = true;
        if (!productExists(product.getLabel())){
            productList.add(product);
            phraseGenerator.addTermWordsToLexicon(product.getLabel());
            found = false;
        }
        return !found;
    }
    
    public boolean addProductDescription(String label, String description)
    {
        if (productExists(label)){
            getProduct(label).addDescription(description);
            return true;
        }
        return false;
    }
            
    
    public boolean productExists(String label)
    {
        for (Product product : productList){
            if (product.getLabel().equals(label)){ 
                return true; 
            }
        }
        return false;
    }
    
    public Product getProduct(String label){
        for (Product product : productList){
            if (product.getLabel().equals(label)){
                return product;
            }
        }
        return null;
    }
     
    public int getProductPrice(String label)
    {
        if (productExists(label)){
            return getProduct(label).getPrice();
        }
        return 0;
    }

    public String getProductDetails(String label)
    {
        return getProduct(label).toString();
    }
    
    public ArrayList<String> getLabels()
    {
        ArrayList<String> productNames = new ArrayList<String>();
        for (Product product : productList){
            productNames.add(product.getLabel());
        }
        return productNames;
    }
    
    public ArrayList<Product> getProducts()
    {
        return productList;
    }
    public ArrayList<Product> getProducts(String type)
    {
        ArrayList<Product> products = new ArrayList<Product>();
        if (type == null)
            products = productList;
            
        else {
            for (Product product : productList){
                if (product.getClass().getSimpleName().equals(type))
                    products.add(product);
            }
        }
        return products;
    }
        
    
    
    

      public ArrayList<String> findPossibleProducts(String phrase)
      {
          HashSet<String> possibles = new HashSet<String>();
          ArrayList<String> alternatives = phraseGenerator.findAlternativePhrases(phrase);
          ArrayList<String> productLabels = getLabels();
       
          for (String term : alternatives){
              if (productLabels.contains(term)){
                  possibles.add(term); 
              }
              else {
                 for (String label : productLabels){
                     String[] splitLabel = label.split(" ");
                     String[] splitTerm = term.split(" ");
                     for (int i = 0; i < splitLabel.length; i++){
                         for (int j = 0; j < splitTerm.length; j++){
                             if (splitLabel[i].equals(splitTerm[j])){
                                 possibles.add(label);
                                }
                            }
                        }
                    }
                }    
        }
        ArrayList<String> possibleArrayList = new ArrayList<String>();
        for (String possible : possibles){
            possibleArrayList.add(possible);
        }
        return possibleArrayList;
       }
               
       
      public String toString()
      {
        String string = "";
        string += formatter.leftJustify("Product name:", 20) + formatter.leftJustify("Price (p):", 12) 
        + formatter.leftJustify("Description:", 30);;

        for (Product product : productList){
            string += "\n" + formatter.leftJustify(product.getLabel(), 20)
                   + formatter.leftIntJustify(product.getPrice(), 12) 
                   + formatter.leftJustify(product.getDescription(), 30);
        }

        return string;
    }
                
    
            
    
    
}
