package com.merge;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;


public class Merger {
	
	private ArrayList<String> pdfs = new ArrayList<String>(); 
	private String pathToDesktop;
	private Document NewDoc;
	private PdfReader ReadInputPDF;
	
	
	// Default Constructer
	
	public Merger() {
		
	}
	
	public int NumberOfPDFs(){
		
		return this.pdfs.size();
		
	}
	
	// add pdfs to array
	
	public void addPDFs(String Pdf){
		
		this.pdfs.add(Pdf);
		
	}
	
	
	public void mergePdfs(String FileName) throws DocumentException, IOException{
		
		try{
			
			
		// path of desktop
		pathToDesktop = System.getProperty("user.home");
		NewDoc = new Document();
		// outputfile
	        PdfCopy copy = new PdfCopy(NewDoc, new FileOutputStream(pathToDesktop + "/" + FileName  + ".pdf"));
	        NewDoc.open();
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

	
