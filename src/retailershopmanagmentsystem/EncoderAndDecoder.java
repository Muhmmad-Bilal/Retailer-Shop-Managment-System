package retailershopmanagmentsystem;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class EncoderAndDecoder {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    public static String IncodeSimpleDateFormate(String date) throws ParseException{
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a").parse(date));
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String d = f.format(cal.getTime());
        System.out.println("date : " + d);
        return d;
    }
    
    public static String DecoderSimpleDateFormate(java.util.Date date){

//  public static String DecoderSimpleDateFormate(java.sql.Timestampdate date){
//         return new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a").format(new java.util.Date(date.getTime()));
//  }
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
        String d = f.format(date);
        return d;
    }
    
    public static String IncodeJChooserDateFormate(String date) throws ParseException{
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(date));
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String d = f.format(cal.getTime());
        return d;
    }
    public static String amountTypeIncode(String amountType){
        switch(amountType){
            //CASH, CHEAKUE,PAYORDER, ONLINE
            case"CASH": return"C";
            case"CHEAKUE": return"CHE";
            case"PAYORDER": return"PAY";
            case"ONLINE": return"O";
         }// end of switch
        return amountType;
    }// end of amountTypeIncode
    
    public static String amountTypeDecode(String amountType){
        switch(amountType){
            
            case"C": return"CASH";
            case"CHE": return"CHEAKUE";
            case"PAY": return"PAYORDER";
            case"O": return"ONLINE";
         }// end of switch
        return amountType;
    }// end of amountTypeDecode
    
    
    
    public static String trasactionTypeIncode(String transactionType){
        switch(transactionType){
            case"SALE": return"S";
            case"PURCHASE": return"P";
         }// end of switch
        return transactionType;
    }// end of trasactionTypeIncode
    
     public static String trasactionTypeDecode(String transactionType){
        switch(transactionType){
            case"S": return"SALE";
            case"P": return"PURCHASE";
         }// end of switch
        return transactionType;
    }// end of trasactionTypeDecode
    
}//End of main Class


    
