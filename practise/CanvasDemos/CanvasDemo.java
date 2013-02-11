import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;
import java.lang.Integer;
import java.util.Random;

/**
 * Class BallDemo - provides two short demonstrations showing how to use the 
 * Canvas class. 
 *
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (23-Jan-2002)
 */

public class CanvasDemo 
{
    private Canvas myCanvas;
    private HashMap balls;
    private HashMap x_levels;
    private HashMap y_levels;
    private final int screenWidth = 1280;
    private final int screenHeight = 960;
    private Random random_no;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public CanvasDemo()
    {
        myCanvas = new Canvas("Ball Demo", screenWidth, screenHeight);
        myCanvas.setVisible(true);
    }
    
 
    
      public void drawDemo(){
      drawFrame(0,myCanvas.canvasHeight(),myCanvas.canvasWidth(),0,20);
      drawWord("Robin...",18,1,1000,110,100);
      drawWord("...Smith...",18,2,500,150,200);
      drawLines(5,200);
      drawWord("...stars in...",22,3,500,200,300);
      drawLines(3,300);
      drawLetters("PIMP MY",60,100,290,460);
      drawLetters("TELESCOPE!",60,100,240,560);
      drawScrollingRectangle(250,480,560,1);
    }
    
    public void customDrawDemo(String first_name, String surname, String film){
      drawFrame(0,myCanvas.canvasHeight(),myCanvas.canvasWidth(),0,20);
      drawWord(first_name+"...",18,1,1000,110,100);
      drawWord("..."+surname+"...",18,2,500,150,200);
      drawLines(5,200);
      drawWord("...stars in...",22,3,500,200,300);
      drawLines(3,300);
      String[] wordArray = film.split(" ");
      for (int index = 0; index < wordArray.length; index++){
      drawLetters(wordArray[index],60,100,290,460+index*100);
      //drawScrollingRectangle(250,480+100*index,560,1);
    }
      drawLines(10,200);

    }
    
    
    public void flashWord(Word word, Color colour1, Color colour2, int thickness){
        myCanvas.setForegroundColor(colour1);
        drawFrame(word.xPos()-20,word.yPos()-20,word.xPos()+word.length()+20,word.yPos()+20+word.fontSize(),thickness);
        myCanvas.wait(50);
        myCanvas.setForegroundColor(colour2);
        drawFrame(word.xPos()-20,word.yPos()-20,word.xPos()+word.length()+20,word.yPos()+20+word.fontSize(),thickness);
        myCanvas.wait(50);
        myCanvas.setForegroundColor(Color.white);
        drawFrame(word.xPos()-20,word.yPos()-20,word.xPos()+word.length()+20,word.yPos()+20+word.fontSize(),thickness);
    }
        
    
      

      public void drawScrollingRectangle(int x0, int y0, int length, int Wait){
        myCanvas.setForegroundColor(Color.red);
        // the shape to draw and move
        int xPos = x0;
        int yPos = y0;
        Rectangle rect = new Rectangle(xPos, yPos, 70, 20);

        // move the rectangle across the screen
        for(int i = 0; i < length; i ++) {
            myCanvas.fill(rect);
            myCanvas.wait(Wait);
            myCanvas.erase(rect);
            
            xPos++;
            rect.setLocation(xPos, yPos);
        }
        // at the end of the move, draw once more so that it remains visible
        myCanvas.fill(rect);
        myCanvas.erase(rect);
    }
    
    
    public void drawLines(int number, int wait){
        Random random_no = new Random();
        for (int index = 0; index < number; index++){
        Color colour = new Color(random_no.nextInt(255), random_no.nextInt(255), random_no.nextInt(255));
        myCanvas.setForegroundColor(colour);
        myCanvas.drawLine(random_no.nextInt(screenWidth),random_no.nextInt(screenHeight),random_no.nextInt(screenWidth),random_no.nextInt(screenHeight));
        myCanvas.wait(wait);
    }
    }
        
    
    
    public void drawLetters(String word, int Size, int Wait, int x0, int y0)
    {
        String[] wordArray = word.split("");
        myCanvas.setFont(new Font("helvetica", Font.BOLD, Size));
        
        int colour = 1;
        for (int index = 0; index < wordArray.length; index++){
            if (1 % colour == 0){myCanvas.setForegroundColor(Color.green);}
            else if (2 % colour == 0){myCanvas.setForegroundColor(Color.red);}
            else if (3 % colour == 0){myCanvas.setForegroundColor(Color.blue);}
            else if (4 % colour == 0){myCanvas.setForegroundColor(Color.yellow);}
            else if (5 % colour == 0){myCanvas.setForegroundColor(Color.black);
            colour = 0;}
            colour++;
            myCanvas.drawString(wordArray[index], x0+Size*index, y0);
            drawLines(5,40);
    }
}

    public void drawWord(String word, int Size, int Colour, int Wait, int x0, int y0)
    {
        int colour = Colour;
        myCanvas.setFont(new Font("helvetica", Font.BOLD, Size));
        
            if (colour == 1){myCanvas.setForegroundColor(Color.green);}
            else if (colour == 2){myCanvas.setForegroundColor(Color.red);}
            else if (colour == 3){myCanvas.setForegroundColor(Color.blue);}
            else if (colour == 4){myCanvas.setForegroundColor(Color.yellow);}
            else if (colour == 5){myCanvas.setForegroundColor(Color.black);}

            myCanvas.drawString(word, x0, y0);
            myCanvas.wait(Wait);
    }



            
        
    public int canvasWidth()
    {
        return myCanvas.getSize().width;
    }
    
    
    public int canvasHeight()
    {
        return myCanvas.getSize().height;
    }
    
    
    public void drawFrame(int bottom_left_x, int bottom_left_y, int top_right_x, int top_right_y, int thickness)
    {
        int width = top_right_x - bottom_left_x;
        int height = bottom_left_y - top_right_y;
       // int top_left_x = bottom_left_x;
   
        myCanvas.setForegroundColor(Color.blue);
        Rectangle borderLeft = new Rectangle(bottom_left_x,top_right_y,thickness,height);
        myCanvas.fill(borderLeft);
        myCanvas.setForegroundColor(Color.yellow);
        Rectangle borderTop = new Rectangle(bottom_left_x,top_right_y,width,thickness);
        myCanvas.fill(borderTop);
        myCanvas.setForegroundColor(Color.blue);
        Rectangle borderRight = new Rectangle(top_right_x-thickness,top_right_y,thickness,height);
        myCanvas.fill(borderRight);
        myCanvas.setForegroundColor(Color.yellow);
        Rectangle borderBottom = new Rectangle(bottom_left_x,bottom_left_y-thickness,width,thickness);
        myCanvas.fill(borderBottom);
        
        myCanvas.setForegroundColor(Color.white);
    }
    
    public void removeFrame()
    {
        int canvasWidth = canvasWidth();
        int canvasHeight = canvasHeight();
        int thickness = 20;
        
        myCanvas.setForegroundColor(myCanvas.getForegroundColor());
        Rectangle borderLeft = new Rectangle(0,0,thickness,canvasHeight);
        myCanvas.fill(borderLeft);
        Rectangle borderTop = new Rectangle(0,0,canvasWidth,thickness);
        myCanvas.fill(borderTop);
        Rectangle borderRight = new Rectangle((canvasWidth-thickness),0,thickness,canvasHeight);
        myCanvas.fill(borderRight);
        Rectangle borderBottom = new Rectangle(0,(canvasHeight-thickness),canvasWidth,thickness);
        myCanvas.fill(borderBottom);
    }
    
    
}