import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class ProductDisplayPanel extends JPanel
{

    private final StoreGUI storeGUI;
    private final ArrayList<Product> products;
    private final Boolean removeOption;
    
    public ProductDisplayPanel(StoreGUI gui, ArrayList<Product> productsToDisplay, Boolean removeOption)
    {        
        storeGUI = gui;
        products = productsToDisplay;
        this.removeOption = removeOption;
        createForm();
    }
        
        
    public JPanel createForm()
    {

        this.setLayout(new BorderLayout());
        JPanel insetPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(insetPanel);
        this.add(scrollPane, BorderLayout.CENTER);  
        
        insetPanel.setLayout(new BorderLayout());
        
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0,1));
        insetPanel.add(listPanel, BorderLayout.NORTH);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1,0));
        this.add(headerPanel, BorderLayout.NORTH);
        String[] headings = {"Product:", "              Price:", "           Amount:", 
            "          Info:", "        Increase:", "      Decrease:", "    Enter:", "  Add:", "Remove:"};
        for (String heading : headings){
           headerPanel.add(new JLabel(heading));
        }
        
        int number = 0;
        for (Product product : products){
            ProductPanel productPanel = new ProductPanel(storeGUI, product, removeOption);
            listPanel.add(productPanel);
            number++;
            }
            
        if (removeOption){
            JLabel totalLabel = new JLabel("Total: " + Formatter.toMoneyString(storeGUI.getBasket().getTotal()));
            totalLabel.setPreferredSize(new Dimension(ProductPanel.WIDTH, ProductPanel.HEIGHT));  
            listPanel.add(totalLabel);
        }
        
        return this;
    }
    
    
}