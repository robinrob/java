import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;



/**
 * This class creates an input form to request information from the user.  It is used in
 * various situations, such as creating an address, creating a user account,
 * searching for products.
 * 
 * @author Robin Smith
 * @version 2009.01.18
 */

public abstract class InputFormPanel extends JPanel
{
    
    private static Boolean ALL_REQUIRED = true;
    private final int FIELD_HEIGHT = 20;
    private final int BUTTON_HEIGHT = 20;
    public static final int WIDTH = 300;
    private static final int TEXTLENGTH = 15;
    protected final StoreGUI storeGUI;
    protected final String title;
    protected String[] prompts;
    protected int number;
    private String[] details;
    protected String[] previousDetails;
    private JTextField[] fields;



    protected InputFormPanel(StoreGUI gui, String title, Boolean allRequired)
    {
        storeGUI = gui;
        this.title = title;
        ALL_REQUIRED = allRequired;
    }
    
    protected InputFormPanel(StoreGUI gui, String title, Boolean allRequired, String[] prevDetails)
    {
        storeGUI = gui;
        this.title = title;
        ALL_REQUIRED = allRequired;
        previousDetails = prevDetails;
    }
    
    protected JPanel createForm()
    {
        storeGUI.statusUpdate(title);

        details = new String[number];
        fields = new JTextField[number];

        this.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,1));
        this.add(formPanel, BorderLayout.NORTH);
        
        JPanel blankPanel = new JPanel();
        this.add(blankPanel, BorderLayout.SOUTH);
        
        JButton submitButton = new JButton("Submit details");
        submitButton.setPreferredSize(new Dimension(WIDTH, BUTTON_HEIGHT));
        submitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { submitDetails(); }
                });
        
        for (int i = 0; i < number; i++){
            final int index = i;
            JPanel inputLine = new JPanel();
            inputLine.setPreferredSize(new Dimension(WIDTH, FIELD_HEIGHT));
            inputLine.setLayout(new BorderLayout());
            
            JLabel label = new JLabel(Formatter.leftJustify(prompts[i], TEXTLENGTH));
            label.setFont(new Font("Courier", Font.PLAIN, 12));
            
            inputLine.add(label, BorderLayout.WEST);
            
            fields[i] = new JTextField(30);
            fields[i].addActionListener(new ActionListener() {
                     final int n = index;
                            public void actionPerformed(ActionEvent e) { 
                                }
                        });
                        
            inputLine.add(fields[i], BorderLayout.CENTER);
            formPanel.add(inputLine);    
        }
        formPanel.add(submitButton);
        
        if (previousDetails != null){
            setFields(previousDetails);
        }
        
        return this;
    }
    

    protected String[] getDetails()
    {
        for (int i = 0; i < number; i++){
             details[i] = fields[i].getText();
        }
        if (ALL_REQUIRED){
            for (int i = 0; i < number; i++){
                if (details[i].equals("")){
                    storeGUI.statusUpdate("All fields required.");
                    return null;
                }
            }
        }
        return details;
    }
    
    protected void setFields(String[] previousDetails)
    {
        for (int i = 0; i < number; i++)
        {
            fields[i].setText(previousDetails[i]);
        }
    }
    
    protected abstract void submitDetails();
    

}