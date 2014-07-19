
/**
 * This class represents a simple picture. You can draw the picture using
 * the draw method. But wait, there's more: being an electronic picture, it
 * can be changed. You can set it to black-and-white display and back to
 * colors (only after it's been drawn, of course).
 *
 * This class was written as an early example for teaching Java with BlueJ.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.1  (24 May 2001)
 */
public class Face
{
    private Square mouthM;
    private Square mouthL;
    private Square mouthR;
    private Triangle nose;
    private Circle eyeL;
    private Circle eyeR;

    /**
     * Constructor for objects of class Picture
     */
    public Face()
    {
        
        // nothing to do... instance variables are automatically set to null
    }

    /**
     * Draw this picture.
     */
    public void draw()
    {

        eyeL = new Circle();
        eyeL.changeSize(40);
        eyeL.changeColor("yellow");
        eyeL.moveHorizontal(35);
        eyeL.makeVisible();
        
        eyeR = new Circle();
        eyeR.changeSize(40);
        eyeR.changeColor("yellow");
        eyeR.moveHorizontal(135);
        eyeR.makeVisible();
        
        nose = new Triangle();  
        nose.changeSize(50, 60);
        //nose.changeColor("");
        nose.moveHorizontal(78);
        nose.moveVertical(80);
        nose.makeVisible();
        
        mouthM = new Square();
        mouthM.changeColor("red");
        mouthM.moveHorizontal(53);
        mouthM.moveVertical(130);
        mouthM.makeVisible();

        mouthL = new Square();
        mouthL.changeColor("red");
        mouthL.moveHorizontal(23);
        mouthL.moveVertical(130);
        mouthL.makeVisible();
        
        mouthR = new Square();
        mouthR.changeColor("red");
        mouthR.moveHorizontal(83);
        mouthR.moveVertical(130);
        mouthR.makeVisible();        

        
    }


    /**
     * Change this picture to black/white display
     */
    public void setBlackAndWhite()
    {
        if(eyeL != null)   // only if it's painted already...
        {
            eyeL.changeColor("black");
            eyeR.changeColor("black");
            nose.changeColor("black");
            mouthM.changeColor("black");
            mouthL.changeColor("black");
            mouthR.changeColor("black");
        }
    }

    /**
     * Change this picture to use color display
     */
    public void setColor()
    {
        if(eyeL != null)   // only if it's painted already...
        {
            eyeL.changeColor("yellow");
            eyeR.changeColor("yellow");
            nose.changeColor("green");
            mouthM.changeColor("red");
            mouthL.changeColor("red");
            mouthR.changeColor("red");
        }
    }
    
    public void Smile()
    {
        mouthL.slowMoveVertical(-20);
        mouthR.slowMoveVertical(-20);
    }
    
    public void Reset()
    {
        draw();
    }
    
    public void Frown()
    {
        mouthL.slowMoveVertical(20);
        mouthR.slowMoveVertical(20);
}

}
