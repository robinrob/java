import java.awt.Color;

public class GreyScaleFilter extends Filter
{


    public GreyScaleFilter(String name)
    {
        super(name);
    }

    public void apply(OFImage image)
    {
       for(int y = 0; y < image.getHeight(); y++) {
           for(int x = 0; x < image.getWidth(); x++) {
               Color pixel = image.getPixel(x, y);
               int brightness = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                image.setPixel(x, y, new Color(brightness, brightness, brightness));
            }
        }
    }
    
}
    
        

