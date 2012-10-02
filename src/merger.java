
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;


public class merger { 
	static ArrayList<String> pdfs = new ArrayList<String>(); 
	
	
	
	public static void main() throws DocumentException, IOException
	{
		 merger_gui isim = new merger_gui();
		 mergePdfs(isim.fileout);
		
	}
	public static void mergePdfs(String FileName) throws DocumentException, IOException
	{
		try{
			String pathToDesktop = System.getProperty("user.home");
			System.out.println(pathToDesktop);
			Document NewDoc = new Document();
	        PdfCopy copy = new PdfCopy(NewDoc, new FileOutputStream(pathToDesktop + "/" + FileName  + ".pdf"));
	        NewDoc.open();
	        PdfReader ReadInputPDF;
	        int number_of_pages;
	        for (int i = 0; i < pdfs.size(); i++) {
	                ReadInputPDF = new PdfReader(pdfs.get(i));
	                number_of_pages = ReadInputPDF.getNumberOfPages();
	                for (int page = 0; page < number_of_pages; ) {
	                        copy.addPage(copy.getImportedPage(ReadInputPDF, ++page));
	                      }
	        }
	        NewDoc.close();
		}
		catch (Exception ex) {
			
	        JOptionPane.showMessageDialog(null, "Dosya bozuk ya da Pdf değil !!!");
	    }
	
	}
}
