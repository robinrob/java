import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Software extends Product
{

    public final String[] EXTRA_DETAIL_PROMPTS = {"Version: ", "Platform: "};
    private String version;
    private String platform;

    public Software(String name, int price, String description, BufferedImage image, String version, String platform)
    {
        super(name, price, description, image);
        this.version = version;
        this.platform = platform;
        ArrayList<String> details = new ArrayList<String>();
        details.add(version);
        details.add(platform);
        extraDetails = extraDetailsListToArray(details);
    }
    
    public String[] getExtraDetails()
    {
        return extraDetails;
    }
    
}