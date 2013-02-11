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
    
    
    public void interact() 
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
                logIn();
            else if (input.equals("2"))
                doShopping();
            else if (input.equals("3"))
                createAccount();
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
    
    private void doShopping()
    {
        prompt = "Shop: ";
        
        printWelcome();
        printShopMenu();
        System.out.print(prompt);
        String input = EasyIn.getString();
        
        while (!input.equals("logout")) {
            prompt = "Shop: ";
            if (input.equals("menu"))
                printShopMenu();
            else if (input.equals("help"))
                printHelp();
            else if (input.equals("1"))
                showProducts();
            else if (input.equals("2"))
                searchForProducts(input);
            else if (input.equals("3"))
                basketModule.addToBasket();
            else if (input.equals("4"))
                basketModule.run();
            else if (input.equals("5") && currentUser != null)
                checkoutModule.run();
            else if (input.equals("6") && currentUser != null)
                accountModule.run();
            else if (input.equals("5") && currentUser == null)
                logIn();
            else if (input.equals("6") && currentUser == null)
                createAccount();
            else if (input.equals("quit"))
                quit();
            
            else
                System.out.println(">>Command not recognised");
                
        printShopMenu();        
        System.out.print(prompt);
        input = EasyIn.getString();
        }
        
        currentUser = null;
    }
        
    private void printWelcome()
    {
        System.out.println("\n-----------------------------------");
        System.out.println("Welcome to Robin's shop!");
        System.out.println("-----------------------------------");
        System.out.println("Enter a menu option (module) to start");
        printHelp();
        System.out.println("    Type 'help' for help");
    }
    
    private void printHelp()
    {
        System.out.println("");
        System.out.println("    Important commands: ");
        System.out.println("    Type 'menu' for the current options menu");
        System.out.println("    Type 'q' to go back a step to previous menu");
        System.out.println("    Type 'quit' to exit the system");
        System.out.println("    Type 'logout' whilst logged in to return to main menu");
        System.out.println();
    }
    
    private void printShopMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("(1) Show list of products");
        System.out.println("(2) Search for product");
        System.out.println("(3) Add item to basket");
        System.out.println("(4) View and edit basket");
        if (currentUser != null){
            System.out.println("(5) Checkout");
            System.out.println("(6) My account");
            System.out.println("(logout) Logout");
        }
        else {
            System.out.println("(5) Log in");
            System.out.println("(6) Create account");
        }
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
    
    
    //Main menu modules
    private void logIn()
    {
        Boolean allRequired = true;
        String[] prompts = {"Enter username: ", "Enter password: "};
        String[] details = inputForm(prompts, allRequired);
        if (details != null){
            currentUser = fileHandler.loadUser(details);
            if (currentUser != null)
                doShopping();
            else
                System.out.println(">>Details not found");
        }
    }
    
    private void createAccount()
    {
        boolean allRequired = true;
        String[] prompts = {"Enter first name: ", "Enter last name: ", "Enter username: ", "Enter password: "};
        String[] details = inputForm(prompts, allRequired);
        
        if (details != null){
            User user = new User(details);
            fileHandler.saveObject(user);
            System.out.println("\nAccount created, you may now log in");
        }
            
    }
    
    
    //Shopping modules
    private void showProducts()
    {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Catalogue:");
        System.out.println("----------------------------------------------------------------------");
        System.out.println(catalogue);
    }
    
    private void searchForProducts(String input)
    {
        String oldPrompt = prompt;
        prompt += "Search: ";
        
        System.out.print(prompt);
        input = EasyIn.getString();
        
        while (!input.equals("q")){
             if (input.equals("quit"))
                quit();
                
             ArrayList<String> possibleTerms = new ArrayList<String>();
             possibleTerms = catalogue.findPossibleProducts(input);
      
             if (possibleTerms.size() > 1){
                 System.out.println("\nDid you mean: ");
                 for (String term : possibleTerms){
                     System.out.println(term);
                    }
                 System.out.println();
                }
                
            else if (possibleTerms.size() == 1){
                String term = possibleTerms.get(0);
                if (!term.equals(input)){
                        System.out.println("\nDid you mean: \n" + term);
                        System.out.println();
                }
                else {
                    System.out.println();
                    System.out.println(catalogue.getProduct(term));
                    System.out.println();
                }
            }
            else {
                System.out.println("Search term found no matches.");
            }
            
            System.out.print(prompt);
            input = EasyIn.getString();
        }
        System.out.println();
        prompt = oldPrompt;
    }
    
    
 
    

}
