import java.util.ArrayList;
import java.util.Iterator;

/**
 * Store details of club memberships.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Club
{
    // Define any necessary fields here ...
    
    private ArrayList members;
    private Membership Member;
    private ArrayList purged;
    
    /**
     * Constructor for objects of class Club
     */
    public Club()
    {
       members = new ArrayList();
    }

    
    /**
     * Add a new member to the club's list of members.
     * @param member The member object to be added.
     */
    public void join(String name, int month, int year)
    {
        members.add(new Membership(name, month, year));
    }
    
    public void join(Membership member)
    {
        String Name = member.getName();
        int Month = member.getMonth();
        int Year = member.getYear();
        members.add(new Membership(Name, Month, Year));
    }
    

    /**
     * @return The number of members (Membership objects) in
     *         the club.
     */
    public int numberOfMembers()
    {
        return members.size();
    }
    
    public void getMemberDetails(int MemberNumber)
    {
        if(MemberNumber > (members.size()-1)){
            System.out.println("Member number does not exit");
        }
        System.out.println(members.get(MemberNumber).toString());
   
    }
    
    public int joinedInMonth(int month)
    {
 int index = 0;
 int count = 0;
 
 while(index < (members.size())){
     Membership Member = (Membership) members.get(index);
     
     if(Member.getMonth() == month){
     count++;
    }
    
    index++;
}
    return count;
}

    public ArrayList purge(int month, int year)
    {   
        int index = 0;
        purged = new ArrayList();
 
        while(index < members.size()){
            Membership Member = (Membership) members.get(index);
     
             if((Member.getMonth() == month) && (Member.getYear() == year)){
             String Name = Member.getName();
             int Month = Member.getMonth();
             int Year = Member.getYear();
    
     purged.add(new Membership(Name, Month, Year));
     members.remove(index);
    }
    else{   
    index++;
}
}
    return purged;
}
    
    public void AllMemberDetails()
    {
    int index = 0;
    System.out.println("------------------------");
    while(index < members.size()){
        Membership Member = (Membership) members.get(index);
        String Name = Member.getName();
        int Month = Member.getMonth();
        int Year = Member.getYear();
        System.out.println("Name: " + Name + ";   Date joined: month " +
        Month + " of year " + Year);
        System.out.println("");
        index++;
    }
}

}