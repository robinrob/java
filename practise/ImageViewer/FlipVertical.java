import java.awt.Color;

public class FlipVertical extends Flip
{

    public FlipVertical(String name)
    {
        super(name);
    }
    
    public void apply(OFImage image)
    {
        //OFImage copy = (OFImage) image.clone();
        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height/2 - 1; y++){
                Color pixel1 = image.getPixel(x, y);
                Color pixel2 = image.getPixel(x, (height-1) - y);
                image.setPixel(x, y, pixel2);
                image.setPixel(x, (height-1) - y, pixel1);
            }
        }
    }
    
}