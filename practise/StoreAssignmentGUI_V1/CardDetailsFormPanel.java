import javax.swing.*;

/**
 * A type of InputForm for creating a set of card details.
 * 
 * @author Robin Smith
 * @version 2009.01.18
 */


public class CardDetailsFormPanel extends InputFormPanel
{

    private static Boolean ALL_REQUIRED = true;
    
    public CardDetailsFormPanel(StoreGUI gui, String title)
    {
        super(gui, title, ALL_REQUIRED);
        prompts = PaymentDetails.DETAIL_PROMPTS;
        number = prompts.length;
        createForm();
    }
    public CardDetailsFormPanel(StoreGUI gui, String title, String[] prevDetails)
    {
        super(gui, title, ALL_REQUIRED, prevDetails);
        prompts = PaymentDetails.DETAIL_PROMPTS;
        number = prompts.length;
        createForm();
    }
    
    public void submitDetails()
    {
        String[] inputDetails = getDetails();
        if (inputDetails != null)
            storeGUI.updateCardDetails(getDetails());
    }
    
}