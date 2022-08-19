/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailershopmanagmentsystem;

import Beans.ProductCategoryBean;
import databaseManager.DatabaseManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Maham Computers
 */
public class ProductCategoryFrame extends javax.swing.JFrame {

    /**
     * Creates new form ProductCategoryFrame
     */
    public ProductCategoryFrame() {
        initComponents();
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize= tk.getScreenSize();
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        jPanel.setBounds(0, 0, screenSize.width, screenSize.height);
        
        getProductCategory();
        
    }
    
    public javax.swing.JPanel getpanel(){
        return jPanel;
    }
    
   private void getProductCategory(){
       try{
           Vector v = DatabaseManager.getProductCategory();
           prodCatList.setListData(v);
       }catch(Exception e){
           e.printStackTrace();
           JOptionPane.showMessageDialog(this,"Error : " + e);
       }
   }//end of getProductCategory()
   
   private void deleteProductCategory(){
     try{
         int size[] = prodCatList.getSelectedIndices();
         prodCatList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         int rows=0;
         for(int i=0; i<size.length; i++){
             ProductCategoryBean bean = (ProductCategoryBean)prodCatList.getModel().getElementAt(size[i]);
             if (bean == null ) continue;
             rows += DatabaseManager.deleteProductCategory(bean.getProdCatIdd());
         }//end of for
            if(rows >= 1){
                JOptionPane.showMessageDialog(this,rows+" Recorde removed..");
                getProductCategory();
                clearText();
                
                ProductFrame.getProductCategory();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of deleteProductCategory()
    
    private void updateProductCategory(){
        
        String prodName = prodCatNameTextField.getText();
        String remarks = remarkTextArea.getText().length()<=0?"":remarkTextArea.getText();
        
        if(prodName.trim().length()>= 1  ){
            ProductCategoryBean bean = (ProductCategoryBean)prodCatList.getSelectedValue();
            if(bean == null ) return;
            try{
                int rows = DatabaseManager.updateProductCategory(bean.getProdCatIdd(), prodName, remarks);
                if(rows >= 1){
                    JOptionPane.showMessageDialog(this, rows + "Recorde Updated..");
                    getProductCategory();
                    clearText();
                    
                    ProductFrame.getProductCategory();
                    
                }
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"Error : "+ e);
            }
            
        }else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,"Fields Can`t be Empty please fill out.");
        }
    }//end of updateProductCategory()
    
