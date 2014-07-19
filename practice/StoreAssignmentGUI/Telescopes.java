import java.util.ArrayList;
import java.awt.image.BufferedImage;


public class Telescopes extends Product
{
    public static final String[] EXTRA_DETAIL_PROMPTS = {"Focal length: ", "Aperture: ", "Focal ratio: "};
    private int focalLength;
    private int aperture;
    private int focalRatio;

    public Telescopes(String label, int price, String description, BufferedImage image, int focalLength, int aperture)
    {
        super(label,price, description, image);
        this.focalLength = focalLength;
        this.aperture = aperture;
        focalRatio = (int) focalLength/aperture;
        ArrayList<String> details = new ArrayList<String>();
        details.add("" + focalLength);
        details.add("" + aperture);
        details.add("" + focalRatio);
        extraDetails = extraDetailsListToArray(details);
        
    }
    

    public String[] getExtraDetails()
    {
        return extraDetails;
    }
    

    
}
        