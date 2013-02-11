import java.io.Serializable;

public class Address implements Serializable
{

    private String[] address = new String[6];
    private boolean isDeliveryAddress;
    private boolean isBillingAddress;
    
    public Address(String[] address)
    {
        this.address = address;
    }
    
    
    public void setLine(int line, String string)
    {
        address[line] = string;
    }
    public void setHouseNumber(String number)
    {
        address[0] = number;
    } 
    public void setStreet(String street)
    {
        address[1] = street;
    }
    public void setAddress1(String address1)
    {
        address[2] = address1;
    }
    public void setAddress2(String address2)
    {
        address[3] = address2;
    }
    public void setAddress3(String address3)
    {
        address[4] = address3;
    }
    public void setPostcode(String postcode)
    {
        address[5] = postcode;
    }
    public void setAddressType(String type, boolean bool)
    {
        if (type.equals("delivery"))
            isDeliveryAddress = bool;
        else if (type.equals("billing"))
            isBillingAddress = bool;  
    }
    
    
    public boolean isAddressType(String type)
    {
        if (type.equals("delivery"))
            return isDeliveryAddress;
        else if (type.equals("billing"))
            return isBillingAddress;
        return  false;
    }
    
    public String toString()
    {
        String string = "";
        for (String line : address){
            if (line != null)
                string += line + "\n";
        }
        return string;
    }

    
}