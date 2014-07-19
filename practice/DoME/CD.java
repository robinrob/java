

public class CD extends Item
{

    private String artist;
    private int numberOfTracks;

    
    public CD(String title, String artist, int number, int time)
    {
        super(title, time);
        this.artist = artist;
        numberOfTracks = number;
        setMediumName("CD");
    }
    
    
    //accessor methods
    public String getArtist()
    {
        return artist;
    }
    public int getNumberOfTracks()
    {
        return numberOfTracks;
    }


    
    //mutator methods
    public void setArtist(String artist)
    {
        this.artist = artist;
    }
    public void setNumberOfTracks(int number)
    {
        numberOfTracks = number;
    }

    
    //extra methods
    public String toString()
    {
        return super.toString() + "\n" + spacing() + "artist: " + artist
                                + "\n" + spacing() + "tracks: " + numberOfTracks
                                + "\n" + spacing() + getComment();
    }
    
    public void print()
    {
        System.out.println(toString());
    }
    
    
}

    
    
    