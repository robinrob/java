import java.util.ArrayList;
import java.io.Serializable;


public class User implements Serializable
{
    private String[] details;
    private ArrayList<Address> addressList;
    private ArrayList<Order> orders;
    private String postagePreference;
    
    
    
    public User (String[] userDetails)
    {
        details = userDetails;
        addressList = new ArrayList<Address>();
        orders = new ArrayList<Order>();
    }
    
    //accessor methods
    public String getFirstName()
    {
        return details[0];
    }
    public String getLastName()
    {
        return details[1];
    }
    public String getUsername()
    {
        return details[2];
    }
    public String getPassword()
    {
        return details[3];
    }
    public String getPostage()
    {
        return postagePreference;
    }
    public ArrayList<Address> getAddressList()
    {
        return addressList;
    }
    public Address[] getAddressArray()
    {
        Address[] array = new Address[addressList.size()];
        int index = 0;
        for (Address address : addressList){
            array[index] = address;
            index++;
        }
        return array;
    }
    public Address getAddressType(String type)
    {
        Address deliveryAddress = null;
        for (Address address : addressList){
            if (address.isAddressType(type))
                deliveryAddress = address;
        }
        return deliveryAddress;
    }
    public ArrayList<Order> getOrders()
    {
        return orders;
    }
    
    //mutator methods
    
        //addition methods
    public void addAddress(Address address)
    {
        addressList.add(address);
    }
    public void addOrder(Order order)
    {
        orders.add(order);
    }

    
        //removal methods
    public void removeAddress(Address address)
    {
        addressList.remove(address);
    }
    
        //set methods
    public void setFirstName(String name)
    {
        details[0] = name;
    }
    public void setLastName(String name)
    {
        details[1] = name;
    }
    public void setUsername(String name)
    {
        details[2] = name;
    }
    public void setPassword(String pass)
    {
        details[3] = pass;
    }
    public void setPostage(String postage)
    {
        postagePreference = postage;
    }
    public void setAddressList(Address[] addresses)
    {
        addressList.clear();
        for (Address address : addresses){
            addressList.add(address);
        }
    }
    

        


}
        