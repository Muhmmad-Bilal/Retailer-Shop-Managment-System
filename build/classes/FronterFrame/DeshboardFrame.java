/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FronterFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import retailershopmanagmentsystem.BarcodeGeneratorJFrame;
import retailershopmanagmentsystem.CustomerFrame;
import retailershopmanagmentsystem.EmployeJFrame;
import retailershopmanagmentsystem.ProductCategoryFrame;
import retailershopmanagmentsystem.SaleBookJFrame;
import retailershopmanagmentsystem.StokeJFrame;

/**
 *
 * @author Maham Computers
 */
public class DeshboardFrame extends javax.swing.JFrame {


    static int screenWidth;
    static int screenHeight;
    int k;

    
    public DeshboardFrame() {
        initComponents();
        constructorLoad();
    }//End of constructor
    
    
    private void constructorLoad(){
        ImageIcon icon = new ImageIcon(new File("Images\\shopLogo.png").getAbsolutePath());
	this.setIconImage(icon.getImage());
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize= tk.getScreenSize();
        screenWidth = (int)screenSize.width;
        screenHeight = (int)screenSize.height;
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        
        jLayeredPane.setBounds(230, 41, screenSize.width, screenSize.height);
        menuPanel.setSize(230, screenSize.height);
        
        bgJLabel.setSize(screenSize.width,screenSize.height);
        bgJLabel.setIcon(new ImageIcon(new ImageIcon(new File("Images//bgImage1.jpg").getAbsolutePath()).getImage().getScaledInstance(bgJLabel.getWidth(), bgJLabel.getHeight(), Image.SCALE_SMOOTH)));

        nameLable.setVisible(false);
    }// End of constructorLoad
    
    
    
    private void mouseEntered(javax.swing.JButton jButton){
        jButton.setContentAreaFilled(true);
        jButton.setBackground(new Color(29,99,169));
        jButton.setForeground(new Color(255,255,255));
    }//End of mouseEntered
    
    private void mouseExited(javax.swing.JButton jButton){
        jButton.setContentAreaFilled(false);
        jButton.setForeground(new Color(255,255,255));
        jButton.setBackground(new Color(29,99,169));
    }//End of mouseExited
    
    private void switchJPanel(JPanel panel){
        
        
        jLayeredPane.removeAll();
        jLayeredPane.add(panel);
        jLayeredPane.repaint();
        
        deactiveMenuPanel();

    }//end of switchJPanel
    
    private void activeMenuPanel(){
        
        menuBarPanel.setBounds(230,0,screenWidth, 41);
        menuPanel.setSize(230,screenHeight);
        
        jLayeredPane.setBounds(230,41,screenWidth, screenHeight);
        mainJPanel.setBounds(0,0,jLayeredPane.getWidth(),jLayeredPane.getHeight());
        
        deshboardBtn.setSize(216, 55);
        productCategoryJButton.setSize(216, 46);
        productJButton.setSize(216,46);
        saleBookJButton.setSize(216,46);
        customerJButton.setSize(216,46);
        employeRegJButton.setSize(224,46);
        barcodeGeneratorJButton.setSize(216,46);
        logoutJButton.setSize(216,46);
        aboutJButton.setSize(216,46);
        
        nameLable.setVisible(false);
        iconJLable.setVisible(true);
        
//        iconJLable.setSize(96,96);
//        iconJLable.setIcon(new ImageIcon(new ImageIcon(new File("Images//shopLogo-96.png").getAbsolutePath()).getImage().getScaledInstance(iconJLable.getWidth(), iconJLable.getHeight(), Image.SCALE_SMOOTH)));
        
        k = 0;
        
    }//activeMenuPanel
    
