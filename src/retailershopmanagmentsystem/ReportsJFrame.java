package retailershopmanagmentsystem;

import Beans.BillBean;
import Beans.CustomerBean;
import Beans.ProductBean;
import Beans.ProductCategoryBean;
import Beans.TransactionBean;
import Recipte.ReciptPrint;
import databaseManager.DatabaseManager;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class ReportsJFrame extends javax.swing.JFrame{

   int counter = 1;
   
   
    public ReportsJFrame() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        
        getTodaysTransaction("S");
        
        
    }
    
    
    public javax.swing.JPanel getPanel(){
        return jPanel;
    }
    
    
    
    private void getTransactionByDate(String fromDate , String toDate ,String type) {
        try{
            java.sql.Date fromDatee = java.sql.Date.valueOf(fromDate);
            java.sql.Date toDatee = java.sql.Date.valueOf(toDate);
            
            Vector v = DatabaseManager.getSaleOrPurchaseByDate(fromDatee, toDatee, type);
            if(v.size() == 0)return;
            
                DefaultTableModel model = (DefaultTableModel)jTable.getModel();
                model.setRowCount(0);
                counter = 1;
                
                for(int i=0; i<v.size(); i++){
                    TransactionBean tBean = (TransactionBean)v.elementAt(i);

                    model.addRow(new Object[]{
                        counter++,
                        tBean.getDateOfTrans(),
                        tBean.getBill_id(),
                        tBean.getUnitPrice(),
                        tBean.getQuantity(),
                        tBean.getTotalPrice(),
                        tBean.getRemarks()
                    });
                }//End of for
                
                jTable.setRowHeight(25);
                jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
//                jTable.getColumnModel().getColumn(1).setPreferredWidth(90);
//                jTable.getColumnModel().getColumn(2).setPreferredWidth(50);
//                jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
//                jTable.getColumnModel().getColumn(4).setPreferredWidth(200);
//                jTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
     private void getTodaysTransaction(String type) {
        try{
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
            Vector v = DatabaseManager.getSaleOrPurchase(date,type);
            if(v.size() == 0)return;
            
                DefaultTableModel model = (DefaultTableModel)jTable.getModel();
                model.setRowCount(0);
                counter = 1;
                
                for(int i=0; i<v.size(); i++){
                    TransactionBean tBean = (TransactionBean)v.elementAt(i);

                    model.addRow(new Object[]{
                        counter++,
                        tBean.getDateOfTrans(),
                        tBean.getBill_id(),
                        tBean.getUnitPrice(),
                        tBean.getQuantity(),
                        tBean.getTotalPrice(),
                        tBean.getRemarks()
                    });
                }//End of for
                
                jTable.setRowHeight(25);
                jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
//                jTable.getColumnModel().getColumn(1).setPreferredWidth(90);
//                jTable.getColumnModel().getColumn(2).setPreferredWidth(50);
//                jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
//                jTable.getColumnModel().getColumn(4).setPreferredWidth(200);
//                jTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
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
    
    
    private String convertJDateChosserDate(String date){
        try{
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(date));
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String d = f.format(cal.getTime());
        return d;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        csvJButton = new javax.swing.JButton();
        pdfJButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        reportTypeJComboBox = new javax.swing.JComboBox();
        fromJDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        toJDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        searchJButton = new javax.swing.JButton();

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
        jLabel1.setText("REPORTS FRAME");
        jPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 21, 1296, 34));

        jTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.NO", "TRANS DATE", "BILL ID", "UNIT PRICE", "TOTAL PRODUCTS", " TOTAL AMOUNT", "TYPE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        jPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 165, 1143, 447));

        csvJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        csvJButton.setText("CSV");
        csvJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvJButtonActionPerformed(evt);
            }
        });
        jPanel.add(csvJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1011, 129, 93, 30));

        pdfJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pdfJButton.setText("PDF");
        pdfJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfJButtonActionPerformed(evt);
            }
        });
        jPanel.add(pdfJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1114, 129, 110, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("REPORT TYPE");
        jPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 95, -1, 29));

        reportTypeJComboBox.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reportTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sale Report", "Purchase Report", "Both" }));
        reportTypeJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportTypeJComboBoxActionPerformed(evt);
            }
        });
        jPanel.add(reportTypeJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 130, 160, 29));

        fromJDateChooser.setDateFormatString("yyyy-MM-d");
        fromJDateChooser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel.add(fromJDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 130, 186, 29));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("FROM DATE");
        jPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 95, -1, 29));

        toJDateChooser.setDateFormatString("yyyy-MM-d");
        toJDateChooser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        toJDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                toJDateChooserPropertyChange(evt);
            }
        });
        toJDateChooser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                toJDateChooserKeyReleased(evt);
            }
        });
        jPanel.add(toJDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 130, 186, 29));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("TO DATE");
        jPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 95, -1, 29));

        searchJButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        searchJButton.setText("SEARCH");
        searchJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJButtonActionPerformed(evt);
            }
        });
        jPanel.add(searchJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 132, -1, -1));

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
            String Tital= "S.NO"+","+"TRANS DATE"+","+"BILL ID"+","+"TOTAL PRODUCT "+","+"TYPE"+","+"TOTAL AMOUNT\n";
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
            
            new ReportListPrint(data);
            JOptionPane.showMessageDialog(null,"List Printed");
            } catch(Exception e){
                e.printStackTrace();
            }
    }//GEN-LAST:event_pdfJButtonActionPerformed

    private void jTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableMousePressed

    private void reportTypeJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportTypeJComboBoxActionPerformed
        if(reportTypeJComboBox.getSelectedItem().equals("Sale Report")){
            getTodaysTransaction("S");
        }//End of if
        if(reportTypeJComboBox.getSelectedItem().equals("Purchase Report")){
           getTodaysTransaction("P");
        }//End of if
        if(reportTypeJComboBox.getSelectedItem().equals("Both")){
            getTodaysTransaction("B");
        }
    }//GEN-LAST:event_reportTypeJComboBoxActionPerformed

    private void toJDateChooserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_toJDateChooserKeyReleased
//      javax.swing.JOptionPane.showMessageDialog(null, toJDateChooser.getDate());
    }//GEN-LAST:event_toJDateChooserKeyReleased

    private void toJDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_toJDateChooserPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_toJDateChooserPropertyChange

    private void searchJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJButtonActionPerformed
       try {
            
           String fromDate = convertJDateChosserDate(String.valueOf(fromJDateChooser.getDate()));
           String toDate = convertJDateChosserDate(String.valueOf(toJDateChooser.getDate()));
           
            if(reportTypeJComboBox.getSelectedItem().equals("Sale Report")){
                getTransactionByDate(fromDate,toDate,"S");
            }//End of if
            if(reportTypeJComboBox.getSelectedItem().equals("Purchase Report")){
               getTransactionByDate(fromDate,toDate,"P");
            }//End of if
            if(reportTypeJComboBox.getSelectedItem().equals("Both")){
                getTransactionByDate(fromDate,toDate,"B");
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchJButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ReportsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportsJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton csvJButton;
    private com.toedter.calendar.JDateChooser fromJDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JButton pdfJButton;
    private javax.swing.JComboBox reportTypeJComboBox;
    private javax.swing.JButton searchJButton;
    private com.toedter.calendar.JDateChooser toJDateChooser;
    // End of variables declaration//GEN-END:variables

    
    
    
}//End of Main Class
