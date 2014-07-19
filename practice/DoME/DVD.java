

public class DVD extends Item
{

    private String director;
    
    public DVD(String title, String director, int time)
    {
        super(title, time);
        this.director = director;
        setMediumName("DVD");
    }
    
    public String getDirector()
    {
        return director;
    }
    
    //mutators
    public void setDirector(String director)
    {
        this.director = director;
    }

    
    public String toString()
    {
        return super.toString() + "\n" + spacing() + "Director: " + director
                                + "\n" + spacing() + getComment();
    }
        
    public void print()
    {
        System.out.println(toString());
    }
    
    
    
    
}
    