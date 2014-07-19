/**
 * This class implements a container for the information of a delivery.
 * 
 * @author Robin Smith
 * @version 2009.01.18
 */


public class Delivery
{

    private String recipient;
    private Address deliveryAddress;
    private String postage;
    private int postageCost;
    
    
    public Delivery()
    {
        recipient = null;
        deliveryAddress = null;
        postage = null;
    }
        
    public Delivery(Address address, String postage)
    {
        deliveryAddress = address;
        this.postage = postage;
        setPostageCost(postage);
    }
    
    
    public String getRecipient()
    {
        return recipient;
    }
    public Address getAddress () 
    {
        return deliveryAddress;
    }
    public String getPostage () 
    {
        return postage;
    }
    public int getPostageCost () 
    {
       return postageCost;
    }
    
    
    
    
    public void setAddress (Address address) 
    {
        deliveryAddress = address;
    }
    public void setPostage(String postage)
    {
        this.postage = postage;
        setPostageCost(postage);
    }
    public void setPostageCost(String postage)
    {
        if (postage.equals("First"))
            postageCost = 200;
        else
            if (postage.equals("Second"))
                postageCost = 100;
            else
                if (postage.equals("Courier"))
                    postageCost = 500;
    }
    
    public boolean isComplete()
    {
        Boolean bool = false;
        if (deliveryAddress != null && postage != null)
            bool = true;
        return bool;
    }
            
    
    
}