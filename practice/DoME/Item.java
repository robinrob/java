

public class Item
{

    private String title;
    private String comment;
    private boolean ownIt = false;
    private int playingTime;
    private String mediumName;
    
    public Item(String title, int playingTime)
    {
        this.title = title;
        this.playingTime = playingTime;
        comment = "";
    }
    
    
     //accessor methods

    protected String getTitle()
    {
        return title;
    }
    protected String getComment()
    {
        return comment;
    }
    protected boolean getOwnIt()
    {
        return ownIt;
    }
    protected int getPlayingTime()
    {
        return playingTime;
    }
    protected String getMediumName()
    {
        return mediumName;
    }
    
   
    
    //mutator methods

    protected void setTitle(String track)
    {
        this.title = title;
    }
    protected void setComment(String comment)
    {
        this.comment = comment;
    }
    protected void setOwnIt(boolean bool)
    {
        ownIt = bool;
    }
    protected void setPlayingTime(int number)
    {
        playingTime = number;
    }
    protected void setMediumName(String name)
    {
        mediumName = name;
    }
    
    protected String spacing()
    {
        int spaces = mediumName.length() + 2;
        String spacing = "";
        for (int i = 0; i < spaces; i++){
            spacing += " ";
        }
        return spacing;
    }
        
    public String toString()
    {
        String line1 = ("----------------------------------------\n");
        String line2 = mediumName + ": " + title;
        if (ownIt)
            return line1 + line2 + " (" + playingTime + " mins) *";
        else
            return line1 + line2;
    }
    
    
}