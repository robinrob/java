

public class DataAnalyser
{

    private int stopped = 0;


    
    
    public void addStopped(){
        stopped++;
    }
    
    public void removeStopped(){
        stopped--;
    }
    
    public int getStopped(){
        return stopped;
    }
    
}