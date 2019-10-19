package info.martinoconnell;

import java.io.FileOutputStream;
import java.io.FileReader;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.opencsv.CSVReader;

public class Main {  
	
        public static void main(String[] args) throws Exception{
        	
        	BaseFont bf = BaseFont.createFont(
                    BaseFont.COURIER,
                    BaseFont.CP1252,
                    BaseFont.EMBEDDED);
            Font font = new Font(bf, 10);
 
                
                /* Step -1 : Read input CSV file in Java */
                String inputCSVFile = "Results.csv";
                CSVReader reader = new CSVReader(new FileReader(inputCSVFile));
                
                /* Variables to loop through the CSV File */
                String [] nextLine; /* for every line in the file */            
                int lnNum = 0; /* line number */
              
                /* Step-2: Initialise PDF documents - logical objects */
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream("Results.pdf"));
                doc.open();            
                PdfPTable table = new PdfPTable(6);
                
                Phrase phrase1 = new Phrase();
                Phrase phrase2 = new Phrase();
                Phrase phrase3 = new Phrase();
                Phrase phrase4 = new Phrase();
                Phrase phrase5 = new Phrase();
                Phrase phrase6 = new Phrase();
                
                phrase1.add(new Chunk("ID",  new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
                phrase2.add(new Chunk("Element",  new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));	
                phrase3.add(new Chunk("Date",  new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
                phrase4.add(new Chunk("Quantity (m)",  new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
                phrase5.add(new Chunk("Men",  new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
                phrase6.add(new Chunk("Notes",  new Font(Font.FontFamily.COURIER, 10, Font.BOLD)));
                
                table.addCell(phrase1);
                table.addCell(phrase2);
                table.addCell(phrase3);
                table.addCell(phrase4);
                table.addCell(phrase5);
                table.addCell(phrase6);
               
                table.setTotalWidth(new float[]{ 25, 65, 75, 80, 25, 200 });
                table.setLockedWidth(true);
                PdfPCell table_cell;
                
                
                /* Step -3: Loop through CSV file and populate data to PDF table */
                while ((nextLine = reader.readNext()) != null) {
                	
                        lnNum++;        
                        
                        table_cell = new PdfPCell(new Phrase(nextLine[0], font));
                        table.addCell(table_cell);
                        table_cell = new PdfPCell(new Phrase(nextLine[1], font));
                        table.addCell(table_cell);
                        table_cell = new PdfPCell(new Phrase(nextLine[2], font));
                        table.addCell(table_cell); 
                        table_cell = new PdfPCell(new Phrase(nextLine[3], font));
                        table.addCell(table_cell); 
                        table_cell = new PdfPCell(new Phrase(nextLine[4], font));
                        table.addCell(table_cell); 
                        table_cell = new PdfPCell(new Phrase(nextLine[5], font));
                        table.addCell(table_cell); 
                }
              
                /* Step -4: Attach table to PDF and close the document */
                doc.add(table);                       
                doc.close();            
        }
}
	

