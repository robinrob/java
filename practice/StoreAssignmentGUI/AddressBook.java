import java.util.ArrayList;
import java.io.Serializable;


public class AddressBook implements Serializable
{


    private ArrayList<Address> addressList;
    private Address deliveryAddress;
    private Address billingAddress;
    
    
    public AddressBook()
    {
        addressList = new ArrayList<Address>();
        deliveryAddress = null;
        billingAddress = null;
    }
    public AddressBook(ArrayList<Address> addresses)
    {
        addressList = addresses;
        deliveryAddress = null;
        billingAddress = null;
    }
    
    
    
    public ArrayList<Address> getAddressList()
    {
        return addressList;
    }
    public Address getAddress(String[] details)
    {
        Address returnAddress = null;
        for (Address address : addressList){
            if (address.getDetails() == details)
                returnAddress = address;
        }
        return returnAddress;
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
        Address address = null;
        if (type.equals("Delivery"))
            address = deliveryAddress;
        else if (type.equals("Billing"))
            address = billingAddress;
            
        return address;
    }
    
    //mutator methods
    
        //addition methods
    public void addAddress(Address address)
    {
        if (addressList.size() == 0){
            setAddressType(address, "Delivery");
            setAddressType(address, "Billing");
        }
        addressList.add(address);
    }
    
    public void removeAddress(Address address)
    {
        addressList.remove(address);
    }
    public void removeAddress(String[] details)
    {
        addressList.remove(getAddress(details));
    }
    
        //setting methods
    public void setAddressList(Address[] addresses)
    {
        addressList.clear();
        for (Address address : addresses){
            addressList.add(address);
        }
    } 
    public void setAddressType(Address address, String type)
    {
        if (type.equals("Delivery"))
            deliveryAddress = address;
        else if (type.equals("Billing"))
            billingAddress = address;
    }
    
        //miscellaneous methods
    public boolean isAddressType(Address address, String type)
    {
        Boolean bool = false;
        if (type.equals("Delivery")){
            if (deliveryAddress == address)
                bool = true;
            else
                bool = false;
        }
        else if (type.equals("Billing")){
            if (billingAddress == address)
                bool = true;
            else
                bool = false;
        }
        return bool;
    }

            

    

    
}