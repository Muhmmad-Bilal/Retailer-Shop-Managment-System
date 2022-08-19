package retailershopmanagmentsystem;
import FronterFrame.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class GetCurrentDate extends Thread {
    private javax.swing.JLabel field;
	
	public GetCurrentDate(javax.swing.JLabel field){
		this.field=field;	
	}//End of GetCurrentDate
        
	
	public void run(){
            while(true){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a");  
                    LocalDateTime now = LocalDateTime.now();  
                    field.setText(dtf.format(now)); 
                    try{
                            Thread.sleep(1000);
                    }catch(Exception e){}
            }

	}//End of getCurrentDate method
}//End of getCurrentDate class
