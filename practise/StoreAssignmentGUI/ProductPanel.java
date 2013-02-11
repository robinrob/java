import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;


public class ProductPanel extends JPanel
{
    

    private StoreGUI storeGUI;
    private Product product;
    private int quantity;
    private int increment;
    private JLabel quantityLabel;
    private JTextField quantityField;
    private Boolean removeOption;
    public static final int HEIGHT = 20;
    public static final int WIDTH = 600;
    private final int TEXTLENGTH = 15;
    
    public ProductPanel(StoreGUI gui, Product product, Boolean removeOption)
    {
        int name_width = WIDTH/5;
        int right_width = WIDTH - name_width;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        storeGUI = gui;
        this.product = product;
        this.removeOption = removeOption;
        
        quantity = storeGUI.getBasket().getQuantity(product.getLabel());
        increment = 0;
        
        int price = product.getPrice();
        String name = product.getLabel();
        
        this.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(name);
        nameLabel.setPreferredSize(new Dimension(name_width, HEIGHT));
        this.add(nameLabel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(right_width, HEIGHT));
        rightPanel.setLayout(new GridLayout(1,0));
        this.add(rightPanel, BorderLayout.CENTER);

        JLabel priceLabel = new JLabel(product.getPriceString());
        quantityLabel = new JLabel("" + quantity);   
        JButton infoButton = new JButton("Info");
        JButton incrementButton = new JButton("+");
        JButton decrementButton = new JButton("-");
        quantityField = new JTextField();
        JButton addButton = new JButton("Add");
        JComponent removePanel;
        if (removeOption){
            removePanel = new JButton("Remove");
        }
        else {
            removePanel = new JPanel();
        }
              
        rightPanel.add(priceLabel);
        rightPanel.add(quantityLabel);
        rightPanel.add(infoButton);
        rightPanel.add(incrementButton);
        rightPanel.add(decrementButton);
        rightPanel.add(quantityField);
        rightPanel.add(addButton);
        rightPanel.add(removePanel);
        
        infoButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { showInfo(); }
                });
        incrementButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { update(1); }
                });
        decrementButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { update(-1); }
                });
        quantityField.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { setQuantity(); }
                });
        addButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { add(); }
                });
        if (removeOption){
            ((JButton) removePanel).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { remove(); }
                });
        }
                        
                          
    }
    
    
    private void showInfo()
    {
        storeGUI.productCloseUp(product);
    }
    
    private String rightJustify(int number, int length)
    {
        Integer intNumber = new Integer(number);
        String numberString = intNumber.toString();
        for ( int i = 0 ; i < length - numberString.length() ; i++ )
            numberString = " " + numberString;
        return numberString;
    }
    
    private void update(int amount)
    {
        increment += amount;
        quantityField.setText(rightJustify(increment, TEXTLENGTH));

    }
    
    private void setQuantity()
    {
        Integer intQuantity = new Integer(quantityField.getText().trim());
        increment = intQuantity.intValue();
        quantityField.setText(rightJustify(increment, TEXTLENGTH));
    }
    
    private void add()
    {
        quantity += increment;
        storeGUI.addToBasket(product, increment);
        storeGUI.statusUpdate("" + increment + " " + product.getLabel() + " added to basket.");
        increment = 0;
        quantityField.setText(rightJustify(increment, TEXTLENGTH));
        quantityLabel.setText("" + quantity);
        if (removeOption)
            storeGUI.basketView();
    }
    
    private void remove()
    {
        storeGUI.removeFromBasket(product);
    }
    

    
        
        
    
}