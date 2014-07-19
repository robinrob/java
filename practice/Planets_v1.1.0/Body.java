/*
 * 
 * Body
 * 
 * Version 1.0.1
 *
 * 28/02/2009
 * 
 * -----------------------------------------------------------------------------
 * Created by Robin Smith Software Systems, Inc.  You may use this code          
 * however you like, i don't care!
 * ----------------------------------------------------------------------------- 
 */
//-----------------------------------80 characters------------------------------  

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.lang.Math;
import java.util.ArrayList;

/**
 * The Planet class. Contains all data relating to a planet, a method to
 * calculate its net acceleration due to other planets, and a method to 
 * calculate new speed and position.
 *
 *
 * @author Robin Smith
 * @version     1.0.1 28 Feb 2009
 */
public class Body {

    /* Gravitational constant. */
    private static final double G=0.001;  
    private int mass; 
    private double xPos;
    private double yPos;
    private double xSpeed;
    private double ySpeed;
    private Color colour;
    private int diameter;
    /* Limit on x-coordinate. */
    private int xLimit;
    /* Limit on y-coordinate. */
    private int yLimit;
    
//-----------------------------------80 characters------------------------------     
     /** 
      * Create a planet. 
      * @param mass Initial mass
      * @param x0 Initial x-coordinate
      * @param y0 Initial y-coordinate
      * @param vx0 Initial x-velocity
      * @param xy0 Initial y-velocity
      * @param colour Colour the planet will be drawn in
      * @param diameter Diameter the planet will be drawn with
      * @param xLimit Max. allowed x-coordinate
      * @param yLimit Max. allowed y-coordinate
      */
    public Body(int mass, double x0, double y0, double vx0, double vy0, 
                  Color colour, int diameter, int xLimit, int yLimit)
    {
        this.mass = mass;
        this.xPos = x0;
        this.yPos = y0;
        this.xSpeed = vx0;
        this.ySpeed = vy0;
        this.colour = colour;
        this.diameter = diameter;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }
//-----------------------------------80 characters------------------------------     
    /** 
     * Calculate the instantaneous net acceleration on this planet 
     * due to a collection of other planets. 
     */
    public void reCalculate(ArrayList<Body> bodies, double delta_t)
    {
        double accelerationX = 0.0;
        double accelerationY = 0.0;
        
        for (Body body : bodies){
            if (!this.equals(body)){
                double xSep = body.getXPos() - xPos;
                double ySep = body.getYPos() - yPos;
               // if (Math.sqrt((xSep*xSep + ySep*ySep)) < (diameter + planet.getDiameter())){
                    //destroyed = true;
                //}
                double rSep = Math.sqrt(xSep*xSep + ySep*ySep);
                accelerationX += delta_t*(body.getMass()/rSep*rSep)*(xSep/rSep);
                accelerationY += delta_t*(body.getMass()/rSep*rSep)*(ySep/rSep);
                xSpeed += accelerationX*delta_t;
                ySpeed += accelerationY*delta_t;
            }
        }
    }
    
    /** 
     * Calculate the new speed and position. 
     */
    public void move(double delta_t)
    {
        if((xPos + xSpeed*delta_t) > (xLimit - 1)){
            xPos += xSpeed*delta_t - (xLimit - 1);
        }
        else{
            xPos += xSpeed*delta_t;
        }
        
        if((yPos + ySpeed*delta_t) > (yLimit - 1)){
            yPos += ySpeed*delta_t - (yLimit - 1);
        }
        else{
            yPos += ySpeed*delta_t;
        }
    }
    
    /** 
     * Accessor method for x-coordinate of this planet. 
     */
    public double getXPos()
    {
        return xPos;
    }
    
    /** 
     * Accessor method for y-coordinate. 
     */
    public double getYPos()
    {
        return yPos;
    }
    
    /** 
     * Accessor method for the mass. 
     */
    public double getMass()
    {
        return mass;
    }
    
    /** 
     * Accessor method for the diameter.
     */
    public int getDiameter()
    {
        return diameter;
    }
    
    /** 
     * Accessor method for the draw colour. 
     * */
    public Color getColour()
    {
        return colour;
    }
    
    /** 
     * Method to set the mass of the planet. 
     */
    public void setMass(int mass)
    {
        this.mass = mass;
    }
    
}
