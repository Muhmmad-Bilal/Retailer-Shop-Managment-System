package FronterFrame;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class GetCurrentDate extends Thread {
    private javax.swing.JMenu field;
	
	public GetCurrentDate(javax.swing.JMenu field){
		this.field=field;	
	}//End of GetCurrentDate
	
	public void run(){
            while(true){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   hh:mm:ss a");  
                    LocalDateTime now = LocalDateTime.now();  
                    field.setText(dtf.format(now)); 
                    try{
                            Thread.sleep(1000);
                    }catch(Exception e){}
            }

	}//End of getCurrentDate method
}//End of getCurrentDate class
