

/**
 * The test class CatalogueTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CatalogueTest extends junit.framework.TestCase
{
	private Catalogue catalogu1;

    /**
     * Default constructor for test class CatalogueTest
     */
    public CatalogueTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
		catalogu1 = new Catalogue();
	}

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

	public void testProductExists()
	{
		assertEquals(true, catalogu1.productExists("baked beans"));
	}

	public void testAddProduct()
	{
		assertEquals(true, catalogu1.addProduct("barbecue charcoal", 500));
	}

	public void testGetProductPrice()
	{
		assertEquals(58, catalogu1.getProductPrice("fresh milk"));
	}

}




