import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class represents a customer.  A customer has personal details, 
 * an address book and payment details.
 * 
 * @author Robin Smith
 * @version 2009.01.18
 */


public class User implements Serializable
{
    private String[] details;
    private AddressBook addressBook;
    private PaymentDetails paymentDetails;
    public static final String[] DETAIL_PROMPTS = {"First name: ", "Last name: ", "Email address: ", "Username: ", "Password: "};
    
   
    public User (String[] userDetails)
    {
        details = userDetails;
        addressBook = new AddressBook();
        paymentDetails = new PaymentDetails();
    }
    
    //accessor methods
    public String[] getDetails()
    {
        return details;
    }
    public String getDetail(int index)
    {
        return details[index];
    }
    public String getFirstName()
    {
        return details[0];
    }
    public String getLastName()
    {
        return details[1];
    }
    public String getEmail()
    {
        return details[2];
    }
    public String getUsername()
    {
        return details[3];
    }
    public String getPassword()
    {
        return details[4];
    }
   // public String getPostage()
   // {
   //     return postagePreference;
    //}
    public AddressBook getAddressBook()
    {
        return addressBook;
    }
    public PaymentDetails getPaymentDetails()
    {
       return paymentDetails;
    }

    
        //removal methods

    
        //set methods
    public void setDetails(String[] newDetails)
    {
        for (int i = 0; i < details.length; i++){
            details[i] = newDetails[i];
        }
    }       
    public void setDetail(int line, String detail)
    {
        details[line] = detail;
    }
    public void setFirstName(String name)
    {
        details[0] = name;
    }
    public void setLastName(String name)
    {
        details[1] = name;
    }
    public void setEmail(String string)
    {
        details[2] = string;
    }
    public void setUsername(String name)
    {
        details[3] = name;
    }
    public void setPassword(String pass)
    {
        details[4] = pass;
    }


    
    public String toString()
    {
        String string = "----------------------------------------" + "\n" +
                        "Your personal details:" + "\n";
        for (int i = 0; i < DETAIL_PROMPTS.length; i++){
            string += DETAIL_PROMPTS[i] + details[i] + "\n";
        }
                        
        return string;
    }

        


}
        