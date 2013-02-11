


public class Instructor extends Person
{

    private String title;
    

    public Instructor(String title, String fullname, String ID)
    {
        super(fullname, ID);
        title = title;
    }

       
    public void print()
    {
        super.print();
        System.out.println("Title: " + title);
    }
    
    
}      