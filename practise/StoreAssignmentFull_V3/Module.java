


public abstract class Module
{

    protected Store store;
    protected String prompt;
    
    public Module(Store store)
    {
        this.store = store;
        prompt = "";
    }
    
    protected void quit()
    {
        store.quit();
    }
    
}
    
    