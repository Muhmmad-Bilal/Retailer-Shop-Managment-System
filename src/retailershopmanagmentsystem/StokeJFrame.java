package retailershopmanagmentsystem;

import Beans.BillBean;
import Beans.CustomerBean;
import Beans.ProductBean;
import Beans.ProductCategoryBean;
import Beans.TransactionBean;
import Recipte.ReciptPrint;
import databaseManager.DatabaseManager;
import static databaseManager.DatabaseManager.getProductCategory;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class StokeJFrame extends javax.swing.JFrame{

   boolean isAllowToWork = true;
   int counter = 1;
   
   
    public StokeJFrame() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        getProductCategory();
        getCustomer();
        
        searchTextField.requestFocusInWindow();
        
//        new Thread(new Runnable(){
//            public void run(){
//                while(true){
//                    getProductCategory();
//                    System.out.println("getProductCategory Working");
//                    try{Thread.sleep(2000);}catch(Exception e){}
//                }
//            }
//        }).start();
        
    }
    
    
    public javax.swing.JPanel getPanel(){
        return jPanel;
    }
    
    public static void getProductCategory(){
       try{
           Vector v = DatabaseManager.getProductCategory();
           if(v.size() == 0)return;
           prodCatComboBox.removeAllItems();
           for(int i=0; i<v.size(); i++)
                prodCatComboBox.addItem(v.elementAt(i));
       }catch(Exception e){
           e.printStackTrace();
       }
   }//end of getProductCategory
    
   private void getCustomer(){
        try{
            Vector v = DatabaseManager.getCustomer();
            if(v.size() == 0)return;
            for(int i=0; i<v.size(); i++)
            customerComboBox.addItem(v.elementAt(i));
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error: " + e);
        }
    }//end of getCustomer()
   
    private void getProduct(){
        try{
            isAllowToWork = false;
            DefaultTableModel model = (DefaultTableModel)jTable.getModel();
            model.setRowCount(0);
            counter = 1;
            isAllowToWork = true;
            ProductCategoryBean bean =(ProductCategoryBean)prodCatComboBox.getSelectedItem();
            if(bean == null ) return;
            
            Vector v = DatabaseManager.getProduct(bean.getProdCatIdd());
            if(v.size() == 0)return;

            for(int i=0; i<v.size(); i++){
                ProductBean pBean = (ProductBean)v.elementAt(i);

                model.addRow(new Object[]{
                    counter++,
                    pBean.getProdId(),
                    pBean.getProdName(),
                    pBean.getBarcode(),
                    pBean.getPrice(),
                    pBean.getQuantity(),
                    pBean.getTotal()
                });
            }//End of for
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of getProduct
    
    
    
    
    
    private String takeDataFromTable() throws Exception{
  
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        Vector data = model.getDataVector();
        String temp="";
        String rows ="";
        for(int i=0; i<data.size(); i++){
            temp+=data.elementAt(i);
            
            String brick1 = temp.replace("[","");
            rows = brick1.replace("]","\n");
        }
        return rows;
    }//End of takeDataFromTable
    
    private void getBarcode(){
       ProductCategoryBean bean =(ProductCategoryBean)prodCatComboBox.getSelectedItem();
       if(bean == null ) return;
       try{
           Vector v = DatabaseManager.getProduct(bean.getProdCatIdd());
           String barcode[] = new String[v.size()];
            for(int i=0; i<v.size(); i++){
                ProductBean pBean = (ProductBean)v.elementAt(i);
                barcode[i] += pBean.getBarcode().concat(",")+pBean.getProdName().concat(",")+pBean.getPrice();
            }//End of for
            
            new ProductBarcodeListPrint(barcode);
            JOptionPane.showMessageDialog(null, "Barcode List Printed..");

        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of getBarcode
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        prodCatComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        customerComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        csvJButton = new javax.swing.JButton();
        pdfJButton = new javax.swing.JButton();
        printJButton = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel.setPreferredSize(new java.awt.Dimension(1293, 582));
        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" STOCK LIST");
        jPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 21, 1296, 34));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("PRODUCT CATEGORY");
        jPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(649, 89, 159, 10));

        prodCatComboBox.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        prodCatComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodCatComboBoxActionPerformed(evt);
            }
        });
        jPanel.add(prodCatComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(649, 105, 213, 30));

        jTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.NO", "PROD ID", "PRODUCT NAME", "BARCODE", "UNIT PRICE", "QUANTITY", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setRowHeight(20);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMousePressed(evt);
            }
        });
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 1143, 447));

        customerComboBox.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel.add(customerComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 106, 229, 28));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("CUSTOMER");
        jPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 89, -1, 10));

        csvJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        csvJButton.setText("CSV");
        csvJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvJButtonActionPerformed(evt);
            }
        });
        jPanel.add(csvJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(907, 105, 152, 30));

        pdfJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pdfJButton.setText("PDF");
        pdfJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfJButtonActionPerformed(evt);
            }
        });
        jPanel.add(pdfJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1077, 105, 154, 30));

        printJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printJButton.setText("PRINT BARCODE");
        printJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJButtonActionPerformed(evt);
            }
        });
        jPanel.add(printJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(907, 66, 324, 33));

        searchTextField.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });
        jPanel.add(searchTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 106, 264, 29));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("SEARCH BARCODE/PRODUCT NAME");
        jPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 260, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1296, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prodCatComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodCatComboBoxActionPerformed
        getProduct();
    }//GEN-LAST:event_prodCatComboBoxActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_S){
            
        }
    }//GEN-LAST:event_formKeyPressed
    
    private void jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyPressed
        formKeyPressed(evt);
    }//GEN-LAST:event_jTableKeyPressed

    private void csvJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvJButtonActionPerformed
        FileDialog dialog=new FileDialog(new JFrame(),"SAVE",FileDialog.SAVE);
        dialog.setVisible(true);
        String extention="";
        if(dialog.getFile() == null || dialog.getDirectory() == null)return;
        if(!dialog.getFile().endsWith(".csv")) extention=dialog.getFile().concat(".csv");
	
        File f = new File(dialog.getDirectory(),extention);
        try {
            FileWriter fileWriter = new FileWriter(f);
            String Tital= "S.NO "+","+"PRODUCT ID "+","+"PRODUCT NAME "+","+"BARCODE"+","+"UNIT PRICE"+","+"QUANTITY"+","+"TOTAL AMOUNT\n";
            fileWriter.write(Tital);
            fileWriter.write(takeDataFromTable());
            fileWriter.close();
           
            ImageIcon icon1  = new ImageIcon(new File("Images\\shopLogo-96.png").getAbsolutePath());
            int responce = JOptionPane.showConfirmDialog(null,"File Save Successfully\nYou want to open it", "INFORMATION",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,icon1);
            if(responce == 0){
                
                File file = new File(f.getAbsolutePath());
                Desktop desktop = Desktop.getDesktop();  
                if(file.exists())   
                    desktop.open(file); 
                
            }//End of if
            else System.out.println("Denied..");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_csvJButtonActionPerformed

    private void pdfJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfJButtonActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            String data[] = new String[model.getRowCount()];
            for(int i = 0; i < model.getRowCount(); i++){
                for(int j = 0; j < model.getColumnCount(); j++){
                    data[i] += (model.getValueAt(i, j).toString()).concat(",");
                }
            }
            
            new StokeListPrint(data);
            JOptionPane.showMessageDialog(null,"List Printed");
            } catch(Exception e){
                e.printStackTrace();
            }
    }//GEN-LAST:event_pdfJButtonActionPerformed

    private void jTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMousePressed
        
            
    }//GEN-LAST:event_jTableMousePressed

    private void printJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printJButtonActionPerformed
        getBarcode();
    }//GEN-LAST:event_printJButtonActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
       
        try{
            
                  
            if(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9'){
                Vector v = DatabaseManager.getProductByBarcode(searchTextField.getText());
                if(v.size() == 0)return;
                
                isAllowToWork = false;
                DefaultTableModel model = (DefaultTableModel)jTable.getModel();
                model.setRowCount(0);
                counter = 1;
                isAllowToWork = true;

                for(int i=0; i<v.size(); i++){
                    ProductBean pBean = (ProductBean)v.elementAt(i);

                    model.addRow(new Object[]{
                        counter++,
                        pBean.getProdId(),
                        pBean.getProdName(),
                        pBean.getBarcode(),
                        pBean.getPrice(),
                        pBean.getQuantity(),
                        pBean.getTotal()
                    });
                }//End of for
                searchTextField.setText("");
                
            }
            else{
                Vector v = DatabaseManager.getProductByName(searchTextField.getText());
                if(v.size() == 0)return;
                isAllowToWork = false;
                DefaultTableModel model = (DefaultTableModel)jTable.getModel();
                model.setRowCount(0);
                counter = 1;
                isAllowToWork = true;

                for(int i=0; i<v.size(); i++){
                    ProductBean pBean = (ProductBean)v.elementAt(i);

                    model.addRow(new Object[]{
                        counter++,
                        pBean.getProdId(),
                        pBean.getProdName(),
                        pBean.getBarcode(),
                        pBean.getPrice(),
                        pBean.getQuantity(),
                        pBean.getTotal()
                    });
                }//End of for
                searchTextField.setText("");
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchTextFieldKeyReleased

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        //        ProductCategoryBean pBean = (ProductCategoryBean)prodCatComboBox.getSelectedItem();
        //        if(pBean == null) return;
        //        try{
            //            if(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9'){
                //                Vector v = DatabaseManager.getProductByBarcode(barcodeTextField.getText());
                //                if(v.size() == 0)return;
                //                prodNameList.setListData(v);
                //                JOptionPane.showMessageDialog(null, "barcode");
                //            }
            //            else{
                //                Vector v = DatabaseManager.getProductByName(pBean.getProdCatIdd(), barcodeTextField.getText());
                //                if(v.size() == 0)return;
                //                prodNameList.setListData(v);
                //                JOptionPane.showMessageDialog(null, "product Name");
                //            }
            //        }catch(Exception e){
            //            e.printStackTrace();
            //        }
    }//GEN-LAST:event_searchTextFieldKeyTyped

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
            java.util.logging.Logger.getLogger(StokeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StokeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StokeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StokeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StokeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton csvJButton;
    private static javax.swing.JComboBox customerComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JButton pdfJButton;
    private javax.swing.JButton printJButton;
    private static javax.swing.JComboBox prodCatComboBox;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables

   
    
    
    
    
}//End of Main Class
