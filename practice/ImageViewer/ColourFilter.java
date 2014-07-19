import java.awt.Color;

public class ColourFilter extends Filter
{

    private final Color colour;
    private final int filterRed, filterGreen, filterBlue;
    
    public ColourFilter(String name, Color color)
    {
        super(name);
        colour = color;
        filterRed = colour.getRed();
        filterGreen = colour.getGreen();
        filterBlue = colour.getBlue();
    }
    
    public Color getColour()
    {
        return colour;
    }
    
    public void apply(OFImage image)
    {
        int total, R, G, B;
        total = filterRed + filterGreen + filterBlue;
        R = filterRed/(total);
        G = filterGreen/(total);
        B = filterBlue/(total);
        for (int y=0; y < image.getHeight(); y++){
            for (int x=0; x < image.getWidth(); x++){
                Color pixel = image.getPixel(x, y);
                int red, green, blue, average;
                red = pixel.getRed();
                green = pixel.getGreen();
                blue = pixel.getBlue();
                average = (red + green + blue)/3;
                image.setPixel(x, y, new Color(average*R,average*B,average*G));
            }
        }
    }
        
}
        
