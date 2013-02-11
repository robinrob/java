
public class ClockDisplay
{
private NumberDisplay hours;
private NumberDisplay minutes;
private String DisplayString;


public ClockDisplay()
{
hours = new NumberDisplay(24);
minutes = new NumberDisplay(60);
updateDisplay();
}

public ClockDisplay(int Hour, int Minute)
{
hours = new NumberDisplay(24);
minutes = new NumberDisplay(60);
setTime(Hour,Minute);
}



public void timeTick()
{
minutes.increment();
if (minutes.getValue() == 0) {
hours.increment();
}
updateDisplay();
}

public void setTime(int Hour, int Minute)
{
hours.setValue(Hour);
minutes.setValue(Minute);
updateDisplay();
}

public String getTime()
{
return DisplayString;
}

private void updateDisplay()
{
DisplayString = "Time = " + hours.getDisplayValue() + ":" + minutes.getDisplayValue();
}
}