import java.io.Serializable;


public class PaymentDetails implements Serializable
{

    public static final String[] DETAIL_PROMPTS = {"Card number: ", "Card type: "};
    private String[] cardDetails;
    private Address billingAddress;
    
    
    public PaymentDetails()
    {
        cardDetails = null;
        billingAddress = null;
    }
  
    public PaymentDetails(String[] details)
    {
        cardDetails = details;
        billingAddress = null;
    }
    
    public PaymentDetails(Address billingAddress)
    {
        cardDetails = null;
        this.billingAddress = billingAddress;
    }
    
    public String getCardNumber()
    {
        return cardDetails[0];
    }
    public String getCardType()
    {
        return cardDetails[1];
    }
    public String[] getCardDetails()
    {
        return cardDetails;
    }
    
    
    public void setCardDetails(String[] newDetails)
    {
        cardDetails = newDetails;
    }
    public void setAddress(Address address)
    {
        billingAddress = address;
    }
    
    public String toString()
    {
        String string = "";
        for (String detail : cardDetails)
            string += detail + ", ";
        return string;
    }
    public boolean isComplete()
    {
        boolean bool = false;
        if (cardDetails != null & billingAddress != null)
            bool = true;
        return bool;
    }
    
}