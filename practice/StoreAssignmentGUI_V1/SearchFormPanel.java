import javax.swing.*;

/**
 * A type of InputForm used for searching for products.
 * 
 * @author Robin Smith
 * @version 2009.01.18
 */

public class SearchFormPanel extends InputFormPanel
{

    private static final Boolean ALL_REQUIRED = true;
    private final String[] PROMPTS = {"Search: "};
    
    public SearchFormPanel(StoreGUI gui, String title)
    {
        super(gui, title, ALL_REQUIRED);
        prompts = PROMPTS;
        number = prompts.length;
        createForm();
    }
    
    public SearchFormPanel(StoreGUI gui, String title, String[] prevDetails)
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
            this.removeAll();
            storeGUI.searchForProduct(inputDetails);
        }
    }
    
    
}