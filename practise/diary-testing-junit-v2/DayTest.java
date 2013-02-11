/**
 * The test class DayTest.
 *
 * @author  David J. Barnes and Michael Kolling
 * @version 2003.10.08
 */
public class DayTest extends junit.framework.TestCase
{
	private Day day1;
	private Appointment appointm1;

    /**
     * Default constructor for test class DayTest
     */
    public DayTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
		day1 = new Day(1);
		appointm1 = new Appointment("Robin", 1);
	}

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    /**
     * Test basic functionality by booking at either end
     * of a day, and in the middle.
     */
    public void testMakeThreeAppointments()
    {
        //Day day1 = new Day(1);
        Appointment appointm1 = new Appointment("Java lecture", 1);
        Appointment appointm2 = new Appointment("Java class", 1);
        Appointment appointm3 = new Appointment("Meet John", 1);
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(true, day1.makeAppointment(13, appointm2));
        assertEquals(true, day1.makeAppointment(17, appointm3));
    }

    /**
     * Check that double-booking is not permitted.
     */
    public void testDoubleBooking()
    {
        //Day day1 = new Day(1);
        Appointment appointm1 = new Appointment("Java lecture", 1);
        Appointment appointm2 = new Appointment("Error", 1);
        assertEquals(true, day1.makeAppointment(9, appointm1));
        assertEquals(false, day1.makeAppointment(9, appointm2));
    }

	public void testFindspace9()
	{
		//Day day1 = new Day(1);
		//Appointment appointm1 = new Appointment("Robin", 1);
		assertEquals(9, day1.findSpace(appointm1));
	}

	public void testFindspace10()
	{
		//Day day1 = new Day(1);
		Appointment appointm1 = new Appointment("Dickhead Ben", 1);
		assertEquals(true, day1.makeAppointment(9, appointm1));
		Appointment appointm2 = new Appointment("Robin", 1);
		assertEquals(10, day1.findSpace(appointm2));
	}

	public void testFindspacefull()
	{
		//Day day1 = new Day(1);
		Appointment appointm1 = new Appointment("Absolute twat", 9);
		assertEquals(true, day1.makeAppointment(9, appointm1));
		Appointment appointm2 = new Appointment("Robin", 1);
		assertEquals(-1, day1.findSpace(appointm2));
	}

	public void testCheckBastard()
	{
		//Day day1 = new Day(1);
		//Appointment appointm1 = new Appointment("Robin", 1);
		assertEquals(true, day1.makeAppointment(10, appointm1));
		Appointment appointm2 = new Appointment("FUCKING BASTARD", 2);
		assertEquals(false, day1.makeAppointment(9, appointm2));
	}
}






