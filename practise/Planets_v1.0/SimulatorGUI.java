/*
 * 
 * SimulatorGUI
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
import java.awt.event.*;


/**
 * The Graphical User Interface of the Planets application.
 * Consists of two main components:
 * 1) The drawing canvas to display the simulation
 * 2) An all-around border of action buttons
 *
 *
 * @author Robin Smith
 * @version     1.0.1 28 Feb 2009
 */
public class SimulatorGUI
{
    private final int FRAME_WIDTH = 1280;
    private final int FRAME_HEIGHT = 994;
    private final int CANVAS_WIDTH = FRAME_WIDTH - 200;
    private final int CANVAS_HEIGHT = FRAME_HEIGHT - 120;
    private Simulator sim;
    private JFrame frame;
    /* Canvas for drawing the simulation on. */
    private Canvas canvas;
    
//-----------------------------------80 characters------------------------------     
    /**
     * Create the GUI.
     * @param simulator The Simulator object running the simulation to be 
     * displayed.
     */
    public SimulatorGUI(Simulator simulator)
    {
        this.sim = simulator;
        this.canvas = new Canvas("Solar System Simulator", CANVAS_WIDTH, 
                                 CANVAS_HEIGHT, Color.white);
        setUpCanvas();
        setUpFrame();
    }

    /**
     * Sets up the canvas by displaying initial information.
     */
    public void setUpCanvas()
    {
        canvas.setVisible(true);
        canvas.setForegroundColor(Color.black);
        displayCycle();
        displaySimSpeed();
        displayCentralMass();
        displayPlanetaryMass();
        //canvas.drawString("Stops at step "+durationFactor, 150, 850);
        //drawFrame(Color.yellow, Color.blue);
    }
    
