
public class Word
{

    private String word;
    private int x0;
    private int y0;
    private int size;
    
    public Word(String Word, int x_pos, int y_pos, int font_size)
    {
        word = Word;
        x0 = x_pos;
        y0 = y_pos;
        size = font_size;
    }
    
    public int xPos()
    {
        return x0;
    }
    
    public int yPos()
    {
        return y0;
    }
    
    public int fontSize()
    {
        return size;
    }
    
    public int length()
    {
        return word.length();
    }
}
        
        