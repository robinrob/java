

public class LightFilter extends Filter
{

    public LightFilter(String name)
    {
        super(name);
    }
    
    
    public void apply(OFImage image)
    {
        for (int y=0; y < image.getHeight(); y++){
            for (int x=0; x < image.getWidth(); x++){
                image.setPixel(x, y, image.getPixel(x, y).brighter());
            }
        }
    }
    
}
        
