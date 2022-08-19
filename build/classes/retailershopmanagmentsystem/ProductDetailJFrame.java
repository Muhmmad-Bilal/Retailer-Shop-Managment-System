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
import java.io.File;
import java.time.LocalDate;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import static retailershopmanagmentsystem.ProductCategoryFrame.setImage;

/**
 *
 * @author Maham Computers
 */
public class ProductDetailJFrame extends javax.swing.JFrame {

    public boolean isValidate = false;
    private static String msg;
    
    public ProductDetailJFrame() {
        initComponents();
         Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize= tk.getScreenSize();
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        jPanel.setBounds(0, 0, screenSize.width, screenSize.height);
        
        getProductCategory();
        getProductCategoryForProduct();
        
        addProductCatJButton.setEnabled(false);
        updateProductCatJButton.setEnabled(false);
        deleteProductCatJButton.setEnabled(false);
        
        addBtn.setEnabled(false);
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        
    }

    public javax.swing.JPanel getpanel(){
        return jPanel;
    }
    
   ////////////////////////////--------------PRODUCT CATEGORY----------------//////////////////
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
                clearProductCatText();
                
                getProductCategoryForProduct();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of deleteProductCategory()
    
    private void updateProductCategory(){
        
        String prodName = prodCatNameTextField.getText();
        String remarks = remarkProductCatTextArea.getText().length()<=0?"":remarkProductCatTextArea.getText();
        
        if(prodName.trim().length()>= 1  ){
            ProductCategoryBean bean = (ProductCategoryBean)prodCatList.getSelectedValue();
            if(bean == null ) return;
            try{
                int rows = DatabaseManager.updateProductCategory(bean.getProdCatIdd(), prodName, remarks);
                if(rows >= 1){
                    JOptionPane.showMessageDialog(this, rows + "Recorde Updated..");
                    getProductCategory();
                    clearProductCatText();
                    getProductCategoryForProduct();
                    
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
        String remarks = remarkProductCatTextArea.getText().length()<=0?"":remarkProductCatTextArea.getText();
        if(prodName.trim().length()>= 1  ){
            try{
                int rows = DatabaseManager.addProductCategory(prodName, remarks);
                if(rows >= 1){
                    JOptionPane.showMessageDialog(this, rows + "Recorde Addded..");
                    getProductCategory();
                    clearProductCatText();
                    
                    getProductCategoryForProduct();
                    BarcodeGeneratorJFrame.getProductCategory();
                    
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
   
   private void clearProductCatText(){
       prodCatList.clearSelection();
        addProductCatJButton.setEnabled(false);
        updateProductCatJButton.setEnabled(false);
        deleteProductCatJButton.setEnabled(false);
        
        prodCatIdTextField.setText("");
        prodCatNameTextField.setText("");
        prodCatNameTextField.setBackground(java.awt.Color.white);
        prodCatNameTextField.removeAll();
        remarkProductCatTextArea.setText("");
   }//end of clearText()
   
   
   
    ////////////////////////////--------------PRODUCT ----------------//////////////////
   
   public  void getProductCategoryForProduct(){
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
           
           prodNameList.setListData(v);
       }catch(Exception e){
           e.printStackTrace();
           JOptionPane.showMessageDialog(this,"Error : " + e);
       }
   }//end of getProduct
   
   private void deleteProduct(){
     try{
         int size[] = prodNameList.getSelectedIndices();
         prodNameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         int rows=0;
         for(int i=0; i<size.length; i++){
             ProductBean bean = (ProductBean)prodNameList.getModel().getElementAt(size[i]);
             if (bean == null ) continue;
             rows += DatabaseManager.deleteProduct(bean.getProdId());
         }//end of for
            if(rows >= 1){
                JOptionPane.showMessageDialog(this,rows+" Recorde removed..");
                getProduct();
                clearText();
                
                StokeJFrame.getProductCategory();
                SaleBookJFrame.getProduct();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of deleteProduct()
    
//    private void updateProduct(){
//        ProductBean bean = (ProductBean)prodNameList.getSelectedValue();
//        if(bean == null ) return;
//        String prodName = prodNameTextField.getText();
//        int price  = Integer.parseInt(priceTextField.getText());
//        String barcode  = barcodeTextField.getText();
//        int qty = Integer.parseInt(quantityTextField.getText());
//        int total = Integer.parseInt(totalTextField.getText());
//        String remarks = remarkTextArea.getText();
//        try{
//            int rows = DatabaseManager.updateProduct(bean.getProdId(), prodName,barcode,price,qty,total,remarks);
//            if(rows >= 1){
//                JOptionPane.showMessageDialog(this, rows + " Recorde Updated..");
//                getProduct();
//                clearText();
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this,"Error : "+ e);
//        }
//    }//end of updateProduct()
//    
//    private void addProduct(){
//        ProductCategoryBean bean = (ProductCategoryBean)prodCatComboBox.getSelectedItem();
//        if(bean == null) return;
//        
//        String prodName = prodNameTextField.getText();
//        int price  = Integer.parseInt(priceTextField.getText());
//        String barcode  = barcodeTextField.getText();
//        int qty = Integer.parseInt(quantityTextField.getText());
//        int total = Integer.parseInt(totalTextField.getText());
//        String remarks = remarkTextArea.getText();
//        
//        try{
//            int rows = DatabaseManager.addProduct(bean.getProdCatIdd(),prodName,barcode,price,qty,total,remarks);
//            
//            if(rows >= 1){
//
//                JOptionPane.showMessageDialog(this, rows + "Recorde Addded..");
//                getProduct();
//                clearText();
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this,"Error : "+ e);
//        }
//    }//end of addProduct()
   
   
   
   
   private void addProduct(){
            String prodName = prodNameTextField.getText();
            String priceStr  = priceTextField.getText();
            String barcode  = barcodeTextField.getText();
            String qtyStr = quantityTextField.getText();
            String totalStr = totalTextField.getText();
            String remarks = remarkTextArea.getText().length()<=0?"":remarkTextArea.getText();

            if(prodName.trim().length()>0   && priceStr.trim().length()> 0 && qtyStr.trim().length()> 0   &&
               totalStr.trim().length()> 0 ){

                try{ 
                    ProductCategoryBean bean = (ProductCategoryBean)prodCatComboBox.getSelectedItem();
                    if(bean == null) return;

                    int price  = Integer.parseInt(priceStr);
                    int qty = Integer.parseInt(qtyStr);
                    int total = Integer.parseInt(totalStr);

                    java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());

                    String counterNo = "1";
                    String amountType = "C";
                    String transType = "P";
                    int rows = DatabaseManager.addBill(3, (java.sql.Date) date, "0", 0, 0, counterNo,transType,"PURCHASE");
                    int billMaxId = DatabaseManager.getMaxBillId();
                    if(rows >= 1){

                            DatabaseManager.addProduct(bean.getProdCatIdd(),prodName,barcode,price,qty,total,remarks);

                            ProductBean pBean = DatabaseManager.getProductByCatId(bean.getProdCatIdd());
                            if(pBean == null )return;

                            DatabaseManager.addTransaction(billMaxId,pBean.getProdId(), date, price, qty, total, amountType, transType, Integer.parseInt(counterNo),"PURCHASE");
                    }
                    JOptionPane.showMessageDialog(this, rows + "Recorde Addded..");
                    clearText();
                    getProduct();
                   
                    StokeJFrame.getProductCategory();
                    SaleBookJFrame.getProduct();
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this,"Fields Can`t be Empty please fill out.");
            }
       
    }//End of addProduct
    
    
    private void updateProduct(){
        
        String prodName = prodNameTextField.getText();
        String priceStr  = priceTextField.getText();
        String barcode  = barcodeTextField.getText();
        String qtyStr = quantityTextField.getText();
        String totalStr = totalTextField.getText();
        String remarks = remarkTextArea.getText().length()<=0?"":remarkTextArea.getText();
        
        if(prodName.trim().length()> 0   && priceStr.trim().length()> 0 && 
            qtyStr.trim().length()> 0    && totalStr.trim().length()> 0  ){
        
            try{ 
                ProductBean bean = (ProductBean)prodNameList.getSelectedValue();
                if(bean == null ) return;
        
                int price  = Integer.parseInt(priceStr);
                int qty = Integer.parseInt(qtyStr);
                int total = Integer.parseInt(totalStr);

                java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
//            String counterNo = counterJLabel.getText();
                String counterNo = "1";
                String amountType = "C";
                String transType = "P";

                int rows = DatabaseManager.addBill(1, (java.sql.Date) date, "", 0, 0, counterNo,transType,"UPDATED PURCHASE");
                int billMaxId = DatabaseManager.getMaxBillId();
                if(rows >= 1){

                        ProductBean pBean = DatabaseManager.getProductForBill(bean.getProdId());
                        if(pBean == null )return;

                        int newTransactionQty =  (qty-pBean.getQuantity());             
                        int newTransactionAmount =  (newTransactionQty * pBean.getPrice()); 

                        DatabaseManager.addTransaction(billMaxId,pBean.getProdId(), date, price, newTransactionQty, newTransactionAmount, amountType, transType, Integer.parseInt(counterNo),"UPDATED PURCHASE");
                        DatabaseManager.updateProduct(bean.getProdId(),prodName,barcode,price,qty,total,remarks);
                        
                        JOptionPane.showMessageDialog(this, rows + " Recorde Updated..");
                }
                clearText();
                getProduct();
                
                StokeJFrame.getProductCategory();
                SaleBookJFrame.getProduct();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,"Fields Can`t be Empty please fill out.");
        }
    }//End of updateProduct
   
   
   
   
    
    private void clearText(){
        
        addBtn.setEnabled(false);
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        
        prodNameList.clearSelection();
        prodCatComboBox.setSelectedIndex(0);
        
        prodNameTextField.setBackground(java.awt.Color.white);
        prodNameTextField.removeAll();
        prodNameTextField.setText("");
        
        searchTextField.setText("");
        priceTextField.setText("");
        barcodeTextField.setText("");
        quantityTextField.setText("");
        totalTextField.setText("");
        totalTextField.setText("");
        remarkTextArea.setText("");
  
    }
    
    private void getBarcode(){
       ProductCategoryBean bean =(ProductCategoryBean)prodCatComboBox.getSelectedItem();
       if(bean == null ) return;
       try{
           Vector v = DatabaseManager.getProduct(bean.getProdCatIdd());
           if(v.size() == 0)return;
           String barcode[] = new String[v.size()];
            for(int i=0; i<v.size(); i++){
                ProductBean pBean = (ProductBean)v.elementAt(i);
                barcode[i] += pBean.getBarcode().concat(",")+pBean.getProdName().concat(",")+pBean.getPrice();
            }//End of for
            
            new ProductBarcodeListPrint(barcode);
             JOptionPane.showMessageDialog(null, "Barcode List Printed..");
//            String data[] = new String[100];
//            for(int j=0; j<data.length; j++)
//                data[j]+=new java.util.Random().nextInt(200000)+10000;
//            
//            new ProductBarcodeListPrint(data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of getBarcode
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        prodCatIdTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        prodCatNameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarkProductCatTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        addProductCatJButton = new javax.swing.JButton();
        updateProductCatJButton = new javax.swing.JButton();
        clearProductCatJButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        prodCatList = new javax.swing.JList();
        deleteProductCatJButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        prodNameTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        barcodeTextField = new javax.swing.JTextField();
        priceTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarkTextArea = new javax.swing.JTextArea();
        quantityTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        totalTextField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        prodNameList = new javax.swing.JList();
        searchTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        printJButton = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        prodCatComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setPreferredSize(new java.awt.Dimension(1293, 582));
        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRODUCT DETAIL");
        jPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1354, 34));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCT CATEGORY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("PRODUCT CATEGORY  ID:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 61, 154, 10));

        prodCatIdTextField.setEditable(false);
        prodCatIdTextField.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(prodCatIdTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 74, 154, 27));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("* PRODUCT CATEGORY NAME:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 117, 367, -1));

        prodCatNameTextField.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        prodCatNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                prodCatNameTextFieldFocusLost(evt);
            }
        });
        prodCatNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                prodCatNameTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prodCatNameTextFieldKeyTyped(evt);
            }
        });
        jPanel1.add(prodCatNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 131, 367, 34));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("REMARKS:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 186, 367, -1));

        remarkProductCatTextArea.setColumns(20);
        remarkProductCatTextArea.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        remarkProductCatTextArea.setRows(5);
        jScrollPane1.setViewportView(remarkProductCatTextArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 200, 367, 216));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PRODUCT CATEGORY NAME");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 61, 214, 10));

        addProductCatJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addProductCatJButton.setText("ADD");
        addProductCatJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductCatJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addProductCatJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 444, 110, -1));

        updateProductCatJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        updateProductCatJButton.setText("UPDATE");
        updateProductCatJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProductCatJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(updateProductCatJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 444, 110, -1));

        clearProductCatJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        clearProductCatJButton.setText("CLEAR");
        clearProductCatJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearProductCatJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(clearProductCatJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 444, 100, -1));

        prodCatList.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        prodCatList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                prodCatListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(prodCatList);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 74, 214, 342));

        deleteProductCatJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        deleteProductCatJButton.setText("DELETE");
        deleteProductCatJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductCatJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(deleteProductCatJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 444, 96, -1));

        jPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 63, 630, 550));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(995, 545));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("PRODUCT CATEGORY");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("* PRODUCT NAME");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 114, 126, 20));

        prodNameTextField.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        prodNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                prodNameTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prodNameTextFieldKeyTyped(evt);
            }
        });
        jPanel2.add(prodNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 134, 361, 29));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText(" BARCODE");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 173, 140, 20));

        barcodeTextField.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        barcodeTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                barcodeTextFieldFocusLost(evt);
            }
        });
        barcodeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcodeTextFieldActionPerformed(evt);
            }
        });
        barcodeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                barcodeTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barcodeTextFieldKeyTyped(evt);
            }
        });
        jPanel2.add(barcodeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 193, 141, 29));

        priceTextField.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        priceTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                priceTextFieldFocusLost(evt);
            }
        });
        priceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceTextFieldKeyTyped(evt);
            }
        });
        jPanel2.add(priceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 251, 141, 29));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("* PRICE");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 231, 61, 20));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("REMARKS");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 280, -1, 24));

        remarkTextArea.setColumns(20);
        remarkTextArea.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        remarkTextArea.setRows(5);
        jScrollPane2.setViewportView(remarkTextArea);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 304, 362, 113));

        quantityTextField.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        quantityTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quantityTextFieldFocusLost(evt);
            }
        });
        quantityTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantityTextFieldKeyTyped(evt);
            }
        });
        jPanel2.add(quantityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 251, 67, 29));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("* QUANTITY");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 231, 80, 20));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("* TOTAL AMOUNT");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 231, 120, 20));

        totalTextField.setEditable(false);
        totalTextField.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        totalTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                totalTextFieldFocusLost(evt);
            }
        });
        totalTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalTextFieldKeyReleased(evt);
            }
        });
        jPanel2.add(totalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 251, 118, 29));

        prodNameList.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        prodNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                prodNameListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(prodNameList);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 105, 224, 312));

        searchTextField.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
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
        jPanel2.add(searchTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 75, 225, 29));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("BARCODE/PRODUCT NAME");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 54, 225, 20));

        printJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printJButton.setText("PRINT BARCODE");
        printJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJButtonActionPerformed(evt);
            }
        });
        jPanel2.add(printJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 445, 192, -1));

        deleteBtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel2.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 445, 94, -1));

        clearBtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel2.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 445, -1, -1));

        updateBtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel2.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 445, -1, -1));

        addBtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel2.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 445, 78, -1));

        prodCatComboBox.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        prodCatComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodCatComboBoxActionPerformed(evt);
            }
        });
        jPanel2.add(prodCatComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 74, 361, 29));

        jPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 63, 656, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1354, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addProductCatJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductCatJButtonActionPerformed
        addProductCategory();
    }//GEN-LAST:event_addProductCatJButtonActionPerformed

    private void updateProductCatJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProductCatJButtonActionPerformed
        updateProductCategory();
    }//GEN-LAST:event_updateProductCatJButtonActionPerformed

    private void clearProductCatJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearProductCatJButtonActionPerformed
        clearProductCatText();
    }//GEN-LAST:event_clearProductCatJButtonActionPerformed

    private void prodCatListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_prodCatListValueChanged
        if(prodCatList.getSelectedIndex()<0){
            updateProductCatJButton.setEnabled(false);
            deleteProductCatJButton.setEnabled(false);
        }
        else{
            updateProductCatJButton.setEnabled(true);
            deleteProductCatJButton.setEnabled(true);
            ProductCategoryBean bean = (ProductCategoryBean)prodCatList.getSelectedValue();
            if(bean == null) return;
            prodCatIdTextField.setText(""+bean.getProdCatIdd());
            prodCatNameTextField.setText(""+bean.getProdCatName());
            remarkProductCatTextArea.setText(""+bean.getRemarks());
        }
    }//GEN-LAST:event_prodCatListValueChanged

    private void deleteProductCatJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductCatJButtonActionPerformed

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
    }//GEN-LAST:event_deleteProductCatJButtonActionPerformed

    private void barcodeTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_barcodeTextFieldFocusLost
