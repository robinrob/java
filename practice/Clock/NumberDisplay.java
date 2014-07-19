
/**
 * Write a description of class NumberDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NumberDisplay
{
    private int limit;
    private int value;

    public NumberDisplay(int RollOverLimit)
    {
        limit = RollOverLimit;
        value = 0;
    }

    public int getValue()
    {
        return value;
    }

public void setValue(int replacementValue)
{
if ((replacementValue >= 0) && (replacementValue < limit)) value = replacementValue;
}

public String getDisplayValue()
{
if (value<10) {
    return "0" + value;
}
else {
    return "" + value;      
}
}

public void increment()
{
value = (value + 1) % limit;
}
}
