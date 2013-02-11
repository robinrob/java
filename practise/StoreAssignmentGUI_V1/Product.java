import java.io.Serializable;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * This class implements a container which represents a product of
 * the catalogue
 * 
 * @author Robin Smith
 * @version 2009.01.18
 */
public class Product implements Serializable
{
    // The product and its price
    public static final String[] TYPES = {"Telescopes", "Binoculars", "Software"};
    private String label;
    protected int price;
    private String description = "";
    private BufferedImage productImage;
    protected String[] extraDetails;

    /**
     * Create a new product
   * @param l  the products label
   * @param d  the products price
   **/
   
   public Product(String label, int price)
   {
       this.label = label;
       this.price = price;
       productImage = null;
       extraDetails = null;
    }
    
    public Product(String label, int price, String description, BufferedImage image) 
      {
        this.label = label;
        this.price = price;
        this.description = description;
        productImage = image;
        extraDetails = null;
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
    public String getPriceString()
    {
        String otherPriceString = " " + price;
        String priceString = " £ " + price/100 + "." + 
                              (price%100/10) + 
                              price%10 + "  ";
        return priceString;
    }
    public String getDescription()
    {
        return description;
    }
    public BufferedImage getImage()
    {
        return productImage;
    }
    
    public void addDescription(String description)
    {
        this.description = description;
    }
    public boolean hasImage()
    {
        boolean bool = false;
        if (productImage != null)
            bool = true;
            
        return bool;
    }

    public String[] extraDetailsListToArray(ArrayList<String> list)
    {
        String[] array = new String[list.size()];
        int i = 0;
        for (String detail : list){
            array[i] = detail;
            i++;
        }
        return array;
    }
    public String toString(){
        return ("Name: " + label + "\n" + "Price: " + price + "\n" 
        + "Description: " + description);
    }
    

}