    private void addProductCategory(){
        
        String prodName = prodCatNameTextField.getText();
        String remarks = remarkTextArea.getText().length()<=0?"":remarkTextArea.getText();
        if(prodName.trim().length()>= 1  ){
            try{
                int rows = DatabaseManager.addProductCategory(prodName, remarks);
                if(rows >= 1){
                    JOptionPane.showMessageDialog(this, rows + "Recorde Addded..");
                    getProductCategory();
                    clearText();
                    
                    ProductFrame.getProductCategory();
                    
                }
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"Error : "+ e);
            }
        }else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,"Fields Can`t be Empty please fill out.");
        }
            
    }//end of addProductCategory()
   
   private void clearText(){
       prodCatIdTextField.setText("");
        prodCatNameTextField.setText("");
        remarkTextArea.setText("");
   }//end of clearText()
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        prodCatIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        prodCatNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarkTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        prodCatList = new javax.swing.JList();
        deleteBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("PRODUCT CATEGORY");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("PRODUCT CATEGORY  ID");

        prodCatIdTextField.setEditable(false);
        prodCatIdTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("PRODUCT CATEGORY NAME");

        prodCatNameTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        prodCatNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                prodCatNameTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prodCatNameTextFieldKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("REMARKS");

        remarkTextArea.setColumns(20);
        remarkTextArea.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        remarkTextArea.setRows(5);
        jScrollPane1.setViewportView(remarkTextArea);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("PRODUCT CATEGORY NAME");

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

        prodCatList.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        prodCatList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                prodCatListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(prodCatList);

        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(470, 470, 470)
                .addComponent(jLabel1))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prodCatNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(prodCatIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(327, 327, 327)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prodCatIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(prodCatNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addBtn)
                    .addComponent(updateBtn)
                    .addComponent(clearBtn)
                    .addComponent(deleteBtn)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        addProductCategory();
    }//GEN-LAST:event_addBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        updateProductCategory();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearText();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void prodCatListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_prodCatListValueChanged
        ProductCategoryBean bean = (ProductCategoryBean)prodCatList.getSelectedValue();
        if(bean == null) return;
        prodCatIdTextField.setText(""+bean.getProdCatIdd());
        prodCatNameTextField.setText(""+bean.getProdCatName());
        remarkTextArea.setText(""+bean.getRemarks());
    }//GEN-LAST:event_prodCatListValueChanged

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
     
        if(prodCatList.getSelectedIndex() <= 0){
             Toolkit.getDefaultToolkit().beep();
             javax.swing.JOptionPane.showMessageDialog(null, "Please select Product first!");
        }
        else{
             Toolkit.getDefaultToolkit().beep();
            int permission = JOptionPane.showConfirmDialog(this,"Are you want to DELETE Recorde!!","CONFIRAMATION", JOptionPane.YES_NO_OPTION);
            if(permission == 0){
                deleteProductCategory();
            }
            else{
                System.out.println("Permission Denied...");
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void prodCatNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prodCatNameTextFieldKeyReleased
        if(!prodCatNameTextField.getText().trim().equals("")){
            prodCatNameTextField.setBackground(java.awt.Color.white);
            prodCatNameTextField.removeAll();
        }
        else{
          prodCatNameTextField.removeAll();
          setImage(new File("Images//alert.png").getAbsolutePath(), prodCatNameTextField);
          Toolkit.getDefaultToolkit().beep();
          prodCatNameTextField.setText("\r");
        }
    }//GEN-LAST:event_prodCatNameTextFieldKeyReleased

    private void prodCatNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prodCatNameTextFieldKeyTyped
        char ch=evt.getKeyChar();
//        if(!Character.isDigit(ch) && ch!=java.awt.event.KeyEvent.VK_LEFT && ch!=java.awt.event.KeyEvent.VK_RIGHT 
//          && ch!=java.awt.event.KeyEvent.VK_BACK_SPACE && ch!=java.awt.event.KeyEvent.VK_DELETE 
//          && (ch!=java.awt.event.KeyEvent.VK_CONTROL && ch!=java.awt.event.KeyEvent.VK_A || ch!=java.awt.event.KeyEvent.VK_C 
//                || ch!=java.awt.event.KeyEvent.VK_V || ch!=java.awt.event.KeyEvent.VK_X) && (ch!='.'))
//          evt.consume();
//        else
          if(prodCatNameTextField.getText().indexOf(".")!=-1 && ch=='.'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
          }
    }//GEN-LAST:event_prodCatNameTextFieldKeyTyped

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
            java.util.logging.Logger.getLogger(ProductCategoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductCategoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductCategoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductCategoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductCategoryFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField prodCatIdTextField;
    private javax.swing.JList prodCatList;
    private javax.swing.JTextField prodCatNameTextField;
    private javax.swing.JTextArea remarkTextArea;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

    
    public static void setImage(String path,javax.swing.JComponent field){
        java.awt.Image img=new javax.swing.ImageIcon(path).getImage().getScaledInstance(field.getHeight()-10,field.getHeight()-10, java.awt.Image.SCALE_SMOOTH);
        javax.swing.JLabel alertLabel = new javax.swing.JLabel(new javax.swing.ImageIcon(img));
        field.setLayout(new java.awt.BorderLayout());
        field.setBackground(new java.awt.Color(247, 141, 141));
        field.add(alertLabel, java.awt.BorderLayout.EAST);
    }//End of setImage method

}
