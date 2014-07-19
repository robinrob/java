

public class DuplicateKeyException extends Exception
{

    private String key;
    
    public DuplicateKeyException(String key)
    {
        this.key = key;
    }
    
    public String getKey()
    {
        return key;
    }
    
    public String toString()
    {
        return "Key '" + key + "' already in use.";
    }
    
}