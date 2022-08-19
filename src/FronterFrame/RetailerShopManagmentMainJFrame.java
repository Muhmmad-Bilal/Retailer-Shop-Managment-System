/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FronterFrame;

import Beans.EmployeBean;
import Beans.UsersBean;
import databaseManager.DatabaseManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import retailershopmanagmentsystem.BarcodeGeneratorJFrame;
import retailershopmanagmentsystem.CustomerFrame;
import retailershopmanagmentsystem.EmployeJFrame;
import retailershopmanagmentsystem.EncoderAndDecoder;
import retailershopmanagmentsystem.ProductCategoryFrame;
import retailershopmanagmentsystem.ProductDetailJFrame;
import retailershopmanagmentsystem.ProductFrame;
import retailershopmanagmentsystem.ReportsJFrame;
import retailershopmanagmentsystem.SaleBookJFrame;
import retailershopmanagmentsystem.StokeJFrame;

/**
 *
 * @author Maham Computers
 */
public class RetailerShopManagmentMainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form RetailerShopManagmentMainJFrame
     */
    static boolean maximized = true;
    
    
    public RetailerShopManagmentMainJFrame() {
        initComponents();
        constractorLoad();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        pack();
        
        BottomjPanel.setBounds(0,(screenSize.height-30),screenSize.width,30);
        
        menuLabel.setIcon(new ImageIcon(new File("Images\\icons8-menu-vertical-35.png").getAbsolutePath()));
        maxLable.setIcon(new ImageIcon(new File("Images\\icons8-restore-window-25.png").getAbsolutePath()));
        
    }
    
    private void constractorLoad(){
        this.setLocationRelativeTo(null);
        
        this.add(menuPanel,0);
        innerclass l = new innerclass();
        ImageIcon icon8 = new ImageIcon(new File("Images\\icons8-enter-100.png").getAbsolutePath());
        jTabbedPane.add(l.getpanel(),icon8); 
        menuLabel.setVisible(false);
        
        ImageIcon icon = new ImageIcon(new File("Images\\shopLogo.png").getAbsolutePath());
	this.setIconImage(icon.getImage());
    }
    
    
  
   
    
    class innerclass extends Login{
        @Override
        protected void login(){

            String userName;
            String password;
            
            boolean isAuthentic = false;

            try{
                Vector v = DatabaseManager.getSignUpData();
                for( int i = 0; i < v.size(); i++){
                   UsersBean bean =(UsersBean)v.elementAt(i);
                   userName = bean.getUserName();
                   password = EncoderAndDecoder.decrypt(bean.getPassword(), "storeManagment!!!");
                   
                    System.err.println("userName : "+userName);
                    System.err.println("password : "+password);

                   if(Login.getUserName().equals(userName) && Login.getPassword().equals(password)){
                       
                       EmployeBean eBean = DatabaseManager.getEmploye(bean.getEmploye_id());

                       if(eBean.getStatus().equals("0")){
                           if(eBean.getEmployeType().toLowerCase().equals("owner")){

                                jTabbedPane.remove(jTabbedPane.getTabCount() - 1);
                                menuLabel.setVisible(true);
                                addAdminPanel();
                                userTxt.setText("User Name");
                                passwordTxt.setText("Password");
                                passwordTxt.setEchoChar((char)0);
                                isAuthentic = true;
                           }
                           else{

                                jTabbedPane.remove(jTabbedPane.getTabCount() - 1);
                                menuLabel.setVisible(true);
                                addUserPanel();
                                userTxt.setText("User Name");
                                passwordTxt.setText("Password");
                                passwordTxt.setEchoChar((char)0);
                                isAuthentic = true;
                           }
                       }//End of active if
                       else{
                            javax.swing.JOptionPane.showMessageDialog(null, "deactive");
                       }


                     }//En of if
                   else
                       isAuthentic = false;
                }//End of for
                
                if(!isAuthentic)
                    javax.swing.JOptionPane.showMessageDialog(null, "Enter Correct username/password!");
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
        }//End of login method   
    }// end of inner class
    
    
    private void addAdminPanel(){
        
        jTabbedPane.setFont(new Font("Times New Roman",Font.BOLD, 18));
        jTabbedPane.setForeground(new Color(0,153,153));
//        jTabbedPane.add("PRODUCT CATEGORY", new ProductCategoryFrame().getpanel());
//        jTabbedPane.add("PRODUCTS", new ProductFrame().getpanel());
        jTabbedPane.add("PRODUCTS DETAIL", new ProductDetailJFrame().getpanel());
        jTabbedPane.add("SALE BOOK", new SaleBookJFrame().getPanel());
        jTabbedPane.add("STOCK", new StokeJFrame().getPanel());
        jTabbedPane.add("REPORTS", new ReportsJFrame().getPanel());
        jTabbedPane.add("BARCODE GENERATOR", new BarcodeGeneratorJFrame().getPanel());
//        jTabbedPane.add("CUSTOMER", new CustomerFrame().getpanel());
//        jTabbedPane.add("EMPLOYE", new EmployeJFrame().getPanel());
        
     }
     
     private void addUserPanel(){
        
        jTabbedPane.setFont(new Font("Times New Roman",Font.BOLD, 18));
        jTabbedPane.setForeground(new Color(0,153,153));
        jTabbedPane.add("SALE BOOK", new SaleBookJFrame().getPanel());
        jTabbedPane.add("BARCODE GENERATOR", new BarcodeGeneratorJFrame().getPanel());
        
     }
 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BottomjPanel = new javax.swing.JPanel();
        developerLabel7 = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel = new javax.swing.JPanel();
        closeLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        minLabel = new javax.swing.JLabel();
        maxLable = new javax.swing.JLabel();
        menuLabel = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        logOutBtn = new javax.swing.JButton();
        AboutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        BottomjPanel.setBackground(new java.awt.Color(0, 153, 153));

        developerLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        developerLabel7.setForeground(new java.awt.Color(240, 240, 240));
        developerLabel7.setText("Developed by Sheeraz Ahmed");
        developerLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                developerLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BottomjPanelLayout = new javax.swing.GroupLayout(BottomjPanel);
        BottomjPanel.setLayout(BottomjPanelLayout);
        BottomjPanelLayout.setHorizontalGroup(
            BottomjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BottomjPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(developerLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        BottomjPanelLayout.setVerticalGroup(
            BottomjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BottomjPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(developerLabel7)
                .addContainerGap())
        );

        jTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane.setToolTipText("");
        jTabbedPane.setAlignmentX(0.0F);
        jTabbedPane.setAlignmentY(0.0F);
        jTabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel.setBackground(new java.awt.Color(0, 153, 153));
        jPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelMouseDragged(evt);
            }
        });
        jPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelMouseReleased(evt);
            }
        });

        closeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        closeLabel.setForeground(new java.awt.Color(240, 240, 240));
        closeLabel.setText("X");
        closeLabel.setToolTipText("CLOSE PROGRAM");
        closeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
        });

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(240, 240, 240));
        nameLabel.setText("GHOUSIA SHOPPING CENTER");

        minLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        minLabel.setForeground(new java.awt.Color(240, 240, 240));
        minLabel.setText("-");
        minLabel.setToolTipText("MINIMIIZE PROGRAM");
        minLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minLabelMouseClicked(evt);
            }
        });

        maxLable.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        maxLable.setForeground(new java.awt.Color(240, 240, 240));
        maxLable.setToolTipText("MAXIMIZE PROGRAM");
        maxLable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        maxLable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maxLableMouseClicked(evt);
            }
        });

        menuLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        menuLabel.setForeground(new java.awt.Color(240, 240, 240));
        menuLabel.setToolTipText("MINIMIIZE PROGRAM");
        menuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuLabelMouseExited(evt);
            }
        });

        menuPanel.setBackground(new java.awt.Color(0, 153, 153));
        menuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanelMouseExited(evt);
            }
        });

        logOutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logOutBtn.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        logOutBtn.setForeground(new java.awt.Color(0, 153, 153));
        logOutBtn.setText("LOGOUT");
        logOutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutBtn.setFocusPainted(false);
        logOutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logOutBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logOutBtnMouseExited(evt);
            }
        });
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });

        AboutBtn.setBackground(new java.awt.Color(255, 255, 255));
        AboutBtn.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        AboutBtn.setForeground(new java.awt.Color(0, 153, 153));
        AboutBtn.setText("ABOUT");
        AboutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AboutBtn.setFocusPainted(false);
        AboutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AboutBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AboutBtnMouseExited(evt);
            }
        });
        AboutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logOutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
            .addComponent(AboutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AboutBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuLabel)
                .addGap(9, 9, 9)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1029, Short.MAX_VALUE)
                .addComponent(minLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maxLable)
                .addGap(14, 14, 14)
                .addComponent(closeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addGap(561, 561, 561)
                    .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(561, Short.MAX_VALUE)))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(minLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(maxLable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(closeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(menuLabel))
            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(BottomjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BottomjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    int x;
    int y;
    
    
    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeLabelMouseClicked
   
    private void jPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMousePressed
      x = evt.getX();
      y = evt.getY();
      setOpacity((float)0.8);
    }//GEN-LAST:event_jPanelMousePressed

    private void jPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMouseDragged
       int xx = evt.getXOnScreen();
       int yy = evt.getYOnScreen();
       this.setLocation(xx-x,yy-y);
    }//GEN-LAST:event_jPanelMouseDragged

    private void jPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMouseReleased
              setOpacity((float)1.0);
    }//GEN-LAST:event_jPanelMouseReleased

    private void maxLableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxLableMouseClicked
       if(maximized){
           RetailerShopManagmentMainJFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
           GraphicsEnvironment evn = GraphicsEnvironment.getLocalGraphicsEnvironment();
           RetailerShopManagmentMainJFrame.this.setMaximizedBounds(evn.getMaximumWindowBounds());
       }
       else{
           setExtendedState(JFrame.NORMAL);
           maximized = true;
       }
    }//GEN-LAST:event_maxLableMouseClicked

    private void minLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minLabelMouseClicked
        this.setState(JFrame.ICONIFIED);
        maximized = false;
    }//GEN-LAST:event_minLabelMouseClicked

    private void menuLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabelMouseEntered
         if(menuLabel.isEnabled()==true){
            menuPanel.setLocation(0, 35);
            menuPanel.setSize(235,145);
         }
         else{
            System.out.println("Denied...");
         }
    }//GEN-LAST:event_menuLabelMouseEntered

    private void menuLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabelMouseExited
        menuPanel.setSize(235,0);
    }//GEN-LAST:event_menuLabelMouseExited

    private void menuLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menuLabelMouseClicked

    private void logOutBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseEntered
        menuPanel.setLocation(0, 35);
        menuPanel.setSize(235,145);
        logOutBtn.setContentAreaFilled(true);
        logOutBtn.setForeground(new Color(240,240,240));
        logOutBtn.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_logOutBtnMouseEntered

    private void logOutBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseExited
        menuPanel.setSize(235,0);
        logOutBtn.setContentAreaFilled(true);
        logOutBtn.setForeground(new Color(0,153,153));
        logOutBtn.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_logOutBtnMouseExited

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
            menuLabel.setVisible(false);
            menuPanel.setSize(235, 0);
            jTabbedPane.removeAll();
            innerclass l = new innerclass();
            ImageIcon icon8 = new ImageIcon(new File("Images\\icons8-enter-100.png").getAbsolutePath());
            jTabbedPane.add(l.getpanel(),icon8);
    }//GEN-LAST:event_logOutBtnActionPerformed

    private void menuPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanelMouseEntered
        menuPanel.setLocation(0, 35);
        menuPanel.setSize(235,145);
    }//GEN-LAST:event_menuPanelMouseEntered

    private void menuPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanelMouseExited
      menuPanel.setSize(235,0);
    }//GEN-LAST:event_menuPanelMouseExited

    private void AboutBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AboutBtnMouseEntered
        menuPanel.setLocation(0, 35);
        menuPanel.setSize(235,145);
        AboutBtn.setContentAreaFilled(true);
        AboutBtn.setForeground(new Color(240,240,240));
        AboutBtn.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_AboutBtnMouseEntered

    private void AboutBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AboutBtnMouseExited
     
        menuPanel.setSize(235,0);
        AboutBtn.setContentAreaFilled(true);
        AboutBtn.setForeground(new Color(0,153,153));
        AboutBtn.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_AboutBtnMouseExited

    private void AboutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutBtnActionPerformed
        ImageIcon icon = new ImageIcon(new File("Images\\shopLogo.png").getAbsolutePath());
        JOptionPane.showConfirmDialog(null,"DEVELOPER : SHEERAZ AHMED\nCOMPANY : BELL LAB`S\nSOFTWARE : STORE MANAGMENT SYSTEM\nDATE : 2021-06-08", "INFORMATION",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,icon);
     
    }//GEN-LAST:event_AboutBtnActionPerformed

    private void developerLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_developerLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_developerLabel7MouseClicked

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
//            // select the Look and Feel
//            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 18)));
            
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RetailerShopManagmentMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RetailerShopManagmentMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RetailerShopManagmentMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RetailerShopManagmentMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RetailerShopManagmentMainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AboutBtn;
    private javax.swing.JPanel BottomjPanel;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JLabel developerLabel7;
    private javax.swing.JPanel jPanel;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JLabel maxLable;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel minLabel;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
