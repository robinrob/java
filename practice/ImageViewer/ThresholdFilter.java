import java.awt.Color;

public class ThresholdFilter extends Filter
{

    public ThresholdFilter(String name)
    {
        super(name);
    }
    
    
    public void apply(OFImage image)
    {
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                Color pixel = image.getPixel(x, y);
                int brightness = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                if(brightness <= 85) {
                    image.setPixel(x, y, Color.BLACK);
                }
                else if(brightness <= 170) {
                    image.setPixel(x, y, Color.GRAY);
                }
                else {
                    image.setPixel(x, y, Color.WHITE);
                }
            }
        }

    }
    
}
        
