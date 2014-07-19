

public class AccountModule extends Module
{

    public AccountModule(Store store)
    {
        super(store);
    }
    
    
    public void run()
    {
        prompt = store.getPrompt() + "My account: ";
        
        printMyAccountMenu();
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        
        while (!input.equals("q")){
            if (input.equals("menu"))
                printMyAccountMenu();
            else if (input.equals("1"))
                printPersonalDetails();
            else if (input.equals("2"))
                editPersonalDetails();
            else if (input.equals("3"))
                orderHistory();
            else if (input.equals("4"))
                store.getAddressModule().run();
            else if (input.equals("quit"))
                quit();
            else
                System.out.println(">>Command not recognised");
                
            printMyAccountMenu();
            System.out.print(prompt);
            input = EasyIn.getString();
        }
    }
    
    private void printMyAccountMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("**" + store.getCurrentUser().getFirstName() + "'s account**");
        System.out.println("(1) View personal details");
        System.out.println("(2) Change personal details");
        System.out.println("(3) Order history");
        System.out.println("(4) Your addresses");
        System.out.println();
    }
        
    
    private void printPersonalDetails()
    {
        System.out.println(store.getCurrentUser());
    }
    
    private void editPersonalDetails()
    {
        String oldPrompt = prompt;
        prompt += "Edit personal details: ";
            
        printEditPersonalDetailsMenu();
        String[] detailPrompts = User.DETAIL_PROMPTS;
        int detailNumber = detailPrompts.length;
 
        String input = "";
        boolean finished = false;
    while(!(finished || input.equals("q"))){
    
        System.out.print(prompt);
        input = EasyIn.getString();
        if (input.equals("menu"))
            printEditPersonalDetailsMenu();
        else if (input.equals("quit"))
            quit();
        try {
            int option = Integer.parseInt(input);
            if (option >= 1 && option <= detailPrompts.length){
                System.out.print(detailPrompts[option - 1]);
                String newInput = EasyIn.getString();
                store.getCurrentUser().setDetail(option - 1, newInput);
            }
        }
        catch (NumberFormatException e){
        }
        try {
            int option = Integer.parseInt(input);
            if (option == detailPrompts.length + 1){
                store.getFileHandler().saveObject(store.getCurrentUser());
                finished = true;
            }
        }
        catch (NumberFormatException e){
                System.out.println(">>Enter an integer or valid command");
        }

    }
        prompt = oldPrompt;
    }
        
    private void printEditPersonalDetailsMenu()
    {
        System.out.println("----------------------------------------");
        String[] detailPrompts = User.DETAIL_PROMPTS;
        int index = 1;
        for (String detail : detailPrompts){
            System.out.println("(" + index + ") Edit " + detail);
            index++;
        }
        System.out.println("(" + index + ") Save & exit");
        System.out.println();
    }
        
    private void orderHistory()
    {
        System.out.println("----------------------------------------");
        System.out.println("Your order history:");
        for (Order order : store.getCurrentUser().getOrders()){
            System.out.println(order);
            System.out.println();
        }
    }
    
    
    public void logIn()
    {
        Boolean allRequired = true;
        String[] prompts = {"Enter username: ", "Enter password: "};
        String[] details = store.inputForm(prompts, allRequired);
        if (details != null){
            store.setCurrentUser(store.getFileHandler().loadUser(details));
            if (store.getCurrentUser() != null)
                store.getShoppingModule().run();
            else
                System.out.println(">>Details not found");
        }
    }
    
    public void createAccount()
    {
        boolean allRequired = true;
        String[] prompts = User.DETAIL_PROMPTS;
        String[] details = store.inputForm(prompts, allRequired);
        
        if (details != null){
            User user = new User(details);
            store.getFileHandler().saveObject(user);
            System.out.println("\nAccount created, you may now log in");
        }
            
    }
    
    
}
    