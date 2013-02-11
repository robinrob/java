

/**
 * The test class AddressBookDemoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AddressBookDemoTest extends junit.framework.TestCase
{
	private AddressBookDemo addressB1;

    /**
     * Default constructor for test class AddressBookDemoTest
     */
    public AddressBookDemoTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
		addressB1 = new AddressBookDemo();
	}

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

	public void testAdd()
	{
		addressB1.testAddition();
	}
}

