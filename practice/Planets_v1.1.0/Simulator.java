/*
 * 
 * Simulator
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
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.Random;

/**
 * This is the main class of the "Planets" application.
 * Planets is a fun and educational solar system simulator currently under
 * development.  This is the first working release.
 *
 *
 * @author Robin Smith
 * @version     1.0.1 28 Feb 2009
 */

public class Simulator {

    /* The GUI of the Planets application. */
    private SimulatorGUI gui;
    
    /* The planets in the simulation. */
    
    private ArrayList<Body> bodies;
    /* The dynamic cycle number of the simulation. */
    private int cycleNumber;
    
    private final double DELTA_T = 0.01;
    
    /* The number of milliseconds between cycles of the simulation 
     * at normal run speed (simSpeed = 1). */
    private int wait = 20;
    
    /* Speed of the simulation. */
    private double simSpeed;
    
    /* Controls execution of run() method. */
    private boolean finished;
  
    /* Indicates whether or not simulation will be paused at end of current 
     * cycle. */
    private boolean pause;
    
    /* Indicates wheter or not simulation is paused. */
    private boolean waiting;
    
    /* Central massive planet. */
    private Planet centralMass;
    
    /* Tool for generating random numbers. */
    private Random random;
    
    /* Spatial bounds (max x and y co-ordinates) on planets' movement. */
    private Dimension bounds;

    /**
     * Create the simulator and initialise: the GUI, planets, an instance of 
     * Random and a load of simulation parameters to default values.
     */
    public Simulator()
    {
        gui = new SimulatorGUI(this);
        bounds = gui.getCanvasSize();
        createPlanets();
        random = new Random();
        cycleNumber = 0;
        simSpeed = 1;
        pause = false;
        waiting = false;
    }
    
    /**
     * The main method.  Creates the simulator and runs it.
     */
    public static void main(String[] args)
    {
        Simulator simulator = new Simulator();
        simulator.run();
    }

    /**
     * Method to create the planets for the starting setup of the simulation.
     */
    private void createPlanets()
    {
        bodies = new ArrayList<Body>();
        int m1 = 10000;
        int m2 = 100;
        int m3 = 1;
        double speed1 = 1000.0;
        double orbit1 = 250.0;
        double x0 = (gui.getCanvasSize().width/2);
        double y0 = (gui.getCanvasSize().height/2);
        double speed2 = 10000.0;
        double orbit2 = 50.0;
        
        centralMass = new Planet(m1, x0, y0, 0.0, 0.0, Color.red, 20,
                                   bounds.width, bounds.height);
        bodies.add(centralMass);
        bodies.add(new Planet(m2, x0+orbit1, y0, 0.0, speed1, Color.BLUE, 5, 
                               bounds.width, bounds.height));
        //bodies.add(new Planet(m3, x0+orbit1+orbit2, y0, 0.0, speed2, 
        //                       Color.black, 5, bounds.width, bounds.height));
        bodies.add(new Planet(m2, x0, y0+orbit1, -speed1, 0.0, Color.blue, 5, 
                               bounds.width, bounds.height));
        bodies.add(new Planet(m2, x0-orbit1, y0, 0.0, -speed1, Color.blue, 5, 
                               bounds.width, bounds.height));
        bodies.add(new Planet(m2, x0, y0-orbit1, speed1, 0.0, Color.blue, 5, 
                               bounds.width, bounds.height));
        bodies.add(new Planet(m2, x0+orbit1/Math.sqrt(2), y0-orbit1/Math.sqrt(2), 
                               speed1/Math.sqrt(2), speed1/Math.sqrt(2), 
                               Color.BLUE, 5, bounds.width, bounds.height));
        bodies.add(new Planet(m2, x0+orbit1/Math.sqrt(2), y0+orbit1/Math.sqrt(2), 
                               -speed1/Math.sqrt(2), speed1/Math.sqrt(2), 
                               Color.BLUE, 5, bounds.width, bounds.height));
        bodies.add(new Planet(m2, x0-orbit1/Math.sqrt(2), y0+orbit1/Math.sqrt(2), 
                               -speed1/Math.sqrt(2), -speed1/Math.sqrt(2), 
                               Color.BLUE, 5, bounds.width, bounds.height));
        bodies.add(new Planet(m2, x0-orbit1/Math.sqrt(2), y0-orbit1/Math.sqrt(2), 
                               speed1/Math.sqrt(2), -speed1/Math.sqrt(2), 
                               Color.BLUE, 5, bounds.width, bounds.height));
    }
 
                
    /**
     * Pauses the simulaton.
     */
    public void pause() {
        pause = true;
    }
    
