/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailershopmanagmentsystem;

import Beans.BillBean;
import Beans.CustomerBean;
import Beans.EmployeBean;
import Beans.ProductBean;
import Beans.ProductCategoryBean;
import Beans.TransactionBean;
import FronterFrame.RetailerShopManagmentMainJFrame;
import Recipte.ReciptPrint;
import Recipte.ReciptPrintUpdated;
import databaseManager.DatabaseManager;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Maham Computers
 */
public class SaleBookJFrame extends javax.swing.JFrame implements TableModelListener{

    boolean isAllowToWork = true;
    
    public boolean isValidate = false;
    private static String msg;
    
    public SaleBookJFrame() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        
        getCustomer();
        getProduct();
        
        new GetCurrentDate(dateJLabel).start();
        
        jTable.getModel().addTableModelListener(this);
        jTable.setFont(new Font("Arial", Font.BOLD, 14));
        
        getBillByCurrentDate();
        searchTextField.requestFocusInWindow();
        
        counterJLabel.setText("Cash-AT-01");
        
    }//End of Constructor


    public javax.swing.JPanel getPanel(){
        return jPanel;
    }
    
 
    
    
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
    
//    private void getProductCategory(){
//       try{
//           Vector v = DatabaseManager.getProductCategory();
//           if(v.size() == 0)return;
//           prodCatComboBox.removeAllItems();
//           for(int i=0; i<v.size(); i++)
//           prodCatComboBox.addItem(v.elementAt(i));
//       }catch(Exception e){
//           e.printStackTrace();
//           JOptionPane.showMessageDialog(this,"Error : " + e);
//       }
//   }//end of getProductCategory
    
    
    
    public static void getProduct(){
//       ProductCategoryBean bean =(ProductCategoryBean)prodCatComboBox.getSelectedItem();
//       if(bean == null ) return;
       try{
           Vector v = DatabaseManager.getProductForSale();
           if(v.size() == 0)return;
            itemJList.setListData(v);
       }catch(Exception e){
           e.printStackTrace();
       }
   }//end of getProduct
  
   
    private void addBillAndTransaction(){
        if(isValidate){
//            ProductBean pBean = (ProductBean)itemJList.getSelectedValue();
//            if(pBean == null) return;
            CustomerBean cBean = (CustomerBean)customerComboBox.getSelectedItem();
            if(cBean == null) return;


            try{ 

                java.sql.Date date = java.sql.Date.valueOf(EncoderAndDecoder.IncodeSimpleDateFormate(dateJLabel.getText()));
                java.sql.Timestamp timeStamp=new java.sql.Timestamp(new java.util.Date().getTime());

                String discount = String.valueOf(discountJComboBox.getSelectedItem());
                int cashPaid = Integer.parseInt(paidAmountTextField.getText());
                int cashBack = Integer.parseInt(returnAmountTextField.getText());
    //            String counterNo = counterJLabel.getText();
                String counterNo = "1";
                String amountType = "C";
                String transType = "S";

                int rows = DatabaseManager.addBill(cBean.getCustomerId(), (java.sql.Date) date, discount, cashPaid, cashBack, counterNo,transType,"SOLD");
                int billMaxId = DatabaseManager.getMaxBillId();
                if(rows >= 1){
                    for(int i=0; i<jTable.getModel().getRowCount(); i++){
                        int prod_id = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 0)));
                        int unitPrice = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 2)));
                        int qty = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 3)));
                        int totalAmount = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 4)));
                        //add transaction
                        DatabaseManager.addTransaction(billMaxId,prod_id, date, unitPrice, qty, totalAmount, amountType, transType, Integer.parseInt(counterNo),"SOLD");

                        // update stoke..
                        int saleQty = DatabaseManager.getSaleSumOfProduct(prod_id);
                        int purchaseQty = DatabaseManager.getPurchaseSumOfProduct(prod_id);
                        int availableQty = (purchaseQty - saleQty);
                        int availableTotalAmount = (availableQty * unitPrice);
                        DatabaseManager.updateProductStoke(prod_id, availableQty, availableTotalAmount);
                    }
                     ///For recipte
                    Vector v = databaseManager.DatabaseManager.getBill(billMaxId);
                    new ReciptPrintUpdated(v);
                    JOptionPane.showMessageDialog(null,"Recipte Printed..");
                }
                clear();
                getBillByCurrentDate();
                
                StokeJFrame.getProductCategory();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,msg);
       }
    }//End of addBillAndTransaction
    
    
    private void updateBillAndTransaction(){
        try{
            BillBean bBean = (BillBean)invoiceNoJList.getSelectedValue();
            if(bBean == null) return;
            
            java.sql.Date date = java.sql.Date.valueOf(EncoderAndDecoder.IncodeSimpleDateFormate(dateJLabel.getText()));
            
            String discount = String.valueOf(discountJComboBox.getSelectedItem());
            int cashPaid = Integer.parseInt(paidAmountTextField.getText());
            int cashBack = Integer.parseInt(returnAmountTextField.getText());
            int billId = bBean.getBill_id();
//            String counterNo = counterJLabel.getText();
            String counterNo = "1";
            String amountType = "C"; //CASH
            String transType = "S"; //SALE
            
            int rows = DatabaseManager.updateBill(billId,bBean.getCustomer_id(), (java.sql.Date) date, discount, cashPaid, cashBack, counterNo,transType,"UPDATED SOLD");
          
            if(rows >= 1){
                Vector vector = DatabaseManager.getTransaction(billId);
                for(int i=0; i<vector.size(); i++){
                    int prod_id = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 0)));
                    int unitPrice = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 2)));
                    int qty = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 3)));
                    int totalAmount = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 4)));
                    
                    TransactionBean tBean = (TransactionBean)vector.elementAt(i);
                    if(tBean == null )return;
                    DatabaseManager.updateTransaction(billId,prod_id, tBean.getTransactionId(),date, unitPrice, qty, totalAmount, amountType, transType, Integer.parseInt(counterNo),"UPDATED SOLD");
                }
                
                int permission = JOptionPane.showConfirmDialog(this,"Are you want to Print Recipte!!","CONFIRAMATION", JOptionPane.YES_NO_OPTION);
                if(permission == 0){
                    Vector v = databaseManager.DatabaseManager.getBill(billId);
                     new ReciptPrintUpdated(v);
                    JOptionPane.showMessageDialog(null,"Recipte Printed..");
                }
                else{
                    JOptionPane.showMessageDialog(this, rows + " Recorde Updated..");
                }
                clear();
                getBillByCurrentDate();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of updateBillAndTransaction
    
    
    private void deleteSelectedBillAndTransaction(){
        try{
            int size[] = invoiceNoJList.getSelectedIndices();
            invoiceNoJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            int rows=0;            
            for(int i=0; i<size.length; i++){
                BillBean bean = (BillBean)invoiceNoJList.getModel().getElementAt(size[i]);
                if (bean == null ) continue;
                Vector vector = DatabaseManager.getTransaction(bean.getBill_id());
                
                rows += DatabaseManager.deleteBill(bean.getBill_id(), bean.getCustomer_id());
                
                for(int j=0; j<vector.size(); j++){
                    TransactionBean tBean = (TransactionBean)vector.elementAt(j);
                    rows += DatabaseManager.deleteTransaction(tBean.getTransactionId());
                }
                
            }//end of for
            if(rows >= 1){
                JOptionPane.showMessageDialog(this,rows+" Recorde removed..");
                getBillByCurrentDate();
                clear();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of deleteSelectedBillAndTransaction
    
    
    
    
     public  void refundTransaction(){
        if(isValidate){
            CustomerBean cBean = (CustomerBean)customerComboBox.getSelectedItem();
            if(cBean == null) return;

            try{ 

                java.sql.Date date = java.sql.Date.valueOf(EncoderAndDecoder.IncodeSimpleDateFormate(dateJLabel.getText()));

                String discount = String.valueOf(discountJComboBox.getSelectedItem());
                int cashPaid = Integer.parseInt(paidAmountTextField.getText());
                int cashBack = Integer.parseInt(returnAmountTextField.getText());
    //            String counterNo = counterJLabel.getText();
                String counterNo = "1";
                String amountType = "C";
                String transType = "P";

                int rows = DatabaseManager.addBill(cBean.getCustomerId(), (java.sql.Date) date, discount, cashPaid, cashBack, counterNo,transType,"REFUND");
                int billMaxId = DatabaseManager.getMaxBillId();
                if(rows >= 1){
                    for(int i=0; i<jTable.getModel().getRowCount(); i++){
                        int prod_id = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 0)));
                        int unitPrice = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 2)));
                        int qty = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 3)));
                        int totalAmount = Integer.parseInt(String.valueOf(jTable.getValueAt(i, 4)));
                        //add transaction
                        DatabaseManager.addTransaction(billMaxId,prod_id, date, unitPrice, qty, totalAmount, amountType, transType, Integer.parseInt(counterNo),"REFUND");

                        // update stoke..
                        int saleQty = DatabaseManager.getSaleSumOfProduct(prod_id);
                        int purchaseQty = DatabaseManager.getPurchaseSumOfProduct(prod_id);
                        int availableQty = (purchaseQty - saleQty);
                        int availableTotalAmount = (availableQty * unitPrice);
                        DatabaseManager.updateProductStoke(prod_id, availableQty, availableTotalAmount);
                    }
                     ///For recipte
                    Vector v = databaseManager.DatabaseManager.getBill(billMaxId);
                    new ReciptPrintUpdated(v);
                    JOptionPane.showMessageDialog(null,"REFUND SUCCESFULLY..");
                }
                clear();
                getBillByCurrentDate();
                
                StokeJFrame.getProductCategory();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,msg);
        }
    }//End of refundTransaction
    
    
    
    
    private void getBillByCurrentDate(){
         try{
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
            Vector v = DatabaseManager.getBillByDate(date,"S");
            if(v.size() == 0)return;
            invoiceNoJList.setListData(v);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of getBillByCurrentDate
    
    private void getBillByDate(java.sql.Date date){
         try{
            Vector v = DatabaseManager.getBillByDate(date,"S");
            if(v.size() == 0)return;
            invoiceNoJList.setListData(v);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of getBillByDate
    
    private void clear(){
        isAllowToWork = false;
        
        searchTextField.setText("");
        customerComboBox.setSelectedIndex(0);
        discountJComboBox.setSelectedIndex(0);
        paidAmountTextField.setText("");
        returnAmountTextField.setText("");
        totalAmountTextField.setText("");
        totalProductTextField.setText("");
        totalQtyTextField.setText("");
        searchInvoiceNoTextField.setText("");
        DefaultTableModel model = (DefaultTableModel)jTable.getModel();
        model.setRowCount(0);
        getBillByCurrentDate();
 
        
        isAllowToWork = true;
    }//End of clear
    
    
    private void getPreviousInvoiceData(long invoiceNo){
         try{
                isAllowToWork = false;
                DefaultTableModel model = (DefaultTableModel)jTable.getModel();
                model.setRowCount(0);
                
                BillBean bean = DatabaseManager.getBillByInvoice(invoiceNo,"S");
                if(bean == null) return;

                String customerName = "";
                isAllowToWork = true;

                Vector v = DatabaseManager.getBill(bean.getBill_id());
                if(v.size() == 0)return;
                
                for(int i=0; i<v.size(); i++){
                    TransactionBean tBean = (TransactionBean)v.elementAt(i);

                    model.addRow(new Object[]{
                        tBean.getProdId(),
                        tBean.getProdName(),
                        tBean.getUnitPrice(),
                        tBean.getQuantity(),
                        tBean.getTotalPrice()
                    });
                    customerName = tBean.getCustomerName();
                }//End of for
                
                jTable.setRowHeight(25);
                jTable.getColumnModel().getColumn(0).setPreferredWidth(70);
                jTable.getColumnModel().getColumn(1).setPreferredWidth(350);
                jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
                jTable.getColumnModel().getColumn(3).setPreferredWidth(50);
                jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            

                String item = customerName;
                boolean exists = false;
                for (int index = 0; index < customerComboBox.getItemCount() && !exists; index++) {
                    if (item.equals(customerComboBox.getItemAt(index))) {
                        exists = true;
                        customerComboBox.setSelectedIndex(index);
                    }
                }
                if (!exists) {
                    customerComboBox.addItem(item);
                }

                paidAmountTextField.setText(""+bean.getCash_paid());
                returnAmountTextField.setText(""+bean.getCash_back());

                int totalAmount = 0;
                int totalQty = 0;
                for(int k=0; k<jTable.getModel().getRowCount(); k++){
                    totalQty += Integer.parseInt(String.valueOf(jTable.getValueAt(k , 3)));
                    totalAmount += Integer.parseInt(String.valueOf(jTable.getValueAt(k , 4)));
                }
                totalQtyTextField.setText(""+totalQty);
                totalAmountTextField.setText(""+totalAmount);

                totalProductTextField.setText(""+model.getRowCount());

                String item1 = new String(bean.getDiscount());
                boolean exists1 = false;
                for (int index1 = 0; index1 < discountJComboBox.getItemCount() && !exists1; index1++) {
                    if (item1.equals(discountJComboBox.getItemAt(index1))) {
                        exists1 = true;
                        discountJComboBox.setSelectedIndex(index1);
                    }
                }
                if (!exists1) {
                    discountJComboBox.addItem(item1);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        itemJList = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        customerComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        dateJLabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        invoiceNoJList = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        totalProductTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        discountJComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        paidAmountTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        returnAmountTextField = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        refundJButton = new javax.swing.JButton();
        removeJButton = new javax.swing.JButton();
        counterJLabel = new javax.swing.JLabel();
        totalAmountTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        totalQtyTextField = new javax.swing.JTextField();
        searchInvoiceNoTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel.setPreferredSize(new java.awt.Dimension(1293, 582));
        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel.add(searchTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 97, 267, 29));

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

        jPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 129, 267, 457));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("SEARCH BARCODE /PRODUCT NAME");
        jPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 267, 20));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SALE BOOK");
        jPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 0, 1310, 34));

        customerComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel.add(customerComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 97, 229, 28));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("CUSTOMER");
        jPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, 20));

        dateJLabel.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        dateJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel.add(dateJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(901, 96, 212, 29));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel18.setText("COUNTER NO :");
        jPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(607, 95, 90, 31));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SEARCH INVOICE N0");
        jPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1117, 60, 213, 27));

        invoiceNoJList.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        invoiceNoJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                invoiceNoJListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(invoiceNoJList);

        jPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1124, 129, 206, 457));

        jTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "QTY", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setRowHeight(20);
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setResizable(false);
            jTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        jPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 129, 800, 331));

        totalProductTextField.setEditable(false);
        totalProductTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalProductTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalProductTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalProductTextFieldActionPerformed(evt);
            }
        });
        jPanel.add(totalProductTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 511, 109, 29));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("TOTAL PRODUCTS");
        jPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, -1, 20));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("DISCOUNT");
        jPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, 87, 20));

        discountJComboBox.setEditable(true);
        discountJComboBox.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        discountJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "15", "25", "50", "75" }));
        discountJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountJComboBoxActionPerformed(evt);
            }
        });
        discountJComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                discountJComboBoxPropertyChange(evt);
            }
        });
        jPanel.add(discountJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 511, 59, 29));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("%");
        jPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(652, 511, 24, 29));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("PAID AMOUNT");
        jPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 495, 125, -1));

        paidAmountTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        paidAmountTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                paidAmountTextFieldFocusLost(evt);
            }
        });
        paidAmountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paidAmountTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paidAmountTextFieldKeyTyped(evt);
            }
        });
        jPanel.add(paidAmountTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(823, 511, 125, 29));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("RETURN AMOUNT");
        jPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 490, 124, 20));

        returnAmountTextField.setEditable(false);
        returnAmountTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jPanel.add(returnAmountTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(966, 511, 125, 29));

        addBtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addBtn.setText("SAVE AND PRINT");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 546, 302, 40));

        clearBtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(818, 546, 130, 40));

        refundJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        refundJButton.setText("REFUND");
        refundJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refundJButtonActionPerformed(evt);
            }
        });
        jPanel.add(refundJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(966, 546, 125, 40));

        removeJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        removeJButton.setText("REMOVE");
        removeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJButtonActionPerformed(evt);
            }
        });
        jPanel.add(removeJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 546, 125, 40));

        counterJLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        counterJLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel.add(counterJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 96, 130, 29));

        totalAmountTextField.setEditable(false);
        totalAmountTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalAmountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalAmountTextFieldKeyReleased(evt);
            }
        });
        jPanel.add(totalAmountTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 511, 125, 29));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("TOTAL AMOUNT");
        jPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 495, 125, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("TOTAL QTY");
        jPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 109, 20));

        totalQtyTextField.setEditable(false);
        totalQtyTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalQtyTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalQtyTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalQtyTextFieldActionPerformed(evt);
            }
        });
        jPanel.add(totalQtyTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 511, 109, 29));

        searchInvoiceNoTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        searchInvoiceNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInvoiceNoTextFieldActionPerformed(evt);
            }
        });
        searchInvoiceNoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchInvoiceNoTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchInvoiceNoTextFieldKeyTyped(evt);
            }
        });
        jPanel.add(searchInvoiceNoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1123, 96, 207, 29));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1397, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
