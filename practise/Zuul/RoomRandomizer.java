import java.util.Random;
import java.util.ArrayList;


public class RoomRandomizer
{

    private Random random;
    private ArrayList<Room> roomList;
    
    
    public RoomRandomizer(ArrayList<Room> rooms)
    {
        random = new Random();
        roomList = rooms;
    }
    
    
    
    
    public Room getRandomRoom()
    {
        return roomList.get(random.nextInt(roomList.size() -1));
    }
    
}
        
    