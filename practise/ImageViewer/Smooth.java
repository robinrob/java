import java.awt.Color;

public class Smooth extends Manipulation
{

    public Smooth(String name)
    {
        super(name);
    }
    
    public void apply(OFImage image)
    {
        OFImage copy = new OFImage(image);
        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 1; x < width - 1; x++){
            for (int y = 1; y < height - 1; y++){
                int totalR = 0, totalG = 0, totalB = 0;
                for (int row = -1; row < 1; row++){
                    for (int col = -1; col < 1; col ++){
                        if (row == 0 && col == 0){
                        }
                        else {
                            Color pixel = copy.getPixel(x + col, y + row); 
                            int r = pixel.getRed();
                            int g = pixel.getGreen();
                            int b = pixel.getBlue();
                            totalR += r;
                            totalG += g;
                            totalB += b;
                        }
                    }
                }
                image.setPixel(x, y, new Color(totalR/8, totalG/8, totalB/8));
            }
        }
    }
    
    
}

                
                

