import javax.swing.*;



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