    /**
     * Draws a two-colour frame around the drawing canvas
     * @param one First colour for the frame
     * @param two Second colour for the frame
     */
    private void drawFrame(Color one, Color two)
    {
        int canvasWidth = canvas.getSize().width;
        int canvasHeight = canvas.getSize().height;
        int thickness = 20;
        canvas.setForegroundColor(one);
        Rectangle borderLeft = new Rectangle(0, 0, thickness, canvasHeight);
        canvas.fill(borderLeft);
        canvas.setForegroundColor(two);
        Rectangle borderTop = new Rectangle(0, 0, canvasWidth, thickness);
        canvas.fill(borderTop);
        Rectangle borderRight = new Rectangle((canvasWidth-thickness), 0, 
                                               thickness, canvasHeight);
        canvas.setForegroundColor(one);
        canvas.fill(borderRight);
        Rectangle borderBottom = new Rectangle(0,(canvasHeight-thickness),
                                               canvasWidth,thickness);
        canvas.setForegroundColor(two);
        canvas.fill(borderBottom);
    }
    
//-----------------------------------80 characters------------------------------    
    /**
     * Creates the GUI's main frame.
     */ 
    private void setUpFrame()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
                
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout(1, 1));
        contentPane.add(canvas.getCanvasPane(), BorderLayout.CENTER);

        frame.setTitle("Solar System Simulator");
        
        JPanel bottomPanel = new JPanel();
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setLayout(new GridLayout(1,0));
        bottomPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 60));
        
        JButton button = new JButton("Run");
        bottomPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                     sim.resume(); 
                                    }
                                });
        
        button = new JButton("Pause");
        bottomPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                     sim.pause(); 
                                    }
                                });        
                    
        button = new JButton("Reset");
        bottomPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                     sim.reset(); 
                                    }
                                });
        
        JPanel rightPanel = new JPanel();
        contentPane.add(rightPanel, BorderLayout.EAST);
        rightPanel.setLayout(new GridLayout(0,1));
        rightPanel.setPreferredSize(new Dimension(100, FRAME_HEIGHT));
        
        button = new JButton("Faster");
        rightPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                   sim.faster(); 
                                }
                            });
                            
        button = new JButton("Slower");
        rightPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                     sim.slower(); }
                                    });
                            
        JPanel leftPanel = new JPanel();
        contentPane.add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new GridLayout(0,1));
        leftPanel.setPreferredSize(new Dimension(100, FRAME_HEIGHT));
        
        button = new JButton("M1 +");
        leftPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                     sim.increaseCentralMass(); 
                                    }
                                });
                            
        button = new JButton("M1 -");
        leftPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                     sim.decreaseCentralMass(); 
                                    }
                                });
                            
        button = new JButton("M2 +");
        leftPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                     sim.increasePlanetaryMass(); 
                                    }
                                });
                            
        button = new JButton("M2 -");
        leftPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) { 
                                     sim.decreasePlanetaryMass(); 
                                    }
                                });
                            
        JPanel topPanel = new JPanel();
        contentPane.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(1,0));
        topPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 60));
        
        button = new JButton("Add a planet!");
        topPanel.add(button);
        button.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     sim.dickhead(); 
                                    }
                                });
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Displays an introduction to the simulation on the canvas.
     */
    public void intro() {
        canvas.setForegroundColor(Color.red);
        canvas.setFont(new Font("helvetica", Font.BOLD, 50));
        canvas.drawString("GET READY!", CANVAS_WIDTH/2 - 150, 80);
        canvas.setFont(new Font(null));
        
        canvas.wait(2000);
        canvas.setForegroundColor(Color.WHITE);
        canvas.fill(new Rectangle(CANVAS_WIDTH/2-200,20,500,100));
    }
    
    /**
     * Displays the current cycle number of the simulation on the canvas.
     */
    public void displayCycle()
    {
        canvas.setForegroundColor(Color.white);
        canvas.fill(new Rectangle(300,690,100,10));
        canvas.setForegroundColor(Color.black);
        canvas.drawString("Simulator time step:", 150, 700);
        canvas.drawString(""+sim.getTimeStep(), 300, 700);
    }
    
    /**
     * Displays the current speed of the simulation on the canvas.
     */
    public void displaySimSpeed()
    {
        canvas.setForegroundColor(Color.white);
        canvas.fill(new Rectangle(300,720,100,10));
        canvas.setForegroundColor(Color.black);
        canvas.drawString("Run speed:", 150, 730);
        canvas.drawString(""+sim.getSimSpeed(), 300, 730);
    }
   
    /**
     * Displays the current mass of the simulator's central planet on the 
     * canvas.
     */
    public void displayCentralMass()
    {
        canvas.setForegroundColor(Color.white);
        canvas.fill(new Rectangle(300,750,100,10));
        canvas.setForegroundColor(Color.black);
        canvas.drawString("Central mass:", 150, 760);
        if (sim.getCentralPlanet() != null){
            canvas.drawString(""+sim.getCentralPlanet().getMass(), 300, 760);
        }
    }
    
    /**
     * Displays the current mass of the simulator's secondary planets on
     * the canvas.
     */
    public void displayPlanetaryMass()
    {
        canvas.setForegroundColor(Color.white);
        canvas.fill(new Rectangle(300,780,100,10));
        canvas.setForegroundColor(Color.black);
        canvas.drawString("Planetary mass:", 150, 790);
        if (sim.getPlanets() != null){
            canvas.drawString(""+sim.getPlanets().get(1).getMass(), 300, 790);
        }
    }
//-----------------------------------80 characters------------------------------  
    /**
     * Draws a planet on the canvas.
     * @param planet Planet to be drawn
     */ 
    public void drawPlanet(Planet planet) {
        canvas.setForegroundColor(planet.getColour());
        canvas.fillCircle((int) planet.getXPos(), (int) planet.getYPos(), 
                          planet.getDiameter());
    }
    /**
     * Erases a planet from the canvas.
     * @paran planet Planet to be erased
     */
    public void erasePlanet(Planet planet) {
        canvas.eraseCircle((int) planet.getXPos(), (int) planet.getYPos(), 
                           planet.getDiameter());
    }
    
    /**
     * Accessor method for the dimensions of the GUI's drawing canvas.
     */
    public Dimension getCanvasSize() {
        return canvas.getSize();
    }
    
    /**
     * Instructs the canvas to wait for a specified amount of time.
     * @param time The amount of time to wait.
     */
    public void wait(int time) {
        canvas.wait(time);
    }
    
    
}
    
    
    
   
