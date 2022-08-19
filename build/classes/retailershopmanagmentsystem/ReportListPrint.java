package retailershopmanagmentsystem;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.connectcode.Code128Auto;


public class ReportListPrint {

    public ReportListPrint(String data[]) {
        try{
            int numOfItem=data.length;

            int itemPerPage=22;
            int totalPages=numOfItem/itemPerPage;
            int lastPageItem=numOfItem%itemPerPage;
            int index = -1;
            int pageNo=0;
            
            Book book = new Book();
            PageFormat pageFormat=getPageFormat();
            
            for(int p = 1; p <= totalPages; p++){
                String recorde[] = new String[itemPerPage];
                for(int i=0; i<recorde.length; i++){
                    recorde[i] = data[++index];
                }
                book.append(new Document(recorde,++pageNo,totalPages+(lastPageItem>=1?1:0)), pageFormat);
            }
                String recorde[] = new String[lastPageItem];
                for(int i=0; i<recorde.length; i++){
                    recorde[i] = data[++index];
                }
                if(lastPageItem > 0){
                    book.append(new Document(recorde,++pageNo,totalPages+(lastPageItem>=1?1:0)), pageFormat);
                }
            
            java.awt.print.PrinterJob printJob=java.awt.print.PrinterJob.getPrinterJob();
            printJob.setPageable(book);
            if(printJob.printDialog())  
                 printJob.print();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of costractor
    
    private static PageFormat getPageFormat(){

        double PIXEL_PER_INCH=72;
	
        double PAPER_WIDTH=8.27*PIXEL_PER_INCH;
	double PAPER_HEIGHT=11.69*PIXEL_PER_INCH;

	double LEFT_MARGIN=0.1*PIXEL_PER_INCH;
	double RIGHT_MARGIN=0.1*PIXEL_PER_INCH;

	double TOP_MARGIN=0.1*PIXEL_PER_INCH;
	double BOTTOM_MARGIN=0.001*PIXEL_PER_INCH;
        
        
        Paper paper=new Paper();
	paper.setSize(PAPER_WIDTH,PAPER_HEIGHT);

        paper.setImageableArea(LEFT_MARGIN,TOP_MARGIN ,
              paper.getWidth()-(LEFT_MARGIN + RIGHT_MARGIN) ,
              (paper.getHeight())-(TOP_MARGIN +BOTTOM_MARGIN));
		
        PageFormat pageFormat = new PageFormat ();
        pageFormat.setPaper(paper);
        pageFormat.setOrientation (PageFormat.PORTRAIT);

        return pageFormat;
        
    }//end of PageFormate
  
    class  Document extends Component implements Printable{
       
	private String data[];
        private int pageNo;
        private int totalPages;
        
	public Document(String data[],int pageNo,int totalPages){
            this.data=data;
            this.pageNo=pageNo;
            this.totalPages=totalPages;
        }//end of constractor Document
        
        
        private int drawCell(Graphics g,String text,int xAxis,int yAxis,int width,String align){
            return drawCell(g,text,xAxis,yAxis,width,align,15);
            //g.getFontMetrics().getHeight()
        }//End of Overloaded drawCell method
        
        private int drawCell(Graphics g,String text,int xAxis,int yAxis,int width,String align,int diff){
            java.awt.FontMetrics metrics=g.getFontMetrics();
            
            String textParts[]=text.split(" ");
            String data="", tempData="";
            for(int i=0;i<textParts.length-1;i++){
                tempData+=metrics.stringWidth((tempData.isEmpty()?textParts[i]:tempData+" "+textParts[i])+" "+textParts[i+1])<width?
                        (" "+textParts[i]):"";
                
                if(!(metrics.stringWidth(tempData+" "+textParts[i+1])<width)){
                    data+=tempData+";i;";
                    tempData="";
                }
                if(i==textParts.length-2 && data.isEmpty())
                    data+=tempData+" "+textParts[i+1];
            }
            
            textParts=data.split(";i;");
            for(int i=0;i<textParts.length;i++){
                int textWidth=metrics.stringWidth(textParts[i]);
            
                int textXAxis=align.toLowerCase().equals("c")?(width-textWidth)/2
                    :(align.toLowerCase().equals("r")?width-textWidth:xAxis);
        
                g.drawString(textParts[i], textXAxis, yAxis);
                
                yAxis+=diff;
            }
                            
            return yAxis;
        }//End of drawCell method
        
        
        @Override
        public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
            try{
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                g2d.setPaint(Color.black);
                g2d.setColor(Color.black);
        
                g.setFont(new Font ("Times New Roman", Font.BOLD, 14));
                
                Font f1=new Font("Arial",Font.BOLD,16);
                g.setFont(f1);
                g.drawString("GHOUSIA SHOPPING CENTER",190,35);
                //header section..
               // Image logo = ImageIO.read(new File("Images//recipt-logo.jpg"));
                //g2d.drawImage(logo, 220, 5, 180, 50, this);
                
//                int x = (int) pageFormat.getImageableX();
                int width=(int)pageFormat.getImageableWidth();
                
                int tempY1 = drawCell(g,"SALE/PURCHASE REPORT",20,70,width+30,"c");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a");  
                LocalDateTime now = LocalDateTime.now(); 
                g.setFont(new Font ("Times New Roman", Font.BOLD, 7));
                int tempY2 = drawCell(g,""+dtf.format(now),20,70,width-10,"r");
                
                g2d.drawLine(20, tempY2-5, width-10, tempY2-5);
                
                g.setFont(new Font ("Times New Roman", Font.BOLD, 10));
                
                g.drawString("S.NO", 20, tempY2+5);
                g.drawString("DATE", 60, tempY2+5);
                g.drawString("BILL ID", 150, tempY2+5);
                g.drawString("TOTAL PRODUCT", 250, tempY2+5);
                g.drawString("TOTAL AMOUNT", 370, tempY2+5);
                g.drawString("TYPE", 500, tempY2+5);
//                g.drawString("AMOUNT", 450, tempY2+5);
//                g.drawString("STATUS", 520, tempY2+5);

                g2d.drawLine(20, tempY2+10, width-10, tempY2+10);
                int x=126,y=tempY2+20;
                
                for(int i=0;i<data.length;i++){
                   
                    String[]  array = data[i].replaceFirst("null", "").split(",");
                   
                    g.drawString(array[0]+".", 25, y);
                    g.drawString(array[1], 60, y);
//                    g.drawString(array[4], 140, y);
                    
//                    String customerName = array[3];
//                    String productName = array[4];
                    
//                    if(customerName.length()<=14){
//                        g.setFont(new Font ("Arial", Font.BOLD, 8));
//                        g.drawString(""+customerName,60,y);
//                    }
//                    else{
//                        g.setFont(new Font ("Arial", Font.BOLD, 8));
//                        g.drawString(""+customerName.substring(0, 13)+"-",60,y);
//                        g.drawString(""+customerName.substring(14, customerName.length()<=29?customerName.length():29),60,y+10);
//                    }
                    
//                    if(productName.length()<=20){
//                        g.setFont(new Font ("Arial", Font.BOLD, 8));
//                        g.drawString(""+productName,140,y);
//                    }
//                    else{
//                        g.setFont(new Font ("Arial", Font.BOLD, 8));
//                        g.drawString(""+productName.substring(0, 19)+"-",140,y);
//                        g.drawString(""+productName.substring(20, productName.length()<=39?productName.length():39),140,y+10);
//                    }
                    
//                    String barcode = code.encode(""+array[5]);
//                    Font barFont = new Font("CCode128_S3_Trial",Font.PLAIN,15);
//                    g.setFont(barFont); 
//                    g.drawString(barcode, 250, y+20);
//                    g.setFont(new Font ("Arial", Font.BOLD, 8));
                    ByteArrayOutputStream in = new ByteArrayOutputStream();
                    ImageIO.write(generateEAN13BarcodeImage(String.valueOf(array[2])), "png", in);
                    Image barCode = new ImageIcon(in.toByteArray()).getImage();
                    g2d.drawImage(barCode, 138, y-7 ,80 , 15, this);
                    
                    g.setFont(new Font ("Arial", Font.BOLD, 8));
                    g.drawString(array[2], 150, y+15);
                    
                    g.drawString(array[4], 250, y);
                    g.drawString(array[5], 370, y);
                    g.drawString(array[6], 500, y);
//                    g.drawString(array[9], 520, y);
                    y+=30;
                }
                
                g2d.drawLine(20, 766-8, 616, 766-8);
                Font f3 = new Font ("Arial", Font.BOLD, 8);
                g.setFont(f3);
//                g.drawString("Page No."+pageNo+" of "+totalPages, 486+30, 766);
                drawCell(g," Page No."+pageNo+" of "+totalPages+" ",20,766,width,"r");
                
            }catch(Exception ee){
		ee.printStackTrace();
		JOptionPane.showMessageDialog(null,"Error: "+ee.getMessage());
	}
            
            return PAGE_EXISTS;
         }//end of paint
    }//end of Documnt
    
    
    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        Code128Writer writer = new Code128Writer();
        BitMatrix matrix = writer.encode(barcodeText, BarcodeFormat.CODE_128, 300, 150);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
    
}//end of MainClass
