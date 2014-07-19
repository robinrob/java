import java.util.ArrayList;

public class ShoppingModule extends Module
{


    public ShoppingModule(Store store)
    {
        super(store);
    }
    
    
    
    public void run()
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
                store.getBasketModule().addToBasket();
            else if (input.equals("4"))
                store.getBasketModule().run();
            else if (input.equals("5") && store.getCurrentUser() != null)
                store.getCheckoutModule().run();
            else if (input.equals("6") && store.getCurrentUser() != null)
                store.getAccountModule().run();
            else if (input.equals("5") && store.getCurrentUser() == null)
                store.getAccountModule().logIn();
            else if (input.equals("6") && store.getCurrentUser() == null)
                store.getAccountModule().createAccount();
            else if (input.equals("quit"))
                store.quit();
            
            else
                System.out.println(">>Command not recognised");
                
        printShopMenu();        
        System.out.print(prompt);
        input = EasyIn.getString();
        }
        
        store.setCurrentUser(null);
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
        if (store.getCurrentUser() != null){
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
    
        private void showProducts()
    {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Catalogue:");
        System.out.println("----------------------------------------------------------------------");
        System.out.println(store.getCatalogue());
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
             possibleTerms = store.getCatalogue().findPossibleProducts(input);
      
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
                    System.out.println(store.getCatalogue().getProduct(term));
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