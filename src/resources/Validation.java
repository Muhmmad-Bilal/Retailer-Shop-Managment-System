package resources;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.text.JTextComponent;

public class Validation{
  
 
  public static void messageBox(String msg,Exception error){
    error.printStackTrace();
    messageBox(msg,error+"");
  }//End of messageBox method
  
  
  
  public static void messageBox(String status){
    messageBox("",status);
  }//End of messageBox method
  
  public static void messageBox(String msg,String status){
    javax.swing.JOptionPane.showMessageDialog(null, (msg.equals("")?"":msg+" ")+status);
  }//End of messageBox method
  
  public static void clearFormData(javax.swing.text.JTextComponent[]  field){
    clearFields(field);
  }//End of clearFormData method
  
  public static void clearFormData(javax.swing.text.JTextComponent[] field, javax.swing.JComboBox[] combo){
    clearFormData(field, combo, null);
  }//End of clearFormData method
  
  public static void clearFormData(javax.swing.text.JTextComponent[] field,javax.swing.JComboBox[] combo,String[] comboDefault){
    clearFormData(field, combo, comboDefault, null, null);
  }//End of clearFormData method
  
  public static void clearFormData(javax.swing.text.JTextComponent[] field,javax.swing.JComboBox[] combo,String comboDefault[], javax.swing.JList list,javax.swing.JTable table){
    if(field!=null)
      clearFields(field);
    
    if(combo!=null){
      clearCombo(combo, comboDefault);
    }
    
    if(list!=null)
      list.clearSelection();
    
    if(table!=null)
      table.clearSelection();
    
  }//End of clearFormData method
  
  public static void clearCombo(javax.swing.JComboBox[] combo,String[] values){
    for(int i=0;i<combo.length;i++){
        if(values.length==combo.length)
          combo[i].setSelectedItem(values[i]);
        else
          combo[i].setSelectedIndex(0);
      }
  }//End of clearCombo method
  
  public static void clearFields(javax.swing.text.JTextComponent[] field,javax.swing.ListSelectionModel list){
    list.clearSelection();
    clearFields(field);
  }//End of clearFields method
  
  public static void clearFields(javax.swing.text.JTextComponent[] field){
    for(int i=0;i<field.length;i++)
        field[i].setText("");
  }//End of clearFields method
  
  public static boolean checkFields(javax.swing.text.JTextComponent[] field){
    boolean check=false;
    int fields=0;
    for(int k=0;k<field.length;k++){
      if(field[k].getText().isEmpty()){
        setImage(new File("Images//alert.png").getAbsolutePath(), field[k]);
        check=true;
        fields++;
        field[k].setText("\r");
        field[k].setText("");
      }
    }
    if(check)
      messageBox(fields+" Field(s) empty!!");
    
    return check;
  }//End of validateFormDataMethod 
  
  //ValidateFormData(new JTextComponent[]{rollNoField,aa,aa..},new JButton[]{addBtn,updateBtn});
  public static void validateFormData(javax.swing.text.JTextComponent[] textField,javax.swing.JButton[] btn){
    if(textField!=null && btn!=null){
      for(int i=0;i<btn.length;i++)
        btn[i].addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            boolean check=false;
            for(int k=0;k<textField.length;k++){
              if(textField[k].getText().equals("")){
                setImage(new File("Images//alert.png").getAbsolutePath(), textField[k]);
                check=true;
                textField[k].setText("\r");
              }
            }
            if(check)
              new javax.swing.JFrame().getToolkit().beep();
          }
        });
    }
  }//End of validateFormData method

  
  public static boolean validateFormData(javax.swing.text.JTextComponent[] textField,javax.swing.JComboBox<String>[] date,javax.swing.JButton[] btn){
    boolean check=false;
    
    if(textField!=null && date!=null && btn!=null){
      
      for(int i=0;i<btn.length;i++)
        btn[i].addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            boolean check=false;
            for(int k=0;k<textField.length;k++){
              if(textField[k].getText().equals("")){
                setImage("Images/alert.png", textField[k]);
                check=true;
                textField[k].setText("\r");
              }
            }
            if(check)
              new javax.swing.JFrame().getToolkit().beep();
          }
        });
    }
    return check;
  }//End of validateFormData method
  
  
  public static void validateNumberFields(javax.swing.text.JTextComponent[] field){
    for(int i=0;i<field.length;i++)
      validateNumberField(field[i]);
  }//End of validateNumberFields methods
  
  public static void validateNumberField(javax.swing.text.JTextComponent field){
    validateField(field, "number");
  }//End of validateNumberField
  
  public static void validateField(javax.swing.text.JTextComponent[] field){
    for(int i=0;i<field.length;i++)
      validateField(field[i]);
  }//End of validateFields method
  
  public static void validateField(javax.swing.text.JTextComponent field){
    validateField(field,"");
  }//End of validateField method
  

  public static void validateField(javax.swing.text.JTextComponent field,String type){
    
    //FocusListener Event
    field.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent fe){
        String text=field.getText();
        if(text.trim().equals("")){
          field.removeAll();
          setImage(new File("Images//alert.png").getAbsolutePath(), field);
          new javax.swing.JFrame().getToolkit().beep();
          field.setText("\r");
        }
      }
      
      public void focusGained(java.awt.event.FocusEvent fe){
        if(field.getText().trim().equals("")){
          field.removeAll();
          field.setBackground(java.awt.Color.white);
        }
      }
    });
    
    
    //KeyListener Event
    field.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent ke){
        if(type.equals("number")){
          char ch=ke.getKeyChar();
          if(!Character.isDigit(ch) && ch!=java.awt.event.KeyEvent.VK_LEFT && ch!=java.awt.event.KeyEvent.VK_RIGHT 
            && ch!=java.awt.event.KeyEvent.VK_BACK_SPACE && ch!=java.awt.event.KeyEvent.VK_DELETE 
            && (ch!=java.awt.event.KeyEvent.VK_CONTROL && ch!=java.awt.event.KeyEvent.VK_A || ch!=java.awt.event.KeyEvent.VK_C 
                  || ch!=java.awt.event.KeyEvent.VK_V || ch!=java.awt.event.KeyEvent.VK_X) && (ch!='.'))
            ke.consume();
          else
            if(field.getText().indexOf(".")!=-1 && ch=='.'){
              ke.consume();
              new javax.swing.JFrame().getToolkit().beep();
            }
         }
      }
      
      public void keyReleased(java.awt.event.KeyEvent ke){
        if(!field.getText().trim().equals("")){
          field.setBackground(java.awt.Color.white);
          field.removeAll();
        }
        else{
//          field.setBackground(new java.awt.Color(247, 141, 141));
          field.removeAll();
          setImage(new File("Images//alert.png").getAbsolutePath(), field);
          new javax.swing.JFrame().getToolkit().beep();
          field.setText("\r");
        }
      }
    });
  }//End of validateNumberField method
  
  public static void setImage(String path,javax.swing.JComponent field){
    java.awt.Image img=new javax.swing.ImageIcon(path).getImage().getScaledInstance(field.getHeight()-10,field.getHeight()-10, java.awt.Image.SCALE_SMOOTH);
    javax.swing.JLabel alertLabel = new javax.swing.JLabel(new javax.swing.ImageIcon(img));
    field.setLayout(new java.awt.BorderLayout());
    field.setBackground(new java.awt.Color(247, 141, 141));
    field.add(alertLabel, java.awt.BorderLayout.EAST);
  
  }//End of setImage method
  

  
  
}//End of class
