
/**
 * Write a description of class TicketMachine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
    class TicketMachine
{
    // instance variables - replace the example below with your own
    private int price;
    
    private int balance;
    
    private int total;
    
    private int change;
    
    public TicketMachine(int ticketCost)
    {
        price = ticketCost;
        balance = 0;
        total = 0;
    }
    
   public int getPrice()
   {
       return price;
   }
   
   public int getBalance()
   {
       return balance;
   }
   
   public void insertMoney(int amount)
   {
       balance = balance + amount;
   }
   
   public void printTicket()
   {
       if (balance >= price)
      {
       System.out.println("####################");
       System.out.println("#  The BlueJ Line");
       System.out.println("#  Ticket");
       System.out.println("#  " + price + " cents.");
       System.out.println("####################");
       System.out.println();
       
       change = balance - price;
       System.out.println("####################");
       System.out.println("#  Receipt");
       System.out.println("#  Change: " + change + " cents.");
       System.out.println("####################");
       
       total = total + price;
       balance=0;
   }
   else
   {
       System.out.println("   Please enter more money");
   }
}
}