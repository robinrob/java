/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kolling.
 * @version 2001.12.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasMoreEntries()) {
            LogEntry entry = reader.nextEntry();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
        
        public int numberOfAccesses()
        {
            int total = 0;
            for (int i=0; i < hourCounts.length; i++){
                total = total + hourCounts[i];
            }
            return total;
        }
        
        public int busiestHour()
        {
            int busiesthour = 0;
            for (int i = 0; i < hourCounts.length; i++){
                if (hourCounts[i] > hourCounts[busiesthour]){
                    busiesthour = i;
                }
                else {
                }
            }
            return busiesthour;
        }
        
        public int quietestHour()
        {
            int quietesthour = 0;
            for (int i = 0; i < hourCounts.length; i++){
                if (hourCounts[i] < hourCounts[quietesthour]){
                    quietesthour = i;
                }
            }
            return quietesthour;
        }
        
        public int busiestTwoHours(){
            int[] twohours = new int[2];
            for (int i = 0; i < hourCounts.length; i=i+2){
                if ((hourCounts[i] + hourCounts[i+1]) > (hourCounts[twohours[0]] + hourCounts[twohours[1]])){
                    twohours[0]=i;
                    twohours[1]=i+1;
                }
            }
            return twohours[0];
        }
}
