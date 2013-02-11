import java.util.ArrayList;
import java.util.Iterator;

public class Auction
{

private ArrayList lots;

private int nextLotNumber;

public Auction()
{
lots = new ArrayList();
nextLotNumber = 1;
}

public void enterLot(String description)
{
lots.add(new Lot(nextLotNumber,description));
nextLotNumber++;
}

public void showLots()
{
Iterator it = lots.iterator();
System.out.println("---------------------");
while(it.hasNext()){
Lot lot = (Lot) it.next();
System.out.println(lot.toString());
}
}

public void bidFor(int lotNumber, Person bidder, long value)
{
Lot selectedLot = getLot(lotNumber);
if(selectedLot != null){
if(selectedLot.bidFor(new Bid(bidder, value))){
System.out.println("The bid for lot number " + lotNumber + " was successful.");
}
else {
System.out.println("Lot number: " + lotNumber + " already has a bid of: "
+ selectedLot.getHighestBid().getValue());
}
}
}

public Lot getLot(int lotNumber)
{
if((lotNumber >= 1) && (lotNumber < nextLotNumber)){

int size=lots.size();
Lot selectedLot = (Lot) lots.get(size - 1);

while((selectedLot.getNumber() != lotNumber)){
size --;
selectedLot = (Lot) lots.get(size);
}

if(selectedLot.getNumber() != lotNumber){
System.out.println("Interal error: " + "Wrong lot returned. " + "Number: " 
+ lotNumber);
}
return selectedLot;
}
else {
System.out.println("Lot number: " + lotNumber + " does not exist.");

return null;
}
}

public void close()
{
Iterator it = lots.iterator();

while(it.hasNext()){
Lot lot = (Lot) it.next();
if(lot.getHighestBid() != null){
System.out.println(lot.toString() + "   SOLD for: " 
+ lot.getHighestBid().getValue());
}
else{
System.out.println(lot.toString() + "   UNSOLD.");
}
}
}

public void removeLot(int lotNumber)
{
lots.remove(lotNumber-1);
}

}