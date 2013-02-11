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
    public static final String[] DETAIL_PROMPTS = {"Recipient: ", "Delivered to: ", "Total cost: "};
    private String[] details;
    private ShoppingBasket basket;
    private Delivery delivery;
    private int total;
    private PaymentDetails paymentDetails;
    private Address billingAddress;
        
    public Order(ShoppingBasket basket) 
    {
        this.basket = basket;
        delivery = null;
        paymentDetails = null;
    }
    public Order()
    {
        basket = null;
        delivery = null;
        paymentDetails = null;
    }
        

    public Delivery getDelivery()
    {
        return delivery;
    }
    //public PaymentDetails getPayment()
    //{
    //    return payment;
    //}
    public String getTotalString()
    {
        String otherTotalString = " " + total;
        String totalString = " £ " + total/100 + "." + 
                              (total%100/10) + 
                              total%10 + "  ";
        return totalString;
    }   
    public String[] getShortDetails()
    {
        return details;
    }
    
    public void setBasket(ShoppingBasket basket)
    {
        this.basket = basket;
        updateTotal();
    }
    public void setDelivery(Delivery delivery)
    {
        this.delivery = delivery;
        updateTotal();
    }
    public void setPaymentDetails(PaymentDetails paymentDetails)
    {
        this.paymentDetails = paymentDetails;
    }


    private void updateTotal()
    {
        if (basket != null & delivery != null)
            total = basket.getTotal() + delivery.getPostageCost();
    }
    public boolean isComplete()
    {
        Boolean bool = false;
        if (delivery.isComplete() && paymentDetails.isComplete() && basket.getTotal() != 0)
            bool = true;
        return bool;
    }


    /**
     * Method to set the type of postal service
     */


    /**
     * Method to set the cost of postal service. This is a simple 
     * implementation. Ideally this information should be taken from a 
     * database or file
     */


     /**
     * This method returns a string with the delivery information
     * return a string containing information about the delivery
     */

} 
