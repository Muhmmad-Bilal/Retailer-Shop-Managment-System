/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailershopmanagmentsystem;

import Beans.CustomerBean;
import Beans.ProductCategoryBean;
import databaseManager.DatabaseManager;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Maham Computers
 */
public class CustomerFrame extends javax.swing.JFrame {

    public boolean isValidate = false;
    private static String msg;
    
    
    public CustomerFrame() {
        initComponents();
        getCustomer();
    }
    public javax.swing.JPanel getpanel(){
        return jPanel;
    }
    private void getCustomer(){
        
        try{
            Vector v = DatabaseManager.getCustomer();
            customerNameList.setListData(v);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error: " + e);
        }
    }//end of getCustomer()
    
    private void deleteCustomer(){
     try{
         int size[] = customerNameList.getSelectedIndices();
         customerNameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         int rows=0;
         for(int i=0; i<size.length; i++){
             CustomerBean bean = (CustomerBean)customerNameList.getModel().getElementAt(size[i]);
             if (bean == null ) continue;
             rows += DatabaseManager.deleteCustomer(bean.getCustomerId());
         }//end of for
            if(rows >= 1){
                JOptionPane.showMessageDialog(this,rows+" Recorde removed..");
                getCustomer();
                clearText();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of deleteCustomer()
    
    private void updateCustomer(){
//        if(isValidate){
            String customerName = customerNameTextField.getText();
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String country = countryTextField.getText();
            String zipCodeStr = zipCodeTextField.getText();
            String email = emailTextField.getText().length()<=0?"":emailTextField.getText();
            String contactNo = contactNoTextField.getText();
            String remarks = remarkTextArea.getText().length()<=0?"":remarkTextArea.getText();

            if(customerName.trim().length()> 0 && address.trim().length()> 0 &&
               city.trim().length()> 0         && country.trim().length()> 0 &&
               zipCodeStr.trim().length()> 0   && contactNo.trim().length()> 0){

                int zipCode = Integer.parseInt(zipCodeTextField.getText());

                CustomerBean bean = (CustomerBean)customerNameList.getSelectedValue();
                if(bean == null ) return;

                try{
                    int rows = DatabaseManager.updateCustomer(bean.getCustomerId(),customerName,address,city,country,zipCode,email,contactNo, remarks);
                    if(rows >= 1){
                        JOptionPane.showMessageDialog(this, rows + " Recorde Updated..");
                        getCustomer();
                        clearText();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,"Error : "+ e);
                }
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this,"Fields Can`t be Empty please fill out.");
            }
//        }else{
//            Toolkit.getDefaultToolkit().beep();
//            JOptionPane.showMessageDialog(this,msg);
//        }
    }//end of updateCustomer()
    
    private void addCustomer(){
//        if(isValidate){
            String customerName = customerNameTextField.getText();
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String country = countryTextField.getText();
            String zipCodeStr = zipCodeTextField.getText();
            String email = emailTextField.getText().length()<=0?"":emailTextField.getText();
            String contactNo = contactNoTextField.getText();
            String remarks = remarkTextArea.getText().length()<=0?"":remarkTextArea.getText();

            if(customerName.trim().length()>=1 && address.trim().length()>=1 &&
               city.trim().length()>=1         && country.trim().length()>=1 &&
               zipCodeStr.trim().length()>=1   && contactNo.trim().length()>=1){

                int zipCode = Integer.parseInt(zipCodeTextField.getText());
                try{
                    int rows = DatabaseManager.addCustomer(customerName,address,city,country,zipCode,email,contactNo, remarks);
                    if(rows >= 1){
                        JOptionPane.showMessageDialog(this, rows + "Recorde Addded..");
                        getCustomer();
                        clearText();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,"Error : "+ e);
                }
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this,"Fields Can`t be Empty please fill out.");
            }
//        }else{
//            Toolkit.getDefaultToolkit().beep();
//            JOptionPane.showMessageDialog(this,msg);
//        }
    }//end of addCustomer()
    
    private void clearText(){
        customerIdTextField.setText("");
        customerNameTextField.setText("");
        addressTextField.setText("");
        cityTextField.setText("");
        countryTextField.setText("");
        zipCodeTextField.setText("");
        emailTextField.setText("");
        contactNoTextField.setText("");
        remarkTextArea.setText("");
    }//end of clearText()
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        customerNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarkTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        customerNameList = new javax.swing.JList();
        customerIdTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        countryTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        zipCodeTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        contactNoTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("CUSTOMER");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CUSTOMER ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("CUSTOMER NAME");

        customerNameTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("REMARKS");

        remarkTextArea.setColumns(20);
        remarkTextArea.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        remarkTextArea.setRows(5);
        jScrollPane1.setViewportView(remarkTextArea);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("CUSTOMER NAME");

        addBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        updateBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        clearBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        backBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backBtn.setText("BACK");

        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        customerNameList.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        customerNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                customerNameListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(customerNameList);

        customerIdTextField.setEditable(false);
        customerIdTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("ADDRESS");

        addressTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("CITY");

        cityTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("COUNTRY");

        countryTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("ZIP CODE");

        zipCodeTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("EMAIL");

        emailTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("CONTACT NO");

        contactNoTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        contactNoTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                contactNoTextFieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(jLabel1))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel2)
                .addGap(55, 55, 55)
                .addComponent(customerIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addComponent(customerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jLabel5))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(updateBtn)
                .addGap(31, 31, 31)
                .addComponent(clearBtn)
                .addGap(41, 41, 41)
                .addComponent(backBtn)
                .addGap(197, 197, 197)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(zipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(contactNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addBtn)
                    .addComponent(updateBtn)
                    .addComponent(clearBtn)
                    .addComponent(backBtn)
                    .addComponent(deleteBtn)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        addCustomer();
    }//GEN-LAST:event_addBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if(customerNameList.getSelectedIndex()>=0)
            updateCustomer();
        else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,"Plaese Customer from list.");
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
          clearText();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if(customerNameList.getSelectedIndex()>=0){
            int permission = JOptionPane.showConfirmDialog(this,"Are you want to DELETE Recorde!!","CONFIRAMATION", JOptionPane.YES_NO_OPTION);
            if(permission == 0){
                 deleteCustomer();
            }
            else{
                System.out.println("Permission Denied...");
            }
        }else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,"Plaese Customer from list.");
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void customerNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_customerNameListValueChanged
                CustomerBean bean = (CustomerBean)customerNameList.getSelectedValue();
                if(bean == null) return;
                customerIdTextField.setText(""+bean.getCustomerId());
                customerNameTextField.setText(""+bean.getCustomerName());
                addressTextField.setText(""+bean.getAddress());
                cityTextField.setText(""+bean.getCity());
                countryTextField.setText(""+bean.getCountry());
                zipCodeTextField.setText(""+bean.getZipCode());
                emailTextField.setText(""+bean.getEmail());
                contactNoTextField.setText(""+bean.getContactNo());
                remarkTextArea.setText(""+bean.getRemarks());
    }//GEN-LAST:event_customerNameListValueChanged

    private void contactNoTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactNoTextFieldFocusLost
        String text = contactNoTextField.getText();
        try {
          int temp = Integer.parseInt(text); 
          isValidate = true;
        } catch (NumberFormatException ee) {
           isValidate = false;
           Toolkit.getDefaultToolkit().beep();
           msg = "Contact No Must be Number/numeric!";
           javax.swing.JOptionPane.showMessageDialog(null,msg );
        }
    }//GEN-LAST:event_contactNoTextFieldFocusLost

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
            java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField contactNoTextField;
    private javax.swing.JTextField countryTextField;
    private javax.swing.JTextField customerIdTextField;
    private javax.swing.JList customerNameList;
    private javax.swing.JTextField customerNameTextField;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea remarkTextArea;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTextField zipCodeTextField;
    // End of variables declaration//GEN-END:variables
}
