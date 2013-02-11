import java.util.ArrayList;
import java.util.Stack;
import java.io.*;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Game 
{
    private Room nextRoom;
    private Parser parser;
    private Stack<Room> roomStack;
    private Room sittingRoom, hallway, diningRoom, utilityArea, kitchen, driveway, bottomOfStairs, garden, garage, 
                 topOfStairs, spareBedroom, oldLanding, tobysBedroom, simonsBedroom, newLanding1, showerRoom, 
                 newLanding2, mumdadsBedroom, bathroom, attic, transporterRoom;
    private Item duke, guineaPig, car, clothesBasket, showerGel, towel, tobysDinner, tRex, spider, mincePies, mole, toby, 
                 banana, gun, iceCream, label, nose, jerry, beer, actionMan;
    private Player player;
    private ArrayList<Room> rooms;
    private RoomRandomizer roomMachine;
    private final static String SAVE_GAME_FILE = "saveGame.txt";
    //private FileWriter writer;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        roomStack = new Stack<Room>();
        rooms = new ArrayList<Room>();
        roomMachine = new RoomRandomizer(rooms);
        //try {
        //    writer = new FileWriter(SAVE_GAME_FILE);
        //}
        //catch (IOException e){
        //    System.out.println("Error creating save file: " + e);
        //}
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
           
        // downstairs rooms
        sittingRoom = new Room("sitting room","in the sitting room");
        hallway = new Room("hallway","in the hallway");
        diningRoom = new Room("dining room", "in the dining room");
        utilityArea = new Room("utility area", "in the utility area");
        kitchen = new Room("kitchen", "in the kitchen");
        driveway = new Room("driveway", "on the driveway outside");
        bottomOfStairs = new Room("stairs", "at the bottom of the stairs");
        garden = new Room("garden", "outside in the garden");
        garage = new Room("garage", "in the cold garage");
        
        // upstairs rooms
        topOfStairs = new Room("top of stairs", "at the top of the stairs");
        spareBedroom = new Room("spare bedroom", "in the spare bedroom");
        oldLanding = new Room("old landing", "the old landing by the airing cupboard");
        tobysBedroom = new Room("toby's bedroom", "in Toby's bedroom");
        simonsBedroom = new Room("simon's bedroom", "in Simon's bedroom");
        newLanding1 = new Room("new landing 1", "halfway along the new landing in the extension");
        showerRoom = new Room("shower room", "the shower room");
        newLanding2 = new Room("new landing 2", "the end of the new landing in the extension");
        mumdadsBedroom = new Room("parent's bedroom", "mum and dad's bedroom");
        bathroom = new Room("bathroom", "in the bathroom");
        attic = new Room("attic", "the dusty attic");
        transporterRoom = new TransporterRoom("transporter room", "in the transporter room", roomMachine);
        
        rooms.add(sittingRoom);
        rooms.add(hallway);
        rooms.add(diningRoom);
        rooms.add(utilityArea);
        rooms.add(kitchen);
        rooms.add(driveway);
        rooms.add(bottomOfStairs);
        rooms.add(garden);
        rooms.add(garage);
        
        rooms.add(topOfStairs);
        rooms.add(spareBedroom);
        rooms.add(oldLanding);
        rooms.add(tobysBedroom);
        rooms.add(simonsBedroom);
        rooms.add(newLanding1);
        rooms.add(showerRoom);
        rooms.add(newLanding2);
        rooms.add(mumdadsBedroom);
        rooms.add(bathroom);
        rooms.add(attic);
        rooms.add(transporterRoom);
        
        
        // initialise downstairs room exits
        sittingRoom.setExit("east", hallway);
        sittingRoom.addItem(nose);
        
        hallway.setExit("north", diningRoom);
        hallway.setExit("east", bottomOfStairs);
        hallway.setExit("south", driveway);
        hallway.setExit("west", sittingRoom);
        hallway.addItem(duke);
        
        diningRoom.setExit("east", utilityArea);
        diningRoom.setExit("south", hallway);
        diningRoom.addItem(tobysDinner);
        
        utilityArea.setExit("north", kitchen);
        utilityArea.setExit("south", garage);
        utilityArea.setExit("west", diningRoom);
        utilityArea.addItem(guineaPig);
        
        kitchen.setExit("south", utilityArea);
        kitchen.setExit("west", garden);
        kitchen.addItem(mincePies);
        
        driveway.setExit("north", hallway);
        driveway.addItem(car);
        
        bottomOfStairs.setExit("west", hallway);
        bottomOfStairs.setExit("up", topOfStairs);
        
        garden.setExit("east", kitchen);
        garden.addItem(mole);
        
        garage.setExit("north", utilityArea);
        garage.addItem(tRex);
        
        //initilise upstairs room exits
        topOfStairs.setExit("north", oldLanding);
        topOfStairs.setExit("east", newLanding1);
        topOfStairs.setExit("west", spareBedroom);
        topOfStairs.setExit("down", bottomOfStairs);
        topOfStairs.addItem(clothesBasket);
        
        spareBedroom.setExit("east", topOfStairs);
        
        oldLanding.setExit("north", simonsBedroom);
        oldLanding.setExit("south", topOfStairs);
        oldLanding.setExit("west", tobysBedroom);
        oldLanding.setExit("up", attic);
        
        tobysBedroom.setExit("east", oldLanding);
        tobysBedroom.addItem(spider);
        tobysBedroom.addItem(toby);
        
        simonsBedroom.setExit("south", oldLanding);
        
        newLanding1.setExit("north", showerRoom);
        newLanding1.setExit("east", newLanding2);
        newLanding1.setExit("west", topOfStairs);
        
        showerRoom.setExit("south", newLanding1);
        showerRoom.addItem(showerGel);
        
        newLanding2.setExit("north", mumdadsBedroom);
        newLanding2.setExit("south", bathroom);
        newLanding2.setExit("west", newLanding1);
        
        mumdadsBedroom.setExit("south", newLanding2);
        mumdadsBedroom.addItem(jerry);
        
        bathroom.setExit("north", newLanding2);
        bathroom.addItem(towel);
        
        attic.setExit("east", transporterRoom);
        attic.addItem(spider);
        attic.addItem(actionMan);
        
        transporterRoom.setExit("somewhere", null);
        
        
       
        
    }
    
    
    private void createItems()
    {
        duke = new Item("duke", "besgusting hairy dog", 12);
        guineaPig = new Item("guineaPig", "a guinea pig", 1.5);
        car = new Item("car", "the car", 1200);
        clothesBasket = new Item("basket", "clothes basket full of dirty clothes", 10);
        showerGel = new Item("gel", "a bottle of shower gel", 0.3);
        towel = new Item("towel", "a dirty wet towel", 1);
        tobysDinner = new Item("dinner", "toby's dinner which he still hasn't eaten!", 0.8);
        tRex = new Item("t-rex", "a GIGANTIC SCARY T-REX!", 20000);
        spider = new Item("spider", "a massive black spider", 0.005);
        mincePies = new Item("pies", "a tin of mince pies", 1);
        mole = new Item("mole", "a naughty mole digging up the garden!", 0.6);
        toby = new Item("toby", "Toby", 21, true, "Help!  I've lost Jerry!  " + player.getName() + ", will you help me find him?");
        actionMan = new Item("actionMan", "a discarded action man", 0.7);
        
        
        banana = new Item("banana,","healthy banana", 0.2);
        gun = new Item("gun","gun", 1.4);
        iceCream = new Item("iceCream","tasty ice cream", 0.2);
        label = new Item("label","label", 0.005);
        nose = new Item("nose","nose", 0.1);
        jerry = new Item("jerry","Jerry", 0.3);
        beer = new Item("beer","beer", 0.5);
    }
    
    

    
        
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        createPlayer();
        createItems();
        createRooms();
        player.setCurrentRoom(tobysBedroom);
        printLocationInfo();
        listRoomItems();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            saveGame(command);
            finished = processCommand(command);
            testFoundJerry();
                
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Welcome to 12 Macualay Drive!");
        System.out.println("Walk around and explore. You may find some interesting things.");
        System.out.println("Type 'help' to see a list of commands.");
        System.out.println();
    }
    
    private void testFoundJerry()
    {
        if (player.getCurrentRoom().getItems().contains(toby) && player.getCurrentRoom().getItems().contains(jerry))
        System.out.println("\n'Hurray!  Cheers my dears " + player.getName() + ", you found Jerry!'");
    }
        
    
    private void createPlayer()
    {
        System.out.print("Enter player name: ");
        String name = parser.getString();
        player = new Player(name);
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help") && !hasMoreWords(command))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("look"))
            look(command);
        else if (commandWord.equals("examine"))
            examine(command);
        else if (commandWord.equals("back"))
            goBack(command);
        else if (commandWord.equals("eat"))
            eat(command);
        else if (commandWord.equals("inventory"))
            inventory(command);
        else if (commandWord.equals("drop"))
            playerDrop(command);
        else if (commandWord.equals("pickup"))
            playerPickup(command);
        else if (commandWord.equals("talk"))
            talk(command);

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Command words:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            roomStack.push(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
            listRoomItems();
        }
    }

    
    private boolean hasMoreWords(Command command)
    {
        if(command.hasSecondWord()){
            System.out.println("command not recognised.");
            return true;
        }
        return false;
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
    
    private void look(Command command)
    {
        if(command.hasSecondWord())
            System.out.println("Look what?");
        else
            printLocationInfo();
    }
    
    private void printLocationInfo()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    
    private void examine(Command command)
    {
        if(command.hasSecondWord())
            System.out.println("examine what?");
        else 
            listRoomItems();
    }
    
    private void listRoomItems()
    {
        boolean itemsFound = false;
        ArrayList<Item> items = player.getCurrentRoom().getItems();
        
        System.out.println();
        System.out.println("Items in area:");
            for (Item item : player.getCurrentRoom().getItems()){
                if (!(item == null)){
                    System.out.println("item: " + item.getDescription() + " (" + item.getName() +")" + ", weight: " + item.getWeight() + " kg");
                    itemsFound = true;
                }
            } 
            if (!itemsFound)
                System.out.println("none");
    }

    
        
    
    private void goBack(Command command)
    {   
        if(command.hasSecondWord())
            System.out.println("back what?");
            
        try{
        player.setCurrentRoom((Room) roomStack.pop());
        printLocationInfo();
        listRoomItems();
        }
        catch (Exception e) {
        System.out.println("You are back to where you started.\n");
        }
    }
    
    private void eat(Command command)
    {
        if(!command.hasSecondWord())
            System.out.println("eat what?");
        
        Item item = getRoomItem(command.getSecondWord());
        
        if (item == null)
            System.out.println("This area does not contain that item.");
        else {
              if (item.getWeight() <= 0.1)
                  System.out.println("Not very nutritional!");
              else if (item.getWeight() > 0.1 && item.getWeight() <= 0.3)
                  System.out.println("A light snack.");
              else if (item.getWeight() > 0.3 && item.getWeight() <= 0.5)
                  System.out.println("A decent snack.");
              else if (item.getWeight() > 0.5 && item.getWeight() <= 1.0)
                  System.out.println("A light meal.");
              else if (item.getWeight() > 1.0)
                  System.out.println("A hearty meal!");
              player.getCurrentRoom().removeItem(item);
        }
    }
    
    private void inventory(Command command)
    {
        if (command.hasSecondWord())
            System.out.println("I don't know what you mean...");
        
        else {
            System.out.println("Your inventory: ");
            for (Item item : player.getItems()){
                System.out.println(item.getDescription() + " (" + item.getName() + "), " + item.getWeight() + " kg");
            }
        }
    }
    
    private Item getRoomItem(String name)
    {
        for (Item item : player.getCurrentRoom().getItems()){
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }
    
    private Item getPlayerItem(String name)
    {
        for (Item item : player.getItems()){
            if (item.getName().equals(name))
                return item;;
        }
        return null;
    }
            
    private void playerDrop(Command command)
    {
        if (!command.hasSecondWord())
            System.out.println("drop what?");
            
        else {
            Item item = getPlayerItem(command.getSecondWord());
            if (item == null)
                System.out.println("You don't have that item.");
            else {
                player.getCurrentRoom().addItem(item);
                player.removeItem(item);
                System.out.println("Item dropped.");
            }
        }
    }
    
    private void playerPickup(Command command)
    {
        if (!command.hasSecondWord())
            System.out.println("pickup what?");
            
        else {
            Item item = getRoomItem(command.getSecondWord());
            if (item == null)
                System.out.println("This area does not contain that item!");
            else {
                player.addItem(item);
                player.getCurrentRoom().removeItem(item);
                System.out.println("Item added to inventory.");
            }
        }
    }
    
    private void talk(Command command)
    {
        if (!command.hasSecondWord())
            System.out.println("talk to who?");
            
        else {
            Item person = getRoomItem(command.getSecondWord());
            if (person == null)
                System.out.println("This area does not contain that person!");
            else {
                if (!person.isAnimate())
                    System.out.println("You can't talk to inanimate objects!");
                else 
                    System.out.println("'" + person.getSpeech() + "'");
            }
        }
    }
    
    public void saveGame(Command command)
    {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(SAVE_GAME_FILE));
            FileWriter writer = new FileWriter(SAVE_GAME_FILE);
            writer.write(command.getCommandWord() + command.getSecondWord());
            writer.close();            
        }
        catch (IOException e1) {
            //System.out.println("Save error with reading: " + e1);
            try {
                FileWriter writer2 = new FileWriter(SAVE_GAME_FILE);
                writer2.write("Command history for player " + player.getName() + ": \n\n");
                writer2.write(command.getCommandWord() + command.getSecondWord());
                writer2.close();
            }
            catch (IOException e2) {
                System.out.println("Save error with writing: " + e2);
            }
        }
    }
        
                  
            
        
        
            
        

        
}
