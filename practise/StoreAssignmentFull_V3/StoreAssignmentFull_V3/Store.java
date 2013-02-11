import java.util.*;
import java.io.*;
import java.net.URL;

/**
 * This class implements a simple simulation of an online store
 * 
 * @author Pablo Romero
 * @version 2004.08.28
 */
public class Store
{
    private ShoppingModule shoppingModule;
    private AddressModule addressModule;
    private BasketModule basketModule;
    private CheckoutModule checkoutModule;
    private AccountModule accountModule;
    private Catalogue catalogue;
    private ShoppingBasket basket;
    private User currentUser;
    private StoreFileHandler fileHandler;
    private String prompt;

    /**
     * Create a new Store
   **/
    public Store() 
      {
        shoppingModule = new ShoppingModule(this);
        addressModule = new AddressModule(this);
        basketModule = new BasketModule(this);
        checkoutModule = new CheckoutModule(this);
        accountModule = new AccountModule(this);
        fileHandler = new StoreFileHandler();
        catalogue = fileHandler.loadCatalogue();
        basket = new ShoppingBasket();                 
        currentUser = null;
        prompt = "";
        
    }
   
    
    public void run() 
    {
        prompt = "Main Menu: ";

        printMainMenu(); 
        System.out.print(prompt);
        String input = EasyIn.getString();
        
        while (!input.equals("exit")){
            prompt = "Main Menu: ";
            if (input.equals("menu"))
                printMainMenu();
            else if (input.equals("1"))
                accountModule.logIn();
            else if (input.equals("2"))
                shoppingModule.run();
            else if (input.equals("3"))
                accountModule.createAccount();
            else if (input.equals("quit"))
                quit();
            else
                System.out.println(">>Command not recognised");
                
            printMainMenu();
            System.out.print(prompt);
            input = EasyIn.getString();
        }
                
        System.out.println("\nSystem exited");
    }
    
    private void printMainMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("(1) Log in and shop");
        System.out.println("(2) Shop without logging in");
        System.out.println("(3) Create an account");
        System.out.println();
    }
    

    

    //miscellaneous methods
    public String[] inputForm(String[] prompts, Boolean allRequired)
    {
        int number = prompts.length;
        String[] details = new String[number];
        
        System.out.print(prompt + prompts[0]);
        String input = EasyIn.getString();
        int i = 0;
        while (i < number){
            if (input.equals("q")){
                return null;
            }
            if (input.equals("") && allRequired){
                System.out.println(">>All fields required\n");
                return null;
            }
            details[i] = input;
            i++;
            if (i < number) {
                System.out.print(prompt + prompts[i]);
                input = EasyIn.getString();
            }
        }
        return details;
    }
    
    public void quit()
    {
        fileHandler.saveObject(currentUser);
        System.out.println("\nThank you for shopping at Robin's Astronomy Shop!");
        System.exit(0);
    }
     

    
    
    public String getPrompt()
    {
        return prompt;
    }
    public User getCurrentUser()
    {
        return currentUser;
    }
    public StoreFileHandler getFileHandler()
    {
        return fileHandler;
    }
    public ShoppingBasket getBasket()
    {
        return basket;
    }
    public Catalogue getCatalogue()
    {
        return catalogue;
    }
    
    public BasketModule getBasketModule()
    {
        return basketModule;
    }
    public AddressModule getAddressModule()
    {
        return addressModule;
    }
    public CheckoutModule getCheckoutModule()
    {
        return checkoutModule;
    }
    public AccountModule getAccountModule()
    {
        return accountModule;
    }
    public ShoppingModule getShoppingModule()
    {
        return shoppingModule;
    }
    public void setCurrentUser(User user)
    {
        currentUser = user;
    }
    
     

}