//        ProductCategoryBean pBean = (ProductCategoryBean)prodCatComboBox.getSelectedItem();
//        if(pBean == null) return;
        try{
            if(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9'){
//                Vector v = DatabaseManager.getProduct(pBean.getProdCatIdd());
                Vector v = DatabaseManager.getProduct();
                if(v.size() == 0)return;
                itemJList.setListData(v);
            }
            else{
//                Vector v = DatabaseManager.getProductByName(pBean.getProdCatIdd(),searchTextField.getText());
                Vector v = DatabaseManager.getProductByName(searchTextField.getText());
                if(v.size() == 0)return;
                itemJList.setListData(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchTextFieldKeyTyped
    
    private void itemJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_itemJListValueChanged
        if(!evt.getValueIsAdjusting()){
            ProductBean pBean = (ProductBean)itemJList.getSelectedValue();
            if(pBean == null) return;
            
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            int rowCount = jTable.getModel().getRowCount();
            
            int qty = 1;
            Object data[] = {pBean.getProdId(), pBean.getProdName(),pBean.getPrice(),qty,pBean.getPrice()};
            
            jTable.setRowHeight(25);
            jTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
            jTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            
            if(rowCount <= 0 ){
                model.addRow(data);
            }//End of outer of
            else if(rowCount >= 0){
                boolean exists = false;
                for (int index = 0; index < rowCount && !exists; index++) {
                    if(model.getValueAt(index, 0).equals(pBean.getProdId())){
                        int tempQty = Integer.parseInt(String.valueOf(model.getValueAt(index, 3)));
                        model.setValueAt(tempQty += 1, index, 3);
                        exists = true;
                    }//End of inner if
                }
                if (!exists) {
                    model.addRow(data);
                }
            }//End of else if
        
            
            ///Calculation...
            totalProductTextField.setText(""+model.getRowCount());
            int totalQty = 0;
            int totalAmount = 0;
            for(int k=0; k<model.getRowCount(); k++){
                totalQty += Integer.parseInt(String.valueOf(model.getValueAt(k , 3)));
                totalAmount += Integer.parseInt(String.valueOf(model.getValueAt(k , 4)));
            }
            totalQtyTextField.setText(""+totalQty);
            totalAmountTextField.setText(""+totalAmount);
            
            itemJList.clearSelection();
            
        }
    }//GEN-LAST:event_itemJListValueChanged
    
    private void invoiceNoJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_invoiceNoJListValueChanged
        if(!evt.getValueIsAdjusting()){
            try{
                isAllowToWork = false;
                DefaultTableModel model = (DefaultTableModel)jTable.getModel();
                model.setRowCount(0);
                BillBean bean = (BillBean)invoiceNoJList.getSelectedValue();
                if(bean == null) return;

                String customerName = "";
                isAllowToWork = true;

                Vector v = DatabaseManager.getBill(bean.getBill_id());
                if(v.size() == 0)return;
                
                for(int i=0; i<v.size(); i++){
                    TransactionBean tBean = (TransactionBean)v.elementAt(i);

                    model.addRow(new Object[]{
                        tBean.getProdId(),
                        tBean.getProdName(),
                        tBean.getUnitPrice(),
                        tBean.getQuantity(),
                        tBean.getTotalPrice()
                    });
                    customerName = tBean.getCustomerName();
                }//End of for
                
                jTable.setRowHeight(25);
                jTable.getColumnModel().getColumn(0).setPreferredWidth(70);
                jTable.getColumnModel().getColumn(1).setPreferredWidth(350);
                jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
                jTable.getColumnModel().getColumn(3).setPreferredWidth(50);
                jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            

                String item = customerName;
                boolean exists = false;
                for (int index = 0; index < customerComboBox.getItemCount() && !exists; index++) {
                    if (item.equals(customerComboBox.getItemAt(index))) {
                        exists = true;
                        customerComboBox.setSelectedIndex(index);
                    }
                }
                if (!exists) {
                    customerComboBox.addItem(item);
                }

                paidAmountTextField.setText(""+bean.getCash_paid());
                returnAmountTextField.setText(""+bean.getCash_back());

                int totalAmount = 0;
                int totalQty = 0;
                for(int k=0; k<jTable.getModel().getRowCount(); k++){
                    totalQty += Integer.parseInt(String.valueOf(jTable.getValueAt(k , 3)));
                    totalAmount += Integer.parseInt(String.valueOf(jTable.getValueAt(k , 4)));
                }
                totalQtyTextField.setText(""+totalQty);
                totalAmountTextField.setText(""+totalAmount);

                totalProductTextField.setText(""+model.getRowCount());

                String item1 = new String(bean.getDiscount());
                boolean exists1 = false;
                for (int index1 = 0; index1 < discountJComboBox.getItemCount() && !exists1; index1++) {
                    if (item1.equals(discountJComboBox.getItemAt(index1))) {
                        exists1 = true;
                        discountJComboBox.setSelectedIndex(index1);
                    }
                }
                if (!exists1) {
                    discountJComboBox.addItem(item1);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_invoiceNoJListValueChanged

    private void totalProductTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalProductTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalProductTextFieldActionPerformed

    private void discountJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountJComboBoxActionPerformed
        DefaultTableModel model = (DefaultTableModel)jTable.getModel();
        int totalAmount = 0;
        for(int k=0; k<model.getRowCount(); k++){
            totalAmount += Integer.parseInt(String.valueOf(model.getValueAt(k , 4)));
        }
        double discountPercentage = Double.parseDouble(String.valueOf(discountJComboBox.getSelectedItem()));
        double discount = discountPercentage/100;
        double saveByCostomer = (totalAmount*discount);
        double finalAmount = (totalAmount-saveByCostomer);
        totalAmountTextField.setText(""+finalAmount);
        
        if(paidAmountTextField.getText().length() > 0){
            int setPaidAmount = ( Integer.parseInt(paidAmountTextField.getText()) - (int)finalAmount);
            returnAmountTextField.setText(""+setPaidAmount);
        }
    }//GEN-LAST:event_discountJComboBoxActionPerformed

    private void discountJComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_discountJComboBoxPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_discountJComboBoxPropertyChange

    private void paidAmountTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidAmountTextFieldKeyReleased
       
        String paidAmountStr = this.paidAmountTextField.getText();
        if(paidAmountStr.length() > 0 ){
            try {
                double totalAmount = Double.parseDouble(totalAmountTextField.getText());
                int paidAmount = Integer.parseInt(paidAmountTextField.getText());
                int  returnAmount = (paidAmount-(int)totalAmount);
                returnAmountTextField.setText(""+returnAmount);


                isValidate = true;
            } catch (NumberFormatException ee) {
               isValidate = false;
               Toolkit.getDefaultToolkit().beep();
               msg = "Paid Amount Must be Number/numeric!";
//               javax.swing.JOptionPane.showMessageDialog(null, msg);
            }
        }else{
            returnAmountTextField.setText("");
        }
    }//GEN-LAST:event_paidAmountTextFieldKeyReleased

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if(jTable.getRowCount() > 0){
            if(paidAmountTextField.getText().length()>=1){
                addBillAndTransaction();
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this,"Please Enter Paid Amount.");
            }
        }else{
             Toolkit.getDefaultToolkit().beep();
             javax.swing.JOptionPane.showMessageDialog(null, "Please select Product first!");
        }
        
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        Toolkit.getDefaultToolkit().beep();
        int permission = JOptionPane.showConfirmDialog(this,"Are you want to clear Fields!!","CONFIRAMATION", JOptionPane.YES_NO_OPTION);
        if(permission == 0){
            clear();
        }
        else{
            System.out.println("Permission Denied...");
        }
    }//GEN-LAST:event_clearBtnActionPerformed

    private void refundJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refundJButtonActionPerformed
        if(this.invoiceNoJList.getSelectedIndex()>=0){
            refundTransaction();
            isValidate = true;
        }else{
            Toolkit.getDefaultToolkit().beep();
            javax.swing.JOptionPane.showMessageDialog(null, "Please select Bill Date!");
        }
    }//GEN-LAST:event_refundJButtonActionPerformed

    private void removeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJButtonActionPerformed
       if(jTable.getSelectedRow() >= 0){
            DefaultTableModel dm = (DefaultTableModel)jTable.getModel();
            isAllowToWork = false;

            int rowCount = dm.getRowCount();
            if(rowCount<=0){
                System.out.println("Empty");
            }
            else{
                //Remove rows one by one from the end of the table
                int selectedIndexs[] = jTable.getSelectedRows();
                for (int i = selectedIndexs.length - 1; i >= 0; i--) {
                    dm.removeRow(selectedIndexs[i]);
                }
                DefaultTableModel model = (DefaultTableModel)jTable.getModel();
                int totalAmount = 0;
                int totalQty = 0;
                for(int k=0; k<jTable.getModel().getRowCount(); k++){
                    totalQty += Integer.parseInt(String.valueOf(jTable.getValueAt(k , 3)));
                    totalAmount += Integer.parseInt(String.valueOf(jTable.getValueAt(k , 4)));
                }
                totalQtyTextField.setText(""+totalQty);
                totalAmountTextField.setText(""+totalAmount);

                totalProductTextField.setText(""+model.getRowCount());
                
            }
            isAllowToWork = true;
        
        }else{
            Toolkit.getDefaultToolkit().beep();
            javax.swing.JOptionPane.showMessageDialog(null, "Please select row than click remove button!");
        }
    }//GEN-LAST:event_removeJButtonActionPerformed

    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
        if(evt.getKeyChar() >= '0'  && evt.getKeyChar() <= '9'){
            String barcode = searchTextField.getText();
//            if(barcode.length() >  13){
//                JOptionPane.showMessageDialog(this, "Please Enter only 13 digits barcode..");
//                searchTextField.setText("");
//                return;
//            }
            if(barcode.length() >= 8){
                try{
                    Vector v = DatabaseManager.getProductByBarcode(barcode);
                    if(v.size() == 0 )return;
                    
                    String tempProdName = "";
                    int prodCatId = 0;
                    for(int j=0; j<v.size(); j++){
                        ProductBean pBean = (ProductBean)v.elementAt(j);
                        tempProdName = pBean.getProdName();
                        prodCatId = pBean.getProdCatId();
                    }
                    
                   boolean exists = false;
                   for(int k=0; k<itemJList.getModel().getSize() && !exists; k++){
                        if(String.valueOf(itemJList.getModel().getElementAt(k)).equals(tempProdName)){
                            itemJList.setSelectedIndex(k);
                            exists = true;
                        }
//                        if(!exists){
//                            JOptionPane.showMessageDialog(null, "Item not found..");
//                        }
                    }

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

    
//    private boolean getCategoryByBarcode(String tempProdName){
//        boolean exits = false;
//        for(int k=0; k<itemJList.getModel().getSize() && !exits; k++){
//            if(String.valueOf(itemJList.getModel().getElementAt(k)).equals(tempProdName)){
//                itemJList.setSelectedIndex(k);
//                exits = true;
//            }
//        }
//        if(!exits)
//            return false;
//        else 
//            return true;
//    }//getCategoryByBarcode()
    
    
    
    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void itemJListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_itemJListPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_itemJListPropertyChange

    private void itemJListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemJListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_itemJListMouseClicked

    private void itemJListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemJListMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_itemJListMouseReleased

    private void totalAmountTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalAmountTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAmountTextFieldKeyReleased

    private void totalQtyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalQtyTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalQtyTextFieldActionPerformed
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void paidAmountTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_paidAmountTextFieldFocusLost
        String text = this.paidAmountTextField.getText();
        try {
            int temp = Integer.parseInt(text);
            isValidate = true;
            
            int returnAmount = Integer.parseInt(returnAmountTextField.getText());
            if(returnAmount < 0){
                isValidate = false;
                Toolkit.getDefaultToolkit().beep();
                msg = "Sorry you have't enough amount for Shopping!";
                javax.swing.JOptionPane.showMessageDialog(null, msg);
            }
           
         
        } catch (NumberFormatException ee) {
           isValidate = false;
           Toolkit.getDefaultToolkit().beep();
           msg = "Paid Amount Must be Number/numeric!";
           javax.swing.JOptionPane.showMessageDialog(null,msg );
        }
    }//GEN-LAST:event_paidAmountTextFieldFocusLost

    private void searchInvoiceNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInvoiceNoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceNoTextFieldActionPerformed
    
    private void searchInvoiceNoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInvoiceNoTextFieldKeyReleased
//        try{
//            
//            String invoiceNoStr = searchInvoiceNoTextField.getText().trim();
//            long invoiceNo =  Long.parseLong(invoiceNoStr);
//            
//            Vector v  = DatabaseManager.getBill(invoiceNo);
//            if(v == null)return;
//            
//            int billId = 0;
//            TransactionBean bean = null;
//            for(int i=0; i<v.size(); i++){
//                bean = (TransactionBean)v.elementAt(i);
//                billId = bean.getBill_id();
//            }
//
//            boolean exists = false;
//            for(int k=0; k<invoiceNoJList.getModel().getSize() && !exists; k++){
//                if(String.valueOf(invoiceNoJList.getModel().getElementAt(k)).equals(String.valueOf(billId))){
//                    invoiceNoJList.setSelectedIndex(k);
//                    exists = true;
//                }
//            }
//            if(!exists){
////                JOptionPane.showMessageDialog(null, "Item not found..");
//                getPreviousInvoiceData(invoiceNo);
//            }
//            
//            searchInvoiceNoTextField.setText("");
//        }catch (NumberFormatException ee) {
//            System.out.println(ee.getMessage());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_searchInvoiceNoTextFieldKeyReleased

    private void searchInvoiceNoTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInvoiceNoTextFieldKeyTyped
        try{
            
            String invoiceNoStr = searchInvoiceNoTextField.getText().trim();
            long invoiceNo = Long.parseLong(invoiceNoStr);  
            
            Vector v  = DatabaseManager.getBill(invoiceNo);
            if(v == null)return;
            
            int billId = 0;
            TransactionBean bean = null;
            for(int i=0; i<v.size(); i++){
                bean = (TransactionBean)v.elementAt(i);
                billId = bean.getBill_id();
            }

            boolean exists = false;
            for(int k=0; k<invoiceNoJList.getModel().getSize() && !exists; k++){
                if(String.valueOf(invoiceNoJList.getModel().getElementAt(k)).equals(String.valueOf(billId))){
                    invoiceNoJList.setSelectedIndex(k);
                    exists = true;
                    searchInvoiceNoTextField.setText("");
                }
            }
            if(!exists){
//                JOptionPane.showMessageDialog(null, "Item not found..");
                getPreviousInvoiceData(invoiceNo);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchInvoiceNoTextFieldKeyTyped

    private void paidAmountTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidAmountTextFieldKeyTyped
       char ch=evt.getKeyChar();
        if(!Character.isDigit(ch) && ch!=java.awt.event.KeyEvent.VK_LEFT && ch!=java.awt.event.KeyEvent.VK_RIGHT 
          && ch!=java.awt.event.KeyEvent.VK_BACK_SPACE && ch!=java.awt.event.KeyEvent.VK_DELETE 
          && (ch!=java.awt.event.KeyEvent.VK_CONTROL && ch!=java.awt.event.KeyEvent.VK_A || ch!=java.awt.event.KeyEvent.VK_C 
                || ch!=java.awt.event.KeyEvent.VK_V || ch!=java.awt.event.KeyEvent.VK_X) && (ch!='.'))
          evt.consume();
        else
          if(paidAmountTextField.getText().indexOf(".")!=-1 && ch=='.'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
          }
    }//GEN-LAST:event_paidAmountTextFieldKeyTyped

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
            java.util.logging.Logger.getLogger(SaleBookJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaleBookJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaleBookJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaleBookJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaleBookJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JLabel counterJLabel;
    private static javax.swing.JComboBox customerComboBox;
    private javax.swing.JLabel dateJLabel;
    private javax.swing.JComboBox discountJComboBox;
    private javax.swing.JList invoiceNoJList;
    private static javax.swing.JList itemJList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable;
    private static javax.swing.JTextField paidAmountTextField;
    private javax.swing.JButton refundJButton;
    private javax.swing.JButton removeJButton;
    private static javax.swing.JTextField returnAmountTextField;
    private javax.swing.JTextField searchInvoiceNoTextField;
    private javax.swing.JTextField searchTextField;
    private static javax.swing.JTextField totalAmountTextField;
    private static javax.swing.JTextField totalProductTextField;
    private static javax.swing.JTextField totalQtyTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(TableModelEvent e) {
        if(isAllowToWork == true){
            int row = e.getFirstRow();
            int column = e.getColumn();

            if(column>=5)return;


            String unitPrice = String.valueOf( jTable.getValueAt(row, 2));
            String qty =       String.valueOf( jTable.getValueAt(row, 3));

            if(column==2 ||column==3){
                int unitPriceInt =     Integer.parseInt(unitPrice);
                int qtyInt = Integer.parseInt(qty);
                jTable.setValueAt(unitPriceInt*qtyInt,row,4);

                int totalAmount = 0;
                int totalQty = 0;
                for(int k=0; k<jTable.getModel().getRowCount(); k++){
                    totalQty += Integer.parseInt(String.valueOf(jTable.getValueAt(k , 3)));
                    totalAmount += Integer.parseInt(String.valueOf(jTable.getValueAt(k , 4)));
                }
                totalQtyTextField.setText(""+totalQty);
                totalAmountTextField.setText(""+totalAmount);

                if(paidAmountTextField.getText().length() > 0){
                    int paidAmount = Integer.parseInt(paidAmountTextField.getText());
                    int temp = (paidAmount-totalAmount);
                    returnAmountTextField.setText(""+temp);
                }

            }//End of if
        }//End of isAllowToWork
        
        
    }//End of method

    
   
}// END OF MAIN CLASS
