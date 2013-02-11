import java.io.*;

public class SavingClass
{

    private Notebook notebook;

    public SavingClass(Notebook notebook)
    {
        this.notebook = notebook;
    }
    
    
    public void saveObjectToFile(String destination)
    {
        try {
            File destinationPath = new File(destination);//makeAbsoluteFilename(destination);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destinationPath));
            os.writeObject(notebook);
            os.close();
        }
        catch (IOException e) {
            System.out.println("Error writing object: " + e);
        }
    }
    
}
    
    