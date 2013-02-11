import java.awt.Color;

public class InvertFilter extends Filter
{

    public InvertFilter(String name)
    {
        super(name);
    }
    
    public void apply(OFImage image)
    {
        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                Color pixel = image.getPixel(x, y);
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                image.setPixel(x, y, new Color(255-red, 255-green, 255-blue));
            }
        }
    }
    
}