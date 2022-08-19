package databaseManager;

import Beans.BillBean;
import Beans.CreditCardNumberBean;
import Beans.CreditTypeBean;
import Beans.CustomerBean;
import Beans.EmployeBean;
import Beans.ProductBean;
import Beans.ProductCategoryBean;
import Beans.TransactionBean;
import Beans.UsersBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

public class DatabaseManager {
    static Connection con;
   
    
    static{
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String path =new java.io.File("E:\\soft\\RetailerShopManagment.accdb").getAbsolutePath();
            String url = "jdbc:ucanaccess://"+path;
            con = DriverManager.getConnection(url);
            System.out.println("Connection Establised");
        }catch(Exception e){
            e.printStackTrace();
        }
}// end of static block
    
    public static Vector getProductCategory()throws Exception{
        String query = "select * from product_category";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               ProductCategoryBean bean = new ProductCategoryBean();
               bean.setProdCatIdd(result.getInt("prod_cat_id"));
               bean.setProdCatName(result.getString("prod_cat_name"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductCategory
    
    public static ProductCategoryBean getProductCategory(int prodCatId)throws Exception{
        String query = "select * from product_category WHERE prod_cat_id="+prodCatId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           ProductCategoryBean bean = null;
           while(result.next()){
               bean = new ProductCategoryBean();
               bean.setProdCatIdd(result.getInt("prod_cat_id"));
               bean.setProdCatName(result.getString("prod_cat_name"));
               bean.setRemarks(result.getString("remarks"));
            
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductCategory
    
    
    
    public static Vector getProduct(int prod_cad_id)throws Exception{
        String query = "select * from product where prod_cat_id="+prod_cad_id;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               ProductBean bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProduct
    
    public static ProductBean getProductByCatId(int prod_cad_id)throws Exception{
        String query = "select * from product where prod_cat_id="+prod_cad_id;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           ProductBean bean = null;
           while(result.next()){
               bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
            
           }
              return bean;
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductByCatId
    
     public static Vector getProductForSale()throws Exception{
        String query = "select * from product Where quantity > 0 ";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               ProductBean bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
               v.addElement(bean);
           }
              return v;
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProduct

    public static Vector getProduct()throws Exception{
        String query = "select * from product";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               ProductBean bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProduct
    
    public static ProductBean getProductForBill(int prod_id)throws Exception{
        String query = "select * from product where prod_id="+prod_id;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           ProductBean bean = null;
           while(result.next()){
               bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
            
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductForBill
    
    
    public static Vector getProductByName(int prod_cad_id ,String ProductName)throws Exception{
        String query = "select * from product where prod_cat_id="+prod_cad_id+" AND prod_name like '"+ProductName+"%'";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               ProductBean bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductByName  
    
    
    
    public static Vector getProductByBarcode(String barcode , Vector v)throws Exception{
        String query = "select * from product where barcode like '"+barcode+"'";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           while(result.next()){
               ProductBean bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductByBarcode
    
    public static Vector getProductByBarcode(int prod_cad_id , String barcode)throws Exception{
        String query = "select * from product where prod_cat_id="+prod_cad_id+" AND barcode like '"+barcode+"'";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               ProductBean bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductByBarcode
    
    public static Vector getProductByName(String ProductName)throws Exception{
        String query = "select * from product where prod_name like '"+ProductName+"%'";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               ProductBean bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductByName  
    
    
    
    public static Vector getProductByBarcode(String barcode)throws Exception{
        String query = "select * from product where barcode='"+barcode+"'";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               ProductBean bean = new ProductBean();
               bean.setProdId(result.getInt("prod_id"));
               bean.setProdCatId(result.getInt("prod_cat_id"));
               bean.setProdName(result.getString("prod_name"));
               bean.setBarcode(result.getString("barcode"));
               bean.setPrice(result.getInt("price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotal(result.getInt("total"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getProductByName
    
    
    public static Vector getTransaction(int billId, int productId)throws Exception{
        String query = "select * from transaction where bill_id="+billId+" and prod_id="+productId+"";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               TransactionBean bean = new TransactionBean();
               bean.setTransactionId(result.getInt("trans_id"));
               bean.setCustomerId(result.getInt("bill_id"));
               bean.setProdId(result.getInt("prod_id"));
               bean.setCardId(result.getInt("card_id"));
               bean.setDateOfTrans(result.getDate("date_of_trans"));
               bean.setUnitPrice(result.getInt("unit_price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotalPrice(result.getInt("total_price"));
               bean.setAmountType(result.getString("amount_type"));
               bean.setTransType(result.getString("transaction_type"));
               bean.setCounterNo(result.getInt("counter_no"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getTransaction
    
    public static Vector getTransaction(int billId, int productId , String tranType)throws Exception{
        String query = "select * from transaction where bill_id="+billId+" AND prod_id="+productId+" AND transaction_type LIKE '%"+tranType+"%' ";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               TransactionBean bean = new TransactionBean();
               bean.setTransactionId(result.getInt("trans_id"));
               bean.setCustomerId(result.getInt("bill_id"));
               bean.setProdId(result.getInt("prod_id"));
               bean.setCardId(result.getInt("card_id"));
               bean.setDateOfTrans(result.getDate("date_of_trans"));
               bean.setUnitPrice(result.getInt("unit_price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotalPrice(result.getInt("total_price"));
               bean.setAmountType(result.getString("amount_type"));
               bean.setTransType(result.getString("transaction_type"));
               bean.setCounterNo(result.getInt("counter_no"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getTransaction
    
    
    public static Vector getTransaction(int billId)throws Exception{
        String query = "select * from transaction where bill_id="+billId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               TransactionBean bean = new TransactionBean();
               bean.setTransactionId(result.getInt("trans_id"));
               bean.setCustomerId(result.getInt("bill_id"));
               bean.setProdId(result.getInt("prod_id"));
               bean.setCardId(result.getInt("card_id"));
               bean.setDateOfTrans(result.getDate("date_of_trans"));
               bean.setUnitPrice(result.getInt("unit_price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotalPrice(result.getInt("total_price"));
               bean.setAmountType(result.getString("amount_type"));
               bean.setTransType(result.getString("transaction_type"));
               bean.setCounterNo(result.getInt("counter_no"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getTransaction
    
        
    
    public static TransactionBean getTransactionByTransId(int transId)throws Exception{
        String query = "select * from transaction where trans_id="+transId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           TransactionBean bean = null;
           while(result.next()){
               bean = new TransactionBean();
               bean.setTransactionId(result.getInt("trans_id"));
               bean.setCustomerId(result.getInt("bill_id"));
               bean.setProdId(result.getInt("prod_id"));
               bean.setCardId(result.getInt("card_id"));
               bean.setDateOfTrans(result.getDate("date_of_trans"));
               bean.setUnitPrice(result.getInt("unit_price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotalPrice(result.getInt("total_price"));
               bean.setAmountType(result.getString("amount_type"));
               bean.setTransType(result.getString("transaction_type"));
               bean.setCounterNo(result.getInt("counter_no"));
               bean.setRemarks(result.getString("remarks"));
            
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getTransactionByTransId
    
    public static TransactionBean getTransactionByBillId(int billId)throws Exception{
        String query = "select * from transaction where bill_id="+billId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           TransactionBean bean = null;
           while(result.next()){
               bean = new TransactionBean();
               bean.setTransactionId(result.getInt("trans_id"));
               bean.setCustomerId(result.getInt("bill_id"));
               bean.setProdId(result.getInt("prod_id"));
               bean.setCardId(result.getInt("card_id"));
               bean.setDateOfTrans(result.getDate("date_of_trans"));
               bean.setUnitPrice(result.getInt("unit_price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotalPrice(result.getInt("total_price"));
               bean.setAmountType(result.getString("amount_type"));
               bean.setTransType(result.getString("transaction_type"));
               bean.setCounterNo(result.getInt("counter_no"));
               bean.setRemarks(result.getString("remarks"));
            
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getTransactionByBillId
    
    
    
    public static TransactionBean getTransactionByInvoiceNo(int transId)throws Exception{
//        String query = "select * from transaction where trans_id like '"+transId+"%'";
        String query = "select * from transaction where trans_id="+transId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           TransactionBean bean = null;
           while(result.next()){
               bean = new TransactionBean();
               bean.setTransactionId(result.getInt("trans_id"));
               bean.setCustomerId(result.getInt("bill_id"));
               bean.setProdId(result.getInt("prod_id"));
               bean.setCardId(result.getInt("card_id"));
               bean.setDateOfTrans(result.getDate("date_of_trans"));
               bean.setUnitPrice(result.getInt("unit_price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotalPrice(result.getInt("total_price"));
               bean.setAmountType(result.getString("amount_type"));
               bean.setTransType(result.getString("transaction_type"));
               bean.setCounterNo(result.getInt("counter_no"));
               bean.setRemarks(result.getString("remarks"));
            
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getTransaction
    
    public static Vector getTransactionByDate(Date date , String transaction_type)throws Exception{
        String remarks = transaction_type.toLowerCase().equals("s")?"SOLD":"PURCHASE";
        String query = "select * from transaction WHERE date_of_trans=#"+date+"# AND transaction_type='"+transaction_type+"' AND remarks='"+remarks+"'";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               TransactionBean bean = new TransactionBean();
               bean.setTransactionId(result.getInt("trans_id"));
               bean.setCustomerId(result.getInt("bill_id"));
               bean.setProdId(result.getInt("prod_id"));
               bean.setCardId(result.getInt("card_id"));
               bean.setDateOfTrans(result.getDate("date_of_trans"));
               bean.setUnitPrice(result.getInt("unit_price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotalPrice(result.getInt("total_price"));
               bean.setAmountType(result.getString("amount_type"));
               bean.setTransType(result.getString("transaction_type"));
               bean.setCounterNo(result.getInt("counter_no"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getBillByDate
    
    
    public static Vector getCustomer()throws Exception{
        String query = "select * from customer";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               CustomerBean bean = new CustomerBean();
               bean.setCustomerId(result.getInt("customer_id"));
               bean.setCustomerName(result.getString("customer_name"));
               bean.setAddress(result.getString("address"));
               bean.setCity(result.getString("city"));
               bean.setCountry(result.getString("country"));
               bean.setEmail(result.getString("email"));
               bean.setZipCode(result.getInt("zip_code"));
               bean.setContactNo(result.getString("contact_number"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getCustomer()
    
    public static CustomerBean getCustomer(int customer_id)throws Exception{
        String query = "select * from customer WHERE customer_id="+customer_id;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           CustomerBean bean = null;
           while(result.next()){
               bean = new CustomerBean();
               bean.setCustomerId(result.getInt("customer_id"));
               bean.setCustomerName(result.getString("customer_name"));
               bean.setAddress(result.getString("address"));
               bean.setCity(result.getString("city"));
               bean.setCountry(result.getString("country"));
               bean.setEmail(result.getString("email"));
               bean.setZipCode(result.getInt("zip_code"));
               bean.setContactNo(result.getString("contact_number"));
               bean.setRemarks(result.getString("remarks"));
            
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getCustomer()
    
    
    public static Vector getCreditCardType()throws Exception{
        String query = "select * from credit_card_type";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               CreditTypeBean bean = new CreditTypeBean();
               bean.setTypeId(result.getInt("type_id"));
               bean.setCardName(result.getString("card_name"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getCreditCardType
    public static Vector getCreditCardNo( int typeId)throws Exception{
        String query = "select * from credit_card_no where type_id="+typeId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               CreditCardNumberBean bean = new CreditCardNumberBean();
               bean.setCardId(result.getInt("card_id"));
               bean.setTypeId(result.getInt("type_id"));
               bean.setCardNo(result.getInt("card_number"));
               bean.setPinCode(result.getInt("pin_code"));
               bean.setAmount(result.getInt("amount"));
               bean.setRemarks(result.getString("remarks"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getCreditCardNo()
    
    public static Vector getEmploye()throws Exception{
        String query = "select * from employe";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               EmployeBean bean = new EmployeBean();
               bean.setEmpId(result.getInt("employe_id"));
               bean.setCnicNO(result.getString("cnic_no"));
               bean.setMobilleNO(result.getString("mobile_no"));
               bean.setEmail(result.getString("email"));
               bean.setEmployeType(result.getString("employe_type"));
               bean.setAllowted_counter_no(result.getString("allowted_counter_no"));
               bean.setDate(result.getDate("date"));
               bean.setStatus(result.getString("status"));
            
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getEmploye
    
    public static EmployeBean getEmploye(int employe_id)throws Exception{
        String query = "select * from employe WHERE employe_id="+employe_id;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           EmployeBean bean = null;
           while(result.next()){
               bean = new EmployeBean();
               bean.setEmpId(result.getInt("employe_id"));
               bean.setCnicNO(result.getString("cnic_no"));
               bean.setMobilleNO(result.getString("mobile_no"));
               bean.setEmail(result.getString("email"));
               bean.setEmployeType(result.getString("employe_type"));
               bean.setAllowted_counter_no(result.getString("allowted_counter_no"));
               bean.setDate(result.getDate("date"));
               bean.setStatus(result.getString("status"));
            
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getEmploye
   
    
    public static Vector getBill( long bill_id)throws Exception{
        String query = "select * from bill b , transaction t where b.bill_id="+bill_id+" AND t.bill_id="+bill_id+"";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               TransactionBean bean = new TransactionBean();
               ////set bill
               bean.setBill_id(result.getInt("bill_id"));
               bean.setCustomerId(result.getInt("customer_id"));
               bean.setDateOfTrans(result.getDate("date"));
               bean.setDiscount(result.getInt("discount"));
               bean.setCashPaid(result.getInt("cash_paid"));
               bean.setCashBack(result.getInt("cash_back"));
               bean.setCounterNo(result.getInt("counter_no"));
               ////set transaction
               bean.setTransactionId(result.getInt("trans_id"));
               bean.setProdId(result.getInt("prod_id"));
               bean.setUnitPrice(result.getInt("unit_price"));
               bean.setQuantity(result.getInt("quantity"));
               bean.setTotalPrice(result.getInt("total_price"));
               bean.setAmountType(result.getString("amount_type"));
               bean.setTransType(result.getString("transaction_type"));
               bean.setRemarks(result.getString("remarks"));
               //set barcode
               ProductBean pBean = getProductForBill(bean.getProdId());
               bean.setBarcode(pBean.getBarcode());
               bean.setProdName(pBean.getProdName());
               //set prodCatName
               ProductCategoryBean pcBean = getProductCategory(pBean.getProdCatId());
               bean.setProdCatName(pcBean.getProdCatName());
               //set customerName
               CustomerBean cBean = getCustomer(bean.getCustomerId());
               bean.setCustomerName(cBean.getCustomerName());
               
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getBill
    
    public static BillBean getBillByCustomer( int customerId)throws Exception{
        String query = "select * from bill WHERE customer_id="+customerId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           BillBean bean = null;
           while(result.next()){
               bean = new BillBean();
               bean.setBill_id(result.getInt("bill_id"));
               bean.setCustomer_id(result.getInt("customer_id"));
               bean.setDate(result.getDate("date"));
               bean.setDiscount(result.getString("discount"));
               bean.setCash_paid(result.getInt("cash_paid"));
               bean.setCash_back(result.getInt("cash_back"));
               bean.setCounter_no(result.getString("counter_no"));
               bean.setTransaction_type(result.getString("transaction_type"));
               
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getBillByCustomer
    
    
     public static BillBean getBillByBillId( int billId)throws Exception{
        String query = "select * from bill WHERE bill_id="+billId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           BillBean bean = null;
           while(result.next()){
               bean = new BillBean();
               bean.setBill_id(result.getInt("bill_id"));
               bean.setCustomer_id(result.getInt("customer_id"));
               bean.setDate(result.getDate("date"));
               bean.setDiscount(result.getString("discount"));
               bean.setCash_paid(result.getInt("cash_paid"));
               bean.setCash_back(result.getInt("cash_back"));
               bean.setCounter_no(result.getString("counter_no"));
               bean.setTransaction_type(result.getString("transaction_type"));
               
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getBillByCustomer
    
    public static Vector getBillByDate(Date date , String transaction_type)throws Exception{
        String remarks = transaction_type.toLowerCase().equals("s")?"SOLD":"PURCHASE";
        String query = "select * from bill WHERE date=#"+date+"# AND transaction_type='"+transaction_type+"' AND remarks='"+remarks+"'";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               BillBean bean = new BillBean();
               bean.setBill_id(result.getInt("bill_id"));
               bean.setCustomer_id(result.getInt("customer_id"));
               bean.setDate(result.getDate("date"));
               bean.setDiscount(result.getString("discount"));
               bean.setCash_paid(result.getInt("cash_paid"));
               bean.setCash_back(result.getInt("cash_back"));
               bean.setCounter_no(result.getString("counter_no"));
               bean.setTransaction_type(result.getString("transaction_type"));
               bean.setRemarks(result.getString("remarks"));
               
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getBillByDate
    
    public static BillBean getBillByInvoice(long billId , String transaction_type)throws Exception{
        String remarks = transaction_type.toLowerCase().equals("s")?"SOLD":"PURCHASE";
        String query = "select * from bill WHERE bill_id="+billId+" AND transaction_type='"+transaction_type+"' AND remarks='"+remarks+"'";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           BillBean bean = null;
           while(result.next()){
               bean = new BillBean();
               bean.setBill_id(result.getInt("bill_id"));
               bean.setCustomer_id(result.getInt("customer_id"));
               bean.setDate(result.getDate("date"));
               bean.setDiscount(result.getString("discount"));
               bean.setCash_paid(result.getInt("cash_paid"));
               bean.setCash_back(result.getInt("cash_back"));
               bean.setCounter_no(result.getString("counter_no"));
               bean.setTransaction_type(result.getString("transaction_type"));
               bean.setRemarks(result.getString("remarks"));
               
           }
              return bean;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getBillByDate
    
    
    public static Vector getSaleOrPurchase(Date date , String transaction_type)throws Exception{
//        String remarks = transaction_type.toLowerCase().equals("s")?"SOLD":"PURCHASE";
//        String query = "select * from bill b , transaction t where b.bill_id = t.bill_id AND date=#"+date+"# AND transaction_type='"+transaction_type+"' AND remarks='"+remarks+"'";
        
        /*
        SELECT prod_id, sum(total_price) AS totalPrice, sum(quantity) AS totalProducts, max(date_of_trans) AS transactionDate
        FROM [transaction] where transaction_type='S'
        GROUP BY prod_id;
        */
        
        String options = transaction_type.toLowerCase().equals("s")?" AND transaction_type='S' AND remarks='SOLD'":transaction_type.toLowerCase().equals("b")?"":" AND transaction_type='P' AND remarks='PURCHASE'";
//        String query = "select * from transaction where date_of_trans=#"+date+"#"+options;
        String query = "SELECT bill_id, sum(total_price) AS totalPrice, sum(quantity) AS totalProducts, max(date_of_trans) AS transactionDate, max(unit_price) AS unitPrice , max(remarks) AS typeOfTransaction"  +
                       " FROM [transaction] where date_of_trans=#"+date+"#"+options +
                       " GROUP BY bill_id;";
        
        
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               TransactionBean bean = new TransactionBean();

//               bean.setTransactionId(result.getInt("trans_id"));
//               bean.setProdId(result.getInt("prod_id"));
               bean.setBill_id(result.getInt("bill_id"));
               bean.setDateOfTrans(result.getDate("transactionDate"));
               bean.setUnitPrice(result.getInt("unitPrice"));
               bean.setQuantity(result.getInt("totalProducts"));
               bean.setTotalPrice(result.getInt("totalPrice"));
//               bean.setAmountType(result.getString("amount_type"));
//               bean.setTransType(result.getString("transaction_type"));
               bean.setRemarks(result.getString("typeOfTransaction"));
               
               //set barcode
//                ProductBean pBean = getProductForBill(bean.getProdId());
//                if(pBean == null)continue;
//                bean.setBarcode(pBean.getBarcode());
//                bean.setProdName(pBean.getProdName());
//                
//                //set customerName
//                BillBean bBean = getBillByBillId(bean.getBill_id());
//                if(bBean == null)continue;
//                CustomerBean cBean = getCustomer(bBean.getCustomer_id());
//                if(cBean == null)continue;
//                bean.setCustomerName(cBean.getCustomerName());

               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getBillByDate
    
    
//    public static Vector getSaleOrPurchaseByDate(Date fromDate , Date toDate , String transaction_type)throws Exception{
//        
//        String options = transaction_type.toLowerCase().equals("s")?"AND transaction_type='S' AND remarks='SOLD'":transaction_type.toLowerCase().equals("b")?"":"AND transaction_type='P' AND remarks='PURCHASE'";
//        String query = "select * from bill b , transaction t where b.bill_id = t.bill_id AND date>=#"+fromDate+"# AND date<=#"+toDate+"#"+options;
//        
//        System.out.println(query);
//        Statement st = null;
//        ResultSet result = null;
//        try{
//           st = con.createStatement();
//           result = st.executeQuery(query);
//           Vector v = new Vector();
//           while(result.next()){
//               TransactionBean bean = new TransactionBean();
//               ////set bill
//               bean.setBill_id(result.getInt("bill_id"));
//               bean.setCustomerId(result.getInt("customer_id"));
//               bean.setDateOfTrans(result.getDate("date"));
//               bean.setDiscount(result.getInt("discount"));
//               bean.setCashPaid(result.getInt("cash_paid"));
//               bean.setCashBack(result.getInt("cash_back"));
//               bean.setCounterNo(result.getInt("counter_no"));
//               ////set transaction
//               bean.setTransactionId(result.getInt("trans_id"));
//               bean.setProdId(result.getInt("prod_id"));
//               bean.setUnitPrice(result.getInt("unit_price"));
//               bean.setQuantity(result.getInt("quantity"));
//               bean.setTotalPrice(result.getInt("total_price"));
//               bean.setAmountType(result.getString("amount_type"));
//               bean.setTransType(result.getString("transaction_type"));
//               bean.setRemarks(result.getString("remarks"));
//               //set barcode
//               ProductBean pBean = getProductForBill(bean.getProdId());
//               bean.setBarcode(pBean.getBarcode());
//               bean.setProdName(pBean.getProdName());
//               //set prodCatName
//               ProductCategoryBean pcBean = getProductCategory(pBean.getProdCatId());
//               bean.setProdCatName(pcBean.getProdCatName());
//               //set customerName
//               CustomerBean cBean = getCustomer(bean.getCustomerId());
//               bean.setCustomerName(cBean.getCustomerName());
//               
//               v.addElement(bean);
//           }
//              return v;
//           
//       }finally{
//           if(result!=null)result.close();
//           if(st!= null)st.close();
//       }
//    }// End of getBillByDate
    
    
    public static Vector getSaleOrPurchaseByDate(Date fromDate , Date toDate , String transaction_type)throws Exception{
        
        String options = transaction_type.toLowerCase().equals("s")?" AND transaction_type='S' AND remarks='SOLD'":transaction_type.toLowerCase().equals("b")?"":" AND transaction_type='P' AND remarks='PURCHASE'";
//        String query = "select * from transaction  where  date_of_trans>=#"+fromDate+"# AND date_of_trans<=#"+toDate+"#"+options;
        
         String query = "SELECT bill_id, sum(total_price) AS totalPrice, sum(quantity) AS totalProducts, max(date_of_trans) AS transactionDate, max(unit_price) AS unitPrice , max(remarks) AS typeOfTransaction"  +
                       " FROM [transaction] WHERE date_of_trans>=#"+fromDate+"# AND date_of_trans<=#"+toDate+"#"+options +
                       " GROUP BY bill_id;";
        
        
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           Vector v = new Vector();
           while(result.next()){
               TransactionBean bean = new TransactionBean();

//               bean.setTransactionId(result.getInt("trans_id"));
//               bean.setProdId(result.getInt("prod_id"));
               bean.setBill_id(result.getInt("bill_id"));
               bean.setDateOfTrans(result.getDate("transactionDate"));
               bean.setUnitPrice(result.getInt("unitPrice"));
               bean.setQuantity(result.getInt("totalProducts"));
               bean.setTotalPrice(result.getInt("totalPrice"));
//               bean.setAmountType(result.getString("amount_type"));
//               bean.setTransType(result.getString("transaction_type"));
               bean.setRemarks(result.getString("typeOfTransaction"));
//        
//        System.out.println(query);
//        Statement st = null;
//        ResultSet result = null;
//        try{
//           st = con.createStatement();
//           result = st.executeQuery(query);
//           Vector v = new Vector();
//           while(result.next()){
//               TransactionBean bean = new TransactionBean();
//               ////set bill
//               bean.setBill_id(result.getInt("bill_id"));
//               bean.setDateOfTrans(result.getDate("date_of_trans"));
//               ////set transaction
//               bean.setTransactionId(result.getInt("trans_id"));
//               bean.setProdId(result.getInt("prod_id"));
//               bean.setUnitPrice(result.getInt("unit_price"));
//               bean.setQuantity(result.getInt("quantity"));
//               bean.setTotalPrice(result.getInt("total_price"));
//               bean.setAmountType(result.getString("amount_type"));
//               bean.setTransType(result.getString("transaction_type"));
//               bean.setRemarks(result.getString("remarks"));
//               
//                //set barcode
//                ProductBean pBean = getProductForBill(bean.getProdId());
//                if(pBean == null)continue;
//                bean.setBarcode(pBean.getBarcode());
//                bean.setProdName(pBean.getProdName());
//                
//                //set customerName
//                BillBean bBean = getBillByBillId(bean.getBill_id());
//                if(bBean == null)continue;
//                CustomerBean cBean = getCustomer(bBean.getCustomer_id());
//                if(cBean == null)continue;
//                bean.setCustomerName(cBean.getCustomerName());
               
               
               v.addElement(bean);
           }
              return v;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getBillByDate
    
    
    
    public static int getMaxCustomerId()throws Exception{
        String query = "select MAX(customer_id) as id from customer";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           int id = 0;
           if(result.next()){
               id = result.getInt("id");
           }
              return id;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// end of getMaxCustomerId
    
      public static int getMaxTransactionId()throws Exception{
        String query = "select MAX(trans_id) as id from transaction";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           int id = 0;
           if(result.next()){
               id = result.getInt("id");
           }
              return id;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getMaxTransactionId()
      
    public static int getMaxProdId()throws Exception{
        String query = "select MAX(prod_id) as id from product";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           int id = 0;
           if(result.next()){
               id = result.getInt("id");
           }
              return id;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getMaxProdId()
    
    public static int getMaxBillId()throws Exception{
        String query = "select MAX(bill_id) as id from bill";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           int id = 0;
           if(result.next()){
               id = result.getInt("id");
           }
              return id;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getMaxBillId
    
    public static int getMaxEmployeId()throws Exception{
        String query = "select MAX(employe_id) as id from employe";
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           int id = 0;
           if(result.next()){
               id = result.getInt("id");
           }
              return id;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getMaxEmployeId()
    
    public static int getSaleSumOfProduct(int prodId)throws Exception{
        String query = "select SUM(quantity) as qty from transaction Where transaction_type='S' AND prod_id="+prodId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           int qty = 0;
           if(result.next()){
               qty = result.getInt("qty");
           }
              return qty;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getSaleSumOfProduct
    
    public static int getPurchaseSumOfProduct(int prodId)throws Exception{
        String query = "select SUM(quantity) as qty from transaction Where transaction_type='P' AND prod_id="+prodId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
           st = con.createStatement();
           result = st.executeQuery(query);
           int qty = 0;
           if(result.next()){
               qty = result.getInt("qty");
           }
              return qty;
           
       }finally{
           if(result!=null)result.close();
           if(st!= null)st.close();
       }
    }// End of getPurchaseSumOfProduct
    
    
    public static int addBill(int costomerId,java.sql.Date Date , String discount,int cashPaid , int cashBack,String counterNo,String transaction_type,String remarks)throws Exception{
        String  query="insert into bill (customer_id,date,discount,cash_paid,cash_back,counter_no,transaction_type,remarks) values ("+costomerId+",#"+Date+"#,'"+discount+"',"+cashPaid+","+cashBack+",'"+counterNo+"','"+transaction_type+"','"+remarks+"')";
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//End of addBill
    
    public static int updateBill(int billID , int costomerId,java.sql.Date Date , String discount,int cashPaid , int cashBack ,String counterNo ,String transaction_type,String remarks)throws Exception{
        String  query=" update bill set date=#"+Date+"#,discount='"+discount+"',cash_paid="+cashPaid+",cash_back="+cashBack+",counter_no='"+counterNo+"',transaction_type='"+transaction_type+"',remarks='"+remarks+"' WHERE bill_id="+billID+" AND customer_id="+costomerId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//End of updateBill
    
    public static int deleteBill(int billID , int costomerId)throws Exception{
        String  query=" delete from bill WHERE bill_id="+billID+" AND customer_id="+costomerId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//End of deleteBill
    
    
    public static int addEmploye(String cnic_no,String mobile_no , String email,String employe_type ,String allowted_counter_no,Date date,String status)throws Exception{
        String  query="insert into employe (cnic_no,mobile_no,email,employe_type,allowted_counter_no,date,status) values ('"+cnic_no+"','"+mobile_no+"','"+email+"','"+employe_type+"','"+allowted_counter_no+"',#"+date+"#,'"+status+"')";
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//End of addEmploye
    
    public static int updateEmploye(int empId , String cnic_no,String mobile_no , String email,String employe_type ,String allowted_counter_no,Date date ,String status)throws Exception{
        String  query="update employe set cnic_no='"+cnic_no+"',mobile_no='"+mobile_no+"',email='"+email+"',employe_type='"+employe_type+"',allowted_counter_no='"+allowted_counter_no+"',date=#"+date+"#,status='"+status+"' WHERE employe_id="+empId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//End of updateEmplye
    
    public static int deleteEmploye(int empId)throws Exception{
        String  query=" delete from employe WHERE employe_id="+empId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//End of deleteEmploye
    
    
    
    
    
  
    
    
    public static int addProductCategory(String prodCatName ,String remarks)throws Exception{
        String  query="insert into product_category (prod_cat_name,remarks) values ('"+prodCatName+"','"+remarks+"')";
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of addProductCategory
    
    public static int updateProductCategory(int prodCatId,String prodCatName ,String remarks)throws Exception{
        String  query="update  product_category set prod_cat_name='"+prodCatName+"',remarks='"+remarks+"' where prod_cat_id="+prodCatId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of updateProductCategory
    
    public static int addProduct(int prodCatId ,String prodName, String barcode ,int price,int Qty,int total,String remarks)throws Exception{
        String  query="insert into product (prod_cat_id,prod_name,barcode,price,quantity,total,remarks) values ("+prodCatId+",'"+prodName+"','"+barcode+"',"+price+","+Qty+","+total+",'"+remarks+"')";
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of addProduct
    
    public static int updateProduct(int prodId ,String prodName, String barcode ,int price,int Qty,int total,String remarks)throws Exception{
        String  query="update product set prod_name='"+prodName+"',barcode='"+barcode+"',price="+price+",quantity="+Qty+",total="+total+",remarks='"+remarks+"' where prod_id="+prodId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of updateProduct
    public static int updateProductStoke(int prodId ,int Qty, int total)throws Exception{
        
        String  query="update product set quantity="+Qty+",total="+total+" where prod_id="+prodId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of updateProductStoke
    
    public static int addCustomer(String customerName, String address,String city,String country,int zipCode,String email,String contactNo ,String remarks)throws Exception{
        String  query="insert into customer (customer_name,address,city,country,zip_code,email,contact_number,remarks) values ('"+customerName+"','"+address+"','"+city+"','"+country+"',"+zipCode+",'"+email+"','"+contactNo+"','"+remarks+"')";
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of addCustomer
    
    
    public static int updateCustomer(int customer_id,String customerName, String address,String city,String country,int zipCode,String email,String contactNo ,String remarks)throws Exception{
        String  query="update customer  set customer_name='"+customerName+"',address='"+address+"',city='"+city+"', country='"+country+"',zip_code="+zipCode+",email='"+email+"',contact_number='"+contactNo+"',remarks='"+remarks+"' where customer_id="+customer_id;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of updateCustomer
    
    public static int addTransaction(int billId,int prodId,Date dataOfTrans,int unitPrice,int quantity,int total,String amountType,String transType,int counterNo,String remarks)throws Exception{
        String  query="insert into transaction (bill_id,prod_id,date_of_trans,unit_price,quantity,total_price,amount_type,transaction_type,counter_no,remarks) values ("+billId+","+prodId+",#"+dataOfTrans+"#,"+unitPrice+","+quantity+","+total+",'"+amountType+"','"+transType+"',"+counterNo+",'"+remarks+"')";
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of addTransaction
    
    public static int updateTransaction(int billId,int prodId,int transId,Date dataOfTrans,int unitPrice,int quantity,int total,String amountType,String transType,int counterNo,String remarks)throws Exception{
        String  query="update transaction  set prod_id="+prodId+", date_of_trans=#"+dataOfTrans+"#,unit_price="+unitPrice+",quantity="+quantity+",total_price="+total+",amount_type='"+amountType+"',transaction_type='"+transType+"',counter_no="+counterNo+",remarks='"+remarks+"' where trans_id="+transId+" AND bill_id="+billId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of updateTransaction
    
     public static int addCreditType(String cardName,String remarks)throws Exception{
        String  query="insert into credit_card_type (card_name,remarks) values ('"+cardName+"','"+remarks+"')";
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of addCreditType
     
    public static int updateCreditType(int typeId,String cardName,String remarks)throws Exception{
        String  query="update credit_card_type  set card_name='"+cardName+"',remarks='"+remarks+"' where type_id="+typeId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of updateCreditType
     
      public static int addCreditCardNo(int typeId,int cardNo,int pinCode,int amount,String remarks)throws Exception{
        String  query="insert into credit_card_no (type_id,card_number,pin_code,amount,remarks) values ("+typeId+","+cardNo+","+pinCode+","+amount+",'"+remarks+"')";
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of addCreditCardNo
      
    public static int updateCreditCardNo(int cardId,int cardNo,int pinCode,int amount,String remarks)throws Exception{
        String  query="update  credit_card_no set card_number="+cardNo+",pin_code="+pinCode+",amount="+amount+",remarks='"+remarks+"' where card_id="+cardId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of updateCreditCardNo
    
    public static int deleteProductCategory(int prodCatId)throws Exception{
        String  query="delete from product_category where prod_cat_id="+prodCatId ;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of deleteProductCategory
      
   public static int deleteProduct(int prodId)throws Exception{
        String  query="delete from product where prod_id="+prodId ;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of deleteProduct  
    
   public static int deleteCustomer(int customerId)throws Exception{
        String  query="delete from customer where customer_id="+customerId ;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of deleteCustomer
      
    public static int deleteTransaction(int transId)throws Exception{
        String  query="delete from transaction where trans_id="+transId ;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of deleteTransaction
    
     public static int deleteCreditType(int typeId)throws Exception{
        String  query="delete from credit_card_type where type_id="+typeId ;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of deleteCreditType
    
    public static int deleteCreditCardNo(int cardId)throws Exception{
        String  query="delete from credit_card_no where card_id="+cardId;
        System.out.println(query);
        Statement st = null;
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null)st.close();
        }
    }//end of deleteCreditCardNo
     
     
     
    
    
    
    // for User And Password
     public static Vector getSignUpData()throws Exception{
        String query = "select * from Users";
            
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            Vector v = new Vector();
            while(result.next()){
                UsersBean bean = new UsersBean();
                bean.setRecNo(result.getInt("rec_no"));
                bean.setEmploye_id(result.getInt("employe_id"));
                bean.setUserName(result.getString("user_name"));
                bean.setPassword(result.getString("password"));

                v.addElement(bean);
            }
               return v;

        }finally{
            if(result!=null)result.close();
            if(st!= null)st.close();
        }
    }
     
     public static UsersBean getUser(int empId)throws Exception{
        String query = "select * from Users WHERE employe_id="+empId;
        System.out.println(query);
        Statement st = null;
        ResultSet result = null;
        try{
            st = con.createStatement();
            result = st.executeQuery(query);
            UsersBean bean = null;
            while(result.next()){
                bean = new UsersBean();
                bean.setRecNo(result.getInt("rec_no"));
                bean.setEmploye_id(result.getInt("employe_id"));
                bean.setUserName(result.getString("user_name"));
                bean.setPassword(result.getString("password"));
            }
               return bean;

        }finally{
            if(result!=null)result.close();
            if(st!= null)st.close();
        }
    }//End of getUser
    
    
    public static int addSignUpData(int employe_id,String UserName,String password) throws SQLException{
       String query="insert into Users(employe_id,user_name,password) values("+employe_id+",'"+UserName+"','"+password+"')";
       System.out.println(query);
       
       Statement st = null;
       try{
           st = con.createStatement();
           int rows  = st.executeUpdate(query);
           return rows;
       }finally{
           if( st!=null)st.close();
       }
    }   
    //for update data in access file
    public static int UpdateSignupData(int recNo,int employe_id,String UserName,String password) throws SQLException{
       String query="UPDATE Users SET user_name='"+UserName+"',password='"+password+"' where rec_no="+recNo+" AND employe_id="+employe_id;
       System.out.println(query);
       
       Statement st = null;
       try{
           st = con.createStatement();
           int rows  = st.executeUpdate(query);
           return rows;
       }finally{
           if( st!=null)st.close();
       }
    }   
     //for delete data in access file
    public static int DeleteSignupData(int recNo) throws SQLException{
       String query="DELETE FROM Users where rec_no="+recNo;
       System.out.println(query); 
       
       Statement st = null;
       try{
           st = con.createStatement();
           int rows  = st.executeUpdate(query);
           return rows;
       }finally{
           if( st!=null)st.close();
       }
    }   
    
    
    
}//END OF DatabaseManager
