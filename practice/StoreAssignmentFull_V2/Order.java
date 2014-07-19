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
    private int postageCost;
    private int totalCost;


    public Order(String name, Address address1, Address address2,
                 ShoppingBasket basket, String postage, int cost) 
      {
          this.name = name;
          deliveryAddress = address1;
          billingAddress = address2;
          this.basket = basket;
          this.postage = postage;
          postageCost = getPostageCost();
          totalCost = cost + postageCost;
        }

    public String getName () 
    {
        return name;
    }

 
    public Address getDeliveryAddress () 
    {
        return deliveryAddress;
    }
    public Address getBillingAddress()
    {
        return billingAddress;
    }


    public String getPostage () 
    {
        return postage;
    }




    public void setName (String n) 
    {
        name = n;
    }


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


    /**
     * Method to set the cost of postal service. This is a simple 
     * implementation. Ideally this information should be taken from a 
     * database or file
     */
    private int getPostageCost () 
    {
        int cost = 0;
        if (postage.equals("second"))
            cost = 20;
        else
            if (postage.equals("first"))
                cost = 30;
            else
                if (postage.equals("courier"))
                    cost = 200;
       return cost;
    }

     /**
     * This method returns a string with the delivery information
     * return a string containing information about the delivery
     */
    public String toString () 
    {
        String string = "==========================================================" + "\n" + 
                        "Delivery recipient: " + name + "\n" +
                        "----------------------------------------" + "\n" +
                        "Items: " + basket + "\n" +
                        "----------------------------------------" + "\n" +
                        "Delivery address: \n" + deliveryAddress + "\n" +
                        "----------------------------------------" + "\n" +
                        "Billing address: \n" + billingAddress + "\n" +
                        "----------------------------------------" + "\n" +
                        "Delivery service: " + postage + "\n" +
                        "----------------------------------------" + "\n" +
                        "Delivery cost: " + postageCost + "\n" +
                         "----------------------------------------" + "\n" +
                        "Total cost: " + totalCost;
        return string;
    }

} 