//        String text = this.barcodeTextField.getText();
//        try {
//            int temp = Integer.parseInt(text);
//            isValidate = true;
//        } catch (NumberFormatException ee) {
//            isValidate = false;
//            Toolkit.getDefaultToolkit().beep();
//            msg = "Barcode Must be Number/numeric!";
//            javax.swing.JOptionPane.showMessageDialog(null, msg);
//        }
    }//GEN-LAST:event_barcodeTextFieldFocusLost

    private void barcodeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeTextFieldKeyReleased
//        String barcode = barcodeTextField.getText();
//        if(barcode.length() >  8){
//            JOptionPane.showMessageDialog(this, "Please Enter only 8 digits barcode..");
//            barcodeTextField.setText("");
//            return;
//        }
    }//GEN-LAST:event_barcodeTextFieldKeyReleased

    private void priceTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_priceTextFieldFocusLost
//        String text = this.priceTextField.getText();
//        try {
//            int temp = Integer.parseInt(text);
//            isValidate = true;
//        } catch (NumberFormatException ee) {
//            isValidate = false;
//            Toolkit.getDefaultToolkit().beep();
//            msg = "Price Must be Number/numeric!";
//            javax.swing.JOptionPane.showMessageDialog(null, msg);
//        }
    }//GEN-LAST:event_priceTextFieldFocusLost

    private void priceTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceTextFieldKeyReleased
        String price = priceTextField.getText();
        if(price.length() <= 0){
            totalTextField.setText("");
            quantityTextField.setText("");
        }
    }//GEN-LAST:event_priceTextFieldKeyReleased

    private void quantityTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantityTextFieldFocusLost
