import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import java.awt.image.*;

import java.util.Iterator;

/**
 * A simple online shopping simulation program
 * 
 * @author Pablo Romero
 * @version 2007.10
 */
public class StoreGUI extends JFrame
{

    private static final String VERSION = "Version 1.0";
    private static final String ICON_IMAGE = "icon.jpg";
    private static final String SHOP_IMAGE = "shop.jpg";
    public static final int CENTER_WIDTH = 800;
    public static final int CENTER_HEIGHT = 463;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JLabel statusLabel;
    
    //private JFrame frame;
    private Catalogue catalogue;
    private ShoppingBasket basket;
    private User currentUser;
    private StoreFileHandler fileHandler;
    private Order tempOrder;
    private Delivery tempDelivery;
    private PaymentDetails tempPayment;
    

    

    /**
     * Create a Store and display its GUI on screen.
     */
    public StoreGUI()
    {
        super("Robin's Astronomy shop");
        
        fileHandler = new StoreFileHandler();
        catalogue = fileHandler.loadCatalogue(); 
        basket = new ShoppingBasket();
        currentUser = null;
        tempOrder = new Order();
        tempDelivery = new Delivery();
        tempPayment = new PaymentDetails();
        
        mainPanel = new JPanel();
        statusLabel = new JLabel(VERSION);
        centerPanel = new JPanel();

        centerPanel.setPreferredSize(new Dimension(CENTER_WIDTH, CENTER_HEIGHT));
        
        makeFrame();
        mainView(); 
    }
    
    /**
     * Create the complete application GUI.
     */
    public void makeFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        
        makeMenuBar();
        
