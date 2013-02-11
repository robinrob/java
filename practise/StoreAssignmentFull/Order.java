import java.io.Serializable;

/**
 * This class implements a container for the information of a delivery
 * 
 * @author Pablo Romero
 * @version 2004.08.10
 */
public class Order implements Serializable
{
    // The name, address and type of postal service for the delivery
    private String name;
    private Address deliveryAddress;
    private Address billingAddress;
    private ShoppingBasket basket;
    private String postage;
    private int cost;

    /**
     * Create a new delivery
   **/
    public Order(String name, Address address1, Address address2,
                 ShoppingBasket basket, String postage, int cost) 
      {
          this.name = name;
          deliveryAddress = address1;
          billingAddress = address2;
          this.basket = basket;
          this.postage = postage;
          this.cost = cost;
        }

    /**
     * Accessor for the name
     */
    public String getName () 
    {
        return name;
    }

     /**
     * Accessor for the address
     */
    public Address getDeliveryAddress () 
    {
        return deliveryAddress;
    }
    public Address getBillingAddress()
    {
        return billingAddress;
    }

    /**
     * Accessor for the type of postal service
     */
    public String getPostage () 
    {
        return postage;
    }

    /**
     * Accessor for the cost
     */
    public int getCost () 
    {
        return cost;
    }

    /**
     * Method to set the name
     */
    public void setName (String n) 
    {
        name = n;
    }

    /**
     * Method to set the address
     */
    public void setDeliveryAddress (Address address) 
    {
        deliveryAddress = address;
    }
    
    public void setBillingAddress (Address address) 
    {
        billingAddress = address;
    }

    /**
     * Method to set the type of postal service
     */
    public void setPostage (String p) 
    {
        postage = p;
        setCost();
    }

    /**
     * Method to set the cost of postal service. This is a simple 
     * implementation. Ideally this information should be taken from a 
     * database or file
     */
    private void setCost () 
    {
        if (postage.equals("second"))
            cost = 20;
        else
            if (postage.equals("first"))
                cost = 30;
            else
                if (postage.equals("courier"))
                    cost = 200;
    }

     /**
     * This method returns a string with the delivery information
     * return a string containing information about the delivery
     */
    public String toString () 
    {
        String output = "==========================================================" + "\n" + 
                        "Delivery recipient: " + name + "\n" + 
                        "Items: " + basket + "\n" +
                        "Delivery address: " + deliveryAddress + "\n" + 
                        "Billing address: " + billingAddress + "\n" +
                        "Postal service: " + postage + "\n" + 
                        "Delivery cost: " + cost + "\n";
        return output;
    }

} 
