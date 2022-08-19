package Recipte;

import Beans.ProductBean;
import Beans.TransactionBean;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;
import javax.swing.JOptionPane;
import net.connectcode.Code128Auto;

public class ReciptPrint1 {

    public ReciptPrint1(Vector v , int customerId) {
        try{
            Book book = new Book();
            PageFormat pageFormat = getPageFormat(v.size()*25);
            
            book.append(new Document(v,customerId), pageFormat);
            java.awt.print.PrinterJob printJob=java.awt.print.PrinterJob.getPrinterJob();
            printJob.setPageable(book);
            if(printJob.printDialog())  
                 printJob.print();
            else System.out.println("DENIED!!!!");
           
            
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
	paper.setSize(PAPER_WIDTH,PAPER_HEIGHT+height);
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
        int customerId;
   
	public Document(Vector v , int customerId){
            this.v = v;
            this.customerId = customerId;
        }//end of constractor Document
        
        
        @Override
        public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
            try{
                Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			g2d.setPaint(Color.black);
                        g2d.setColor(Color.BLUE);
                        g2d.drawRect(20,20,246+5,398+(25*v.size()));
                        
                        Font f1 = new Font ("Arial", Font.BOLD, 16);
			g.setFont(f1);
                        g.drawString("GHOUSIA SHOPPING CENTER", 75, 46+10);
                        Font f2 = new Font ("Arial", Font.BOLD, 12);
			g.setFont(f2);
                        g.drawString("BESIDE GHOUSIA PETROL PUMP NEAR QADRI DARBAR QUETTA ROAD JACOBABAD ", 36+15, 56+20);
                        Font f3 = new Font ("Arial", Font.BOLD, 10);
			g.setFont(f3);
                        g.drawString("Contact #:03337344918/03334789173", 96, 66+30);
                        
                        float[] dashingPattern1 = {2f, 2f};
                        Stroke stroke1 = new BasicStroke(2f, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
                        g2d.setStroke(stroke1);
                        
                        g2d.drawLine(20, 116, 246+25, 116);
                        g.setFont(f2);
                        g.drawString("Sale Recipte", 96, 126+5);
                        g2d.drawLine(20, 136, 246+25, 136);
                        g.setFont(f3);
                        g.drawString("Invoice #:", 33, 146+5);
                        g.drawString("1425", 126, 146+5);
                        g.drawString("Inv Date:", 33, 156+10);
                        g.drawString("6-8-2020", 126, 156+10);
                        g.drawString("Client Name:", 33, 166+15);
                        g.drawString("Mr.Walking cutomer", 126, 166+15);
                        g.drawString("Counter#:", 33, 176+20);
                        g.drawString("1", 126, 176+20);
                        
                        g2d.drawLine(20, 206, 246+25, 206);
                        g.setFont(f3);
                        g.drawString("Item", 33, 216+5);
                        g.drawString("Price", 66+30, 216+5);
                        g.drawString("Qty", 96+60, 216+5);
                        g.drawString("Amount", 126+80, 216+5);
                        g.drawLine(20, 226, 246+25, 226);
			
			int x=33,y=246;
                        int counter = 0;
			for(int i=0;i<v.size();i++){
                            counter++;
                            ProductBean bean = (ProductBean)v.elementAt(i);
				g.drawString(bean.getProdName(),x,y);
				g.drawString(bean.getPrice()+"",x+70,y);
				g.drawString(counter+"",x+130,y);
				g.drawString(""+bean.getPrice(),x+170,y);
				y+=25;
                               
			}
			Stroke stroke = new BasicStroke(1f);
                        g2d.setStroke(stroke);
			g2d.drawLine(20, y, 246+25, y);
                        g.setFont(f3);
                        g.drawString("Total Item________", 33, y+=15);
                        g.drawString(""+counter, 100, y);
                        g.drawString("Total Qty_________", 156, y-=2);
                        g.drawString("1", 220, y);
                        
                        g2d.drawLine(20, y+=10, 246+25, y);
                        g.setFont(f2);
                        g.drawString("Net Amount", 33, y+=15);
                        g.drawString("50", 200, y);
                        g2d.drawLine(20, y+=10, 246+25, y);
                        
                        g2d.drawRect(33, y+=60,246-22 , y-=y+50);
                  
                        g2d.drawLine(33,386+(25*v.size())+y-10 ,246+10,386+(25*v.size())+y-10);
                        g.setFont(f2);
                        g.drawString("Cash Paid",36, 386+(25*v.size())+y-15);
                        g.drawString("600",200, 386+(25*v.size())+y-15);
                        g2d.drawLine(126,386+(25*v.size())+y-32 ,126,386+(25*v.size())+y+18);
                        g.setFont(f2);
                        g.drawString("Cash Back", 36, 386+(25*v.size())+y+15);
                        g.drawString("50", 200, 386+(25*v.size())+y+15);
                        
			g2d.drawLine(20, 386+(25*v.size())+y+25, 246+25, 386+(25*v.size())+y+25);
                        
                        Code128Auto code = new Code128Auto();
                        String barcode = code.encode("1425");
                        Font barFont = new Font("CCode128_S3_Trial",Font.PLAIN,18);
                        g.setFont(barFont);
                        g.drawString(barcode, 66+20, 426-5+(25*v.size())+y+25);
                        Font f4 = new Font ("Arial", Font.BOLD, 6);
                        g.setFont(f4);
                        g.drawString("1425", 126+10, 426+5+(25*v.size())+y+25);
                        g.setFont(f3);
                        g.drawString("THANKS FOR SHOPING ", 66+15, 406+40+(25*v.size())+y+20);
                      
                        
            }catch(Exception ee){
		ee.printStackTrace();
		JOptionPane.showMessageDialog(null,"Error: "+ee.getMessage());
	}
            
            return PAGE_EXISTS;
         }//end of paint
    }//end of Documnt
}//end of MainClass
