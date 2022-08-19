/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailershopmanagmentsystem;

import Beans.ProductBean;
import Beans.ProductCategoryBean;
import databaseManager.DatabaseManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maham Computers
 */
public class BarcodeGeneratorJFrame extends javax.swing.JFrame {

    DefaultListModel dm = new DefaultListModel();
    
    public BarcodeGeneratorJFrame() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        
        getProductCategory();
        for(int i=1; i<=100; i++){
            qtyPerCodeComboBox.addItem(i);
        }
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
    
    
    
    private void getProduct(){
       ProductCategoryBean bean =(ProductCategoryBean)prodCatComboBox.getSelectedItem();
       if(bean == null ) return;
       try{
           Vector v = DatabaseManager.getProduct(bean.getProdCatIdd());
           if(v.size() == 0)return;
            itemJList.setListData(v);
       }catch(Exception e){
           e.printStackTrace();
           JOptionPane.showMessageDialog(this,"Error : " + e);
       }
   }//end of getProduct
    
   private boolean getCategoryByBarcode(String tempProdName){
        boolean exits = false;
        for(int k=0; k<itemJList.getModel().getSize() && !exits; k++){
            if(String.valueOf(itemJList.getModel().getElementAt(k)).equals(tempProdName)){
                itemJList.setSelectedIndex(k);
                exits = true;
            }
        }
        if(!exits)
            return false;
        else 
            return true;
    }//getCategoryByBarcode()
   
   private void addItemToSelectedItem(){
        int size[] = itemJList.getSelectedIndices();
        itemJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         for(int i=0; i<size.length; i++){
             ProductBean bean = (ProductBean)itemJList.getModel().getElementAt(size[i]);
             if (bean == null ) continue;
             selectedBarcodeJList.setModel(dm);
             dm.addElement(bean.getProdName());
         }
         
         itemJList.clearSelection();

     }//end of addProductFromProductJListToItemJList
   
     private void clear(){
        prodCatComboBox.setSelectedIndex(0);
        qtyPerCodeComboBox.setSelectedIndex(0);
        
        DefaultListModel model = new DefaultListModel();
        selectedBarcodeJList.setModel(model);
            
        if(selectedBarcodeJList.getSelectedIndex()>0){
            int selectedIndex = selectedBarcodeJList.getSelectedIndex();
            if (selectedIndex != -1) {
                model.remove(selectedIndex);
            }
        }else{
            for(int index=0; index<model.getSize(); index++){
                model.removeElementAt(index);
            }
        }
        
        selectedBarcodeJList.clearSelection();
        
     }//End of clear
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        cancleItemMenu = new javax.swing.JMenuItem();
        jPanel = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        itemJList = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        codeGenerateJButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        prodCatComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        qtyPerCodeComboBox = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        selectedBarcodeJList = new javax.swing.JList();
        addToCodeListJButton = new javax.swing.JButton();
        clearJButton = new javax.swing.JButton();

