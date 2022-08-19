package Recipte;

import Beans.TransactionBean;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.connectcode.Code128Auto;
import retailershopmanagmentsystem.EncoderAndDecoder;
import retailershopmanagmentsystem.GetCurrentDate;

public class ReciptPrintUpdated {
    
    public ReciptPrintUpdated(Vector v) {
        try{
            Book book = new Book();
            PageFormat pageFormat = getPageFormat(v.size()*35);
            
            book.append(new Document(v), pageFormat);
            java.awt.print.PrinterJob printJob=java.awt.print.PrinterJob.getPrinterJob();
            printJob.setPageable(book);
            if(printJob.printDialog())  
                 printJob.print();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of costractor
    
    private static PageFormat getPageFormat(int height){

        double PIXEL_PER_INCH=72;
        double PAPER_WIDTH=4*PIXEL_PER_INCH;
	double PAPER_HEIGHT=10.69*PIXEL_PER_INCH;

	double LEFT_MARGIN=0.001*PIXEL_PER_INCH;
	double RIGHT_MARGIN=0.001*PIXEL_PER_INCH;

	double TOP_MARGIN=0.005*PIXEL_PER_INCH;
	double BOTTOM_MARGIN=0.001*PIXEL_PER_INCH;
        
        
        Paper paper=new Paper();
	paper.setSize(PAPER_WIDTH,PAPER_HEIGHT+height+50);
        paper.setImageableArea(LEFT_MARGIN,TOP_MARGIN ,
              paper.getWidth()-(LEFT_MARGIN + RIGHT_MARGIN) ,
              (paper.getHeight())-(TOP_MARGIN +BOTTOM_MARGIN));
		
        PageFormat pageFormat = new PageFormat ();
        pageFormat.setPaper(paper);
        pageFormat.setOrientation (PageFormat.PORTRAIT);

        return pageFormat;
        
    }//end of PageFormate
  
    class  Document extends Component implements Printable{
        Vector v;
        
	public Document(Vector v ){
            this.v=v;
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
                        g2d.drawRect(1,20,200,425+(35*v.size()));
                        
                        int widthOfRecept = 200;
                        
                        Font f1 = new Font ("Arial", Font.BOLD, 16);
			g.setFont(f1);
//                        g.drawString("Ali`s Collection", 70, 46+10);
                        Font f2 = new Font ("Arial", Font.BOLD, 10);
			g.setFont(new Font ("Arial", Font.BOLD, 9));
//                        g.drawString("Shop No 2,Apple Tower Opposite Mehran", 10, 56+30);
//                        g.drawString("Dairy, Hirabad, Hyderabad, Pakistan", 15, 56+40);
//                        g.drawString("Contact #: 0315-0300342", 33, 66+43);
                        drawCell(g, "Beside Ghousia Petrol Pump Near Qadri   ", 1, 48+30, widthOfRecept, "c");
                        drawCell(g, "Darbar, Quetta, Road, Jacobabad ", 1, 48+40, widthOfRecept, "c");
                        g.setFont(new Font ("Arial", Font.BOLD, 11));
                        drawCell(g, " Contact #: 03337344918/03334789173 ", 1, 56+50, widthOfRecept, "c");
                        
                        g.setFont(f2);
                        Font f10=new Font("Arial",Font.BOLD,13);
                        g.setFont(f10);
                        //Image logo = ImageIO.read(new File("Images//recipt-logo.jpg"));
                        drawCell(g,"GHOUSIA SHOPPING CENTER", 1, 34+20,widthOfRecept, "c");
                        
                        float[] dashingPattern1 = {2f, 2f};
                        Stroke stroke1 = new BasicStroke(2f, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
                        g2d.setStroke(stroke1);
                        
                        g2d.drawLine(1, 116, widthOfRecept, 116);
                        g.setFont(f2);
//                        g.drawString("Sale Receipt", 66, 126+5);
                        drawCell(g, " Sale Receipt ", 1, 126+5, widthOfRecept, "c");
                        g2d.drawLine(1, 136, widthOfRecept, 136);
                       
                        g.setFont(new Font ("Arial", Font.BOLD, 8));
                        g.drawString("Invoice #:", 10, 146+5);
                        g.drawString("Inv Date:", 10, 156+10);
                        g.drawString("Client Name:", 10, 166+15);
                        g.drawString("Counter:", 10, 176+20);
                        
                        g2d.drawLine(1, 206, widthOfRecept, 206);
                        
                        g.drawString("Item Name", 10, 216+5);
                        g.drawString("Price", 66, 216+5);
                        g.drawString("Qty/Wt", 110, 216+5);
//                        g.drawString("Amount", 150, 216+5);
                        drawCell(g, " Amount ", 1, 220, widthOfRecept-5, "r");
                        g.drawLine(1, 226, widthOfRecept, 226);
                        
			g.setFont(new Font ("Arial", Font.BOLD, 8));
                        
			int x=10,y=246;
                        int counter = 0;
                        int totalQty = 0;
                        int netAmount = 0;
                        long invoiceNO = 0;
                        int cashPaid = 0;
                        int cashBack = 0;
                        int discount = 0;
                        String catName = "";
                        
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a");  
                        LocalDateTime now = LocalDateTime.now();  
                    
                        
			for(int i=0;i<v.size();i++){
                            TransactionBean bean = (TransactionBean)v.elementAt(i);
                            
                            if(i <= 0){
                            g.drawString(""+bean.getBill_id(),90, 146+5);
//                            g.drawString(""+EncoderAndDecoder.DecoderSimpleDateFormate(bean.getDateOfTrans()),90, 156+10);
                            g.drawString(""+dtf.format(now),90, 156+10);
                            g.drawString(""+bean.getCustomerName(),90, 166+15);
                            g.drawString("Cash-AT-"+bean.getCounterNo(),90, 176+20);
                            }
                            invoiceNO = bean.getBill_id();
                            cashPaid = bean.getCashPaid();
                            cashBack = bean.getCashBack();
                            discount = bean.getDiscount();
                            
                            totalQty += bean.getQuantity();
                            netAmount += bean.getTotalPrice();

                            catName = bean.getProdCatName();
                            
//                            int xAxis = (141 - catName.length()*3);
//
//                            if(xAxis >= 33 && catName.length()<=38){
//                               g.drawString(""+catName,xAxis,y);
//                            }
//                            else{
//                                String temp = catName.substring(0, 38);
//                                g.drawString(""+temp,(xAxis+33),y);
//                                y+=10;
//                            }
                            
                            g.drawString(""+bean.getBarcode()+"  "+bean.getProdName(),x,y+5);
                            g.drawString(""+bean.getUnitPrice()+".00",66,y+20);
                            g.drawString(""+bean.getQuantity()+".00",110,y+20);
//                            g.drawString(""+bean.getTotalPrice()+".00",150,y+20);
                            drawCell(g, " "+bean.getTotalPrice()+".00 ", 1, y+20, widthOfRecept, "r");
                            
                            float[] dashingPattern2 = {3f, 3f};
                            Stroke stroke2 = new BasicStroke(0.9f, BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER, 1.0f, dashingPattern2, 1.0f);
                            g2d.setStroke(stroke2);
                        
                            g2d.drawLine(1, y+25, widthOfRecept, y+25);
                            y+=35;
                            counter++;
			}//End of for
                        Stroke stroke = new BasicStroke(1f);
                        g2d.setStroke(stroke);
                            
                        g.setFont(f2);
                        g.drawString("Total Item _______", 10, y+=15);
                        g.drawString(""+counter+".00", 66, y);
                        g.drawString("Total Qty _______", 110, y-=2);
                        g.drawString(""+totalQty+".00", 160, y);
                        
                        g2d.drawLine(1, y+=10, widthOfRecept, y);
                        
                        
                        Font f3 = new Font ("Arial", Font.BOLD, 12);
			g.setFont(f3);
                        g.drawString("Net Amount", 10, y+=15);
                        if(discount != 0){
                            //calculating discounted ammount
                            double tempDescount = Double.parseDouble(String.valueOf(discount));
                            double discountPercentage = tempDescount/100;
                            double saveByCostomer = (netAmount*discountPercentage);
                            double finalAmount = (netAmount-saveByCostomer);
                            
                            g.setFont(new Font ("Arial", Font.BOLD, 8));
                            g.setFont(f2);
                            drawCell(g, " "+(int)finalAmount+".00 "+"("+discount+"% off)" , 1, y, widthOfRecept, "r");
                        }
                        else{
                            drawCell(g, " "+netAmount+".00 ", 1, y, widthOfRecept, "r");
                        }
                        
                        g2d.drawLine(96, y+=5, 200, y);
                        g2d.setStroke(new BasicStroke(1.5f));
                        g2d.drawLine(96, y+=3, 200, y);
                        
                        
                        g2d.setStroke(stroke);
                        g2d.drawLine(1, y+=5, widthOfRecept, y);
                      
                        g2d.drawRect(10, y+=60,widthOfRecept-22 , y-=y+50);
                  
                        g2d.drawLine(10,386+(35*v.size())+y-5 ,widthOfRecept-12,386+(35*v.size())+y-5);
                        g.setFont(f2);
                        g.drawString("Cash Paid",15, 386+(35*v.size())+y-15);
//                        g.drawString(""+cashPaid+".00",150, 386+(35*v.size())+y-15);
                        drawCell(g, " "+cashPaid+".00 ", 1, 386+(35*v.size())+y-15, widthOfRecept-14, "r");
                        
                        g2d.drawLine(80,386+(35*v.size())+y-28 ,80,386+(35*v.size())+y+20);
                        
                        g.setFont(f2);
                        g.drawString("Cash Back", 15, 386+(35*v.size())+y+10);
//                        g.drawString(""+cashBack+".00", 150, 386+(35*v.size())+y+15);
                        drawCell(g, " "+cashBack+".00 ", 1, 386+(35*v.size())+y+15, widthOfRecept-14, "r");
                        
			g2d.drawLine(1, 386+(35*v.size())+y+25, widthOfRecept, 386+(35*v.size())+y+25);
                        
//                        Code128Auto code = new Code128Auto();
//                        String barcode = code.encode(""+invoiceNO);
//                        Font barFont = new Font("CCode128_S3_Trial",Font.PLAIN,18);
//                        g.setFont(barFont);
                  
//                        g.drawString(barcode, (widthOfRecept/2)-barcode.length()*5, 421+(35*v.size())+y+25);
                        
                        ByteArrayOutputStream in = new ByteArrayOutputStream();
                        ImageIO.write(generateEAN13BarcodeImage(String.valueOf(invoiceNO)), "png", in);
                        Image barCode = new ImageIcon(in.toByteArray()).getImage();
                        
                        g2d.drawImage(barCode, 10, 379+(35*v.size())+y+35, 185, 30, this);
                        
                        Font f4 = new Font ("Arial", Font.BOLD, 6);
                        g.setFont(f4);
                        g.drawString("* "+invoiceNO+" *", (widthOfRecept/2)-(String.valueOf(invoiceNO).length()/2), 420+5+(35*v.size())+y+27);
                        g.setFont(f3);
//                        g.drawString("THANKS FOR SHOPPING", 20, 406+40+(35*v.size())+y+25);
Font f11=new Font("Arial",Font.BOLD,7);
g.setFont(f11);
drawCell(g,"Before leaving shop kindly check your shopping and recipt",1,401+40+(35*v.size())+y+20,widthOfRecept,"c");
drawCell(g, " THANKS FOR SHOPPING ", 1, 406+40+(35*v.size())+y+25, widthOfRecept, "c");
                        g2d.drawLine(1, 406+45+(35*v.size())+y+25, widthOfRecept, 406+45+(35*v.size())+y+25);
                        g.setFont(new Font ("Arial", Font.BOLD, 8));
//                        drawCell(g, " Software Developed By: Sheeraz | cell: 03128499828 | ", 1, 406+55+(35*v.size())+y+25, widthOfRecept, "c");
                        g.drawString("Software Developed By:", 2, 406+55+(35*v.size())+y+25);
                        g.setFont(new Font ("Arial", Font.ITALIC+Font.BOLD, 8));
                        g.drawString("Sheeraz", 97, 406+55+(35*v.size())+y+25);
                        g.setFont(new Font ("Arial", Font.BOLD, 8));
                        g.drawString("|cell:03128499828|", 130, 406+55+(35*v.size())+y+25);
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
    
//    public static void main(String arg[])throws Exception{
//        Vector v = databaseManager.DatabaseManager.getBill(171);
//        new ReciptPrintUpdated(v);
//        JOptionPane.showMessageDialog(null,"Recipte Printed..");
//    }
}//end of MainClass
