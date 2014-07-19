import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Room 
{   
    private String name;
    private String description;
    private HashMap<String,Room> exits;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Room(String name, String description) 
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<String,Room>();
        items = new ArrayList<Item>();
    }
    
    
    public Room getExit(String direction)
    {
        return (Room) exits.get(direction);
    }
        

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }
    

    /**
     * Return the description of the room (the one that was defined
     * in the constructor).
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getExitString()
    {
        String exitString = "";
        Set<String> exitSet = exits.keySet();
        for (String exit : exitSet){
            if (exit != null)
                exitString += exit + " ";
        }
        return exitString;
    }
    
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + "exits are: "+ getExitString();
    }
    
    public ArrayList<Item> getItems()
    {
        return items;
    }
    
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    public void removeItem(Item item)
    {
        items.remove(item);
    }
    
    public String getName()
    {
        return name;
    }
        


}
