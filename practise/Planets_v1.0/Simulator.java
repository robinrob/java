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
    
    private ArrayList<Planet> planets;
    /* The dynamic cycle number of the simulation. */
    private int cycleNumber;
    
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
    private Planet centralPlanet;
    
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
        random = new Random();
        bounds = gui.getCanvasSize();
        createPlanets();
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
        planets = new ArrayList<Planet>();
        int m1 = 10000;
        int m2 = 100;
        int m3 = 1;
        double speed = 10;
        double orbit = 250.0;
        double x0 = gui.getCanvasSize().width/2;
        double y0 = gui.getCanvasSize().height/2;
        double speed2 = speed;
        double orbit2 = 50;
        
        centralPlanet = new Planet(m1, x0, y0, 0.0, 0.0, Color.red, 20,
                                   bounds.width, bounds.height);
        planets.add(centralPlanet);
        planets.add(new Planet(m2, x0+orbit, y0, 0.0, speed, Color.BLUE, 5, 
                               bounds.width, bounds.height));
        //planets.add(new Planet(m3, x0+orbit+orbit2, y0, 0.0, speed2, 
        //                       Color.black, 5, bounds.width, bounds.height));
        planets.add(new Planet(m2, x0, y0+orbit, -speed, 0.0, Color.blue, 5, 
                               bounds.width, bounds.height));
        planets.add(new Planet(m2, x0-orbit, y0, 0.0, -speed, Color.blue, 5, 
                               bounds.width, bounds.height));
        planets.add(new Planet(m2, x0, y0-orbit, speed, 0.0, Color.blue, 5, 
                               bounds.width, bounds.height));
        planets.add(new Planet(m2, x0+orbit/Math.sqrt(2), y0-orbit/Math.sqrt(2), 
                               speed/Math.sqrt(2), speed/Math.sqrt(2), 
                               Color.BLUE, 5, bounds.width, bounds.height));
        planets.add(new Planet(m2, x0+orbit/Math.sqrt(2), y0+orbit/Math.sqrt(2), 
                               -speed/Math.sqrt(2), speed/Math.sqrt(2), 
                               Color.BLUE, 5, bounds.width, bounds.height));
        planets.add(new Planet(m2, x0-orbit/Math.sqrt(2), y0+orbit/Math.sqrt(2), 
                               -speed/Math.sqrt(2), -speed/Math.sqrt(2), 
                               Color.BLUE, 5, bounds.width, bounds.height));
        planets.add(new Planet(m2, x0-orbit/Math.sqrt(2), y0-orbit/Math.sqrt(2), 
                               speed/Math.sqrt(2), -speed/Math.sqrt(2), 
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
     * Increases the mass (by a factor of 2) of centralPlanet.
     */
    public void increaseCentralMass() {
        centralPlanet.setMass((int) centralPlanet.getMass()*2);
        if (centralPlanet.getMass() == 0){
            centralPlanet.setMass(1);
        }
        gui.displayCentralMass();
    }
    
    /**
     * Decreases the mass (by a factor of 2) of centralPlanet.
     */
    public void decreaseCentralMass() {
        centralPlanet.setMass((int) centralPlanet.getMass()/2);
        gui.displayCentralMass();
    }
//-----------------------------------80 characters------------------------------    
    /**
     * Increases the mass (by a factor of 2) of all planets other than 
     * centralPlanet.
     */
    public void increasePlanetaryMass()
    {
        for (Planet planet : planets){
            if (!(planet.equals(centralPlanet))){
                planet.setMass((int) planet.getMass()*2);
                if (planet.getMass() == 0){
                    planet.setMass(1);
                }
            }
        }
    }
    
    /**
     * Decreases the mass (by a factor of 2) of all planets other than 
     * centralPlanet.
     */
    public void decreasePlanetaryMass()
    {
        for (Planet planet : planets){
            if (!(planet.equals(centralPlanet))){
                planet.setMass((int) planet.getMass()/2);
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
                planets.add(new Planet(100, centralPlanet.getXPos(), 
                                       centralPlanet.getYPos() 
                                       - random.nextInt(400), 
                                       random.nextInt(20), 0.0, 
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
    private void movePlanets()
    {
        for (Planet planet : planets){
            planet.reCalculate(planets);
            gui.erasePlanet(planet);
        }
        for (Planet planet : planets){
            planet.move();
            gui.drawPlanet(planet);
        }
    }
    
    /**
     * Runs the simulation.
     */
    public void run() {
        for (Planet planet : planets){
            gui.drawPlanet(planet);
        }
        
        gui.intro();
        
        double time = 0;
        while (!finished){
            while(pause && !finished){
                waiting = true;
            }
            waiting = false;
            gui.wait((int) (wait/simSpeed));
            movePlanets();
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
     * Acessor method for centralPlanet.
     */
    public Planet getCentralPlanet() {
        return centralPlanet;
    }
    
    /**
     * Acessor method for collection of planets in the simulation. 
     * @ returns An ArrayList of Planet objects.
     */
    public ArrayList<Planet> getPlanets() {
        return planets;
    }

}
