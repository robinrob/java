

public class Item
{

    private String name;
    private String description;
    private double weight;
    private boolean animate = false;
    private String speech;
    
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        
    }
    
    public Item(String name, String description, double weight, boolean animate, String speech)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.animate = animate;
        this.speech = speech;
    }
    
    
    
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public double getWeight()
    {
        return weight;
    }
    public boolean isAnimate()
    {
        return animate;
    }
    public String getSpeech()
    {
        return speech;
    }
}
    
    