import java.util.ArrayList;

public class AddressModule
{
    private Store store;
    private String prompt;
    
    public AddressModule(Store store)
    {
        this.store = store;
        prompt = "";
    }
    
    private void run()
    {
        //String oldPrompt = prompt;
        prompt = store.getPrompt() + "Address options: ";

        printAddressOptionsMenu();
        
        System.out.print(prompt);
        String input = EasyIn.getString();
        
        while (!input.equals("q")){

            if (input.equals("1"))
                printAddresses(store.getCurrentUser().getAddressArray());
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
                store.quit();
            
            printAddressOptionsMenu();
            System.out.print(prompt);
            input = EasyIn.getString();
        }
        //prompt = oldPrompt;
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
        String[] details = store.inputForm(prompts, allRequired);
        if (details != null){
            Address newAddress = new Address(details);
            store.getCurrentUser().addAddress(newAddress);
            store.getFileHandler().saveObject(store.getCurrentUser());
        }   
        System.out.println();
        prompt = oldPrompt;
    }
    
    private void removeAddress()
    {
        Address[] addressArray = store.getCurrentUser().getAddressArray();
        printAddresses(addressArray);
        
        System.out.println("\nChoose an address to remove");
        String input = EasyIn.getString();
        
        if(!input.equals("q")){
            int inputInt = Integer.parseInt(input);
            if (inputInt <= addressArray.length){
                store.getCurrentUser().removeAddress(addressArray[inputInt-1]);  
                store.getFileHandler().saveObject(store.getCurrentUser());
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
        
        Address[] addressArray = store.getCurrentUser().getAddressArray();
        printAddresses(addressArray);

        System.out.println("Choose an address to edit: ");
        System.out.print(prompt);
        int inputInt = EasyIn.getInt();
        
        if (inputInt <= addressArray.length){
            boolean allRequired = false;
            String[] prompts = {"House number: ", "Street: ", "Address1: ", "Address2: ", "Address3: ", "Postcode: "};
            String[] details = store.inputForm(prompts, allRequired);
            
            if (details != null){
                Address newAddress = new Address(details);
                addressArray[inputInt-1] = newAddress;
                store.getCurrentUser().setAddressList(addressArray);          
            
                System.out.println("\nYour new address:");
                System.out.println(newAddress);
            
                store.getFileHandler().saveObject(store.getCurrentUser());
            }
        }
        else
            System.out.println("Address does not exist");
        
        System.out.println();
        prompt = oldPrompt;
    }
      
    private void specifyAddress(String type)
    {
        Address[] addressArray = store.getCurrentUser().getAddressArray();
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
                        store.getCurrentUser().setAddressList(addressArray);
                        
                        store.getFileHandler().saveObject(store.getCurrentUser());
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
        ArrayList<Address> addresses = store.getCurrentUser().getAddressList();
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
    
    
    
    
    
}