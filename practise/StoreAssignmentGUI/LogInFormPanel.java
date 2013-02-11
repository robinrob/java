

import javax.swing.*;


public class LogInFormPanel extends InputFormPanel
{

    private static final Boolean ALL_REQUIRED = true;
    
    public LogInFormPanel(StoreGUI gui, String title, boolean allRequired)
    {
        super(gui, title, ALL_REQUIRED);
        String[] strings = {"Username: ", "Password: "};
        prompts = strings;
        number = prompts.length;
        createForm();
    }
    
   // public LogInForm(StoreGUI gui, JPanel panel, String title, boolean allRequired, String[] prevDetails)
   // {
   //     super(gui, panel, title, allRequired);
   //     String[] strings = {"Username: ", "Password: "};
   //     prompts = strings;
   //     number = prompts.length;
   //     previousDetails = prevDetails;
   //     createForm();
   // }
    
    
    public void submitDetails()
    {
        String[] inputDetails = getDetails();
        if (inputDetails != null){
            storeGUI.logIn(inputDetails);
        }
    }
    
    
}