    /**
     * Resumes the simulation from a pause.
     */
    public void resume() {
        pause = false;
    }
    
    /**
     * Resets the simulation to starting conditions.
     */
    public void reset() {
            pause();
            gui.setUpCanvas();
            createPlanets();
            cycleNumber = 0;
            simSpeed = 1;
            resume();
    }
//-----------------------------------80 characters------------------------------    
    /**
     * Makes the simulation run faster.
     */
    public void faster() {
        simSpeed=simSpeed*2;
        gui.displaySimSpeed();
    }
    
    /**
     * Makes the simulation run slower
     */
    public void slower(){
        if (simSpeed*wait > 1){
            simSpeed=simSpeed/2;
            gui.displaySimSpeed();
        }
    }  
    
    /**
     * Increases the mass (by a factor of 2) of centralMass.
     */
    public void increaseCentralMass() {
        centralMass.setMass((int) centralMass.getMass()*2);
        if (centralMass.getMass() == 0){
            centralMass.setMass(1);
        }
        gui.displayCentralMass();
    }
    
    /**
     * Decreases the mass (by a factor of 2) of centralMass.
     */
    public void decreaseCentralMass() {
        centralMass.setMass((int) centralMass.getMass()/2);
        gui.displayCentralMass();
    }
//-----------------------------------80 characters------------------------------    
    /**
     * Increases the mass (by a factor of 2) of all planets other than 
     * centralMass.
     */
    public void increasePlanetaryMass()
    {
        for (Body body : bodies){
            if (!(body.equals(centralMass))){
                body.setMass((int) body.getMass()*2);
                if (body.getMass() == 0){
                    body.setMass(1);
                }
            }
        }
    }
    
    /**
     * Decreases the mass (by a factor of 2) of all planets other than 
     * centralMass.
     */
    public void decreasePlanetaryMass()
    {
        for (Body body : bodies){
            if (!(body.equals(centralMass))){
                body.setMass((int) body.getMass()/2);
            }
        }
    }
//-----------------------------------80 characters------------------------------    
    /**
     * Introduces a rogue planet to make things more interesting.
     */
    public void dickhead()
    {
        pause();
        while (pause){
            if (waiting){
                bodies.add(new Planet(100, centralMass.getXPos(), 
                                       centralMass.getYPos() 
                                       - random.nextInt(300), 
                                       random.nextInt(1000), 0.0, 
                                       Color.black, 5, bounds.width, 
                                       bounds.height));
                resume();
            }
        }
    }
    
    /**
     * Called by method run() on every cycle of the simulation.
     * First the planets' accelerations and speeds are re-calculated.
     * Then their new positions are calculated and displayed.
     */
    private void moveBodies()
    {
        for (int i = 0; i < 10; i++){
            for (Body body : bodies){
                body.reCalculate(bodies, DELTA_T);
            }
        }
        for (Body body : bodies){
            gui.eraseBody(body);
            body.move(DELTA_T);
            gui.drawBody(body);
        }
    }
    
    /**
     * Runs the simulation.
     */
    public void run() {
        for (Body body : bodies){
            gui.drawBody(body);
        }
        
        gui.intro();
        
        double time = 0;
        while (!finished){
            while(pause && !finished){
                waiting = true;
            }
            waiting = false;
            gui.wait((int) (wait/simSpeed));
            moveBodies();
            cycleNumber++;
            gui.displayCycle();
            gui.displaySimSpeed();
            gui.displayCentralMass();
            gui.displayPlanetaryMass();
        }
    }
    
    /**
     * Accessor method for current cycle number (timeStep) of simulation.
     */
    public int getTimeStep() {
        return cycleNumber;
    } 
    
    /**
     * Accessor method for current simulation running speed.
     */
    public double getSimSpeed() {
        return simSpeed;
    }
    
    /**
     * Acessor method for centralMass.
     */
    public Planet getCentralMass() {
        return centralMass;
    }
    
    /**
     * Acessor method for collection of planets in the simulation. 
     * @ returns An ArrayList of Planet objects.
     */
    public ArrayList<Body> getBodies() {
        return bodies;
    }
    
    public ArrayList<Planet> getPlanets() {
        ArrayList<Planet> planets = new ArrayList<Planet>();
        if (bodies != null){
            for (Body body : bodies) {
                if (body instanceof Planet) {
                    planets.add((Planet) body);
                }
            }
        }
        return planets;
    }

}
