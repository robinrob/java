import java.awt.*;
import java.util.Random;

public class Platform
{
    //x-coordinate of left end of platform
    private int xPos;
    //y-coordinate of left end of platform
    private int yPos;
    //length of platform
    private int length;
    //canvas on which the platforms are drawn
    private CanvasScreen myCanvasScreen;
    


    public Platform(int x, int y, int length)
    {
        xPos = x;
        yPos = y;
        this.length = length;
    }
    
    public void drawPlatform(Color colour){
        Color Colour = colour;
        myCanvasScreen.canvas().setForegroundColor(colour);
        myCanvasScreen.canvas().drawLine(xPos,yPos,xPos+length,yPos);
    }
    
    public void placePlatform(CanvasScreen screen){
        myCanvasScreen = screen;
            for (int y = 0; y < myCanvasScreen.canvas().getSize().height; y++){
                for (int x = platformX(); x < platformX() + platformLength(); x++){
                    myCanvasScreen.setValue(x, y, platformY());
                }
            }
        }
    
    public int platformX(){
        return xPos;
    }
    
    public int platformY(){
        return yPos;
    }
    
    public int platformLength(){
        return length;
    }
        
 
}
