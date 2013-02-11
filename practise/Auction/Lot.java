

public class Lot
{

private final int number;

private String description;

private Bid highestBid;


public Lot(int number, String description)
{
this.number = number;
this.description = description;
}


public boolean bidFor(Bid bid)
{
if((highestBid == null) || (bid.getValue() > highestBid.getValue()))
{
highestBid = bid;
return true;
}
else {
return false;
}
}

public String toString()
{
String details = number + ": " + description;
if(highestBid != null){
details += "     Bid: " + highestBid.getValue();
}
else {
details += "     (No bid)";
}
return details;
}

public int getNumber()
{
return number;
}

public String getDescription()
{
return description;
}

public Bid getHighestBid()
{
return highestBid;
}
}





