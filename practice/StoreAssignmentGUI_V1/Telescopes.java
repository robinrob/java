import java.util.ArrayList;
import java.awt.image.BufferedImage;

/**
 * This class represents a Telescope, a type of Product.
 * 
 * @author Robin Smith
 * @version 2009.01.18
 */

public class Telescopes extends Product
{
    public static final String[] EXTRA_DETAIL_PROMPTS = {"Focal length: ", "Aperture: ", "Focal ratio: "};
    private int focalLength;
    private int aperture;
    private int focalRatio;

    public Telescopes(String label, int price, String description, BufferedImage image, int aperture, int focalLength)
    {
        super(label,price, description, image);
        this.aperture = aperture;
        this.focalLength = focalLength;
        focalRatio = (int) focalLength/aperture;
        ArrayList<String> details = new ArrayList<String>();
        details.add("" + aperture + "mm");
        details.add("" + focalLength + "mm");
        details.add("" + focalRatio);
        extraDetails = extraDetailsListToArray(details);
        
    }
    

    public String[] getExtraDetails()
    {
        return extraDetails;
    }
    

    
}
        