    private void deactiveMenuPanel(){
        
        menuBarPanel.setBounds(63,0,screenWidth, 41);
        menuPanel.setSize(63,screenHeight);
        jLayeredPane.setBounds(63,41,screenWidth, screenHeight);
        mainJPanel.setBounds(0,0,jLayeredPane.getWidth(),jLayeredPane.getHeight());

        deshboardBtn.setSize(53,46);
        productCategoryJButton.setSize(53,46);
        productJButton.setSize(53,46);
        saleBookJButton.setSize(53,46);
        customerJButton.setSize(53,46);
        employeRegJButton.setSize(53,46);
        barcodeGeneratorJButton.setSize(53,46);
        logoutJButton.setSize(53,46);
        aboutJButton.setSize(53,46);
        
//        nameLable.setVisible(true);
//        iconJLable.setVisible(false);
//        nameLable.setText("S.M");
//        nameLable.setForeground(Color.RED);
//        iconJLable.setLocation(7,10);
//        iconJLable.setSize(48,48);
//        iconJLable.setIcon(new ImageIcon(new ImageIcon(new File("Images//shopLogo-96.png").getAbsolutePath()).getImage().getScaledInstance(iconJLable.getWidth(), iconJLable.getHeight(), Image.SCALE_SMOOTH)));
        
        k = 1;
        
    }//deactiveMenuPanel
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBarPanel = new javax.swing.JPanel();
        menuJButton = new javax.swing.JButton();
        dateJLabel = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        deshboardBtn = new javax.swing.JButton();
        productCategoryJButton = new javax.swing.JButton();
        productJButton = new javax.swing.JButton();
        saleBookJButton = new javax.swing.JButton();
        customerJButton = new javax.swing.JButton();
        employeRegJButton = new javax.swing.JButton();
        logoutJButton = new javax.swing.JButton();
        aboutJButton = new javax.swing.JButton();
        iconJLable = new javax.swing.JLabel();
        nameLable = new javax.swing.JLabel();
        barcodeGeneratorJButton = new javax.swing.JButton();
        jLayeredPane = new javax.swing.JLayeredPane();
        mainJPanel = new javax.swing.JPanel();
        bgJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeiconified(java.awt.event.WindowEvent evt) {
                formWindowDeiconified(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        menuBarPanel.setBackground(new java.awt.Color(29, 99, 169));
        menuBarPanel.setPreferredSize(new java.awt.Dimension(322, 41));

        menuJButton.setBackground(new java.awt.Color(29, 99, 169));
        menuJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-menu-30 (3).png")); // NOI18N
        menuJButton.setBorderPainted(false);
        menuJButton.setContentAreaFilled(false);
        menuJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuJButton.setFocusPainted(false);
        menuJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuJButtonMouseExited(evt);
            }
        });
        menuJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuJButtonActionPerformed(evt);
            }
        });

        dateJLabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        dateJLabel.setForeground(new java.awt.Color(255, 255, 255));
        dateJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout menuBarPanelLayout = new javax.swing.GroupLayout(menuBarPanel);
        menuBarPanel.setLayout(menuBarPanelLayout);
        menuBarPanelLayout.setHorizontalGroup(
            menuBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBarPanelLayout.createSequentialGroup()
                .addComponent(menuJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(463, 463, 463)
                .addComponent(dateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 395, Short.MAX_VALUE))
        );
        menuBarPanelLayout.setVerticalGroup(
            menuBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addComponent(dateJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuPanel.setBackground(new java.awt.Color(29, 99, 169));
        menuPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        menuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanelMouseEntered(evt);
            }
        });
        menuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deshboardBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deshboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        deshboardBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-home-24 (1).png")); // NOI18N
        deshboardBtn.setText(" Deshboard");
        deshboardBtn.setActionCommand("Deshboard");
        deshboardBtn.setBorderPainted(false);
        deshboardBtn.setContentAreaFilled(false);
        deshboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deshboardBtn.setFocusPainted(false);
        deshboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        deshboardBtn.setIconTextGap(20);
        deshboardBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deshboardBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deshboardBtnMouseExited(evt);
            }
        });
        deshboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deshboardBtnActionPerformed(evt);
            }
        });
        menuPanel.add(deshboardBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 98, 218, 43));

        productCategoryJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        productCategoryJButton.setForeground(new java.awt.Color(255, 255, 255));
        productCategoryJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-opened-folder-24 (1).png")); // NOI18N
        productCategoryJButton.setText("Product Category");
        productCategoryJButton.setBorderPainted(false);
        productCategoryJButton.setContentAreaFilled(false);
        productCategoryJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productCategoryJButton.setFocusPainted(false);
        productCategoryJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        productCategoryJButton.setIconTextGap(25);
        productCategoryJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productCategoryJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productCategoryJButtonMouseExited(evt);
            }
        });
        productCategoryJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCategoryJButtonActionPerformed(evt);
            }
        });
        menuPanel.add(productCategoryJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 148, 218, 45));

        productJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        productJButton.setForeground(new java.awt.Color(255, 255, 255));
        productJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-buy-24.png")); // NOI18N
        productJButton.setText("Stoke Product");
        productJButton.setBorderPainted(false);
        productJButton.setContentAreaFilled(false);
        productJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productJButton.setFocusPainted(false);
        productJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        productJButton.setIconTextGap(25);
        productJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productJButtonMouseExited(evt);
            }
        });
        productJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productJButtonActionPerformed(evt);
            }
        });
        menuPanel.add(productJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 193, 218, 45));

        saleBookJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saleBookJButton.setForeground(new java.awt.Color(255, 255, 255));
        saleBookJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-financial-analytics-24 (1).png")); // NOI18N
        saleBookJButton.setText("Sale Book");
        saleBookJButton.setBorderPainted(false);
        saleBookJButton.setContentAreaFilled(false);
        saleBookJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saleBookJButton.setFocusPainted(false);
        saleBookJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        saleBookJButton.setIconTextGap(25);
        saleBookJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saleBookJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saleBookJButtonMouseExited(evt);
            }
        });
        saleBookJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleBookJButtonActionPerformed(evt);
            }
        });
        menuPanel.add(saleBookJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 238, 218, 45));

        customerJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        customerJButton.setForeground(new java.awt.Color(255, 255, 255));
        customerJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-add-user-group-man-man-24 (1).png")); // NOI18N
        customerJButton.setText("Costomer");
        customerJButton.setBorderPainted(false);
        customerJButton.setContentAreaFilled(false);
        customerJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customerJButton.setFocusPainted(false);
        customerJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        customerJButton.setIconTextGap(25);
        customerJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customerJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customerJButtonMouseExited(evt);
            }
        });
        customerJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerJButtonActionPerformed(evt);
            }
        });
        menuPanel.add(customerJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 283, 218, 45));

        employeRegJButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        employeRegJButton.setForeground(new java.awt.Color(255, 255, 255));
        employeRegJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-registration-24.png")); // NOI18N
        employeRegJButton.setText("Employe Registration");
        employeRegJButton.setBorderPainted(false);
        employeRegJButton.setContentAreaFilled(false);
        employeRegJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employeRegJButton.setFocusPainted(false);
        employeRegJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        employeRegJButton.setIconTextGap(25);
        employeRegJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                employeRegJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                employeRegJButtonMouseExited(evt);
            }
        });
        employeRegJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeRegJButtonActionPerformed(evt);
            }
        });
        menuPanel.add(employeRegJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 328, 220, 45));

        logoutJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logoutJButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-logout-rounded-left-24.png")); // NOI18N
        logoutJButton.setText("Logout");
        logoutJButton.setBorderPainted(false);
        logoutJButton.setContentAreaFilled(false);
        logoutJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutJButton.setFocusPainted(false);
        logoutJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        logoutJButton.setIconTextGap(25);
        logoutJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutJButtonMouseExited(evt);
            }
        });
        logoutJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutJButtonActionPerformed(evt);
            }
        });
        menuPanel.add(logoutJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 213, 45));

        aboutJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        aboutJButton.setForeground(new java.awt.Color(255, 255, 255));
        aboutJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-about-26.png")); // NOI18N
        aboutJButton.setText("About");
        aboutJButton.setBorderPainted(false);
        aboutJButton.setContentAreaFilled(false);
        aboutJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aboutJButton.setFocusPainted(false);
        aboutJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        aboutJButton.setIconTextGap(25);
        aboutJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aboutJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aboutJButtonMouseExited(evt);
            }
        });
        aboutJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJButtonActionPerformed(evt);
            }
        });
        menuPanel.add(aboutJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 213, 45));

        iconJLable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        iconJLable.setForeground(new java.awt.Color(255, 255, 255));
        iconJLable.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\logo-min.png")); // NOI18N
        iconJLable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconJLable.setMinimumSize(new java.awt.Dimension(0, 0));
        menuPanel.add(iconJLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 90));

        nameLable.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        nameLable.setForeground(new java.awt.Color(157, 15, 15));
        nameLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLable.setText("S.M");
        menuPanel.add(nameLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 2, 80, 81));

        barcodeGeneratorJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        barcodeGeneratorJButton.setForeground(new java.awt.Color(255, 255, 255));
        barcodeGeneratorJButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maham Computers\\Desktop\\Netbean programs\\RetailerShopManagmentSystem\\Images\\icons8-barcode-30 (1).png")); // NOI18N
        barcodeGeneratorJButton.setText("Barcode Generator");
        barcodeGeneratorJButton.setBorderPainted(false);
        barcodeGeneratorJButton.setContentAreaFilled(false);
        barcodeGeneratorJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        barcodeGeneratorJButton.setFocusPainted(false);
        barcodeGeneratorJButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        barcodeGeneratorJButton.setIconTextGap(20);
        barcodeGeneratorJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                barcodeGeneratorJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                barcodeGeneratorJButtonMouseExited(evt);
            }
        });
        barcodeGeneratorJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcodeGeneratorJButtonActionPerformed(evt);
            }
        });
        menuPanel.add(barcodeGeneratorJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 373, -1, 45));

        jLayeredPane.setLayout(new java.awt.CardLayout());

        mainJPanel.setPreferredSize(new java.awt.Dimension(666, 666));
        mainJPanel.setLayout(null);

        bgJLabel.setForeground(new java.awt.Color(255, 255, 255));
        mainJPanel.add(bgJLabel);
        bgJLabel.setBounds(0, 0, 1340, 710);

        jLayeredPane.add(mainJPanel, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(menuBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLayeredPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1344, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLayeredPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1360, 751);
    }// </editor-fold>//GEN-END:initComponents
    
    private void deshboardBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deshboardBtnMouseEntered
        mouseEntered(deshboardBtn);
    }//GEN-LAST:event_deshboardBtnMouseEntered

    private void deshboardBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deshboardBtnMouseExited
        mouseExited(deshboardBtn);
    }//GEN-LAST:event_deshboardBtnMouseExited

    private void productCategoryJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productCategoryJButtonMouseEntered
        mouseEntered(productCategoryJButton);
    }//GEN-LAST:event_productCategoryJButtonMouseEntered

    private void productCategoryJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productCategoryJButtonMouseExited
        mouseExited(productCategoryJButton);
    }//GEN-LAST:event_productCategoryJButtonMouseExited

    private void productCategoryJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCategoryJButtonActionPerformed
        switchJPanel(new ProductCategoryFrame().getpanel());
    }//GEN-LAST:event_productCategoryJButtonActionPerformed

    private void productJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productJButtonMouseEntered
       mouseEntered(productJButton);
    }//GEN-LAST:event_productJButtonMouseEntered

    private void productJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productJButtonMouseExited
        mouseExited(productJButton);
    }//GEN-LAST:event_productJButtonMouseExited

    private void productJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productJButtonActionPerformed
       switchJPanel(new StokeJFrame().getPanel());
    }//GEN-LAST:event_productJButtonActionPerformed

    private void saleBookJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saleBookJButtonMouseEntered
        mouseEntered(saleBookJButton);
    }//GEN-LAST:event_saleBookJButtonMouseEntered

    private void saleBookJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saleBookJButtonMouseExited
        mouseExited(saleBookJButton);
    }//GEN-LAST:event_saleBookJButtonMouseExited

    private void saleBookJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleBookJButtonActionPerformed
        switchJPanel(new SaleBookJFrame().getPanel());
    }//GEN-LAST:event_saleBookJButtonActionPerformed

    private void customerJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerJButtonMouseEntered
       mouseEntered(customerJButton);
    }//GEN-LAST:event_customerJButtonMouseEntered

    private void customerJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerJButtonMouseExited
        mouseExited(customerJButton);
    }//GEN-LAST:event_customerJButtonMouseExited

    private void customerJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerJButtonActionPerformed
       switchJPanel(new CustomerFrame().getpanel());
    }//GEN-LAST:event_customerJButtonActionPerformed

    private void employeRegJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeRegJButtonMouseEntered
        mouseEntered(employeRegJButton);
    }//GEN-LAST:event_employeRegJButtonMouseEntered

    private void employeRegJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeRegJButtonMouseExited
        mouseExited(employeRegJButton);
    }//GEN-LAST:event_employeRegJButtonMouseExited

    private void employeRegJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeRegJButtonActionPerformed
       switchJPanel(new EmployeJFrame().getPanel());
    }//GEN-LAST:event_employeRegJButtonActionPerformed

    private void logoutJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutJButtonMouseEntered
        mouseEntered(logoutJButton);
    }//GEN-LAST:event_logoutJButtonMouseEntered

    private void logoutJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutJButtonMouseExited
        mouseExited(logoutJButton);
    }//GEN-LAST:event_logoutJButtonMouseExited

    private void logoutJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutJButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutJButtonActionPerformed

    private void aboutJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutJButtonMouseEntered
        mouseEntered(aboutJButton);
    }//GEN-LAST:event_aboutJButtonMouseEntered

    private void aboutJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutJButtonMouseExited
        mouseExited(aboutJButton);
    }//GEN-LAST:event_aboutJButtonMouseExited

    private void aboutJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutJButtonActionPerformed
        ImageIcon icon = new ImageIcon(new File("Images\\shopLogo-96.png").getAbsolutePath());
        JOptionPane.showConfirmDialog(null,"DEVELOPER :\t SHEERAZ AHMED\nCOMPANY     :\t BELL LAB`S\nSOFTWARE   :\t STORE MANAGMENT SYSTEM\nDATE              : 6-Aug-2020", "INFORMATION",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,icon);
    }//GEN-LAST:event_aboutJButtonActionPerformed

    private void menuPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuPanelMouseEntered

    private void deshboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deshboardBtnActionPerformed
        switchJPanel(mainJPanel);
    }//GEN-LAST:event_deshboardBtnActionPerformed
    
    private void menuJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuJButtonMouseEntered
        mouseEntered(menuJButton);
    }//GEN-LAST:event_menuJButtonMouseEntered

    private void menuJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuJButtonMouseExited
        mouseExited(menuJButton);
    }//GEN-LAST:event_menuJButtonMouseExited

    private void barcodeGeneratorJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barcodeGeneratorJButtonMouseEntered
        mouseEntered(barcodeGeneratorJButton);
    }//GEN-LAST:event_barcodeGeneratorJButtonMouseEntered

    private void barcodeGeneratorJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barcodeGeneratorJButtonMouseExited
        mouseExited(barcodeGeneratorJButton);
    }//GEN-LAST:event_barcodeGeneratorJButtonMouseExited

    private void barcodeGeneratorJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcodeGeneratorJButtonActionPerformed
        switchJPanel(new BarcodeGeneratorJFrame().getPanel());
    }//GEN-LAST:event_barcodeGeneratorJButtonActionPerformed
    private void menuJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuJButtonActionPerformed
        if(k == 0){
            deactiveMenuPanel();
        }
        else{
            activeMenuPanel();
        }
    }//GEN-LAST:event_menuJButtonActionPerformed

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        System.out.println("WindowIconified "+ k);
//        if(k == 1){
//            deactiveMenuPanel();
//        }
//        else{
//            activeMenuPanel();
//        }
    }//GEN-LAST:event_formWindowIconified

    private void formWindowDeiconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeiconified
        System.out.println("WindowDeconified "+ k);
//        if(k == 1){
//            deactiveMenuPanel();
//        }
//        else{
//            activeMenuPanel();
//        }
    }//GEN-LAST:event_formWindowDeiconified

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
//            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 18)));
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeshboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeshboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeshboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeshboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeshboardFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutJButton;
    private javax.swing.JButton barcodeGeneratorJButton;
    private javax.swing.JLabel bgJLabel;
    private javax.swing.JButton customerJButton;
    private javax.swing.JLabel dateJLabel;
    private javax.swing.JButton deshboardBtn;
    private javax.swing.JButton employeRegJButton;
    private static javax.swing.JLabel iconJLable;
    private javax.swing.JLayeredPane jLayeredPane;
    private javax.swing.JButton logoutJButton;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JPanel menuBarPanel;
    private javax.swing.JButton menuJButton;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel nameLable;
    private javax.swing.JButton productCategoryJButton;
    private javax.swing.JButton productJButton;
    private javax.swing.JButton saleBookJButton;
    // End of variables declaration//GEN-END:variables
}
