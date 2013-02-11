import java.util.ArrayList;


public class Player
{

    private String name;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Room currentRoom;
    
    
    public Player(String name)
    {
        this.name = name;
    }
    
    
    public String getName()
    {
        return name;
    }
    public ArrayList<Item> getItems()
    {
        return items;
    }
    public Item getItem(String object)
    {
        for (Item item : items){
            if (item.getName().equals(object))
            return item;
        }
        return null;
    }
    
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    public void removeItem(Item item)
    {
        items.remove(item);
    }
    
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    
    

    
}
    
    
    
    
    
    