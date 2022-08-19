package retailershopmanagmentsystem;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
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
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.connectcode.Code128Auto;
import static retailershopmanagmentsystem.StokeListPrint.generateEAN13BarcodeImage;


public class ProductBarcodeListPrint {

    public ProductBarcodeListPrint(String data[]) {
        try{
            int numOfBarcode=data.length;

            int barcodePerPage=55;
            int totalPages=numOfBarcode/barcodePerPage;
            int lastPageBarcode=numOfBarcode%barcodePerPage;
            int index = -1;
            int pageNo=0;
            
            Book book = new Book();
            PageFormat pageFormat=getPageFormat();
            
            for(int p = 1; p <= totalPages; p++){
                String recorde[] = new String[barcodePerPage];
                for(int i=0; i<recorde.length; i++){
                    recorde[i] = data[++index];
                }
                book.append(new Document(recorde,++pageNo,totalPages+(lastPageBarcode>=1?1:0)), pageFormat);
            }
                String recorde[] = new String[lastPageBarcode];
                for(int i=0; i<recorde.length; i++){
                    recorde[i] = data[++index];
                }
                if(lastPageBarcode > 0){
                    book.append(new Document(recorde,++pageNo,totalPages+(lastPageBarcode>=1?1:0)), pageFormat);
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
        
        
        @Override
        public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
            try{
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                g2d.setPaint(Color.black);
                g2d.setColor(Color.black);
        
                Font f = new Font ("Arial", Font.BOLD, 14);
                g.setFont(f);
                
                g.drawString("PRODUCT BARCODE LIST", 226, 20);
                g2d.drawLine(20, 36, 616, 36);
                
                Font f2 = new Font ("Arial", Font.BOLD, 10);
                g.setFont(f2);
                
                int x=30,y=86;
                int i;
                for(i=0; i<data.length; i++){
                    String[]  array = data[i].replaceAll("null", "").split(",");
                    
                    g.setFont(new Font ("Arial", Font.BOLD, 8));
                    g.drawString(array[1], x, y-35);
                    
//                    Code128Auto code = new Code128Auto();
//                    String barcode = code.encode(""+array[0]);
//                    Font barFont = new Font("CCode128_S3_Trial",Font.PLAIN,18);
//                    g.setFont(barFont);  
//                    g.drawString(barcode, x, y);
//                    g.setFont(new Font ("Arial", Font.BOLD, 8));
//                    g.drawString(array[0], x, y+15);
                    
                    ByteArrayOutputStream in = new ByteArrayOutputStream();
                    ImageIO.write(generateEAN13BarcodeImage(array[0]), "png", in);
                    Image barCode = new ImageIcon(in.toByteArray()).getImage();
                    g2d.drawImage(barCode, x-10, y-30, 120, 30, this);
                    
//                    g.drawString("PKR "+array[1], (data[i].length()*3)+x+2, y+15);
                    g.drawString("PKR "+array[2], x, y+15);
                    x+=110;
                    if(x >= 547){
                        x = 30;
                        y += 65;
                    }
                }//End of for
                y+=65;
                
                g2d.drawLine(20, 766-8, 616, 766-8);
                Font f3 = new Font ("Arial", Font.BOLD, 8);
                g.setFont(f3);
                g.drawString("Page No."+pageNo+" of "+totalPages, 486+30, 766);
                
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
