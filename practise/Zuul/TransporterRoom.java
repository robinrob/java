import java.util.ArrayList;


public class TransporterRoom extends Room
{
    private RoomRandomizer roomMachine;
    
    public TransporterRoom(String name, String description, RoomRandomizer randomizer)
    {   
        super(name, description);
        roomMachine = randomizer;
    }
    
    
    public Room getExit(String direction)
    {
        return roomMachine.getRandomRoom();
    }
   
        
    
}