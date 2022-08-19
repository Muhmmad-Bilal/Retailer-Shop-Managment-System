/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailershopmanagmentsystem;

import Beans.CustomerBean;
import Beans.ProductBean;
import Beans.ProductCategoryBean;
import Recipte.ReciptPrint;
import databaseManager.DatabaseManager;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import net.connectcode.Code128Auto;

/**
 *
 * @author Maham Computers
 */
public class ProductFrame extends javax.swing.JFrame {

    public boolean isValidate = false;
    private static String msg;
    
    public ProductFrame() {
        initComponents();
        getProductCategory();
        
//        new Thread(new Runnable(){
//            public void run(){
//                while(true){
//                    getProductCategory();
//                    System.out.println("getProductCategory Working");
//                    try{Thread.sleep(1000);}catch(Exception e){}
//                }
//            }
//        }).start();
  
    }//End of constructor
    
    
    
    
    public javax.swing.JPanel getpanel(){
        return jPanel;
    }
    public static void getProductCategory(){
       try{
           Vector v = DatabaseManager.getProductCategory();
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
       if(isValidate){
            String prodName = prodNameTextField.getText();
            String priceStr  = priceTextField.getText();
            String barcode  = barcodeTextField.getText();
            String qtyStr = quantityTextField.getText();
            String totalStr = totalTextField.getText();
            String remarks = remarkTextArea.getText().length()<=0?"":remarkTextArea.getText();

            if(prodName.trim().length()>0   && priceStr.trim().length()> 0 && 
               barcode.trim().length()> 0 && qtyStr.trim().length()> 0   &&
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
       }
       else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,msg);
       }
    }//End of addProduct
    
    
    private void updateProduct(){
        
        
        
        String prodName = prodNameTextField.getText();
        String priceStr  = priceTextField.getText();
        String barcode  = barcodeTextField.getText();
        String qtyStr = quantityTextField.getText();
        String totalStr = totalTextField.getText();
        String remarks = remarkTextArea.getText().length()<=0?"":remarkTextArea.getText();
        
        if(prodName.trim().length()>= 1   && priceStr.trim().length()>= 1 && 
           barcode.trim().length()>= 1 && qtyStr.trim().length()>= 1   &&
           totalStr.trim().length()>= 1 ){
        
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

                int rows = DatabaseManager.addBill(3, (java.sql.Date) date, "", 0, 0, counterNo,transType,"UPDATED PURCHASE");
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
        prodCatComboBox.setSelectedIndex(0);
        prodNameTextField.setText("");
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        prodNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarkTextArea = new javax.swing.JTextArea();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        printJButton = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        prodNameList = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        barcodeTextField = new javax.swing.JTextField();
        prodCatComboBox = new javax.swing.JComboBox();
        quantityTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        totalTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRODUCT ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("PRODUCT CATEGORY");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("PRODUCT NAME");

        prodNameTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("REMARKS");

        remarkTextArea.setColumns(20);
        remarkTextArea.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        remarkTextArea.setRows(5);
        jScrollPane1.setViewportView(remarkTextArea);

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

        printJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printJButton.setText("PRINT BARCODE");
        printJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJButtonActionPerformed(evt);
            }
        });

        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        prodNameList.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        prodNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                prodNameListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(prodNameList);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("PRICE");

        priceTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        priceTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                priceTextFieldFocusLost(evt);
            }
        });
        priceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceTextFieldKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("BARCODE");

        barcodeTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        barcodeTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                barcodeTextFieldFocusLost(evt);
            }
        });
        barcodeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                barcodeTextFieldKeyReleased(evt);
            }
        });

        prodCatComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        prodCatComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        prodCatComboBox.setEditor(null);
        prodCatComboBox.setInheritsPopupMenu(true);
        prodCatComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodCatComboBoxActionPerformed(evt);
            }
        });

        quantityTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        quantityTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quantityTextFieldFocusLost(evt);
            }
        });
        quantityTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityTextFieldKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("QUANTITY");

        totalTextField.setEditable(false);
        totalTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("TOTAL AMOUNT");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("SEARCH BARCODE/PRODUCT NAME");

        searchTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prodNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(barcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel12)
                                .addGap(60, 60, 60)
                                .addComponent(jLabel13))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(updateBtn)
                        .addGap(11, 11, 11)
                        .addComponent(clearBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(printJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(prodCatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchTextField))))
                .addGap(0, 70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(434, 434, 434))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodCatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(prodNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(barcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addBtn)
                    .addComponent(updateBtn)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clearBtn)
                        .addComponent(deleteBtn))
                    .addComponent(printJButton))
                .addContainerGap(40, Short.MAX_VALUE))
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
           addProduct();
    }//GEN-LAST:event_addBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
           updateProduct();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
           clearText();
    }//GEN-LAST:event_clearBtnActionPerformed

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

    private void prodNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_prodNameListValueChanged
        ProductBean bean = (ProductBean)prodNameList.getSelectedValue();
        if(bean == null) return;
        prodNameTextField.setText(""+bean.getProdName());
        priceTextField.setText(""+bean.getPrice());
        quantityTextField.setText(""+bean.getQuantity());
        totalTextField.setText(""+bean.getTotal());
        barcodeTextField.setText(""+bean.getBarcode());
        remarkTextArea.setText(""+bean.getRemarks());

    }//GEN-LAST:event_prodNameListValueChanged

    private void prodCatComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodCatComboBoxActionPerformed
        getProduct();
    }//GEN-LAST:event_prodCatComboBoxActionPerformed

    private void quantityTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityTextFieldKeyReleased
        try{
            int qtyLength = quantityTextField.getText().length();
            if(qtyLength > 0 ){
                int unitPrice = Integer.parseInt(priceTextField.getText());
                int qty = Integer.parseInt(quantityTextField.getText());
                int sum = (unitPrice*qty);
                totalTextField.setText(""+sum);
            }
            else{
                totalTextField.setText("");
            }
            isValidate = true;
        } catch (NumberFormatException ee) {
           isValidate = false;
           Toolkit.getDefaultToolkit().beep();
           msg = "Price Must be Number/numeric!";
           javax.swing.JOptionPane.showMessageDialog(null, msg);
        }
    }//GEN-LAST:event_quantityTextFieldKeyReleased

    private void totalTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_totalTextFieldKeyReleased

    private void printJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printJButtonActionPerformed
      getBarcode();
    }//GEN-LAST:event_printJButtonActionPerformed

    private void barcodeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeTextFieldKeyReleased
        String barcode = barcodeTextField.getText();
            if(barcode.length() >  8){
                JOptionPane.showMessageDialog(this, "Please Enter only 8 digits barcode..");
                barcodeTextField.setText("");
                return;
            }
    }//GEN-LAST:event_barcodeTextFieldKeyReleased

    private void barcodeTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_barcodeTextFieldFocusLost
        String text = this.barcodeTextField.getText();
        try {
          int temp = Integer.parseInt(text); 
          isValidate = true;
        } catch (NumberFormatException ee) {
           isValidate = false;
           Toolkit.getDefaultToolkit().beep();
           msg = "Barcode Must be Number/numeric!";
           javax.swing.JOptionPane.showMessageDialog(null, msg);
        }
    }//GEN-LAST:event_barcodeTextFieldFocusLost

    private void priceTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_priceTextFieldFocusLost
       String text = this.priceTextField.getText();
        try {
          int temp = Integer.parseInt(text); 
          isValidate = true;
        } catch (NumberFormatException ee) {
           isValidate = false;
           Toolkit.getDefaultToolkit().beep();
           msg = "Price Must be Number/numeric!";
           javax.swing.JOptionPane.showMessageDialog(null, msg);
        }
    }//GEN-LAST:event_priceTextFieldFocusLost

    private void quantityTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantityTextFieldFocusLost
       String text = this.quantityTextField.getText();
        try {
          int temp = Integer.parseInt(text); 
          isValidate = true;
        } catch (NumberFormatException ee) {
           isValidate = false;
           Toolkit.getDefaultToolkit().beep();
           msg = "Quantity Must be Number/numeric!";
           javax.swing.JOptionPane.showMessageDialog(null, msg);
        }
    }//GEN-LAST:event_quantityTextFieldFocusLost

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

    private void priceTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceTextFieldKeyReleased
       String price = priceTextField.getText();
       if(price.length() <= 0){
           totalTextField.setText("");
           quantityTextField.setText("");
       }
    }//GEN-LAST:event_priceTextFieldKeyReleased

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
            java.util.logging.Logger.getLogger(ProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField barcodeTextField;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JButton printJButton;
    private static javax.swing.JComboBox prodCatComboBox;
    private javax.swing.JList prodNameList;
    private javax.swing.JTextField prodNameTextField;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JTextArea remarkTextArea;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
