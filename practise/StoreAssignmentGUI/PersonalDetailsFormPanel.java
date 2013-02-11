import javax.swing.*;


public class PersonalDetailsFormPanel extends InputFormPanel
{

    private static final Boolean ALL_REQUIRED = true;
    
    public PersonalDetailsFormPanel(StoreGUI gui, String title)
    {
        super(gui, title, ALL_REQUIRED);
        prompts = User.DETAIL_PROMPTS;
        number = prompts.length;
        createForm();
    }
    
    public PersonalDetailsFormPanel(StoreGUI gui, String title, String[] prevDetails)
    {
        super(gui, title, ALL_REQUIRED, prevDetails);
        prompts = User.DETAIL_PROMPTS;
        number = prompts.length;
        createForm();
    }
    
    
    
    public void submitDetails()
    {
        String[] inputDetails = getDetails();
        if (inputDetails != null){
            if (previousDetails != null)
                storeGUI.updatePersonalDetails(inputDetails);  
            else
                storeGUI.createAccount(inputDetails);
        }
    }
    
    
}