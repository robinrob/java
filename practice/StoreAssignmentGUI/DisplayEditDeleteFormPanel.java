import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class DisplayEditDeleteFormPanel extends JPanel
{

    public static final int WIDTH = 300;
    private final int LINE_HEIGHT = 20;
    private final int BUTTON_HEIGHT = 20;
    private final StoreGUI storeGUI;
    private final String formType;
    private final String[] details;
    private String option;
    
    public DisplayEditDeleteFormPanel(StoreGUI gui, String type, String[] details, String option)
    {
        storeGUI = gui;
        formType = type;
        this.details = details;
        this.option = option;
        createForm();
    }
            
    public JPanel createForm()
    {
        this.setLayout(new GridLayout(0,1));
        
        String[] prompts = null;
        if (formType.equals("User")){
            prompts = User.DETAIL_PROMPTS;
        }
        else if (formType.equals("CardDetails")){
            prompts = PaymentDetails.DETAIL_PROMPTS;
        }
        else if (formType.equals("Address")){
            prompts = Address.DETAIL_PROMPTS;
        }
        else if (formType.equals("Order")){
            prompts = Order.DETAIL_PROMPTS;
        }
        
        final int number = prompts.length;
        
        JButton button = new JButton(option);
        if (option.equals("Delivery") && formType.equals("Address"))
            button.setText("Set to delivery address");
        if (option.equals("Billing") && formType.equals("Address"))
            button.setText("Set to billing address");
        
        button.setPreferredSize(new Dimension(WIDTH, BUTTON_HEIGHT));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                if (option.equals("Edit"))
                    editDetails();
                else if (option.equals("Delete"))
                    deleteDetails();
                else if (option.equals("Delivery") || option.equals("Billing"))
                    addressType();
            }
        });
        
        
        for (int i = 0; i < number; i++){ 
            JPanel line = new JPanel();
            line.setLayout(new BorderLayout());
            line.setPreferredSize(new Dimension(WIDTH, LINE_HEIGHT));

            JLabel promptLabel = new JLabel(Formatter.leftJustify(prompts[i], 20));
            promptLabel.setFont(new Font("Courier", Font.PLAIN, 12));
            line.add(promptLabel, BorderLayout.WEST);
            
            JLabel detailLabel = new JLabel(Formatter.leftJustify(details[i], 20));
            detailLabel.setFont(new Font("Courier", Font.PLAIN, 12));
            line.add(detailLabel, BorderLayout.CENTER);
            
            this.add(line);
        }
        this.add(button);
        
        return this;
    }
            
    private void editDetails()
    {
        this.removeAll();
        if (formType.equals("User"))
            this.add(new PersonalDetailsFormPanel(storeGUI, "Edit account details.", details));
        else if (formType.equals("Address"))
            this.add(new AddressFormPanel(storeGUI, "Edit address details.", details));
        else if (formType.equals("CardDetails"))
            this.add(new CardDetailsFormPanel(storeGUI, "Edit card details.", details));
            
       storeGUI.pack();
    }
    
    private void deleteDetails()
    {
        this.removeAll();
        if (formType.equals("Address"))
            storeGUI.deleteAddress(details);
    }
    
    private void addressType()
    {
        storeGUI.specifyAddressType(details, option);
    }
            
    
}