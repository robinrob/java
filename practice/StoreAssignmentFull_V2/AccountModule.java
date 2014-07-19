

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
        System.out.println("----------------------------------------");
        System.out.println("Your personal details:");
        System.out.println("First name: " + store.getCurrentUser().getFirstName());
        System.out.println("Last name: " + store.getCurrentUser().getLastName());
        System.out.println("Username: " + store.getCurrentUser().getUsername());
        System.out.println("Password: " + store.getCurrentUser().getPassword());
        System.out.println();
    }
    
    private void editPersonalDetails()
    {
        String oldPrompt = prompt;
        prompt += "Edit personal details: ";

        String input = "";
        boolean finished = false;
        while (!input.equals("q") && !finished){
            
            printEditPersonalDetailsMenu();
            System.out.print(prompt);
            input = EasyIn.getString();
            
            if (input.equals("menu"))
                printEditPersonalDetailsMenu();
            else if (input.equals("1")){
                System.out.print("Enter new first name: ");
                store.getCurrentUser().setFirstName(EasyIn.getString());
            }
            else if (input.equals("2")){
                System.out.print("Enter new second name: ");
                store.getCurrentUser().setLastName(EasyIn.getString());
            }
            else if (input.equals("3")){
                System.out.print("Enter new username: ");
                store.getCurrentUser().setUsername(EasyIn.getString());
            }
            else if (input.equals("4")){
                System.out.print("Enter new password: ");
                store.getCurrentUser().setPassword(EasyIn.getString());
            }
            else if (input.equals("5")){
                store.getFileHandler().saveObject(store.getCurrentUser());
                finished = true;
            }
            else if (input.equals("quit"))
                quit();
            else if (!input.equals("q"))
                System.out.println(">>Command not recognised");
                
        }
        prompt = oldPrompt;
    }
        
    private void printEditPersonalDetailsMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("(1) Edit first name");
        System.out.println("(2) Edit last name");
        System.out.println("(3) Edit username");
        System.out.println("(4) Edit password");
        System.out.println("(5) Save & exit");
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
    
    
    
}
    