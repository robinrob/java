import java.awt.Color;

public class FlipHorizontal extends Flip
{

    public FlipHorizontal(String name)
    {
        super(name);
    }
    
    public void apply(OFImage image)
    {
        //OFImage copy = (OFImage) image.clone();
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width/2 - 1; x++){
                Color pixel1 = image.getPixel(x, y);
                Color pixel2 = image.getPixel((width-1) - x, y);
                image.setPixel(x, y, pixel2);
                image.setPixel((width-1) - x, y, pixel1);
            }
        }
    }
    
}