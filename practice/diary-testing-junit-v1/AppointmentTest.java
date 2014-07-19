

/**
 * The test class AppointmentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AppointmentTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class AppointmentTest
     */
    public AppointmentTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

	public void testHello()
	{
	}

	public void testMakeappointment()
	{
		Appointment appointm1 = new Appointment("Robin", 1);
		appointm1.getDescription();
		appointm1.getDuration();
	}
}


