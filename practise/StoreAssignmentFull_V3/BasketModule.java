


public class BasketModule extends Module
{


    public BasketModule(Store store)
    {
        super(store);
    }
    
    
    
    
    public void run()
    {
        //String oldPrompt = prompt;
        prompt = store.getPrompt() + "Basket: ";
        
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
    
    public void showBasket()
    {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("    Shopping basket:");
        System.out.println(store.getBasket().toString());
    }    
    
    public void addToBasket()
    {
        String oldPrompt = prompt;
        prompt += "Add to basket: ";
        
        System.out.print(prompt + "Enter product name: ");
        String inputName = EasyIn.getString();
        System.out.print(prompt + "Enter quantity: ");
        String inputQuantity = EasyIn.getString();
            
        if (store.getCatalogue().productExists(inputName)){
            store.getBasket().addItem(inputName,store.getCatalogue().getProductPrice(inputName),Integer.parseInt(inputQuantity));
            System.out.println(">>Item added successfully");
        }
        else {
            System.out.println(">>Item not added");
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
        if (store.getBasket().deleteItem(input)){
            System.out.println(">>Item removed successfully");
        }
        else {
            System.out.println(">>Item not removed");
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
            if (store.getBasket().itemExists(inputName)){
                store.getBasket().getItem(inputName).changeQuantity(inputQuantity);
                System.out.println(">>Item quantity successfully modified");
            }
            else {
                System.out.println(">>Item quantity not modified");
            }
        }
        System.out.println();
        prompt = oldPrompt;
    }
    
    
}