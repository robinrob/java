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

public class BallDemo   
{
    private Canvas myCanvas;
    private BouncingBall[] balls;
    private Platform[] platforms;
    private final int screenWidth = 1280;
    private final int screenHeight = 964;
    private final int GROUND = 400;
    private CanvasScreen Screen;
    private Random random_no;
    private DataAnalyser data;
    private int time = 0;
    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", screenWidth, screenHeight, Color.black);
        myCanvas.setVisible(true);
        Screen = new CanvasScreen(screenWidth, screenHeight, GROUND, myCanvas);
        data = new DataAnalyser();
	runStairsBounce(10, 5);
    }
 
    /**
     * This method demonstrates some of the drawing operations that are
     * available on a Canvas object.
     */
 
    public int screenWidth(){
        return screenWidth;
    }
    
    public int screenHeight(){
        return screenHeight;
    }
    
    public int canvasWidth()
    {
        return myCanvas.getSize().width;
    }
    
    
    public int canvasHeight()
    {
        return myCanvas.getSize().height;
    }
    
    
    public void drawFrame()
    {
        int canvasWidth = canvasWidth();
        int canvasHeight = canvasHeight();
        int thickness = 20;
        myCanvas.setForegroundColor(Color.blue);
        Rectangle borderLeft = new Rectangle(0,0,thickness,canvasHeight);
        myCanvas.fill(borderLeft);
        myCanvas.setForegroundColor(Color.yellow);
        Rectangle borderTop = new Rectangle(0,0,canvasWidth,thickness);
        myCanvas.fill(borderTop);
        Rectangle borderRight = new Rectangle((canvasWidth-thickness),0,thickness,canvasHeight);
        myCanvas.setForegroundColor(Color.blue);
        myCanvas.fill(borderRight);
        Rectangle borderBottom = new Rectangle(0,(canvasHeight-thickness),canvasWidth,thickness);
        myCanvas.setForegroundColor(Color.yellow);
        myCanvas.fill(borderBottom);
    }
        

    public void drawPlatforms(Platform platforms[], Color colour)
    {
        for (int index = 0; index < platforms.length; index++){
            platforms[index].drawPlatform(colour);
        }
    }
    
    public void createPlatforms(int number)
    {
        platforms = new Platform[number];
        int length = screenWidth/number;
        int vert_shift = 500/number;
        for (int index = 0; index < platforms.length; index++){
            int xPos = index*length;
            int yPos = GROUND+index*vert_shift;
            platforms[index] = new Platform(xPos,yPos,length);
            platforms[index].placePlatform(Screen);
        }
    }


    
    public void setUpCanvas()
    {
        myCanvas.setVisible(true);
        myCanvas.setForegroundColor(Color.red);
        myCanvas.setForegroundColor(Color.yellow);
        myCanvas.drawString("Yellow ball = last ball", 150, 700);
        myCanvas.drawString("Simulator time:", 150, 750);
        myCanvas.drawString("Stops at t = 500", 150, 770);
        drawFrame();
        //Rectangle fillArea = new Rectangle(20,20,1240,924);
        //myCanvas.setForegroundColor(Color.black);
        //myCanvas.fill(fillArea);
    }
    
    public void refreshTime(){
        time++;
        Rectangle fillArea = new Rectangle(300,740,40,10);
        myCanvas.setForegroundColor(Color.black);
        myCanvas.fill(fillArea);
    }
    
    public void refreshStopped(){
        Rectangle fillArea = new Rectangle(350,790,80,800);
        myCanvas.setForegroundColor(Color.black);
        myCanvas.fill(fillArea);
    }

    
    public void createBalls(int number){
        balls = new BouncingBall[number];
        for (int i = 0; i < number; i++){
            random_no = new Random(i+1);
            
            if (i == number - 1){
                balls[number - 1] = new BouncingBall(100+random_no.nextInt(i+1), 100+random_no.nextInt(50), 2+random_no.nextInt(10), random_no.nextInt(10), 15, Color.yellow, 2+random_no.nextInt(3), Screen, data);
            }
            else {
            balls[i] = new BouncingBall(100+random_no.nextInt(i+1), 100+random_no.nextInt(50), 2+random_no.nextInt(10), random_no.nextInt(10), 5, Color.blue, 2+random_no.nextInt(3), Screen, data);
        }
    }
}
        //100+random_no.nextInt(10)
    
    public void drawBalls(BouncingBall[] balls)
    {
         for (int i = 0; i < balls.length; i++){
         balls[i].draw();
        }
    }
    
    public void eraseBalls()
    {
         for (int i = 0; i < balls.length; i++){
         balls[i].erase();
        }
    }
            
    /**
     * Simulates two bouncing balls
     */
    public void setUpStairsBounce(int n_balls, int n_platforms)
    {
        setUpCanvas();
        createPlatforms(n_platforms);
        drawPlatforms(platforms, Color.cyan);
        createBalls(n_balls);
    }
 
        public void runStairsBounce(int n_balls, int n_platforms)
        {   
        setUpStairsBounce(n_balls, n_platforms);
        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);
            refreshTime();
            //refreshStopped();
            myCanvas.setForegroundColor(Color.yellow);
            myCanvas.drawString("" + time, 300, 750);
            //myCanvas.drawString("" + data.getStopped(), 350, 800);
        for (BouncingBall ball : balls){ 
                ball.move();
            }
            if (time == 500){ finished = true; }
   	 }
            //eraseBalls();
        }

	public static void main(String[] args)
	{
	new BallDemo();
	}

}   