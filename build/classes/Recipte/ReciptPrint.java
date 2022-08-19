package Recipte;

import Beans.TransactionBean;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.connectcode.Code128Auto;
import retailershopmanagmentsystem.EncoderAndDecoder;
import retailershopmanagmentsystem.GetCurrentDate;

public class ReciptPrint {
    
    public ReciptPrint(Vector v) {
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
	paper.setSize(PAPER_WIDTH,PAPER_HEIGHT+(height-30));
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
        @Override
        public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
            try{
                Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			g2d.setPaint(Color.black);
                        g2d.setColor(Color.black);
                        g2d.drawRect(1,20,200,405+(35*v.size()));
                        
                        int widthOfRecept = 200;
                        
                        Font f1 = new Font ("Arial", Font.BOLD, 16);
			g.setFont(f1);
//                      g.drawString("Ali`s Collection", 70, 46+10);
                        Font f2 = new Font ("Arial", Font.BOLD, 10);
			g.setFont(new Font ("Arial", Font.BOLD, 9));
                        g.drawString("Shop No 2,Apple Tower Opposite", 10, 56+30);
                        g.drawString("Dairy, Hirabad, Hyderabad, Pakistan", 15, 56+40);
                        g.drawString("Contact #: 0315-0300342", 33, 66+43);
                        
                        g.setFont(f2);
//                        Image logo = ImageIO.read(new File("Images//recipt-logo.jpg"));
//                        g2d.drawImage(logo, 10, 25, 185, 50, this);
                        
                        float[] dashingPattern1 = {2f, 2f};
                        Stroke stroke1 = new BasicStroke(2f, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
                        g2d.setStroke(stroke1);
                        
                        g2d.drawLine(1, 116, widthOfRecept, 116);
                        g.setFont(f2);
                        g.drawString("Sale Receipt", 66, 126+5);
                        g2d.drawLine(1, 136, widthOfRecept, 136);
                       
                        g.drawString("Invoice #:", 10, 146+5);
                        g.drawString("Inv Date:", 10, 156+10);
                        g.drawString("Client Name:", 10, 166+15);
                        g.drawString("Counter#:", 10, 176+20);
                        
                        g2d.drawLine(1, 206, widthOfRecept, 206);
                        
                        g.drawString("Item Name", 10, 216+5);
                        g.drawString("Price", 66, 216+5);
                        g.drawString("Qty/Wt", 110, 216+5);
                        g.drawString("Amount", 150, 216+5);
                        g.drawLine(1, 226, widthOfRecept, 226);
			
			int x=10,y=246;
                        int counter = 0;
                        int totalQty = 0;
                        int netAmount = 0;
                        long invoiceNO = 0;
                        int cashPaid = 0;
                        int cashBack = 0;
                        int discount = 0;
                        String catName = "";
			for(int i=0;i<v.size();i++){
                            TransactionBean bean = (TransactionBean)v.elementAt(i);
                            
                            if(i <= 0){
                            g.drawString(""+bean.getBill_id(),90, 146+5);
                            g.drawString(""+EncoderAndDecoder.DecoderSimpleDateFormate(bean.getDateOfTrans()),90, 156+10);
                            g.drawString(""+bean.getCustomerName(),90, 166+15);
                            g.drawString(""+bean.getCounterNo(),90, 176+20);
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
                            g.drawString(""+bean.getTotalPrice()+".00",150,y+20);
                            
                            Stroke stroke = new BasicStroke(1f);
                            g2d.setStroke(stroke);
                            g2d.drawLine(1, y+25, widthOfRecept, y+25);
                            y+=35;
                            counter++;
			}//End of for
                        
//			Stroke stroke = new BasicStroke(1f);
//                        g2d.setStroke(stroke);
//			g2d.drawLine(20, y, 246+25, y);
                    
                        g.drawString("Total Item _______", 10, y+=15);
                        g.drawString(""+counter+".00", 66, y);
                        g.drawString("Total Qty ________", 110, y-=2);
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
                            g.drawString("("+discount+"% off)", 108, y-1);
                            g.setFont(f2);
                            g.drawString(""+(int)finalAmount, 200, y);
                        }
                        else{
                            g.drawString(""+netAmount+".00", 96, y);
                        }
//                        g2d.drawLine(155, y+=5, 248, y);
                        
                        g2d.drawLine(96, y+=5, 200, y);
                        
                        g2d.setStroke(new BasicStroke(1.5f));
//                        g2d.drawLine(155, y+=2, 248, y);
                        g2d.drawLine(96, y+=2, 200, y);
                        
                        
                        Stroke stroke = new BasicStroke(1f);
                        g2d.setStroke(stroke);
                        g2d.drawLine(1, y+=5, widthOfRecept, y);
                      
                        g2d.drawRect(10, y+=60,widthOfRecept-22 , y-=y+50);
                  
                        g2d.drawLine(10,386+(35*v.size())+y-5 ,widthOfRecept-12,386+(35*v.size())+y-5);
                        g.setFont(f2);
                        g.drawString("Cash Paid",15, 386+(35*v.size())+y-15);
                        g.drawString(""+cashPaid+".00",150, 386+(35*v.size())+y-15);
                        
                        g2d.drawLine(80,386+(35*v.size())+y-30 ,80,386+(35*v.size())+y+20);
                        
                        g.setFont(f2);
                        g.drawString("Cash Back", 15, 386+(35*v.size())+y+10);
                        g.drawString(""+cashBack+".00", 150, 386+(35*v.size())+y+15);
                        
			g2d.drawLine(1, 386+(35*v.size())+y+25, widthOfRecept, 386+(35*v.size())+y+25);
                        
                        Code128Auto code = new Code128Auto();
                        String barcode = code.encode(""+invoiceNO);
                        Font barFont = new Font("CCode128_S3_Trial",Font.PLAIN,18);
                        g.setFont(barFont);
                        
//                        g.drawString(barcode, 125-barcode.length()*4, 426-5+(35*v.size())+y+25);
                        
                        g.drawString(barcode, (widthOfRecept/2)-barcode.length()*5, 421+(35*v.size())+y+25);
                        
                        Font f4 = new Font ("Arial", Font.BOLD, 6);
                        g.setFont(f4);
                        g.drawString("* "+invoiceNO+" *", (widthOfRecept/2)-(String.valueOf(invoiceNO).length()/2), 426+5+(35*v.size())+y+27);
                        g.setFont(f3);
                        g.drawString("THANKS FOR SHOPPING", 20, 406+40+(35*v.size())+y+25);
                      
                        
            }catch(Exception ee){
		ee.printStackTrace();
		JOptionPane.showMessageDialog(null,"Error: "+ee.getMessage());
	}
            
            return PAGE_EXISTS;
         }//end of paint
    }//end of Documnt
}//end of MainClass
