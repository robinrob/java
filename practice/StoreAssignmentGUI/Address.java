import java.io.Serializable;

public class Address implements Serializable
{

    public static final String[] DETAIL_PROMPTS = {"House number: ", "Street: ", "Address1: ", "Address2: ", "Address3: ", "Postcode: "};
    private String[] details;
    
    public Address(String[] addressDetails)
    {
        details = addressDetails;
    }
    
    public String getNumber()
    {
        return details[0];
    }
    public String getStreet()
    {
        return details[1];
    }
    public String[] getDetails()
    {
        return details;
    }
    
    public void setDetail(int line, String string)
    {
        details[line] = string;
    }
    public void setDetails(String[] newDetails)
    {
        details = newDetails;
    }
    public void setHouseNumber(String number)
    {
        details[0] = number;
    } 
    public void setStreet(String street)
    {
        details[1] = street;
    }
    public void setAddress1(String address1)
    {
        details[2] = address1;
    }
    public void setAddress2(String address2)
    {
        details[3] = address2;
    }
    public void setAddress3(String address3)
    {
        details[4] = address3;
    }
    public void setPostcode(String postcode)
    {
        details[5] = postcode;
    }


    public String toString()
    {
        String string = getNumber() + " " + getStreet();
        return string;
    }

    
}