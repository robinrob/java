import java.util.ArrayList;
import java.awt.image.BufferedImage;




public class Binoculars extends Product
{
    public final String[] EXTRA_DETAIL_PROMPTS = {"Aperture: ", "Magnification: ", "Field of view: "};
    private int aperture;
    private int magnification;
    private String fov;

    public Binoculars(String label, int price, String description, BufferedImage image, int aperture, int mag, String fov)
    {
        super(label,price, description, image);
        this.aperture = aperture;
        magnification = mag;
        this.fov = fov;
        ArrayList<String> details = new ArrayList<String>();
        details.add("" + aperture);
        details.add("" + mag);
        details.add(fov);
        extraDetails = extraDetailsListToArray(details);

    }
    
    public String[] getExtraDetails()
    {
        return extraDetails;
    }
    
}
        