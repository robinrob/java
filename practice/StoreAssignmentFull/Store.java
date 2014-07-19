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
    private Catalogue catalogue;
    private ShoppingBasket basket;
    private User currentUser;
    private StoreFileHandler fileHandler;
    private String prompt;
    private String postage;

    /**
     * Create a new Store
   **/
    public Store() 
      {
        fileHandler = new StoreFileHandler();
        catalogue = fileHandler.loadCatalogue();
        basket = new ShoppingBasket();                 
        currentUser = null;
        prompt = "";
        postage = "";
    }

    /**
     * Interacts with the user in a simple way. When the user inputs a
     * valid product, this method asks for the number of products of
     * this type to add to the shopping basket. If the product entered
     * is not valid, the method alerts the user of this and asks for 
   * another product. The interaction finishes when the user enters
     * the special term `finish'. The method prints out the total amount
   * to pay for the items in the shopping basket before finishing
   **/
   
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
                addToBasket();
            else if (input.equals("4"))
                basket();
            else if (input.equals("5") && currentUser != null)
                checkout();
            else if (input.equals("6") && currentUser != null)
                myAccount();
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
        System.out.println("    Type 'exit' to exit a module to the current main menu");
        System.out.println("    Type 'exit' at main menu to exit the system");
        System.out.println("    Type 'logout' whilst logged in to return to main menu");
        System.out.println();
    }
        
    private void printMainMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("(1) Log in and shop");
        System.out.println("(2) Shop without logging in");
        System.out.println("(3) Create an account");
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
    
    

    
    private void basket()
    {
        String oldPrompt = prompt;
        prompt += "Basket: ";
        
        showBasket();
        printBasketMenu();
        System.out.print(prompt);
        String input = EasyIn.getString();
        while (!input.equals("q")){
            if (input.equals("menu"))
                printBasketMenu();
            else if (input.equals("1"))
                showBasket();
            else if (input.equals("2"))
                removeFromBasket();
            else if (input.equals("3"))
                modifyItemQuantity();
            else if (input.equals("quit"))
                quit();
            else
                System.out.println(">>Command not recognised");
         
         printBasketMenu();
         System.out.print(prompt);
         input = EasyIn.getString();
        }
    }
        
    private void printBasketMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("**Shopping basket options**");
        System.out.println("(1) Show basket");
        System.out.println("(2) Remove item");
        System.out.println("(3) Modify quantity of item");
        System.out.println();
    }
    
    private void showBasket()
    {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("    Shopping basket:");
        System.out.println(basket.toString());
    }    
    
    private void addToBasket()
    {
        String oldPrompt = prompt;
        prompt += "Add to basket: ";
        
        System.out.print(prompt + "Enter product name: ");
        String inputName = EasyIn.getString();
        System.out.print(prompt + "Enter quantity: ");
        String inputQuantity = EasyIn.getString();
            
        if (catalogue.productExists(inputName)){
            basket.addItem(inputName,catalogue.getProductPrice(inputName),Integer.parseInt(inputQuantity));
            System.out.println("Item added successfully");
        }
        else {
            System.out.println("Item not added");
        }
        
        System.out.println();
        prompt = oldPrompt;
    }
    
    private void removeFromBasket()
    {
        String oldPrompt = prompt;
        prompt += "Remove item: ";
        
        System.out.print(prompt + "Product name: ");
        String input = EasyIn.getString();
        if (basket.deleteItem(input)){
            System.out.println("Item removed successfully");
        }
        else {
            System.out.println("Item not removed");
        }
        System.out.println();
        prompt = oldPrompt;
    }
    
    private void modifyItemQuantity()
    {
        String oldPrompt = prompt;
        prompt += "Modify quantity: ";
        System.out.print(prompt + "Product name: ");
        String inputName = EasyIn.getString();
        if(!inputName.equals("q")){
            System.out.print(prompt + "New quantity: ");
            int inputQuantity = EasyIn.getInt();
            if (basket.itemExists(inputName)){
                basket.getItem(inputName).changeQuantity(inputQuantity);
                System.out.println("Item quantity successfully modified");
            }
            else {
                System.out.println("Item quantity not modified");
            }
        }
        System.out.println();
        prompt = oldPrompt;
    }
            
    
    //Address module
    private void addressOptions()
    {
        String oldPrompt = prompt;
        prompt += "Address options: ";

        printAddressOptionsMenu();
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        
        while (!input.equals("q")){

            if (input.equals("1"))
                printAddresses(currentUser.getAddressArray());
            else if (input.equals("2"))
                addAddress();
            else if (input.equals("3"))
                editAddress();
            else if (input.equals("4"))
                removeAddress();    
            else if (input.equals("5"))
                specifyAddress("delivery");
            else if (input.equals("6"))
                specifyAddress("billing");
            else if (input.equals("7"))
                showAddress("delivery");
            else if (input.equals("8"))
                showAddress("billing");
            else if (input.equals("menu"))
                printAddressOptionsMenu();
            else if (input.equals("quit"))
                quit();
            
            printAddressOptionsMenu();
            System.out.print(prompt);
            input = EasyIn.getString();
        }
        prompt = oldPrompt;
    }
    
    private void printAddressOptionsMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("**Address Options**");
        System.out.println("(1) Show your addresses");
        System.out.println("(2) Add an address");
        System.out.println("(3) Edit an address");
        System.out.println("(4) Remove an address");
        System.out.println("(5) Specify delivery address");
        System.out.println("(6) Specify billing address");;
        System.out.println("(7) Show delivery address");
        System.out.println("(8) Show billing address");
        System.out.println();
    }
    
    
    private void printAddresses(Address[] addresses)
    {   
        System.out.println("----------------------------------------");
        System.out.println("\nYour addresses:");
        int index = 1;
        for (Address address : addresses){
            String heading = "  Address number ("+index+")";
            if (address.isAddressType("delivery"))
                heading += " [Delivery address]";
            if (address.isAddressType("billing"))
                heading += " [Billing address]";
                System.out.println(heading);
                System.out.println(address);
                index++;
            }
       System.out.println();
    }
    
    private void addAddress()
    {
        String oldPrompt = prompt;
        prompt += "Add address: ";
        
        boolean allRequired = false;
        String[] prompts = {"House number: ", "Street: ", "Address1: ", "Address2: ", "Address3: ", "Postcode: "};
        String[] details = inputForm(prompts, allRequired);
        if (details != null){
            Address newAddress = new Address(details);
            currentUser.addAddress(newAddress);
            fileHandler.saveObject(currentUser);
        }   
        System.out.println();
        prompt = oldPrompt;
    }
    
    private void removeAddress()
    {
        Address[] addressArray = currentUser.getAddressArray();
        printAddresses(addressArray);
        
        System.out.println("\nChoose an address to remove");
        String input = EasyIn.getString();
        
        if(!input.equals("q")){
            int inputInt = Integer.parseInt(input);
            if (inputInt <= addressArray.length){
                currentUser.removeAddress(addressArray[inputInt-1]);  
                fileHandler.saveObject(currentUser);
            }
            else
                System.out.println("Address does not exist");
        }
        System.out.println();
    }
   
    private void editAddress()
    {
        String oldPrompt = prompt;
        prompt += "Edit address: ";
        
        Address[] addressArray = currentUser.getAddressArray();
        printAddresses(addressArray);

        System.out.println("Choose an address to edit: ");
        System.out.print(prompt);
        int inputInt = EasyIn.getInt();
        
        if (inputInt <= addressArray.length){
            boolean allRequired = false;
            String[] prompts = {"House number: ", "Street: ", "Address1: ", "Address2: ", "Address3: ", "Postcode: "};
            String[] details = inputForm(prompts, allRequired);
            
            if (details != null){
                Address newAddress = new Address(details);
                addressArray[inputInt-1] = newAddress;
                currentUser.setAddressList(addressArray);          
            
                System.out.println("\nYour new address:");
                System.out.println(newAddress);
            
                fileHandler.saveObject(currentUser);
            }
        }
        else
            System.out.println("Address does not exist");
        
        System.out.println();
        prompt = oldPrompt;
    }
      
    private void specifyAddress(String type)
    {
        Address[] addressArray = currentUser.getAddressArray();
        if (addressArray != null){
            printAddresses(addressArray);
        
            System.out.println("\nYour addresses:");
        
            int index = 1;
            for (Address address : addressArray){
                System.out.println("  Address number ("+index+")");
                System.out.println(address);
                address.setAddressType(type, false);
                index++;
            }
            
            System.out.print("\nChoose a " + type + " address: ");
            String input = EasyIn.getString();
            
            if(!input.equals("q") && input != null){ 
                try {
                    int inputInt = Integer.parseInt(input);
                    if (inputInt <= addressArray.length){
                        addressArray[inputInt-1].setAddressType(type, true);
                        currentUser.setAddressList(addressArray);
                        
                        fileHandler.saveObject(currentUser);
                    }
                    else {
                        System.out.println("Address does not exist");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Make sure you enter an integer");
                }
            }
        }
        
        else {
            System.out.println("You have no addresses");
        }
        System.out.println();
    }
    
    private void showAddress(String type)
    {
        ArrayList<Address> addresses = currentUser.getAddressList();
        for (Address address : addresses){
            if (address.isAddressType(type)){
                System.out.println("\n**Your current " + type + " address**");
                System.out.println(address);
            }
            else
                System.out.println("No " + type + " address specified");
        }
        System.out.println();
    }

   
        
    private void checkout()
    {
        String oldPrompt = prompt;
        prompt += "Checkout: ";
        
        printCheckoutMenu();
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        
        while (!input.equals("q")){
            if (input.equals("menu"))
                printCheckoutMenu();
            else if (input.equals("1"))
                showBasket();
            else if (input.equals("2"))
                deliveryOptions();
            else if (input.equals("3"))
                paymentOptions();
            else if (input.equals("4"))
                checkConfirmOrder();
            else if (input.equals("quit"))
                quit();
            else 
                System.out.println(">>Command not recognised");
            
            printCheckoutMenu();
            System.out.print(prompt);
            input = EasyIn.getString();
        }
        System.out.println();
        prompt = oldPrompt;
    }
    
    private void printCheckoutMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("**Checkout options**");
        System.out.println("(1) Your items");
        System.out.println("(2) Delivery options");
        System.out.println("(3) Payment options");
        if (currentUser.getAddressType("delivery") != null && 
            currentUser.getAddressType("billing") != null &&
            postage != "" && !basket.isEmpty()){
        System.out.println("(4) Check and confirm order");
        }
        System.out.println();
    }
    
    private void deliveryOptions()
    {
        String oldPrompt = prompt;
        prompt += "Delivery options: ";
        
        Address deliveryAddress = currentUser.getAddressType("delivery");
        
        System.out.println("----------------------------------------");
        System.out.println("Your delivery address:");
        if (deliveryAddress != null)
            System.out.println(deliveryAddress);
        else 
            System.out.println("No delivery address specified yet");
                 
        System.out.println("----------------------------------------");
        System.out.println("Specified postage:");
        if (postage != null)
            System.out.println(postage);
        else
            System.out.println("No postage specified yet");
            
        printDeliveryOptionsMenu();
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        while (!input.equals("q")){
            if (input.equals("menu"))
                printDeliveryOptionsMenu();
            else if (input.equals("1"))
                addressOptions();
            else if (input.equals("2"))
                postageOptions();
            else if (input.equals("quit"))
                quit();
            else
                System.out.println(">>Command not recognised");
                
            printDeliveryOptionsMenu();
            System.out.print(prompt);
            input = EasyIn.getString();
        }
        System.out.println();
        prompt = oldPrompt;
    }
    
    private void printDeliveryOptionsMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("**Delivery Options**");
        System.out.println("(1) Change or set delivery address");
        System.out.println("(2) Choose postage options");
        System.out.println();
    }
    
    private void postageOptions()
    {
        String oldPrompt = prompt;
        prompt += "Postage: ";
        
        printPostageOptionsMenu();
        
        System.out.print(prompt);
        String input = EasyIn.getString();

            if (input.equals("menu"))
               printPostageOptionsMenu();
            else if (input.equals("1"))
                postage = "first";
            else if (input.equals("2"))
                postage = "second";
            else if (input.equals("3"))
                postage = "courier";
            else if (input.equals("quit"))
                quit();
            else 
                System.out.println(">>Command not recognised");
            
        fileHandler.saveObject(currentUser);
        System.out.println();
        prompt = oldPrompt;
    }
                   
    private void printPostageOptionsMenu()
    {
        System.out.println("Select postage type: ");
        System.out.println("(1) First class");
        System.out.println("(2) Second class");
        System.out.println("(3) Courier");
        System.out.println();
    }
    
    private void paymentOptions()
    {
        String oldPrompt = prompt;
        prompt += "Payment: ";
        
        Address billingAddress = currentUser.getAddressType("billing");
        
        System.out.println("----------------------------------------");
        System.out.println("Your billing address:");
        if (billingAddress == null)
            System.out.println("No billing address specified yet");
        else
            System.out.println(billingAddress);
            
        printPaymentOptionsMenu();
        System.out.print(prompt);
        String input = EasyIn.getString();
        while (!input.equals("q")){
            if (input.equals("menu"))
                printDeliveryOptionsMenu();
            else if (input.equals("1"))
                addressOptions();
            else
                System.out.println(">>Command not recognised");
            
            printPaymentOptionsMenu();    
            System.out.print(prompt);
            input = EasyIn.getString();
        }
        System.out.println();
        prompt = oldPrompt;
    }
    
    private void printPaymentOptionsMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("**Payment Options**");
        System.out.println("(1) Change or set billing address");
        System.out.println();
    }
        
    private void checkConfirmOrder()
    {
        String oldPrompt = prompt;
        prompt += "Check & confirm: ";
        
        printOrderDetails();
        
        System.out.println("\nConfirm order?");
        System.out.println("yes or no: ");
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        if (input.equals("yes"))
            createOrderAndFinish();
        else if (input.equals("no")){
        }
        else {
        }
        prompt = oldPrompt;
    }
               
    private void printOrderDetails()
    {
        System.out.println("----------------------------------------");
        System.out.println("Your items:");
        System.out.println(basket);
        System.out.println("----------------------------------------");
        System.out.println("Delivery address: ");
        System.out.println(currentUser.getAddressType("delivery"));
        System.out.println("----------------------------------------");
        System.out.println("Billing address: ");
        System.out.println(currentUser.getAddressType("billing"));
        System.out.println("----------------------------------------");
        System.out.println("Postage option: ");
        System.out.println(postage);
        System.out.println();
    }
    
    private void createOrderAndFinish()
    {
        Order order = new Order(currentUser.getUsername(), currentUser.getAddressType("delivery"),
                                currentUser.getAddressType("billing"), basket, postage, basket.getTotal());
        currentUser.addOrder(order);
        
        fileHandler.saveObject(currentUser);
        
        quit();
    }
                                
                   
    private void myAccount()
    {
        String oldPrompt = prompt;
        prompt += "My account: ";
        
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
                addressOptions();
            else if (input.equals("quit"))
                quit();
            else
                System.out.println(">>Command not recognised");
                
            printMyAccountMenu();
            System.out.print(prompt);
            input = EasyIn.getString();
        }
       prompt = oldPrompt;
    }
    
    private void printMyAccountMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("**" + currentUser.getFirstName() + "'s account**");
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
        System.out.println("First name: " + currentUser.getFirstName());
        System.out.println("Last name: " + currentUser.getLastName());
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Password: " + currentUser.getPassword());
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
                currentUser.setFirstName(EasyIn.getString());
            }
            else if (input.equals("2")){
                System.out.print("Enter new second name: ");
                currentUser.setLastName(EasyIn.getString());
            }
            else if (input.equals("3")){
                System.out.print("Enter new username: ");
                currentUser.setUsername(EasyIn.getString());
            }
            else if (input.equals("4")){
                System.out.print("Enter new password: ");
                currentUser.setPassword(EasyIn.getString());
            }
            else if (input.equals("5")){
                fileHandler.saveObject(currentUser);
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
        System.out.println("Your order history:\n");
        for (Order order : currentUser.getOrders()){
            System.out.println(order);
            System.out.println();
        }
    }
    
    public void quit()
    {
        fileHandler.saveObject(currentUser);
        System.out.println("\nThank you for shopping at Robin's Astronomy Shop!");
        System.exit(0);
    }

    
    

}
