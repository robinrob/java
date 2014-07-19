import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;
//import javax.swing.border.*;
import java.util.ArrayList;



/**
 * ImageViewer is the main class of the image viewer application. It builds
 * and displays the application GUI and initialises all other components.
 * 
 * To start the application, create an object of this class.
 * 
 * @author Michael Kolling and David J Barnes 
 * @version 0.1
 */
public class ImageViewer
{
    private static final String VERSION = "Version 1.0";
    private JFrame frame;
    private final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    private ImagePanel imagePanel;
    private OFImage currentImage;
    private OFImage cloneImage;
    private Stack<OFImage> imageStack;
    private JLabel statusLabel;
    private String statusText;
    private ArrayList<Manipulation> manipulations;

        
    /**
     * Main function for ImageViewer
     */    
	public static void main(String[] args) {
		
		ImageViewer viewer = new ImageViewer();
	}
	
    /**
     * Create an ImageViewer show it on screen.
     */ 
    public ImageViewer()
    {
        manipulations = createManipulations();
        statusText = "Version 1.0";
        statusLabel = new JLabel(statusText);
        makeFrame();
        imageStack = new Stack<OFImage>();
        currentImage = null;
    }
    
    
    // ---- swing stuff to build the frame and all its components ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("ImageViewer");        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        

        JLabel fileNameLabel = new JLabel();
        contentPane.add(fileNameLabel, BorderLayout.NORTH);
        
        imagePanel = new ImagePanel();
        contentPane.add(imagePanel, BorderLayout.CENTER);
        
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        
        makeMenuBar(frame);
             
        //createLabels(contentPane);

        frame.pack();
        frame.setVisible(true);
    }
    
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menuBar = new JMenuBar();
        
        makeFileMenu(menuBar);
        makeEditMenu(menuBar);
        makeImageMenu(menuBar);
        makeHelpMenu(menuBar);
          
        frame.setJMenuBar(menuBar);
    }
    
    private void makeFileMenu(JMenuBar bar)
    {  
        JMenu fileMenu = new JMenu("File");
        
        JMenu open = new JMenu("Open...");
        fileMenu.add(open);
        
        JMenuItem openFileItem = new JMenuItem("Local file");
        openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
        openFileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { openFile(); }
        });
        open.add(openFileItem);
        

        
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK));
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { saveFile(); }
        });
        fileMenu.add(saveItem);
        
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { quit(); }
        });
        fileMenu.add(quitItem);
        
        
        bar.add(fileMenu);
    }    
    
    private void makeEditMenu(JMenuBar bar)
    {
        JMenu editMenu = new JMenu("Edit");
        bar.add(editMenu);
        
        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, SHORTCUT_MASK));
        undoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { undo(); }
        });
        editMenu.add(undoItem);
    }
    
    private void makeImageMenu(JMenuBar bar)
    {
        JMenu imageMenu = new JMenu("Image");
        bar.add(imageMenu);
        
        JMenu flipMenu = new JMenu("Flip");
        imageMenu.add(flipMenu);
        
        JMenu filterMenu = new JMenu("Filter");
        imageMenu.add(filterMenu);
        
        JMenu colourFiltersMenu = new JMenu("Colour filter");
        filterMenu.add(colourFiltersMenu); 
        
        for (final Manipulation manipulation : manipulations){
            JMenuItem manipulationItem = new JMenuItem(manipulation.getName());
            manipulationItem.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) { applyManipulation(manipulation); }
            });
            
            if (manipulation instanceof ColourFilter)
                colourFiltersMenu.add(manipulationItem);
            else if (manipulation instanceof Filter)
                filterMenu.add(manipulationItem);
            else if (manipulation instanceof Flip)
                flipMenu.add(manipulationItem);
            else 
                imageMenu.add(manipulationItem);
            }
    }
        
    private void makeHelpMenu(JMenuBar bar)
    {
        JMenu helpMenu = new JMenu("Help");
        bar.add(helpMenu);
        
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { about(); }
        });
        helpMenu.add(aboutItem);
    }
        
    
    private Container createLabels(Container container)
    {
       // JLabel label = new JLabel("I am a label. I can display some text.");
       // container.add(label);
         
       // JButton button = new JButton("Button");
        //button.addActionListener(new ActionListener() {
        //    public void actionPerformed(ActionEvent e) { button(); }
        //});
        //container.add(button);
        
        
        return container;
    }
    
    
    private ArrayList<Manipulation> createManipulations()
    {
        ArrayList<Manipulation> manipulationList = new ArrayList<Manipulation>();
        manipulationList.addAll(createFilters());
        manipulationList.addAll(createFlips());
        manipulationList.addAll(createOthers());
        return manipulationList;
    }
    
    private ArrayList<Manipulation> createFilters()
    {
        ArrayList<Manipulation> filterList = new ArrayList<Manipulation>();
        filterList.add(new DarkFilter("Dark filter"));
        filterList.add(new LightFilter("Light filter"));
        filterList.add(new ThresholdFilter("Threshold filter"));
        filterList.add(new ColourFilter("Red filter", Color.RED));
        filterList.add(new ColourFilter("Blue filter", Color.GREEN));
        filterList.add(new ColourFilter("Green filter", Color.BLUE));
        filterList.add(new GreyScaleFilter("Grey scale filter"));
        filterList.add(new InvertFilter("Invert filter"));
        return filterList;
    }
    private ArrayList<Manipulation> createFlips()
    {
        ArrayList<Manipulation> flipList = new ArrayList<Manipulation>();
        flipList.add(new FlipHorizontal("Flip horizontal"));
        flipList.add(new FlipVertical("Flip vertical"));
        return flipList;
    }
    private ArrayList<Manipulation> createOthers()
    {
        ArrayList<Manipulation> othersList = new ArrayList<Manipulation>();
        othersList.add(new Smooth("Smooth"));
        return othersList;
    }
        
        
    
        
    private void openFile()
    {
        currentImage = ImageFileManager.getImage();
        imageStack.push((OFImage) currentImage.clone());
        imagePanel.setImage(currentImage);
        System.out.println("Opening file...");
        frame.pack();
    }
    private void saveFile()
    {
        System.out.println("Saving file...");
    }
    private void quit()
    {
        System.out.println("Quitting...");
        System.exit(0);
    } 
    private void about()
    {
        JOptionPane.showMessageDialog(frame, "ImageViewer\n"+VERSION, "About", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("About function...");
    }
    private void button()
    {
        System.out.println("Button pressed");
    }
    private void applyManipulation(Manipulation manipulation)
    {
        String statusText = "Applying " + manipulation.getName() + " ... ";
        showStatus(statusText);
        if (currentImage != null) {
            manipulation.apply(currentImage);
            frame.repaint();
            statusText += "done.";
        }
        else
            statusText = "No image loaded.";
            
            showStatus(statusText);
    }   

    
    private void undo()
    {
        showStatus("Applying undo ... ");
        try{
            OFImage imageCopy = imageStack.pop();
            currentImage = imageCopy;
            imagePanel.setImage(currentImage);
            frame.repaint();
            System.out.println("worked");
        }
        catch (Exception e) {
            statusText += "didn't work.";
            showStatus(statusText);
            System.out.println("Didn't work");
        }
    }  
        
    private void showStatus(String status)
    {
        statusLabel.setText(status);
    }
        
    
        
}
