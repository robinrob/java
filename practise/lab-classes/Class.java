import java.util.ArrayList;


public class Class
{
    private String name;
    private ArrayList<Student> students;
    private Instructor instructor;
    
    
    
    public String getName(){
        return name;
    }
    
    public void addStudent(Student student)
    {
        students.add(student);
    }
    public void removeStudent(Student student)
    {
        students.remove(student);
    }
    
    public void addInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }
    public void removeInstructor(Instructor instructor)
    {
        this.instructor = null;
    }
    
    public void print()
    {
        System.out.println("Students:");
        for (Student student : students){
            System.out.println("" + student.getFirstName() + student.getSurname());
        }
    }
    
    
}