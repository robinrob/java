/**
 * Perform tests of the Day class that involve
 * making single-hour appointments.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2001.09.14
 */
public class TwoHourTests
{
    // The Day object being tested.
    private Day day;

    /**
     * Constructor for objects of class OneHourTests
     */
    public TwoHourTests()
    {
        // Create a Day object that can be used in testing.
        // Individual methods might choose to create
        // their own instances.
        day = new Day(1);
    }

    /**
     * Test basic functionality by booking at either end
     * of a day, and in the middle.
     */
    public void makeThreeAppointments()
    {
        // Start with a fresh Day object.
        day = new Day(1);
        // Create three one-hour appointments.
        Appointment first = new Appointment("Java lecture", 2);
        Appointment second = new Appointment("Java class", 2);
        Appointment third = new Appointment("Meet John", 2);
        
        // Make each appointment at a different time.
        day.makeAppointment(9, first);
        day.makeAppointment(13, second);
        day.makeAppointment(16, third);
        
        day.showAppointments();
    }
    
    /**
     * Check that double-booking is not permitted.
     */
    public void testDoubleBooking()
    {
        // Set up the day with three legitimate appointments.
        makeThreeAppointments();
        Appointment badAppointment = new Appointment("Error", 2);
        day.makeAppointment(9, badAppointment);
        
        // Show that the badAppointment has not been made.
        day.showAppointments();
    }

    /**
     * Test basic functionality by filling a complete
     * day with appointments.
     */
    public void fillTheDay()
    {
        // Start with a fresh Day object.
        day = new Day(1);
        for(int time = Day.START_OF_DAY;
                    time <= Day.FINAL_APPOINTMENT_TIME-1;
                        time+=2) {
            day.makeAppointment(time,
                                new Appointment("Test " + time, 2));
        }
        
        day.showAppointments();
    }
}
