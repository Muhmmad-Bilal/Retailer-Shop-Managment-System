package resources;

import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

/**
 *
 * @author IB
 */
public class Resources {
    
    public static void validateFrame(javax.swing.JFrame frame){
        Field[] fields=frame.getClass().getDeclaredFields();
        
        for(Field f:fields){
            f.setAccessible(true);
            try{
                if(f.getType()==JTextField.class || f.getType()==JTextArea.class){

                    JTextComponent  textField=(JTextComponent)f.get(frame);
                    
                    if(textField.isEnabled() && !f.getName().toLowerCase().contains("search") && 
                        f.getName().toLowerCase().endsWith("i")){

                        textField.addFocusListener(new java.awt.event.FocusAdapter() {
                            public void focusLost(java.awt.event.FocusEvent fe){
                              String text=textField.getText();
                              if(text.trim().equals("")){
                                textField.removeAll();
                                setImage(new File("Images//alert.png").getAbsolutePath(), textField);
                                new javax.swing.JFrame().getToolkit().beep();
                                textField.setText("\r");
                              }
                            }

                            public void focusGained(java.awt.event.FocusEvent fe){
                              if(textField.getText().trim().equals("")){
                                textField.removeAll();
                                textField.setBackground(java.awt.Color.white);
                              }
                            }
                        });

                        textField.addKeyListener(new java.awt.event.KeyAdapter() {

                            public void keyReleased(java.awt.event.KeyEvent ke){
                                if(!textField.getText().trim().equals("")){
                                  textField.setBackground(java.awt.Color.white);
                                  textField.removeAll();
                                }
                                else{
                                  textField.removeAll();
                                  setImage(new File("Images//alert.png").getAbsolutePath(), textField);
                                  new javax.swing.JFrame().getToolkit().beep();
                                  textField.setText("\r");
                                }
                            }
                        });
                    }//End textFields if
                }else if(f.getType()==javax.swing.JButton.class){
                    javax.swing.JButton btn=(javax.swing.JButton)f.get(frame);
                    if(f.getName().toLowerCase().contains("exit"))
                        btn.addActionListener(new java.awt.event.ActionListener(){
                            public void actionPerformed(java.awt.event.ActionEvent ae){
                                frame.dispose();
                            }
                        });
                    else if(f.getName().toLowerCase().contains("clear"))
                        btn.addActionListener(new java.awt.event.ActionListener(){
                            public void actionPerformed(java.awt.event.ActionEvent ae){
                                clearFieldsData(frame);
                            }
                        });
                    else if(f.getName().toLowerCase().contains("update") || f.getName().toLowerCase().contains("delete"))
                        btn.setEnabled(false);
                    else if(f.getName().toLowerCase().contains("update") || f.getName().toLowerCase().contains("delete") ||
                            f.getName().toLowerCase().contains("add")){
                        btn.addActionListener(new java.awt.event.ActionListener(){
                            public void actionPerformed(java.awt.event.ActionEvent ae){
                                validateFields(frame);
                            }
                        });
                    }
                }else if(f.getType()==javax.swing.ListSelectionModel.class || f.getType()==javax.swing.JList.class){
                    final javax.swing.ListSelectionModel selectionModel;
                    if(f.getType()==javax.swing.ListSelectionModel.class){
                        selectionModel=(javax.swing.ListSelectionModel)f.get(frame);
                    }else{
                        javax.swing.JList list=(javax.swing.JList)f.get(frame);
                        selectionModel=(javax.swing.ListSelectionModel)list.getSelectionModel();
                    }
                    
                    if(selectionModel!=null)
                        selectionModel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                                try {
                                    Field addField=frame.getClass().getDeclaredField("addBtn");
                                    Field updateField=frame.getClass().getDeclaredField("updateBtn");
                                    Field deleteField=frame.getClass().getDeclaredField("deleteBtn");
                                    
                                    addField.setAccessible(true);
                                    updateField.setAccessible(true);
                                    deleteField.setAccessible(true);

                                    javax.swing.JButton addBtn=(javax.swing.JButton)addField.get(frame);
                                    javax.swing.JButton updateBtn=(javax.swing.JButton)updateField.get(frame);
                                    javax.swing.JButton deleteBtn=(javax.swing.JButton)deleteField.get(frame);
                                    
                                    addBtn.setEnabled(selectionModel.isSelectionEmpty());
                                    updateBtn.setEnabled(!selectionModel.isSelectionEmpty());
                                    deleteBtn.setEnabled(!selectionModel.isSelectionEmpty());
                                } catch (NoSuchFieldException |SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//End of validateFrame method
    
    public static void validateFields(javax.swing.JFrame frame){
        Field[] fields=frame.getClass().getDeclaredFields();
        boolean check=false;
        
        for(Field f:fields){
            f.setAccessible(true);
                    
            try{
                if(f.getType()==JTextField.class){

                    JTextField textField=(JTextField)f.get(frame);
                    
                    if(textField.getText().equals("") && textField.isEnabled() && !f.getName().toLowerCase().contains("search") && 
                            f.getName().toLowerCase().endsWith("i")){
                      setImage(new File("Images//alert.png").getAbsolutePath(), textField);
                      check=true;
                      textField.setText("\r");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        if(check)
            new javax.swing.JFrame().getToolkit().beep();
    }//End of validateFields method
    
    public static boolean isFormCompleted(javax.swing.JFrame frame){
        Field[] fields=frame.getClass().getDeclaredFields();
        
        for(Field f:fields){
            f.setAccessible(true);
            try{
                if(f.getType()==JTextField.class || f.getType()==JTextArea.class){

                    JTextComponent  textField=(JTextComponent)f.get(frame);
                    if(textField.getText().trim().isEmpty() && textField.isEnabled() &&
                            !f.getName().toLowerCase().contains("search") && f.getName().toLowerCase().endsWith("i")){
                        return false;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
            
        return true;
    }//End of isFormCompleted
    
    public static void clearFieldsData(javax.swing.JFrame frame){
        Field[] fields=frame.getClass().getDeclaredFields();
        
        for(Field f:fields){
            f.setAccessible(true);
            try{
                if(f.getType()==JTextField.class || f.getType()==JTextArea.class){

                        JTextComponent  textField=(JTextComponent)f.get(frame);
                        if(textField.isEnabled() && !f.getName().toLowerCase().contains("search"))
                                    textField.setText("");

                }else if(f.getType()==javax.swing.JButton.class){
                    javax.swing.JButton btn=(javax.swing.JButton)f.get(frame);
                    if(f.getName().toLowerCase().contains("update") || f.getName().toLowerCase().contains("delete"))
                        btn.setEnabled(false);
                    else if(f.getName().toLowerCase().contains("add"))
                        btn.setEnabled(true);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }//End of clearFieldsData method
        
    public static void setImage(String path,javax.swing.JComponent field){
        java.awt.Image img=new javax.swing.ImageIcon(path).getImage().getScaledInstance(field.getHeight()-10,field.getHeight()-10, java.awt.Image.SCALE_SMOOTH);
        javax.swing.JLabel alertLabel = new javax.swing.JLabel(new javax.swing.ImageIcon(img));
        field.setLayout(new java.awt.BorderLayout());
        field.setBackground(new java.awt.Color(247, 141, 141));
        field.add(alertLabel, java.awt.BorderLayout.EAST);
    }//End of setImage method
    
    
    
}//End of Resources class