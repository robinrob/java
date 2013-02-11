


public class CheckoutModule extends Module
{


    public CheckoutModule(Store store)
    {
        super(store);
    }
    
    
    
    
    public void run()
    {
        prompt = store.getPrompt() + "Checkout: ";
        
        printCheckoutMenu();
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        
        while (!input.equals("q")){
            if (input.equals("menu"))
                printCheckoutMenu();
            else if (input.equals("1"))
                store.getBasketModule().run();
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
    }
    
    private void printCheckoutMenu()
    {
        System.out.println("----------------------------------------");
        System.out.println("**Checkout options**");
        System.out.println("(1) Your items");
        System.out.println("(2) Delivery options");
        System.out.println("(3) Payment options");
        if (store.getCurrentUser().getAddressType("delivery") != null && 
            store.getCurrentUser().getAddressType("billing") != null &&
            store.getCurrentUser().getPostage() != "" && !store.getBasket().isEmpty()){
        System.out.println("(4) Check and confirm order");
        }
        System.out.println();
    }
    
    private void deliveryOptions()
    {
        String oldPrompt = prompt;
        prompt += "Delivery options: ";
        
        Address deliveryAddress = store.getCurrentUser().getAddressType("delivery");
        
        System.out.println("----------------------------------------");
        System.out.println("Your delivery address:");
        if (deliveryAddress != null)
            System.out.println(deliveryAddress);
        else 
            System.out.println("No delivery address specified yet");
                 
        System.out.println("----------------------------------------");
        System.out.println("Specified postage:");
        if (store.getCurrentUser().getPostage() != null)
            System.out.println(store.getCurrentUser().getPostage());
        else
            System.out.println("No postage specified yet");
            
        printDeliveryOptionsMenu();
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        while (!input.equals("q")){
            if (input.equals("menu"))
                printDeliveryOptionsMenu();
            else if (input.equals("1"))
                store.getAddressModule().run();
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
                store.getCurrentUser().setPostage("first");
            else if (input.equals("2"))
                store.getCurrentUser().setPostage("second");
            else if (input.equals("3"))
                store.getCurrentUser().setPostage("courier");
            else if (input.equals("quit"))
                quit();
            else 
                System.out.println(">>Command not recognised");
            
        store.getFileHandler().saveObject(store.getCurrentUser());
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
        
        Address billingAddress = store.getCurrentUser().getAddressType("billing");
        
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
                store.getAddressModule().run();
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
        
        Order order = new Order(store.getCurrentUser().getUsername(), 
                                store.getCurrentUser().getAddressType("delivery"),
                                store.getCurrentUser().getAddressType("billing"), 
                                store.getBasket(), 
                                store.getCurrentUser().getPostage(), 
                                store.getBasket().getTotal());
                                
        System.out.println(order);
        System.out.println("\nConfirm order?");
        System.out.println("yes or no: ");
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        if (input.equals("yes")){
            store.getCurrentUser().addOrder(order);
            quit();
        }
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
        System.out.println(store.getBasket());
        System.out.println("----------------------------------------");
        System.out.println("Delivery address: ");
        System.out.println(store.getCurrentUser().getAddressType("delivery"));
        System.out.println("----------------------------------------");
        System.out.println("Billing address: ");
        System.out.println(store.getCurrentUser().getAddressType("billing"));
        System.out.println("----------------------------------------");
        System.out.println("Postage option: ");
        System.out.println(store.getCurrentUser().getPostage());
        System.out.println();
    }
    
    
    
}
        