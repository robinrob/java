


public abstract class Manipulation
{
    private String name;
    
    
    public Manipulation(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public abstract void apply(OFImage image);
    
}