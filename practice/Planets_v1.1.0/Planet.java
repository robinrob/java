import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.lang.Math;
import java.util.ArrayList;


public class Planet extends Body {

    public Planet(int mass, double x0, double y0, double vx0, double vy0, 
                  Color colour, int diameter, int xLimit, int yLimit)
    {
        super(mass, x0, y0, vx0, vy0, colour, diameter, xLimit, yLimit);
    }
    
}