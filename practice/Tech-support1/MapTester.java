import java.util.HashMap;
/**
 * Write a description of class MapTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MapTester
{
    // instance variables - replace the example below with your own
   private HashMap phonebook;

    /**
     * Constructor for objects of class MapTester
     */
    public MapTester()
    {
    phonebook = new HashMap();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void enterNumber(String name, String number)
    {
        if(!phonebook.containsKey(name)){
            phonebook.put(name,number);
        }  
    }
    
    public Boolean containsNumber(String number)
    {
        if(phonebook.containsValue(number)){
            return true;
        }
        else{
            return null;
        }
    }
    
    public String lookupNumber(String name)
    {
        return (String) phonebook.get(name);
    }
    
    public int numberOfEntries()
    {
        return (int) phonebook.size();
    }
    
}
