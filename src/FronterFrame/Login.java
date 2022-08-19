/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FronterFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import retailershopmanagmentsystem.EncoderAndDecoder;

/**
 *
 * @author Maham Computers
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        jPanel.setFocusable(true);
        passwordTxt.setEchoChar((char)0);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        
        jLabel2.setIcon(new ImageIcon(new File("Images\\loginDesign.png").getAbsolutePath()));
        jLabel1.setIcon(new ImageIcon(new File("Images\\icons8-male-user-96.png").getAbsolutePath()));
        jLabel3.setIcon(new ImageIcon(new File("Images\\icons8-user-30.png").getAbsolutePath()));
        jLabel6.setIcon(new ImageIcon(new File("Images\\icons8-security-shield-green-30.png").getAbsolutePath()));
        showBtn.setIcon(new ImageIcon(new File("Images\\icons8-eye-30.png").getAbsolutePath()));
        loginBtn.setIcon(new ImageIcon(new File("Images\\icons8-import-48.png").getAbsolutePath()));
    }
    
    public javax.swing.JPanel getpanel(){
        return jPanel;
    }
    
    public static String getUserName(){
        return userTxt.getText();
    }
    public static String getPassword(){
	return passwordTxt.getText();
    }
    
    public void Cheak(){
        if(userTxt.getText().length()==0 || userTxt.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Insert User Name plese!");
        }
        else if(passwordTxt.getText().length()==0 || passwordTxt.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Insert Password plese!");
        }
    }  
    
    
    

    
    protected void login(){}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        userTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passwordTxt = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        showBtn = new javax.swing.JButton();
        loginBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setLayout(null);

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jPanel.add(jLabel1);
        jLabel1.setBounds(730, 110, 100, 100);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userTxt.setFont(new java.awt.Font("Papyrus", 1, 14)); // NOI18N
        userTxt.setText("User Name");
        userTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        userTxt.setOpaque(false);
        userTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userTxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                userTxtFocusLost(evt);
            }
        });
        userTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userTxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userTxtKeyReleased(evt);
            }
        });
        jPanel1.add(userTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 30, 243, 33));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 33));

        passwordTxt.setFont(new java.awt.Font("Papyrus", 1, 14)); // NOI18N
        passwordTxt.setText("Password");
        passwordTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        passwordTxt.setOpaque(false);
        passwordTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordTxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordTxtFocusLost(evt);
            }
        });
        passwordTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordTxtKeyTyped(evt);
            }
        });
        jPanel1.add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 81, 247, 33));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, -1, 33));

        showBtn.setBorderPainted(false);
        showBtn.setContentAreaFilled(false);
        showBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showBtn.setFocusPainted(false);
        showBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showBtnMouseExited(evt);
            }
        });
        showBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBtnActionPerformed(evt);
            }
        });
        jPanel1.add(showBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 91, 43, 23));

        loginBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginBtn.setText("Login");
        loginBtn.setToolTipText("Login");
        loginBtn.setBorderPainted(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.setFocusPainted(false);
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtnMouseExited(evt);
            }
        });
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 120, 133, -1));

        jPanel.add(jPanel1);
        jPanel1.setBounds(620, 200, 340, 0);
        jPanel.add(jLabel2);
        jLabel2.setBounds(0, 0, 1280, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1212, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    int k;
    private void userTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userTxtFocusGained
        if(userTxt.getText().equals("User Name")){
            userTxt.setText("");
        }
    }//GEN-LAST:event_userTxtFocusGained

    private void userTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userTxtFocusLost
        if(userTxt.getText().equals("")){
            userTxt.setText("User Name");
        }
    }//GEN-LAST:event_userTxtFocusLost

    private void userTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTxtKeyPressed
       int length = userTxt.getText().length();
        if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
              if(length < 13){
                userTxt.setEditable(true);
              }
              else{
                  userTxt.setEditable(false);
              }       
        }
        else{
              if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
                  userTxt.setEditable(true);
              }
              else{
                  userTxt.setEditable(false);
              }       
        }
    }//GEN-LAST:event_userTxtKeyPressed

    private void passwordTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTxtKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
               Cheak();
                login();
           }
    }//GEN-LAST:event_passwordTxtKeyPressed

    private void passwordTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTxtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTxtKeyTyped

    private void showBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBtnActionPerformed
        if(k==0){
            passwordTxt.setEchoChar((char)0);
            k=1;
        }
        else{
            passwordTxt.setEchoChar('*');
            k=0;
        }
    }//GEN-LAST:event_showBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        Cheak();
        login();
    }//GEN-LAST:event_loginBtnActionPerformed

    private void passwordTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTxtFocusGained
     if(passwordTxt.getText().equals("Password")){
         passwordTxt.setText("");
         passwordTxt.setEchoChar('*');
     }
    }//GEN-LAST:event_passwordTxtFocusGained

    private void passwordTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTxtFocusLost
      if(passwordTxt.getText().equals("")){
         passwordTxt.setText("Password");
         passwordTxt.setEchoChar((char)0);
     }
    }//GEN-LAST:event_passwordTxtFocusLost

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jPanel1.setSize(620,200);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
      // jPanel1.setSize(0,0);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
     //  jPanel1.setSize(620,200);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
     // jPanel1.setSize(0,0);
    }//GEN-LAST:event_jPanel1MouseExited

    private void loginBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseEntered
        loginBtn.setContentAreaFilled(true);
    }//GEN-LAST:event_loginBtnMouseEntered

    private void loginBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseExited
        loginBtn.setContentAreaFilled(false);
    }//GEN-LAST:event_loginBtnMouseExited

    private void showBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showBtnMouseEntered
        showBtn.setContentAreaFilled(true);
    }//GEN-LAST:event_showBtnMouseEntered

    private void showBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showBtnMouseExited
        showBtn.setContentAreaFilled(false);
    }//GEN-LAST:event_showBtnMouseExited

    private void userTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTxtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_userTxtKeyReleased

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    public static javax.swing.JPasswordField passwordTxt;
    private javax.swing.JButton showBtn;
    protected static javax.swing.JTextField userTxt;
    // End of variables declaration//GEN-END:variables
}