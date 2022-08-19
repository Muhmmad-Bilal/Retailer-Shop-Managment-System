/*/
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailershopmanagmentsystem;

import Beans.EmployeBean;
import Beans.UsersBean;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import databaseManager.DatabaseManager;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Maham Computers
 */
public class EmployeJFrame extends javax.swing.JFrame {

    static FileOutputStream fout_front = null;
    static FileInputStream fin_front = null;
    
    static FileOutputStream fout_back = null;
    static FileInputStream fin_back = null;
    
    static ByteArrayOutputStream baos = null;
    static ByteArrayInputStream bais = null;
    
    static BufferedImage bufferImage_front = null;
    static BufferedImage bufferImage_back = null;
    static byte frontImageData[] = null;
    static byte backImageData[] = null;
    
    boolean isFront;
    //For camera
    

    
    
    
    public EmployeJFrame() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        passwordJPanel.setSize(0, 0);
        getEmploye();
        
    }//End of constructor
   
    
    
    
    public javax.swing.JPanel getPanel(){
        return jPanel;
    }
    
    private void getEmploye(){
        try{
            Vector v = databaseManager.DatabaseManager.getEmploye();
            cnicJList.setListData(v);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of getEmploye
    
    private void clear(){
        employeTypeJComboBox.setSelectedIndex(0);
        allowtedCounterNoJComboBox.setSelectedIndex(0);
        cnicJTextField.setText("");
        mobileNojTextField.setText("");
        emailjTextField.setText("");
        PasswordField.setText("");
        reTypePasswordField.setText("");
        frontCnicJLabel.setText("FRONT CNIC");
        frontCnicJLabel.setIcon(null);
        backCnicJLabel.setText("BACK CNIC");
        backCnicJLabel.setIcon(null);
    }//End of clear
    
    
     private void addEmploye(){
        try{ 
            
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
            String cnicNo = cnicJTextField.getText().replaceAll("-", "");
            String  mobileNo = mobileNojTextField.getText();
            String email = emailjTextField.getText();
            if(cnicNo.trim().length()>=1 &&  mobileNo.trim().length()>=1){
                if(PasswordField.getText().equals(reTypePasswordField.getText())){
                    String password = EncoderAndDecoder.encrypt(reTypePasswordField.getText(), "storeManagment!!!");
                    String employeType = ""+employeTypeJComboBox.getSelectedItem();
                    String allowtedCounterNo =  "";
                    if(!allowtedCounterNoJComboBox.getSelectedItem().equals("--SELECT--")){
                        allowtedCounterNo =  ""+allowtedCounterNoJComboBox.getSelectedItem();
                    }
                    int maxEmployeId = databaseManager.DatabaseManager.getMaxEmployeId();
                    int rows = databaseManager.DatabaseManager.addEmploye(cnicNo, mobileNo, email, employeType,allowtedCounterNo, date, "0");


                    if(rows >= 1){
                        if(password.length() > 0){
                            databaseManager.DatabaseManager.addSignUpData((maxEmployeId+1), cnicNo, password);
                        }
                        CNIC_COPYES();
                        JOptionPane.showMessageDialog(this, rows + " Recorde Addded..");
                    }
                    getEmploye();
                    clear();
                }else 
                    javax.swing.JOptionPane.showMessageDialog(null, "Re-type Password mismatch!!");
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this,"Fields Can`t be Empty please fill out.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of addEmploye
     
     
     private void updateEmploye(){
        try{ 
            
            
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
            String cnicNo = cnicJTextField.getText().replaceAll("-", "");
            String  mobileNo = mobileNojTextField.getText();
            String email = emailjTextField.getText();
            if(cnicNo.trim().length()>=1 &&  mobileNo.trim().length()>=1){
                
                EmployeBean bean = (EmployeBean)cnicJList.getSelectedValue();
                if(bean == null)return;
            
                if(PasswordField.getText().equals(reTypePasswordField.getText())){
                    String password = EncoderAndDecoder.encrypt(reTypePasswordField.getText(), "storeManagment!!!");
                    String employeType = ""+employeTypeJComboBox.getSelectedItem();
                    String allowtedCounterNo =  "";
                    if(!allowtedCounterNoJComboBox.getSelectedItem().equals("--SELECT--")){
                        allowtedCounterNo =  ""+allowtedCounterNoJComboBox.getSelectedItem();
                    }
                    int rows = databaseManager.DatabaseManager.updateEmploye(bean.getEmpId(),cnicNo, mobileNo, email, employeType,allowtedCounterNo, date, "");


                    if(rows >= 1){
                        if(password.length() > 0){
                            UsersBean uBean = databaseManager.DatabaseManager.getUser(bean.getEmpId());
                            if(uBean == null ) return;
                            databaseManager.DatabaseManager.UpdateSignupData(uBean.getRecNo(),bean.getEmpId(), cnicNo, password);
                        }
                        CNIC_COPYES();                    
                        JOptionPane.showMessageDialog(this, rows + " Recorde Updated..");
                    }
                    getEmploye();
                    clear();
                }else javax.swing.JOptionPane.showMessageDialog(null, "Re-type Password mismatch!!");
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this,"Fields Can`t be Empty please fill out.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of updateEmploye
     
    
     private void deleteSelectedEmployes(){
        try{
            int size[] = cnicJList.getSelectedIndices();
            cnicJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            int rows=0;            
            for(int i=0; i<size.length; i++){
                EmployeBean bean = (EmployeBean)cnicJList.getModel().getElementAt(size[i]);
                JOptionPane.showMessageDialog(null, bean.getEmpId()+" ---  "+i);
                if (bean == null ) continue;
                UsersBean uBean = databaseManager.DatabaseManager.getUser(bean.getEmpId());
                if(uBean == null ) continue;
                rows += DatabaseManager.DeleteSignupData(uBean.getRecNo());
                rows += DatabaseManager.deleteEmploye(bean.getEmpId());
                deleteCnic(bean.getCnicNO());
            }//end of for
            if(rows >= 1){
                JOptionPane.showMessageDialog(this,rows+" Recorde removed..");
                getEmploye();
                clear();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of deleteSelectedEmployes
     
    private void deleteCnic(String cnicNo){
       try{
            File file_front = new File(new File("CnicCopys//"+cnicNo.concat("_F").concat(".PNG")).getAbsolutePath());
            File file_back = new File(new File("CnicCopys//"+cnicNo.concat("_B").concat(".PNG")).getAbsolutePath());
            if(file_front.exists()){
                 file_front.delete();
                 System.out.println(cnicNo+"  Front CNIC deleted!");
            }
            if(file_back.exists()){
               file_back.delete();
               System.out.println(cnicNo+"  Back CNIC deleted!");
            }
       }catch(Exception e){
           e.printStackTrace();
       }
    }//End of deleteCnic 
    
    private void uploadeImageFront(){
        try{
            int index_front=filename_front.indexOf('.');
            String subStr_front=filename_front.substring(index_front,filename_front.length());
            FileOutputStream frontCnic=new FileOutputStream(new File("CnicCopys").getAbsolutePath()+"\\"+cnicJTextField.getText().replaceAll("-", "").concat("_F")+subStr_front);
            FileInputStream upFile_front=new FileInputStream(filename_front);
            byte frontData[]=new byte[upFile_front.available()];
            upFile_front.read(frontData);
            frontCnic.write(frontData);
            frontCnic.close();
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of uploadeImageFront()
    private void uploadeImageBack(){
        try{
            int index_back=filename_back.indexOf('.');
            String subStr_back=filename_back.substring(index_back,filename_back.length());
            FileOutputStream backCnic=new FileOutputStream(new File("CnicCopys").getAbsolutePath()+"\\"+cnicJTextField.getText().replaceAll("-", "").concat("_B")+subStr_back);
            FileInputStream upFile_back=new FileInputStream(filename_back);
            byte backData[]=new byte[upFile_back.available()];
            upFile_back.read(backData);
            backCnic.write(backData);
            backCnic.close();
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }//End of uploadeImageBack()
    
    private void CNIC_COPYES(){
        try{
             //CNIC COYIES
            if(bufferImage_front != null && bufferImage_back != null){
                javax.swing.JOptionPane.showMessageDialog(null, new ImageIcon(bufferImage_front));
                fout_front = new FileOutputStream(new File("CnicCopys//"+cnicJTextField.getText().replaceAll("-","").concat("_F")+".jpg").getAbsolutePath());
                fout_back = new FileOutputStream(new File("CnicCopys//"+cnicJTextField.getText().replaceAll("-","").concat("_B")+".jpg").getAbsolutePath());
                fout_front.write(frontImageData, 0, frontImageData.length);
                fout_back.write(backImageData, 0, backImageData.length);
            }

            else if(bufferImage_front == null && bufferImage_back != null ){
                javax.swing.JOptionPane.showMessageDialog(this,"Front uploded Back Scanned");
                fout_back = new FileOutputStream(new File("CnicCopys//"+cnicJTextField.getText().replaceAll("-","").concat("_B")+".jpg").getAbsolutePath());
                fout_back.write(backImageData, 0, backImageData.length);
                uploadeImageFront();
            }
            else if(bufferImage_front != null && bufferImage_back == null ){
                javax.swing.JOptionPane.showMessageDialog(this,"Back uploded Front Scanned!");
                fout_front = new FileOutputStream(new File("CnicCopys//"+cnicJTextField.getText().replaceAll("-","").concat("_F")+".jpg").getAbsolutePath());
                fout_front.write(frontImageData, 0, frontImageData.length);
                uploadeImageBack();
            }
            else if(frontCnicJLabel.getIcon() != null && backCnicJLabel.getIcon() != null){
                uploadeImageFront();
                uploadeImageBack();
            }//End of else if
            
            fout_front.close();
            fout_back.close();
            baos.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//End of CNIC_COPYES
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cnicJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        mobileNojTextField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        cnicJList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        emailjTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        addJButton = new javax.swing.JButton();
        upateJButton = new javax.swing.JButton();
        clearJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        frontCnicJLabel = new javax.swing.JLabel();
        backCnicJLabel = new javax.swing.JLabel();
        frontBrowserJButton = new javax.swing.JButton();
        frontScanJButton = new javax.swing.JButton();
        backScanJButton = new javax.swing.JButton();
        backBrowserJButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        employeTypeJComboBox = new javax.swing.JComboBox();
        passwordJPanel = new javax.swing.JPanel();
        PasswordField = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        reTypePasswordField = new javax.swing.JPasswordField();
        warningJLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        allowtedCounterNoJComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setPreferredSize(new java.awt.Dimension(1293, 582));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("EMPLOYE REGISTRATION");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("EMPLOYE TYPE");

        cnicJTextField.setFont(new java.awt.Font("Arial", 1, 12));
        cnicJTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cnicJTextFieldMouseReleased(evt);
            }
        });
        cnicJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnicJTextFieldActionPerformed(evt);
            }
        });
        cnicJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cnicJTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cnicJTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cnicJTextFieldKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("MOBILE NO");

        mobileNojTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mobileNojTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobileNojTextFieldActionPerformed(evt);
            }
        });
        mobileNojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mobileNojTextFieldKeyPressed(evt);
            }
        });

        cnicJList.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        cnicJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cnicJListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(cnicJList);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CNICS");

        emailjTextField.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        emailjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailjTextFieldActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("EMAIL");

        addJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addJButton.setText("ADD");
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });

        upateJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        upateJButton.setText("UPDATE");
        upateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upateJButtonActionPerformed(evt);
            }
        });

        clearJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearJButton.setText("CLEAR");
        clearJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJButtonActionPerformed(evt);
            }
        });

        deleteJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteJButton.setText("DELETE");
        deleteJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CNIC COPY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monospaced", 1, 14))); // NOI18N

        frontCnicJLabel.setBackground(new java.awt.Color(102, 102, 102));
        frontCnicJLabel.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        frontCnicJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        frontCnicJLabel.setText("FRONT CNIC");
        frontCnicJLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        backCnicJLabel.setBackground(new java.awt.Color(153, 153, 153));
        backCnicJLabel.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        backCnicJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backCnicJLabel.setText("BACK CNIC");
        backCnicJLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        frontBrowserJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        frontBrowserJButton.setText("BROWSER");
        frontBrowserJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frontBrowserJButtonActionPerformed(evt);
            }
        });

        frontScanJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        frontScanJButton.setText("SCAN");
        frontScanJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frontScanJButtonActionPerformed(evt);
            }
        });

        backScanJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backScanJButton.setText("SCAN");
        backScanJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backScanJButtonActionPerformed(evt);
            }
        });

        backBrowserJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backBrowserJButton.setText("BROWSER");
        backBrowserJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBrowserJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(frontCnicJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backCnicJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backScanJButton)
                .addGap(18, 18, 18)
                .addComponent(backBrowserJButton)
                .addGap(72, 72, 72))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(79, 102, Short.MAX_VALUE)
                    .addComponent(frontScanJButton)
                    .addGap(18, 18, 18)
                    .addComponent(frontBrowserJButton)
                    .addGap(0, 496, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frontCnicJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backCnicJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backScanJButton)
                    .addComponent(backBrowserJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 246, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(frontScanJButton)
                        .addComponent(frontBrowserJButton))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("CNIC");

        employeTypeJComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        employeTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Counter And Retail Clerk", "Retail Manager", "Sales Manager", "Merchandise Displayers", "Stock Clerk", "security guard", "sweeper", "Owner" }));
        employeTypeJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeTypeJComboBoxActionPerformed(evt);
            }
        });

        PasswordField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("PASSWORD");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setText("RE-TYPE PASSWORD");

        reTypePasswordField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        reTypePasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reTypePasswordFieldActionPerformed(evt);
            }
        });
        reTypePasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                reTypePasswordFieldKeyReleased(evt);
            }
        });

        warningJLabel.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        warningJLabel.setForeground(new java.awt.Color(255, 0, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("<HTML>\nALLOWTED <BR>\nCOUNTER NO\n</HTML>");

        allowtedCounterNoJComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        allowtedCounterNoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--SELECT--", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        javax.swing.GroupLayout passwordJPanelLayout = new javax.swing.GroupLayout(passwordJPanel);
        passwordJPanel.setLayout(passwordJPanelLayout);
        passwordJPanelLayout.setHorizontalGroup(
            passwordJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordJPanelLayout.createSequentialGroup()
                .addGroup(passwordJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(passwordJPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10))
                    .addGroup(passwordJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(passwordJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(passwordJPanelLayout.createSequentialGroup()
                        .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(passwordJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(warningJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reTypePasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
                    .addComponent(allowtedCounterNoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        passwordJPanelLayout.setVerticalGroup(
            passwordJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(passwordJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(passwordJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(passwordJPanelLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(reTypePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningJLabel)
                .addGap(8, 8, 8)
                .addGroup(passwordJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allowtedCounterNoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(551, 551, 551)
                        .addComponent(jLabel1))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(addJButton)
                        .addGap(37, 37, 37)
                        .addComponent(upateJButton)
                        .addGap(21, 21, 21)
                        .addComponent(clearJButton)
                        .addGap(21, 21, 21)
                        .addComponent(deleteJButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cnicJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(employeTypeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(mobileNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(emailjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(passwordJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cnicJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(employeTypeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mobileNojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(emailjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(passwordJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addJButton)
                    .addComponent(upateJButton)
                    .addComponent(clearJButton)
                    .addComponent(deleteJButton)))
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
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mobileNojTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobileNojTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileNojTextFieldActionPerformed

    private void cnicJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_cnicJListValueChanged
       if(!evt.getValueIsAdjusting()){
            try{
                EmployeBean bean = (EmployeBean)cnicJList.getSelectedValue();
                if(bean == null) return;

                String item = bean.getEmployeType();
                boolean exists = false;
                for (int index = 0; index < employeTypeJComboBox.getItemCount() && !exists; index++) {
                    if (item.equals(employeTypeJComboBox.getItemAt(index))) {
                        exists = true;
                        employeTypeJComboBox.setSelectedIndex(index);
                    }
                }
                if (!exists) {
                    employeTypeJComboBox.addItem(item);
                }
                cnicJTextField.setText(bean.getCnicNO());
                mobileNojTextField.setText(bean.getMobilleNO());
                emailjTextField.setText(bean.getEmail());

                frontCnicJLabel.setText("");
                backCnicJLabel.setText("");


                UsersBean uBean = databaseManager.DatabaseManager.getUser(bean.getEmpId());
                if(uBean == null ) return;
                PasswordField.setText(""+EncoderAndDecoder.decrypt(uBean.getPassword(),"storeManagment!!!"));
                
                if(bean.getAllowted_counter_no() != null){
                    String item1 = bean.getAllowted_counter_no();
                    boolean exists1 = false;
                    for (int index1 = 0; index1 < allowtedCounterNoJComboBox.getItemCount() && !exists1; index1++) {
                        if (item1.equals(allowtedCounterNoJComboBox.getItemAt(index1))) {
                            exists1 = true;
                            allowtedCounterNoJComboBox.setSelectedIndex(index1);
                        }
                    }
                    if (!exists1) {
                        allowtedCounterNoJComboBox.addItem(item1);
                    }
                }
                
                boolean existFrontFile = false;
                boolean existBackFile = false;
                
                File file_front = new File(new File("CnicCopys//"+bean.getCnicNO().concat("_F")+".jpg").getAbsolutePath());
                File file_back = new File(new File("CnicCopys//"+bean.getCnicNO().concat("_B")+".jpg").getAbsolutePath());
                if(file_front.exists())
                    existFrontFile = true;
                
                 if(file_back.exists())
                    existBackFile = true;
                
                if(existFrontFile){
                    fin_front = new FileInputStream(file_front);
                    int size = fin_front.available();
                    byte data[] = new byte[size];
                    fin_front.read(data, 0, size);
                    bais = new ByteArrayInputStream(data,0,size);
                    bufferImage_front= ImageIO.read(bais);

                    baos = new ByteArrayOutputStream(); 
                    ImageIO.write(bufferImage_front,"PNG",baos);
                    frontImageData = baos.toByteArray();
                    
                    frontCnicJLabel.setIcon(new ImageIcon(new ImageIcon(new File("CnicCopys//"+bean.getCnicNO().concat("_F")+".jpg").getAbsolutePath()).getImage().getScaledInstance(frontCnicJLabel.getWidth(), frontCnicJLabel.getHeight(), Image.SCALE_SMOOTH)));
                 
                }
                
                if(existBackFile){
                    fin_back = new FileInputStream(file_back);
                    int size = fin_back.available();
                    byte data[] = new byte[size];
                    fin_back.read(data, 0, size);
                    bais = new ByteArrayInputStream(data,0,size);
                    bufferImage_back= ImageIO.read(bais);

                    baos = new ByteArrayOutputStream(); 
                    ImageIO.write(bufferImage_back,"PNG",baos);
                    backImageData = baos.toByteArray();
                    
                    backCnicJLabel.setIcon(new ImageIcon(new ImageIcon(new File("CnicCopys//"+bean.getCnicNO().concat("_B")+".jpg").getAbsolutePath()).getImage().getScaledInstance(frontCnicJLabel.getWidth(), frontCnicJLabel.getHeight(), Image.SCALE_SMOOTH)));
                    fin_front.close();
                    fin_back.close();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
       }
    }//GEN-LAST:event_cnicJListValueChanged

    private void emailjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailjTextFieldActionPerformed
    String filename_front;
    String filename_back;
    private void frontBrowserJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frontBrowserJButtonActionPerformed
       FileDialog dialog=new FileDialog(new JFrame(),"Upload",FileDialog.LOAD);
        dialog.setVisible(true);
        filename_front=dialog.getFile();
        String directory=dialog.getDirectory();
        
        if(filename_front!=null){
            filename_front=directory+filename_front;
            
        frontCnicJLabel.setText("");
        frontCnicJLabel.setIcon(new ImageIcon(new ImageIcon(filename_front).getImage().getScaledInstance(frontCnicJLabel.getWidth(), frontCnicJLabel.getHeight(), Image.SCALE_SMOOTH)));

        }
    }//GEN-LAST:event_frontBrowserJButtonActionPerformed

    private void backBrowserJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBrowserJButtonActionPerformed
        FileDialog dialog=new FileDialog(new JFrame(),"Upload",FileDialog.LOAD);
        dialog.setVisible(true);
        filename_back=dialog.getFile();
        String directory=dialog.getDirectory();
        
        if(filename_back!=null){
            filename_back=directory+filename_back;
        
        backCnicJLabel.setText("");
        backCnicJLabel.setIcon(new ImageIcon(new ImageIcon(filename_back).getImage().getScaledInstance(backCnicJLabel.getWidth(), backCnicJLabel.getHeight(), Image.SCALE_SMOOTH)));

        }
    }//GEN-LAST:event_backBrowserJButtonActionPerformed

    private void frontScanJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frontScanJButtonActionPerformed
         try{
             isFront = true;
            new TakeSnapshotFromVideo().setVisible(true);
            
//            Webcam webcam = Webcam.getDefault();
//            if (webcam != null) {
//                    System.out.println("Webcam: " + webcam.getName());
//            } else {
//                    System.out.println("No webcam detected");
//            }
//            webcam.setViewSize(new Dimension(640, 480));
//            webcam.open();
//            //Image reading...
//            baos = new ByteArrayOutputStream(); 
//            bufferImage_front = webcam.getImage();
//            ImageIO.write(bufferImage_front,"PNG",baos);
//            frontImageData = baos.toByteArray();
//            frontCnicJLabel.setText("");
//            frontCnicJLabel.setIcon(new ImageIcon(new ImageIcon(bufferImage_front).getImage().getScaledInstance(frontCnicJLabel.getWidth(), frontCnicJLabel.getHeight(), Image.SCALE_SMOOTH)));
//            webcam.close();
       }catch(Exception e){
           e.printStackTrace();
       }
       
    }//GEN-LAST:event_frontScanJButtonActionPerformed

    private void backScanJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backScanJButtonActionPerformed
        try{
            isFront = false;
            new TakeSnapshotFromVideo().setVisible(true);
//            Webcam webcam = Webcam.getDefault();
//            if (webcam != null) {
//                    System.out.println("Webcam: " + webcam.getName());
//            } else {
//                    System.out.println("No webcam detected");
//            }
//            webcam.setViewSize(new Dimension(640, 480));
//            webcam.open();
//           //Image reading...
//            baos = new ByteArrayOutputStream(); 
//            bufferImage_back = webcam.getImage();
//            ImageIO.write(bufferImage_back,"png",baos);
//            backImageData = baos.toByteArray();
//            backCnicJLabel.setText("");
//            backCnicJLabel.setIcon(new ImageIcon(new ImageIcon(bufferImage_back).getImage().getScaledInstance(backCnicJLabel.getWidth(), backCnicJLabel.getHeight(), Image.SCALE_SMOOTH)));
//            webcam.close();
       }catch(Exception e){
           e.printStackTrace();
       }
    }//GEN-LAST:event_backScanJButtonActionPerformed

    private void cnicJTextFieldMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cnicJTextFieldMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cnicJTextFieldMouseReleased

    private void cnicJTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnicJTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cnicJTextFieldKeyReleased

    private void cnicJTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnicJTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cnicJTextFieldKeyTyped

    private void cnicJTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnicJTextFieldKeyPressed
        int length = cnicJTextField.getText().length();
        if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
              if(length < 15){
                if(length == 5)cnicJTextField.setText(cnicJTextField.getText().concat("-"));
                else if(length ==13)cnicJTextField.setText(cnicJTextField.getText().concat("-"));
                cnicJTextField.setEditable(true);
              }
              else{
                  cnicJTextField.setEditable(false);
              }       
        }
        else if(evt.getKeyChar()>='a' && evt.getKeyChar()<='z' || evt.getKeyChar()>='A' && evt.getKeyChar()<='Z'){
            javax.swing.JOptionPane.showMessageDialog(null,"Invalid Cnic!");
            cnicJTextField.setText("");
        }
        else{
              if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
                  cnicJTextField.setEditable(true);
              }
              else{
                  cnicJTextField.setEditable(false);
              }       
        }
    }//GEN-LAST:event_cnicJTextFieldKeyPressed

    private void mobileNojTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mobileNojTextFieldKeyPressed
        int length = mobileNojTextField.getText().length();
        if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
              if(length < 11){
                  mobileNojTextField.setEditable(true);
              }
              else{
                  mobileNojTextField.setEditable(false);
              }       
        } 
        else{
              if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
                  mobileNojTextField.setEditable(true);
              }
              else{
                  mobileNojTextField.setEditable(false);
              }       
        }
    }//GEN-LAST:event_mobileNojTextFieldKeyPressed

    private void reTypePasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reTypePasswordFieldKeyReleased
        if(!reTypePasswordField.getText().equals(PasswordField.getText())){
            warningJLabel.setText("Re-Type Passwaord mismatch!!");
            if(reTypePasswordField.getText().isEmpty()==true){
                warningJLabel.setText("");
            }
        }
        
        else{
            warningJLabel.setText("");
        }
    }//GEN-LAST:event_reTypePasswordFieldKeyReleased
    
    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        addEmploye();
    }//GEN-LAST:event_addJButtonActionPerformed

    private void employeTypeJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeTypeJComboBoxActionPerformed
        String employeType = ""+employeTypeJComboBox.getSelectedItem();
        if(employeType.equals("Retail Manager") || employeType.equals("Sales Manager")||
            employeType.equals("Merchandise Displayers") || employeType.equals("Stock Clerk") ||
            employeType.equals("Owner")){
            passwordJPanel.setSize(810, 91);
        }
        else{
            passwordJPanel.setSize(0, 0);
        }
    }//GEN-LAST:event_employeTypeJComboBoxActionPerformed

    private void reTypePasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reTypePasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reTypePasswordFieldActionPerformed

    private void clearJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearJButtonActionPerformed

    private void upateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upateJButtonActionPerformed
        if(cnicJList.getSelectedIndex()>=0){
            updateEmploye();
        }else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,"Plaese Select CNIC from list");
        }
    }//GEN-LAST:event_upateJButtonActionPerformed

    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJButtonActionPerformed
        if(cnicJList.getSelectedIndex()>=0){
            int permission = JOptionPane.showConfirmDialog(this,"Are you want to DELETE Recorde!!","CONFIRAMATION", JOptionPane.YES_NO_OPTION);
            if(permission == 0){
               deleteSelectedEmployes();

            }
            else{
                System.out.println("Permission Denied...");
            }
        }else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,"Plaese Select CNIC from list");
        }
    }//GEN-LAST:event_deleteJButtonActionPerformed

    private void cnicJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnicJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnicJTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton addJButton;
    private javax.swing.JComboBox allowtedCounterNoJComboBox;
    private javax.swing.JButton backBrowserJButton;
    private javax.swing.JLabel backCnicJLabel;
    private javax.swing.JButton backScanJButton;
    private javax.swing.JButton clearJButton;
    private javax.swing.JList cnicJList;
    private javax.swing.JTextField cnicJTextField;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JTextField emailjTextField;
    private javax.swing.JComboBox employeTypeJComboBox;
    private javax.swing.JButton frontBrowserJButton;
    private javax.swing.JLabel frontCnicJLabel;
    private javax.swing.JButton frontScanJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField mobileNojTextField;
    private javax.swing.JPanel passwordJPanel;
    private javax.swing.JPasswordField reTypePasswordField;
    private javax.swing.JButton upateJButton;
    private javax.swing.JLabel warningJLabel;
    // End of variables declaration//GEN-END:variables

    public class TakeSnapshotFromVideo extends JFrame {
        private final Executor executor = Executors.newSingleThreadExecutor();
        private final Dimension size = new Dimension(640, 480);
        private final List<Webcam> webcams = Webcam.getWebcams();
        private final List<WebcamPanel> panels = new ArrayList<WebcamPanel>();
        private final JButton btSnapMe = new JButton(new SnapMeAction());
        private final JButton btStart = new JButton(new StartAction());

        public TakeSnapshotFromVideo() {
            for (Webcam webcam : webcams) {
                webcam.setViewSize(size);
                WebcamPanel panel = new WebcamPanel(webcam, size, false);
                panel.setFPSDisplayed(true);
                panel.setFillArea(true);
                panels.add(panel);
            }

            btSnapMe.setEnabled(false);

            setLayout(new FlowLayout());
            for (WebcamPanel panel : panels) {
                    add(panel);
            }
            Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
            btSnapMe.setCursor(cursor);
            btStart.setCursor(cursor);
            btSnapMe.setFont(new Font("Arial", Font.BOLD,20));
            btStart.setFont(new Font("Arial", Font.BOLD,20));
            
            add(btSnapMe);
            add(btStart);
            setResizable(false);
            pack();
            setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }//End of contructor
        
        public class SnapMeAction extends AbstractAction {

            public SnapMeAction() {
                super("CAPTURE");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < webcams.size(); i++) {
                        Webcam webcam = webcams.get(i);
                        if(isFront == true){
                            //Image reading...
                            File f=new File( new File("music//scanBeep.wav").getAbsolutePath());
                            URL url=f.toURL();
                            AudioClip clip=Applet.newAudioClip(url);
                            clip.play();
                            
                            baos = new ByteArrayOutputStream(); 
                            bufferImage_front = webcam.getImage();
                            ImageIO.write(bufferImage_front,"PNG",baos);
                            frontImageData = baos.toByteArray();
                            frontCnicJLabel.setText("");
                            frontCnicJLabel.setIcon(new ImageIcon(new ImageIcon(bufferImage_front).getImage().getScaledInstance(frontCnicJLabel.getWidth(), frontCnicJLabel.getHeight(), Image.SCALE_SMOOTH)));
                            webcam.close();
                            setVisible(false);
                        }
                        else{
                            //Image reading...
                            File f=new File( new File("music//scanBeep.wav").getAbsolutePath());
                            URL url=f.toURL();
                            AudioClip clip=Applet.newAudioClip(url);
                            clip.play();
                            
                            baos = new ByteArrayOutputStream(); 
                            bufferImage_back = webcam.getImage();
                            ImageIO.write(bufferImage_back,"PNG",baos);
                            backImageData = baos.toByteArray();
                            backCnicJLabel.setText("");
                            backCnicJLabel.setIcon(new ImageIcon(new ImageIcon(bufferImage_back).getImage().getScaledInstance(backCnicJLabel.getWidth(), backCnicJLabel.getHeight(), Image.SCALE_SMOOTH)));
                            webcam.close();
                            setVisible(false);
                        }

                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        }

        public class StartAction extends AbstractAction implements Runnable {

                public StartAction() {
                    super("START");
                }

                @Override
                public void actionPerformed(ActionEvent e) {

                    btStart.setEnabled(false);
                    btSnapMe.setEnabled(true);
                    executor.execute(this);
                }

                @Override
                public void run() {
                    for (WebcamPanel panel : panels) {
                        panel.start();
                    }
                }
        }


    }//End of TakeSnapshotFromVideo Class
    
    
    
    
}//End of Main Classs
