


public class Person
{

    private String[] fullName;
    private String firstName;
    private String[] lastNames;
    private String id;

    public Person(String fullname, String ID)
    {
        fullName = fullname.split(" ");
        id = ID;
    }

        
    public String getFirstName()
    {
        return fullName[0];
    }
    public String getSurname()
    {
        return fullName[fullName.length - 1];
    }
    
    public String[] getFullName()
    {
        return fullName;
    }

    public void changeName(String newname)
    {
        fullName = newname.split(" ");
    }

    public String getID()
    {
        return id;
    }


    public String getLoginName()
    {
        return fullName[0].substring(0,4) + id.substring(0,3);
    }

    public void print()
    {
        System.out.println("ID: " + id);
        System.out.println("Full name: ");
        for (String name : fullName)
        System.out.println(name);
    }
    
    
}      