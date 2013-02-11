import java.awt.*;
import java.awt.geom.*;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Bruce Quig
 * @author Michael Kolling (mik)
 * @author David J. Barnes
 *
 * @version 1.1  (23-Jan-2002)
 */

public class BouncingBall
{
    private int GRAVITY;  // effect of gravity

    private int ballDegradation = 6;
    private Ellipse2D.Double circle;
    private int diameter;
    private Color colour;
    private int xPos;
    private int yPos;
    //private int groundPosition;      // y position of ground
    private CanvasScreen screen;
    private int ySpeed;                // initial downward speed
    private double xSpeed;
    private int ground;
    private DataAnalyser data;
    private boolean stopped = false;
    private double airCoeff = 0.0001;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BouncingBall(int x, int y, double x_speed, int y_speed, int ballDiameter, Color ballColour, 
                                                                        int gravity, CanvasScreen screen, DataAnalyser data_analyser)
    {
        xPos = x;
        yPos = y;
        xSpeed = x_speed;
        ySpeed = y_speed;
        colour = ballColour;
        diameter = ballDiameter;
        GRAVITY = gravity;
        this.screen = screen;
        data = data_analyser;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        screen.canvas().setForegroundColor(colour);
        screen.canvas().fillCircle(xPos, yPos, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        screen.canvas().eraseCircle(xPos, yPos, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
        
        if (yPos > 0){
        ground = screen.value(xPos, yPos);
       // if (yPos == screen.value(xPos, yPos) && ySpeed <= 0){
       //     ySpeed = 0;
       //     stopped = true;
       // }
    }

        // compute new position
        //canvas.setPositionMapValue(xPosition, yPosition, 0);
        

        ySpeed+=GRAVITY; 
        
        if(xPos + xSpeed > screen.width() - 1){
            xPos = (xPos + (int) xSpeed - (screen.width() - 1));
            yPos = screen.platformGround() - (ground - yPos);
        }
        else{
            xPos += xSpeed;
            yPos += ySpeed;
        }
        
        if (!(xSpeed - airCoeff*xSpeed < 0)){
        xSpeed = xSpeed - airCoeff*xSpeed;
    }

        //canvas.setPositionMapValue(xPosition, yPosition, 1);
        if (yPos + diameter > ground){
            yPos = (ground - diameter);
            ySpeed = -ySpeed; 
        }
        

        if (ySpeed == 0 && !stopped){
            data.addStopped();
            stopped = true;  
        }
        if (ySpeed != 0 && stopped){
            data.removeStopped();
            stopped = false;
        }

        // draw again at new position
        //if(yPos < screen.height() - 1){ draw(); }
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPos;
    }
    
    public int getXSpeed()
    {
        return (int) xSpeed;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPos;
    }
    
    public int getYSpeed()
    {
        return ySpeed;
    }
    
    public int getGravity()
    {
        return GRAVITY;
    }
    

    

    

   

}
