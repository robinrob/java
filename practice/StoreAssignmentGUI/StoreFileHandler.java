import java.io.*;
import java.awt.image.BufferedImage;
//import javax.imageio.stream.FileImageInputStream;
import javax.imageio.ImageIO;
//edu.sdsc.mbt.gui;


public class StoreFileHandler
{

    //private Store store;
    private static final String USER_DIR = "userFiles/";
    private static final String CATALOGUE = "catalogue.txt";
    private static final String IMAGE_DIR = "images/";
    
    
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
               // else 
                   // storeGUI.statusUpdate("Details could not be found.");
            }
            catch (FileNotFoundException e) {
               //storeGUI.statusUpdate("Details could not be found.");
            }
            catch (ClassNotFoundException e) {
               // storeGUI.statusUpdate("Details could not be found.");
            }
            catch (IOException e) {
               // storeGUI.statusUpdate("Details could not be found.");
            }
        }
        return returnUser;
    }
    
    
    public Catalogue loadCatalogue()
    {
        File file = new File(CATALOGUE);
        Catalogue catalogue = new Catalogue();
        String[] possibleTypes = Product.TYPES;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String type = "";
            Product product = null;

            while (line != null){
                for (String possibleType : possibleTypes){
                    if (line.equals(possibleType)){
                        type = line;
                        line = reader.readLine();
                    }
                }

                
                String name = line;
                int price = Integer.parseInt(reader.readLine());
                String description = reader.readLine();
                String imageFilename = reader.readLine();
                BufferedImage image = null;
                if (!imageFilename.equals(""))
                    image = loadImage(imageFilename);
                
                    
                
                if (type.equals("Telescopes")){    
                    int aperture = Integer.parseInt(reader.readLine());
                    int focalLength = Integer.parseInt(reader.readLine());
                    product = new Telescopes(name, price, description, image, aperture, focalLength);
                }
                else if (type.equals("Binoculars")){
                    int aperture = Integer.parseInt(reader.readLine());
                    int magnification = Integer.parseInt(reader.readLine());
                    String fov = reader.readLine();
                    product = new Binoculars(name, price, description, image, aperture, magnification, fov);
                }
                else if (type.equals("Software")){
                    String version = reader.readLine();
                    String platform = reader.readLine();
                    product = new Software(name, price, description, image, version, platform);
                }
                
                if (product != null){
                    catalogue.addProduct(product);
                }
                line = reader.readLine();
            }
        }
        catch (IOException e){
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

        try { 
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(object);
            outputStream.close();
            saved = true;
        }
        
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return saved;
    }
    
    public BufferedImage loadImage(String filename)
    {
        BufferedImage returnImage = null;
        File file = new File(IMAGE_DIR + filename);
        
        if (file.exists()) {
            try {
                returnImage = ImageIO.read(file);
            } 
            catch (IOException e) {
            }

        }
        return returnImage;
    }
                
                
}