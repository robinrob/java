import java.util.ArrayList;

public class Database
{

    private ArrayList<CD> cds;
    private ArrayList<DVD> dvds;
    
    
    public Database()
    {
        cds = new ArrayList<CD>();
        dvds = new ArrayList<DVD>();
    }
    
    
    public void addCD(CD cd)
    {
        cds.add(cd);
    }
    
    public void addDVD(DVD dvd)
    {
        dvds.add(dvd);
    }
    
    
    public void print()
    {
        System.out.println("CDs:");
        for (CD cd : cds)
            System.out.println(cd.toString());

        System.out.println();
        System.out.println("DVDs:");
        for (DVD dvd : dvds)
            System.out.println(dvd.toString());
            
    }
    
}

    
    