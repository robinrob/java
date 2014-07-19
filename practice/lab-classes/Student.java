


public class Student extends Person
{

    private int credits;

    public Student(String fullname, String ID)
    {
        super(fullname, ID);
        credits = 0;
    }

        

    public void addCredits(int newCredits)
    {
        credits = credits + newCredits;
    }

    public int getCredits()
    {
        return credits;
    }

    public void print()
    {
        super.print();
        System.out.println("credits: " + credits);
    }
    
    public void testMethod()
    {
        Student st = new Student("blah", "blah");
        String s = st.toString();
        System.out.println(st);
    }
    
    
}      