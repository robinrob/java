

public class Bid
{

private final Person bidder;

private final long value;


public Bid(Person bidder, long value)
{
this.bidder = bidder;
this.value = value;
}

public Person getBidder()
{
return bidder;
}

public long getValue()
{
return value;
}
}
