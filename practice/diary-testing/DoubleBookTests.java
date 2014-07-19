
/**
 * Write a description of class DoubleBookTests here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DoubleBookTests
{
    // instance variables - replace the example below with your own
    private Day day;

    /**
     * Constructor for objects of class DoubleBookTests
     */
    public void bookAllDaySingleHours()
    {
        day = new Day(1);
        for (int time = Day.START_OF_DAY; time <= Day.FINAL_APPOINTMENT_TIME; time++){
        day.makeAppointment(time, new Appointment("Test_single " + time, 1));
        day.showAppointments();
    }
}

    public void overBookAllDayTwoHours()
    {
        day = new Day(1);
        bookAllDaySingleHours();
        for (int time = Day.START_OF_DAY; time <= Day.FINAL_APPOINTMENT_TIME-1; time+=2){
            day.makeAppointment(time, new Appointment("Test_double " + time, 2));
        }
        day.showAppointments();
    }
    
    public void overBookAllDayFourHours()
    {
        day = new Day(1);
        bookAllDaySingleHours();
        for (int time = Day.START_OF_DAY; time <= Day.FINAL_APPOINTMENT_TIME-3; time+=4){
            day.makeAppointment(time, new Appointment("Test_qaudruple " + time, 4));
        }
        day.showAppointments();
    }

}