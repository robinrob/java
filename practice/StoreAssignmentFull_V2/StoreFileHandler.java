import java.io.*;

public class StoreFileHandler
{

    //private Store store;
    private static final String USER_DIR = "userFiles/";
    private static final String CATALOGUE = "catalogue.txt";
    
    
    public StoreFileHandler()
    {
    }
    
    
    
    public User loadUser(String[] details)
    {
        String inputName = details[0];
        String inputPassword = details[1];
        
        User returnUser = null;
        File file = new File(USER_DIR + inputName);
        if (file.exists()) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
                User user = (User) inputStream.readObject();
                String password = user.getPassword();
                if (inputPassword.equals(password))
                    returnUser = user;
            }
            catch (FileNotFoundException e) {
                System.out.println("Details not found");
            }
            catch (ClassNotFoundException e) {
                System.out.println("Error: " + e);
            }
            catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
        return returnUser;
    }
    
    
    public Catalogue loadCatalogue()
    {
        File file = new File(CATALOGUE);
        Catalogue catalogue = new Catalogue();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String name = reader.readLine();
            while (name != null){
                int price = Integer.parseInt(reader.readLine());
                String description = reader.readLine();
                catalogue.addProduct(name, price, description);
                name = reader.readLine();
            }
        }
        catch (IOException e){
            System.out.println("Error reading file: " + file);
        }
        return catalogue;
    }
                
                
        
    public boolean saveObject(Object object)
    {
        boolean saved = false;
        File file = null;
        if (object instanceof User) {
            file = new File(USER_DIR + ((User) object).getUsername());
        }
        if (file == null)
            return false;
        
        try { 
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(object);
            outputStream.close();
            saved = true;
            System.out.println("Saved");
        }
        
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return saved;
    }

 
}