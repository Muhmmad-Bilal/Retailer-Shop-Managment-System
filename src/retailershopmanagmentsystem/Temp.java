/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailershopmanagmentsystem;

import databaseManager.DatabaseManager;
import java.time.LocalDate;

/**
 *
 * @author Maham Computers
 */
public class Temp {
    public static void main(String arg[]){
    
        try{
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());

            String counterNo = "1";
            String amountType = "C";
            String transType = "P";
            for(int i=1; i<=10000; i++){
                DatabaseManager.addBill(3, (java.sql.Date) date, "0", 0, 0, counterNo,transType,"PURCHASE");
                System.out.println("data : "+i);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