        cancleItemMenu.setText("Cancel");
        cancleItemMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleItemMenuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(cancleItemMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setPreferredSize(new java.awt.Dimension(1293, 582));

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

        itemJList.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        itemJList.setVerifyInputWhenFocusTarget(false);
        itemJList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemJListMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemJListMouseReleased(evt);
            }
        });
        itemJList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                itemJListPropertyChange(evt);
            }
        });
        itemJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                itemJListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(itemJList);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("SEARCH BARCODE /PRODUCT NAME");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("BARCODE GENERATOR");

        codeGenerateJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        codeGenerateJButton.setText("GENERATE BARCODE");
        codeGenerateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeGenerateJButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("PRODUCT CATEGORY");

        prodCatComboBox.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        prodCatComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodCatComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("QUANTITY PER CODE");

        qtyPerCodeComboBox.setEditable(true);
        qtyPerCodeComboBox.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        qtyPerCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyPerCodeComboBoxActionPerformed(evt);
            }
        });

        selectedBarcodeJList.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        selectedBarcodeJList.setVerifyInputWhenFocusTarget(false);
        selectedBarcodeJList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                selectedBarcodeJListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                selectedBarcodeJListMouseReleased(evt);
            }
        });
        selectedBarcodeJList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectedBarcodeJListPropertyChange(evt);
            }
        });
        selectedBarcodeJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                selectedBarcodeJListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(selectedBarcodeJList);

        addToCodeListJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addToCodeListJButton.setText("ADD");
        addToCodeListJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCodeListJButtonActionPerformed(evt);
            }
        });

        clearJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        clearJButton.setText("CLEAR");
        clearJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(jLabel1))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(prodCatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codeGenerateJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                    .addComponent(qtyPerCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addToCodeListJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(clearJButton))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(prodCatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qtyPerCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addToCodeListJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codeGenerateJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
        if(evt.getKeyChar() >= '0'  && evt.getKeyChar() <= '9'){
            String barcode = searchTextField.getText();
//            if(barcode.length() >  8){
//                JOptionPane.showMessageDialog(this, "Please Enter only 8 digits barcode..");
//                searchTextField.setText("");
//                return;
//            }
           if(barcode.length() >=11){
                try{
                    ListModel model = selectedBarcodeJList.getModel();
                    Vector v = new Vector();
                    for(int i=0; i<model.getSize(); i++){
                        v.addElement(model.getElementAt(i).toString());
                    }
                    v = DatabaseManager.getProductByBarcode(barcode,v);
//                    v = DatabaseManager.getProductByBarcode(barcode);
                    if(v.size() == 0 )return;
                    selectedBarcodeJList.setListData(v);
//                     
                     
//                    String tempProdName = "";
//                    int prodCatId = 0;
//                    for(int j=0; j<v.size(); j++){
//                        ProductBean pBean = (ProductBean)v.elementAt(j);
//                        tempProdName = pBean.getProdName();
//                        prodCatId = pBean.getProdCatId();
//                    }
//                    
//                    if(getCategoryByBarcode(tempProdName) == false){
//                        ProductCategoryBean pcBean = DatabaseManager.getProductCategory(prodCatId);
//                        if(pcBean == null )return;
//                        String item = pcBean.getProdCatName();
//                        boolean exists = false;
//                        for (int index = 0; index < prodCatComboBox.getItemCount() && !exists; index++) {
//                            if (item.equals(String.valueOf(prodCatComboBox.getItemAt(index)))) {
//                                exists = true;
//                                prodCatComboBox.setSelectedIndex(index);
//                                getCategoryByBarcode(tempProdName);
//                            }
//                        }
//                        if (!exists) {
//                           getCategoryByBarcode(tempProdName);
//                        }
//                    }
                     
                     searchTextField.setText("");
                 }catch(Exception e){
                     e.printStackTrace();
                 }
            }
        }//cheack integer of

    }//GEN-LAST:event_searchTextFieldKeyReleased

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        ProductCategoryBean pBean = (ProductCategoryBean)prodCatComboBox.getSelectedItem();
        if(pBean == null) return;
        try{
            if(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9'){
                Vector v = DatabaseManager.getProduct(pBean.getProdCatIdd());
                if(v.size() == 0)return;
                itemJList.setListData(v);
            }
            else{
                Vector v = DatabaseManager.getProductByName(pBean.getProdCatIdd(),searchTextField.getText());
                if(v.size() == 0)return;
                itemJList.setListData(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchTextFieldKeyTyped

    private void itemJListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemJListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_itemJListMouseClicked

    private void itemJListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemJListMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_itemJListMouseReleased

    private void itemJListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_itemJListPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_itemJListPropertyChange

    private void itemJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_itemJListValueChanged
        if(!evt.getValueIsAdjusting()){
//            DefaultListModel model = new DefaultListModel();
//            selectedBarcodeJList.setModel(model);
//            model.addElement(itemJList.getSelectedValue());
        }
    }//GEN-LAST:event_itemJListValueChanged

    private void codeGenerateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeGenerateJButtonActionPerformed
        try{
            String tempData[] = new String[selectedBarcodeJList.getModel().getSize()];
            for(int k=0; k<selectedBarcodeJList.getModel().getSize(); k++){
                String item = String.valueOf(selectedBarcodeJList.getModel().getElementAt(k));
                 Vector v = DatabaseManager.getProductByName(item);
                 for(int j=0; j<v.size(); j++){
                     ProductBean bean = (ProductBean)v.elementAt(j);
                     if(bean == null)continue;
                     tempData[k] += bean.getBarcode().concat(",")+bean.getProdName().concat(",")+bean.getPrice();
                 }
            }
            int qtyPerBarcode = Integer.parseInt(String.valueOf(qtyPerCodeComboBox.getSelectedItem()));
            int totalSize = qtyPerBarcode*tempData.length;
            
            String data[] = new String[totalSize];
            
            int counter=0;
            for(int i=0; i<tempData.length; i++){
                for(int l=0; l<qtyPerBarcode; l++){
                    data[counter++]+= tempData[i];
                }
            }

            new ProductBarcodeListPrint(data);
            JOptionPane.showMessageDialog(null, "Barcode List Printed..");
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_codeGenerateJButtonActionPerformed

    private void prodCatComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodCatComboBoxActionPerformed
        getProduct();
    }//GEN-LAST:event_prodCatComboBoxActionPerformed

    private void qtyPerCodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyPerCodeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyPerCodeComboBoxActionPerformed

    private void selectedBarcodeJListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectedBarcodeJListPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedBarcodeJListPropertyChange

    private void selectedBarcodeJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_selectedBarcodeJListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedBarcodeJListValueChanged

    private void addToCodeListJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCodeListJButtonActionPerformed
       addItemToSelectedItem();
    }//GEN-LAST:event_addToCodeListJButtonActionPerformed

    private void clearJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearJButtonActionPerformed
    
    private void selectedBarcodeJListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectedBarcodeJListMousePressed
        if (evt.isPopupTrigger()) {
            selectedBarcodeJList.setSelectedIndex(selectedBarcodeJList.locationToIndex(evt.getPoint())); 
            jPopupMenu1.show(selectedBarcodeJList, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_selectedBarcodeJListMousePressed

    private void selectedBarcodeJListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectedBarcodeJListMouseReleased
        if (evt.isPopupTrigger()) {
            selectedBarcodeJList.setSelectedIndex(selectedBarcodeJList.locationToIndex(evt.getPoint())); 
            jPopupMenu1.show(selectedBarcodeJList, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_selectedBarcodeJListMouseReleased

    private void cancleItemMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleItemMenuActionPerformed
        DefaultListModel model = (DefaultListModel) selectedBarcodeJList.getModel();
        int selectedIndex = selectedBarcodeJList.getSelectedIndex();
        if (selectedIndex != -1) {
            model.remove(selectedIndex);
        }
    }//GEN-LAST:event_cancleItemMenuActionPerformed

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
            java.util.logging.Logger.getLogger(BarcodeGeneratorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarcodeGeneratorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarcodeGeneratorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarcodeGeneratorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarcodeGeneratorJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCodeListJButton;
    private javax.swing.JMenuItem cancleItemMenu;
    private javax.swing.JButton clearJButton;
    private javax.swing.JButton codeGenerateJButton;
    private javax.swing.JList itemJList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private static javax.swing.JComboBox prodCatComboBox;
    private javax.swing.JComboBox qtyPerCodeComboBox;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JList selectedBarcodeJList;
    // End of variables declaration//GEN-END:variables
}
