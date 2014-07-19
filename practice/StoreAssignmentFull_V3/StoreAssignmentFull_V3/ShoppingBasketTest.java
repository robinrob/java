

/**
 * The test class ShoppingBasketTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ShoppingBasketTest extends junit.framework.TestCase
{
    private ShoppingBasket shopping1;

    
    

    

    /**
     * Default constructor for test class ShoppingBasketTest
     */
    public ShoppingBasketTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        shopping1 = new ShoppingBasket();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    public void testAddItem()
    {
        assertEquals(true, shopping1.addItem("baked beans", 30, 2));
    }
    
    public void testItemExists()
    {
        testAddItem();
        assertEquals(true, shopping1.itemExists("baked beans"));
    }
       
    public void testAddItemAgain()
    {
        testAddItem();
        assertEquals(false, shopping1.addItem("baked beans", 40, 1));
    }
    
    public void testDeleteItem()
    {
        testAddItem();
        assertEquals(true, shopping1.deleteItem("baked beans"));
    }
    
    
}

