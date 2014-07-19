import javax.swing.*;


public class AddressFormPanel extends InputFormPanel
{

    private static Boolean ALL_REQUIRED = false;
    
    public AddressFormPanel(StoreGUI gui, String title)
    {
        super(gui, title, ALL_REQUIRED);
        prompts = Address.DETAIL_PROMPTS;
        number = prompts.length;
        createForm();
    }
    public AddressFormPanel(StoreGUI gui, String title, String[] prevDetails)
    {
        super(gui, title, ALL_REQUIRED, prevDetails);
        prompts = Address.DETAIL_PROMPTS;
        number = prompts.length;
        createForm();
    }
    
    public void submitDetails()
    {
        String[] inputDetails = getDetails();
        if (inputDetails != null){
            if (previousDetails != null)
            storeGUI.updateAddressDetails(getDetails(), previousDetails);
            
            else
                storeGUI.createAddress(getDetails());
        }
    }
    
}