//        String text = this.quantityTextField.getText();
//        try {
//            int temp = Integer.parseInt(text);
//            isValidate = true;
//        } catch (NumberFormatException ee) {
//            isValidate = false;
//            Toolkit.getDefaultToolkit().beep();
//            msg = "Quantity Must be Number/numeric!";
//            javax.swing.JOptionPane.showMessageDialog(null, msg);
//        }
    }//GEN-LAST:event_quantityTextFieldFocusLost

    private void quantityTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityTextFieldKeyReleased
        try{
            int qtyLength = quantityTextField.getText().length();
            if(qtyLength > 0 ){
                int unitPrice = Integer.parseInt(priceTextField.getText());
                int qty = Integer.parseInt(quantityTextField.getText());
                int sum = (unitPrice*qty);
                totalTextField.setText(""+sum);
                addBtn.setEnabled(true);
            }
            else{
                totalTextField.setText("");
                addBtn.setEnabled(false);
            }
            isValidate = true;
        } catch (NumberFormatException ee) {
            isValidate = false;
            Toolkit.getDefaultToolkit().beep();
            msg = "Price Must be Number/numeric!";
//            javax.swing.JOptionPane.showMessageDialog(null, msg);
        }
    }//GEN-LAST:event_quantityTextFieldKeyReleased

    private void totalTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_totalTextFieldFocusLost
        String text = this.totalTextField.getText();
        try {
            int temp = Integer.parseInt(text);
            isValidate = true;
        } catch (NumberFormatException ee) {
            isValidate = false;
            Toolkit.getDefaultToolkit().beep();
            msg = "Total Amount Must be Number/numeric!";
            javax.swing.JOptionPane.showMessageDialog(null,msg );
        }
    }//GEN-LAST:event_totalTextFieldFocusLost

    private void totalTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_totalTextFieldKeyReleased

    private void prodNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_prodNameListValueChanged
        if(prodNameList.getSelectedIndex()<  0){
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }
        else{
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            ProductBean bean = (ProductBean)prodNameList.getSelectedValue();
            if(bean == null) return;
            prodNameTextField.setText(""+bean.getProdName());
            priceTextField.setText(""+bean.getPrice());
            quantityTextField.setText(""+bean.getQuantity());
            totalTextField.setText(""+bean.getTotal());
            barcodeTextField.setText(""+bean.getBarcode());
            remarkTextArea.setText(""+bean.getRemarks());
        }
    }//GEN-LAST:event_prodNameListValueChanged

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
        if(searchTextField.getText().length() <= 0){
            getProductCategory();
        }
        else{
            try{
                if(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9'){
                    Vector v = DatabaseManager.getProductByBarcode(searchTextField.getText());
                    if(v.size() == 0)return;

                    int prodCatId = 0;

                    for(int j=0; j<v.size(); j++){
                        ProductBean pBean = (ProductBean)v.elementAt(j);
                        prodCatId = pBean.getProdCatId();
                    }
                    ProductCategoryBean pcBean = DatabaseManager.getProductCategory(prodCatId);
                    if(pcBean == null )return;
                    String item = pcBean.getProdCatName();
                    boolean exists = false;
                    for (int index = 0; index < prodCatComboBox.getItemCount() && !exists; index++) {
                        if (item.equals(String.valueOf(prodCatComboBox.getItemAt(index)))) {
                            exists = true;
                            prodCatComboBox.setSelectedIndex(index);
                        }
                    }

                    prodNameList.setListData(v);

                }
                else{
                    Vector v = DatabaseManager.getProductByName(searchTextField.getText());
                    if(v.size() == 0)return;
                    prodNameList.setListData(v);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
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

    private void printJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printJButtonActionPerformed
        getBarcode();
    }//GEN-LAST:event_printJButtonActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if(prodNameList.getSelectedIndex() <= 0){
            Toolkit.getDefaultToolkit().beep();
            javax.swing.JOptionPane.showMessageDialog(null, "Please select Product first!");
        }
        else{
            Toolkit.getDefaultToolkit().beep();
            int permission = JOptionPane.showConfirmDialog(this,"Are you want to DELETE Recorde!!","CONFIRAMATION", JOptionPane.YES_NO_OPTION);
            if(permission == 0){
                deleteProduct();
            }
            else{
                System.out.println("Permission Denied...");
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearText();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        updateProduct();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        addProduct();
    }//GEN-LAST:event_addBtnActionPerformed

    private void prodCatNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prodCatNameTextFieldKeyReleased
        if(!prodCatNameTextField.getText().trim().equals("")){
            prodCatNameTextField.setBackground(java.awt.Color.white);
            prodCatNameTextField.removeAll();
            addProductCatJButton.setEnabled(true);
        }
        else{
          prodCatNameTextField.removeAll();
          setImage(new File("Images//alert.png").getAbsolutePath(), prodCatNameTextField);
          Toolkit.getDefaultToolkit().beep();
          prodCatNameTextField.setText("\r");
          addProductCatJButton.setEnabled(false);
        }
            
    }//GEN-LAST:event_prodCatNameTextFieldKeyReleased

    private void prodCatNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prodCatNameTextFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_prodCatNameTextFieldFocusLost

    private void prodCatNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prodCatNameTextFieldKeyTyped
        char ch=evt.getKeyChar();
        if(prodCatNameTextField.getText().indexOf(".")!=-1 && ch=='.'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        
    }//GEN-LAST:event_prodCatNameTextFieldKeyTyped

    private void prodNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prodNameTextFieldKeyReleased
        if(!prodNameTextField.getText().trim().equals("")){
            prodNameTextField.setBackground(java.awt.Color.white);
            prodNameTextField.removeAll();
        }
        else{
          prodNameTextField.removeAll();
          setImage(new File("Images//alert.png").getAbsolutePath(), prodNameTextField);
          Toolkit.getDefaultToolkit().beep();
          prodNameTextField.setText("\r");
        }
    }//GEN-LAST:event_prodNameTextFieldKeyReleased

    private void prodNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prodNameTextFieldKeyTyped
        char ch=evt.getKeyChar();
        if(prodCatNameTextField.getText().indexOf(".")!=-1 && ch=='.'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_prodNameTextFieldKeyTyped

    private void barcodeTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeTextFieldKeyTyped
        char ch=evt.getKeyChar();
        if(!Character.isDigit(ch) && ch!=java.awt.event.KeyEvent.VK_LEFT && ch!=java.awt.event.KeyEvent.VK_RIGHT 
          && ch!=java.awt.event.KeyEvent.VK_BACK_SPACE && ch!=java.awt.event.KeyEvent.VK_DELETE 
          && (ch!=java.awt.event.KeyEvent.VK_CONTROL && ch!=java.awt.event.KeyEvent.VK_A || ch!=java.awt.event.KeyEvent.VK_C 
                || ch!=java.awt.event.KeyEvent.VK_V || ch!=java.awt.event.KeyEvent.VK_X) && (ch!='.'))
          evt.consume();
        else
          if(barcodeTextField.getText().indexOf(".")!=-1 && ch=='.'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
          }
    }//GEN-LAST:event_barcodeTextFieldKeyTyped

    private void priceTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceTextFieldKeyTyped
        char ch=evt.getKeyChar();
        if(!Character.isDigit(ch) && ch!=java.awt.event.KeyEvent.VK_LEFT && ch!=java.awt.event.KeyEvent.VK_RIGHT 
          && ch!=java.awt.event.KeyEvent.VK_BACK_SPACE && ch!=java.awt.event.KeyEvent.VK_DELETE 
          && (ch!=java.awt.event.KeyEvent.VK_CONTROL && ch!=java.awt.event.KeyEvent.VK_A || ch!=java.awt.event.KeyEvent.VK_C 
                || ch!=java.awt.event.KeyEvent.VK_V || ch!=java.awt.event.KeyEvent.VK_X) && (ch!='.'))
          evt.consume();
        else
          if(priceTextField.getText().indexOf(".")!=-1 && ch=='.'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
          }
    }//GEN-LAST:event_priceTextFieldKeyTyped

    private void quantityTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityTextFieldKeyTyped
       char ch=evt.getKeyChar();
        if(!Character.isDigit(ch) && ch!=java.awt.event.KeyEvent.VK_LEFT && ch!=java.awt.event.KeyEvent.VK_RIGHT 
          && ch!=java.awt.event.KeyEvent.VK_BACK_SPACE && ch!=java.awt.event.KeyEvent.VK_DELETE 
          && (ch!=java.awt.event.KeyEvent.VK_CONTROL && ch!=java.awt.event.KeyEvent.VK_A || ch!=java.awt.event.KeyEvent.VK_C 
                || ch!=java.awt.event.KeyEvent.VK_V || ch!=java.awt.event.KeyEvent.VK_X) && (ch!='.'))
          evt.consume();
        else
          if(quantityTextField.getText().indexOf(".")!=-1 && ch=='.'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
          }
    }//GEN-LAST:event_quantityTextFieldKeyTyped

    private void barcodeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcodeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeTextFieldActionPerformed

    private void prodCatComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodCatComboBoxActionPerformed
       getProduct();
    }//GEN-LAST:event_prodCatComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(ProductDetailJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductDetailJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductDetailJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductDetailJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductDetailJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton addProductCatJButton;
    private javax.swing.JTextField barcodeTextField;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton clearProductCatJButton;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton deleteProductCatJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JButton printJButton;
    private javax.swing.JComboBox prodCatComboBox;
    private javax.swing.JTextField prodCatIdTextField;
    private javax.swing.JList prodCatList;
    private javax.swing.JTextField prodCatNameTextField;
    private javax.swing.JList prodNameList;
    private javax.swing.JTextField prodNameTextField;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JTextArea remarkProductCatTextArea;
    private javax.swing.JTextArea remarkTextArea;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JButton updateBtn;
    private javax.swing.JButton updateProductCatJButton;
    // End of variables declaration//GEN-END:variables
}