        BufferedImage iconImage = fileHandler.loadImage(ICON_IMAGE);
        setIconImage(iconImage);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());     
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);
     
        pack();
        
        // place this frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar()
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("Log in");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { logInView(); }
                            });
        menu.add(item);
        
        item = new JMenuItem("Log out");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { logOut(); }
                            });
        if (currentUser == null){
            item.setEnabled(false);
        }
        menu.add(item);
        
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { quit(); }
                           });
        menu.add(item);
        
        menu = new JMenu("Go");
        menubar.add(menu);
        
        item = new JMenuItem("Main menu");
            item.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) { mainView(); }
                            });
        menu.add(item);
        
        item = new JMenuItem("Shop");
            item.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) { shopView(); }
                            });
        if (currentUser == null){
            item.setEnabled(false);
        }
        menu.add(item);
                            

        // put a spacer into the menubar, so the next menu appears to the right
        menubar.add(Box.createHorizontalGlue());

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About Virtual Shop ...");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { showAbout(); }
                           });
        menu.add(item);
    }

    /**
     * Create the main frame's products bar.
     * @return the created products bar
     */
    
    
    
    private void mainView()
    {
        statusUpdate("Main menu.");
        
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding(null, 0.25);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
       
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,1));
        centerPanel.add(menuPanel, BorderLayout.CENTER);
        
        JButton logInButton = new JButton("Log in");
        JButton shopButton = new JButton("Shop without logging in");
        JButton createAccountButton = new JButton("Create account");
        
        menuPanel.add(logInButton);
        menuPanel.add(shopButton);
        menuPanel.add(createAccountButton);
        
        logInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { logInView(); }
            });
        shopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { shopView(); }
            });
        createAccountButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { createAccountView(); }
            });
            
        pack();
    }
    
     private void logInView()
     {
         mainPanel.removeAll();
         mainPanel.add(centerPanel, BorderLayout.CENTER);
         
         centerPanel.removeAll();
         centerPanel.setLayout(new BorderLayout());
         centerPanelPadding(null, 0.25);
         
         JPanel formPanel = new JPanel();
         centerPanel.add(formPanel);

         formPanel.add(new LogInFormPanel(this, "Log in.", true));
                                    
         pack();
     }
     
     public void logIn(String[] details)
     {
         if (currentUser == null){
            currentUser = fileHandler.loadUser(details);
            if (currentUser != null){
                tempOrder = new Order();
                tempDelivery = new Delivery();
                tempPayment = new PaymentDetails();
                shopView();
                makeMenuBar();
                statusUpdate("Welcome " + currentUser.getFirstName() + " to Robin's Astronomy Shop!");
            }   
            else
                statusUpdate("Your details could not be found.");           
        }
        else
            statusUpdate(currentUser.getUsername() + " already logged in.  Log out first.");
    }
     
     public void logOut()
     {
        if (currentUser != null){
            fileHandler.saveObject(currentUser);
            statusUpdate(currentUser.getUsername() + " logged out.");
            currentUser = null;
            tempOrder = null;
            tempDelivery = null;
            tempPayment = null;
            makeMenuBar();
        }
        else 
            statusUpdate("No one logged in.");
            
        mainView();
     }
     
     private void createAccountView()
     {   
         mainPanel.removeAll();
         mainPanel.setLayout(new BorderLayout());
         mainPanel.add(centerPanel, BorderLayout.CENTER);
         
         centerPanel.removeAll();
         centerPanel.setLayout(new BorderLayout());
         centerPanelPadding(null, 0.25);
         
         JPanel formPanel = new JPanel();
         centerPanel.add(formPanel);
         
         formPanel.add(new PersonalDetailsFormPanel(this, "Create account."));
                
        pack();
     }
     
     public void createAccount(String[] details)
     {    
         fileHandler.saveObject(new User(details));
         statusUpdate("Account created.  You may now log in.");
         mainView();
    }

    
     
    private void shopView()
    {
        statusUpdate("Welcome to Robin's Astronomy Shop!");
        setPreferredSize(null);
        
        mainPanel.removeAll();
        mainPanel.add(getProductsBar(), BorderLayout.NORTH);
        mainPanel.add(getOptionsBar(), BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding(null, 0.125);
        
        ImagePanel imagePanel = new ImagePanel();
        BufferedImage image = fileHandler.loadImage(SHOP_IMAGE);
        imagePanel.setImage(image);
        centerPanel.add(imagePanel, BorderLayout.CENTER);

 
        pack();
    }
    
    private JPanel getProductsBar()
    {
        JPanel productsBar = new JPanel();

        productsBar.setLayout(new GridLayout(2, 3));
        
        JButton button = new JButton("Search for products");
        button.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { searchView(); }
                           });
        productsBar.add(button);
  
        button = new JButton("All products");
        button.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { displayCatalogue(null); }
                           });
        productsBar.add(button);
        
        
        for (final String type : Product.TYPES){
            button = new JButton(type);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { displayCatalogue(type); }
            });
            productsBar.add(button);
        }
        
        return productsBar;
    }
    
    private JPanel getOptionsBar()
    {
        JPanel optionsBar = new JPanel();
        
        optionsBar.setLayout(new GridLayout(0, 1));
        
        JButton buttonBasket = new JButton("Basket");
        buttonBasket.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) { basketView(); }
                        });
        optionsBar.add(buttonBasket);
        
        JButton buttonAccount = new JButton("My account");
        buttonAccount.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) { 
                                if (currentUser != null)
                                    accountMenuView();
                                else 
                                    statusUpdate("You must log in first.");
                            }
                        });
        optionsBar.add(buttonAccount);
        
        //JButton buttonDelivery = new JButton("Delivery");
        //buttonDelivery.addActionListener(new ActionListener() {
        //                    public void actionPerformed(ActionEvent e) { delivery(); }
        //                });
       // optionsBar.add(buttonDelivery);   
        
        JButton buttonCheckout = new JButton("Checkout");
        buttonCheckout.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {  
                                if (currentUser == null)
                                        statusUpdate("You must log in first.");
                                else 
                                    checkoutMenuView();
                            }
                        });
        optionsBar.add(buttonCheckout);
         
        
        return optionsBar;
    }
    

    private void searchView()
    {
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding("vertical", 0.05);
        centerPanelPadding("sides", 0.05);
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.CENTER);
        
        searchPanel.add(new SearchFormPanel(this, "Search."), BorderLayout.NORTH);
        pack();
    }
    
    public void searchForProduct(String[] details)
    {
        ArrayList<String> possibleNames = catalogue.findPossibleProducts(details[0]);
       
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding("vertical", 0.05);
        centerPanelPadding("sides", 0.05);
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.CENTER);
        
        searchPanel.add(new SearchFormPanel(this, "Search."), BorderLayout.NORTH);
        
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(0,1));
        searchPanel.add(resultsPanel, BorderLayout.CENTER);
        
        ArrayList<Product> possibleProducts = new ArrayList<Product>();
        for (String name : possibleNames){
            possibleProducts.add(catalogue.getProduct(name));
        }
        
       resultsPanel.add(new ProductDisplayPanel(this, possibleProducts, false));
       pack();
        
    }
        
    
    private void displayCatalogue(String type)
    {
        centerPanel.removeAll();
        centerPanelPadding(null, 0.05);
        
        JPanel displayPanel = new JPanel();
        centerPanel.add(displayPanel, BorderLayout.CENTER);
        
        displayPanel.add(new ProductDisplayPanel(this, catalogue.getProducts(type), false));
        
        pack();
        
    }
      

    
    public void productCloseUp(Product product)
    {
        centerPanel.removeAll();
        centerPanelPadding("sides", 0.05);
        centerPanelPadding("vertical", 0.2);
        
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        centerPanel.add(displayPanel, BorderLayout.CENTER);
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product);
        displayPanel.add(new ProductDisplayPanel(this, products, false), BorderLayout.NORTH);
        
        ImagePanel imagePanel = new ImagePanel();
        
        if (product.hasImage()){
            imagePanel.setImage(product.getImage());
            imagePanel.setPreferredSize(imagePanel.getPreferredSize());
            displayPanel.add(imagePanel, BorderLayout.CENTER);
        }
        
        int width = imagePanel.getPreferredSize().width;
        int height = imagePanel.getPreferredSize().height;
        
        JPanel extraDetailsPanel = new JPanel();
        extraDetailsPanel.setLayout(new GridLayout(0, 1));
        displayPanel.add(extraDetailsPanel, BorderLayout.WEST);
        
        JPanel blankPanel = new JPanel();
        blankPanel.setPreferredSize(new Dimension(CENTER_WIDTH - 200 - width, height));
        displayPanel.add(blankPanel, BorderLayout.EAST);
       
        String[] extraPrompts = null;
        String[] extraDetails = null;
        String productType = product.getClass().getSimpleName();
        if (productType.equals("Telescopes")){
            extraPrompts = ((Telescopes) product).EXTRA_DETAIL_PROMPTS;
            extraDetails = ((Telescopes) product).getExtraDetails();
        }
        else if (productType.equals("Binoculars")){
            extraPrompts = ((Binoculars) product).EXTRA_DETAIL_PROMPTS;
            extraDetails = ((Binoculars) product).getExtraDetails();
        }
        else if (productType.equals("Software")){
            extraPrompts = ((Software) product).EXTRA_DETAIL_PROMPTS;
            extraDetails = ((Software) product).getExtraDetails();
        }
            
        if (extraDetails != null){
            String labelText = "";
            for (int i = 0; i < extraDetails.length; i++){
                labelText = extraPrompts[i] + extraDetails[i];
                JLabel label = new JLabel(labelText);
                extraDetailsPanel.add(label);
            }
        }
        pack();
    }
    
            
        //MAKE EACH ITEM TYPE RETURN AN ARRAY OF STRINGS WITH ITS EXTRA DETAILS!
        //THEN DISPLAY THESE AND ITS PICTURE!
        

            
    public void addToBasket(Product product, int quantity)
    {
        basket.add(product, quantity);
    }
    
     public void removeFromBasket(Product product)
    {
        basket.removeItem(product.getLabel());
        statusUpdate(product.getLabel() + " removed from basket.");
        basketView();
    }
    
    public void basketView()
    {
        centerPanel.removeAll();
        centerPanelPadding(null, 0.05);
               
        JPanel basketPanel = new JPanel();
        centerPanel.add(basketPanel, BorderLayout.CENTER);
        
        basketPanel.add(new ProductDisplayPanel(this, basket.getItems(), true));
        
        pack();
   }
        
   
    private void accountMenuView()
    {
        statusUpdate("Your account.");
        
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding(null, 0.25);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,1));
        centerPanel.add(menuPanel, BorderLayout.CENTER);  
        
        JButton viewDetailsButton = new JButton("View personal details");
        JButton addressesButton = new JButton("Your addresses");
        JButton paymentDetailsButton = new JButton("View payment details");
        JButton orderHistoryButton = new JButton("View order history");
        
        menuPanel.add(viewDetailsButton);
        menuPanel.add(addressesButton);
        menuPanel.add(paymentDetailsButton);
        menuPanel.add(orderHistoryButton);
        
        viewDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { viewPersonalDetails(); }
        });       
        addressesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { addressMenuView(); }
        });
        paymentDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { viewPaymentDetailsView(); }
        });
        orderHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { viewOrderHistory(); }
        });
        
       pack();                
    }
    
    private void viewPersonalDetails()
    {
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding(null, 0.25);
        
        JPanel detailsPanel = new JPanel();
        centerPanel.add(detailsPanel, BorderLayout.CENTER);
        
        detailsPanel.add(new DisplayEditDeleteFormPanel(this, "User", currentUser.getDetails(), "Edit"));
        
        pack();
    }
    
     public void updatePersonalDetails(String[] details)
     {    
        currentUser.setDetails(details);
        fileHandler.saveObject(currentUser);
        statusUpdate("Account updated.");
        viewPersonalDetails();
    }
    
    
    private void viewOrderHistory()
    {
        statusUpdate("Your order history.");
        
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding("sides", 0.25);
        
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0,1));
        centerPanel.add(listPanel, BorderLayout.CENTER);
        
        for (Order order : currentUser.getOrders()){  
            JPanel detailsPanel = new JPanel();
            listPanel.add(detailsPanel);
            detailsPanel.add(new DisplayEditDeleteFormPanel(this, "Order", order.getShortDetails(), "Edit"));
        }
        
        pack();
    }
    
    private void viewPaymentDetailsView()
    {
        statusUpdate("Your payment details.");
        
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding(null, 0.25);
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        centerPanel.add(detailsPanel, BorderLayout.CENTER);
        
        String[] details = currentUser.getPaymentDetails().getCardDetails();
        if (details != null)
            detailsPanel.add(new DisplayEditDeleteFormPanel(this, "CardDetails", details, "Edit"), BorderLayout.NORTH);
        else
            detailsPanel.add(new CardDetailsFormPanel(this, "Set card details."), BorderLayout.NORTH);
            
        Address address = currentUser.getAddressBook().getAddressType("Billing");
        if (address != null)
            detailsPanel.add(new DisplayEditDeleteFormPanel(this, "Address", address.getDetails(), "Edit"), BorderLayout.CENTER);
        else
            detailsPanel.add(new AddressFormPanel(this, "Set billing address"), BorderLayout.CENTER);
        
        pack();
    }
    
     public void updateCardDetails(String[] details)
     {    
        currentUser.getPaymentDetails().setCardDetails(details);
        fileHandler.saveObject(currentUser);
        statusUpdate("Payment details updated.");
        viewPaymentDetailsView();
    }
    
    
    private void addressMenuView()
    {
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding(null, 0.25);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,1));
        centerPanel.add(menuPanel, BorderLayout.CENTER);  
        
        JButton viewAddressesButton = new JButton("View addresses");
        JButton addAddressButton = new JButton("Add address");
        JButton deleteAddressButton = new JButton("Delete address");
        JButton specifyDeliveryAddressButton = new JButton("Specify delivery address");
        JButton specifyBillingAddressButton = new JButton("Specify billing address");
        
        menuPanel.add(viewAddressesButton);
        menuPanel.add(addAddressButton);
        menuPanel.add(deleteAddressButton);
        menuPanel.add(specifyDeliveryAddressButton);
        menuPanel.add(specifyBillingAddressButton);
        
        viewAddressesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { viewAddressDetailsView(); }
        });                 
        addAddressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { addAddressView(); }
        });
        deleteAddressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { deleteAddressView(); }
        });
        specifyDeliveryAddressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { specifyAddressTypeView("Delivery"); }
        });
        specifyBillingAddressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { specifyAddressTypeView("Billing"); }
        });
        
        
       pack();                
    }
        
    private void addressDetailsView(String option)
    {
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding("sides", 0.25);
        centerPanelPadding("vertical", 0.1);
        
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0,1));
        displayPanel.add(listPanel, BorderLayout.NORTH);

        int number = 0;
        for (Address address : currentUser.getAddressBook().getAddressList()){
            JPanel addressPanel = new JPanel();
            addressPanel.setLayout(new BorderLayout());
            listPanel.add(addressPanel);
            JLabel headerLabel = new JLabel();
            addressPanel.add(headerLabel, BorderLayout.NORTH);
            headerLabel.setText(" ");
            if (currentUser.getAddressBook().isAddressType(address, "Delivery") &&
                     currentUser.getAddressBook().isAddressType(address, "Billing"))
                headerLabel.setText("Delivery address and Billing address");
            else if (currentUser.getAddressBook().isAddressType(address, "Delivery"))
                headerLabel.setText("Delivery address");
            else if (currentUser.getAddressBook().isAddressType(address, "Billing"))
                headerLabel.setText("Billing address");
                
            addressPanel.add(new DisplayEditDeleteFormPanel(this, "Address", address.getDetails(), option), BorderLayout.CENTER);
            number++;
        }
        listPanel.setPreferredSize(new Dimension(DisplayEditDeleteFormPanel.WIDTH, number * 160));
        
        pack();
    }
    
    private void viewAddressDetailsView()
    {
        addressDetailsView("Edit");
    }
     
     private void addAddressView()
     {   
         centerPanel.removeAll();
         centerPanel.setLayout(new BorderLayout());
         centerPanelPadding(null, 0.25);
         
         JPanel formPanel = new JPanel();
         centerPanel.add(formPanel);

         formPanel.add(new AddressFormPanel(this, "Enter details."));
                
         pack();
     }
     
     private void deleteAddressView()
     {
         statusUpdate("Delete address");
         addressDetailsView("Delete");
     }
     
     public void specifyAddressTypeView(String type)
    {
        statusUpdate("Specify " + type + " address");
        addressDetailsView(type);
    }
     
     public void createAddress(String[] details)
     {    
        currentUser.getAddressBook().addAddress(new Address(details));
        fileHandler.saveObject(currentUser);
        statusUpdate("Address added.");
        viewAddressDetailsView();
    }
    
     public void updateAddressDetails(String[] newDetails, String[] previousDetails)
     {    
         currentUser.getAddressBook().getAddress(previousDetails).setDetails(newDetails);
         fileHandler.saveObject(currentUser);
         statusUpdate("Address updated.");
         viewAddressDetailsView();
    }

    public void deleteAddress(String[] details)
    {
        currentUser.getAddressBook().removeAddress(details);
        fileHandler.saveObject(currentUser);
        statusUpdate("Address deleted.");
        viewAddressDetailsView();
    }
    
    public void specifyAddressType(String[] details, String type)
    {
        Address typeAddress = currentUser.getAddressBook().getAddress(details);
        currentUser.getAddressBook().setAddressType(typeAddress, type);
        fileHandler.saveObject(currentUser);
        statusUpdate(type + " address set.");
        addressDetailsView(type);
    }
    
    
    public void deliveryMenuView()
    {
    }
        
      
        
    public void checkoutMenuView()
    {
        updateTempOrder();
        statusUpdate("Checkout.");
        
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding(null, 0.25);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0,2));
        centerPanel.add(mainPanel, BorderLayout.CENTER);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,1));
        mainPanel.add(menuPanel);  
        
        JButton viewBasketButton = new JButton("View shopping basket");
        JButton addressButton = new JButton("Delivery address");
        JButton postageButton = new JButton("Postage");
        JButton paymentButton = new JButton("Payment");
        JButton confirmButton = new JButton("Confirm order");
        
        menuPanel.add(viewBasketButton);
        menuPanel.add(addressButton);
        menuPanel.add(postageButton);
        menuPanel.add(paymentButton);
        menuPanel.add(confirmButton);

        viewBasketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { basketView(); }
        });                 
        addressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { addressMenuView(); }
        });
        postageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { postageView(); }
        });
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { viewPaymentDetailsView(); }
        });
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { confirmOrder(); }
        });
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0,1));
        mainPanel.add(infoPanel);
        
        String labelText = "Basket total: " + Formatter.toMoneyString(basket.getTotal());
        JLabel label = new JLabel("      " + labelText);
        infoPanel.add(label);
    
        labelText = "";
        if (tempDelivery.getAddress() != null)
            labelText = tempOrder.getDelivery().getAddress().toString();
        label = new JLabel("      " + labelText);
        infoPanel.add(label);
        
        labelText = "";
        if (tempDelivery.getPostage() != null)
            labelText = tempDelivery.getPostage() + ": " + Formatter.toMoneyString(tempDelivery.getPostageCost());
        label = new JLabel("      " + labelText);
        infoPanel.add(label);
        
        labelText = "";
        String[] details = tempPayment.getCardDetails();
        if (details != null)
            labelText = tempPayment.toString();
        label = new JLabel("      " + labelText);
        infoPanel.add(label);
         
        infoPanel.add(new JLabel());
        
       pack();                
    }
    
    public void updateTempOrder()
    {
        tempOrder.setBasket(basket);
        
        Address address = currentUser.getAddressBook().getAddressType("Delivery");
        if (address != null)
            tempDelivery.setAddress(address);
        tempOrder.setDelivery(tempDelivery);
        
        String[] details = currentUser.getPaymentDetails().getCardDetails();
        if (details != null)
            tempPayment.setCardDetails(details);
            
        address = currentUser.getAddressBook().getAddressType("Billing");
        if (address != null)
            tempPayment.setAddress(address);
        tempOrder.setPaymentDetails(tempPayment);
    }

        
        
    public void postageView()
    {
        statusUpdate("Choose postage option.");
        
        centerPanel.removeAll();
        centerPanel.setLayout(new BorderLayout());
        centerPanelPadding(null, 0.3);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,1));
        centerPanel.add(menuPanel, BorderLayout.CENTER);  
        
        JButton firstClassButton = new JButton(Formatter.leftJustify("First class: ", 20) + "£1.00");
        JButton secondClassButton = new JButton(Formatter.leftJustify("Second class: ", 20) + "£2.00");
        JButton courierButton = new JButton(Formatter.leftJustify("Courier: ", 20) + "£5.00");
        
        menuPanel.add(firstClassButton);
        menuPanel.add(secondClassButton);
        menuPanel.add(courierButton);
        
        firstClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { setPostage("First"); }
        });                 
        secondClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { setPostage("Second"); }
        });
        courierButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) { setPostage("Courier"); }
       });
        
       pack();  
    }
    
    public void setPostage(String postage)
    {
        tempDelivery.setPostage(postage);
        checkoutMenuView();
    }
        
    public void confirmOrder()
    {
        updateTempOrder();
        if (tempOrder.isComplete()){
            fileHandler.saveObject(currentUser);
            statusUpdate("Order completed.  Thank you for shopping at Robin's Astronomy Shop :D");
            shopView();
            tempOrder = new Order();
            tempDelivery = new Delivery();
            tempPayment = new PaymentDetails();
            basket.empty();
        }
        else
            statusUpdate("Not all details are complete or the basket is empty.");
    }
        
        
    
    
        
    //Miscellaneous methods
    private void centerPanelPadding(String direction, Double thickness)
    {
        if (direction == null || direction.equals("vertical")){
            JPanel northPanel = new JPanel();
            northPanel.setPreferredSize(new Dimension(CENTER_WIDTH, (int) (CENTER_HEIGHT * thickness)));
            centerPanel.add(northPanel, BorderLayout.NORTH);

            JPanel southPanel = new JPanel();
            southPanel.setPreferredSize(new Dimension(CENTER_WIDTH, (int) (CENTER_HEIGHT * thickness)));
            centerPanel.add(southPanel, BorderLayout.SOUTH);
            
            if (direction == null){
                direction = "sides";
            }
        }
        
        if (direction.equals("sides")){
            JPanel eastPanel = new JPanel();
            eastPanel.setPreferredSize(new Dimension((int) (CENTER_WIDTH * thickness), CENTER_HEIGHT));
            centerPanel.add(eastPanel, BorderLayout.EAST);
        
            JPanel westPanel = new JPanel();
            westPanel.setPreferredSize(new Dimension((int) (CENTER_WIDTH * thickness), CENTER_HEIGHT));
            centerPanel.add(westPanel, BorderLayout.WEST);
        }
    }
    
    public ShoppingBasket getBasket()
    {
        return basket;
    }
    
                  

    
    
    
    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(this, 
                    "Virtual Shop\n" + VERSION,
                    "About Virtual Shop", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
        

     

    
    public void quit()
    {
        if (currentUser != null)
            fileHandler.saveObject(currentUser);
            
        statusUpdate("Thank you for shopping at Robin's Astronomy Shop!");
        System.exit(0);
    }
    
            
    
    public void statusUpdate(String update)
    {
        statusLabel.setText(update);
    }
    

    

    

        




}
