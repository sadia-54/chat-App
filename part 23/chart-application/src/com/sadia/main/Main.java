package com.sadia.main;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.sadia.event.EventImageView;
import com.sadia.event.EventMain;
import com.sadia.event.PublicEvent;
import com.sadia.model.Model_User_Account;
import com.sadia.service.Service;
import com.sadia.swing.ComponentResizer;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends javax.swing.JFrame {

    public Main() {
        // Initialize GUI components and custom setup
        initComponents();
        init();
    }


     // Custom initialization method to set up the frame and components.

    private void init() {
        // Set window icon
        setIconImage(new ImageIcon(getClass().getResource("/com/sadia/icon/icon.png")).getImage());

        // Register the window with a resizer for adjustable sizing
        ComponentResizer com = new ComponentResizer();
        com.registerComponent(this);
        com.setMinimumSize(new Dimension(2200, 1000));
        com.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        com.setSnapSize(new Dimension(10, 10));

        // Initialize the visibility of different panels
        login.setVisible(true);
        loading.setVisible(false);
        vIew_Image.setVisible(false);
        home.setVisible(false);

        // Initialize events for handling various actions
        initEvent();

        // Start the server service
        Service.getInstance().startServer();
    }


     // Initialize event handling for different components.

    private void initEvent() {
        // Set up main event handling
        PublicEvent.getInstance().addEventMain(new EventMain() {
            @Override
            public void showLoading(boolean show) {
                loading.setVisible(show);
            }

            @Override
            public void initChat() {
                // Show the home panel and hide the login panel on chat initialization
                home.setVisible(true);
                login.setVisible(false);

                // Emit event to get list of users
                Service.getInstance().getClient().emit("list_user", Service.getInstance().getUser().getUserID());
            }

            @Override
            public void selectUser(Model_User_Account user) {
                // Update home with the selected user details
                home.setUser(user);
            }

            @Override
            public void updateUser(Model_User_Account user) {
                // Update user details in the home panel
                home.updateUser(user);
            }

        });

        // Set up image viewing event handling
        PublicEvent.getInstance().addEventImageView(new EventImageView() {
            @Override
            public void viewImage(Icon image) {
                // Show the image in the view image panel
                vIew_Image.viewImage(image);
            }

            @Override
            public void saveImage(Icon image) {
                // Placeholder for image save functionality
                System.out.println("Save Image next update");
            }

        });
    }

    // Method to initialize and arrange GUI components
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Instantiate components
        border = new javax.swing.JPanel();
        background = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        cmdMinimize = new javax.swing.JButton();
        cmdClose = new javax.swing.JButton();
        body = new javax.swing.JLayeredPane();
        loading = new com.sadia.form.Loading();
        login = new com.sadia.form.Login();
        vIew_Image = new com.sadia.form.VIew_Image();
        home = new com.sadia.form.Home();

        // Set up frame properties
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        // Configure border panel
        border.setBackground(new java.awt.Color(229, 229, 229));

        // Configure background panel
        background.setBackground(new java.awt.Color(255, 255, 255));

        // Configure title panel with minimize and close buttons
        title.setBackground(new java.awt.Color(229, 229, 229));
        title.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titleMouseDragged(evt);
            }
        });
        title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titleMousePressed(evt);
            }
        });

        // Set minimize button properties
        cmdMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sadia/icon/minimize.png")));
        cmdMinimize.setBorder(null);
        cmdMinimize.setContentAreaFilled(false);
        cmdMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMinimizeActionPerformed(evt);
            }
        });

        // Set close button properties
        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sadia/icon/close.png")));
        cmdClose.setBorder(null);
        cmdClose.setContentAreaFilled(false);
        cmdClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        // Layout configuration for title panel
        javax.swing.GroupLayout titleLayout = new javax.swing.GroupLayout(title);
        title.setLayout(titleLayout);
        titleLayout.setHorizontalGroup(
                titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdMinimize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdClose)
                                .addContainerGap())
        );
        titleLayout.setVerticalGroup(
                titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(titleLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmdClose, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                        .addComponent(cmdMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, 0))
        );

        // Configure layered pane to hold different panels
        body.setLayout(new java.awt.CardLayout());
        body.add(loading, "card5");
        body.add(login, "card4");
        body.setLayer(vIew_Image, javax.swing.JLayeredPane.POPUP_LAYER);
        body.add(vIew_Image, "card3");
        body.add(home, "card2");

        // Layout configuration for background panel
        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE)
                                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                                .addContainerGap())
        );

        // Layout configuration for border panel
        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
                borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(borderLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
        );
        borderLayout.setVerticalGroup(
                borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(borderLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
        );

        // Layout configuration for main content pane
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        // Finalize and center the window on the screen
        pack();
        setLocationRelativeTo(null);
    }

    // Variables for mouse drag functionality
    private int pX;
    private int pY;

    // Method to handle window dragging for custom title bar
    private void titleMouseDragged(java.awt.event.MouseEvent evt) {
        this.setLocation(this.getLocation().x + evt.getX() - pX, this.getLocation().y + evt.getY() - pY);
    }

    // Method to capture mouse press location for dragging
    private void titleMousePressed(java.awt.event.MouseEvent evt) {
        pX = evt.getX();
        pY = evt.getY();
    }

    // Action performed when the close button is clicked
    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    // Action performed when the minimize button is clicked
    private void cmdMinimizeActionPerformed(java.awt.event.ActionEvent evt) {
        this.setState(JFrame.ICONIFIED);
    }

    // Main method to start the application
    public static void main(String args[]) {
        // Set up the FlatArcIJTheme look and feel
        FlatArcIJTheme.setup();

        // Start the GUI on the Event Dispatch Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel background;
    private javax.swing.JLayeredPane body;
    private javax.swing.JPanel border;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdMinimize;
    private com.sadia.form.Home home;
    private com.sadia.form.Loading loading;
    private com.sadia.form.Login login;
    private javax.swing.JPanel title;
    private com.sadia.form.VIew_Image vIew_Image;
    // End of variables declaration
}
