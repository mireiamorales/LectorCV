package lectorcv;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


public class PdfToString {
	
	public static final String DEST = "C:\\Wrk\\inditex\\EjemploCVPdf.pdf";
	//public static final String DEST = "C:\\Wrk\\inditex\\EjemploCVDocx.docx";
	//public static final String DEST = "C:\\Wrk\\inditex\\EjemploCVDoc.doc";
	

	public static void main(String[] args) throws IOException {
		
		final Logger logger = Logger.getLogger(PdfToString.class);
		logger.setLevel(Level.OFF);
		
		if(DEST.toUpperCase().endsWith(".PDF"))
		{		
			try{
				PDDocument document = null; 
				document = PDDocument.load(new File(DEST));
				document.getClass();
				if( !document.isEncrypted() ){
				    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				    stripper.setSortByPosition( true );
				    PDFTextStripper Tstripper = new PDFTextStripper();
				    String st = Tstripper.getText(document);
				    System.out.println(st);
				}
			}catch(Exception e){
			    e.printStackTrace();
			}			
			
		}
		else if (DEST.toUpperCase().endsWith(".DOCX"))
		{	
			
			try {
				File file = new File(DEST);
				FileInputStream fis = new FileInputStream(file.getAbsolutePath());

				XWPFDocument document = new XWPFDocument(fis);

				List<XWPFParagraph> paragraphs = document.getParagraphs();
				
				System.out.println("Total no of paragraph "+paragraphs.size());
				for (XWPFParagraph para : paragraphs) {
					System.out.println(para.getText());
				}
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (DEST.toUpperCase().endsWith(".DOC")) 
		{
			try {
				File file = new File(DEST);
				FileInputStream fis = new FileInputStream(file.getAbsolutePath());

				HWPFDocument doc = new HWPFDocument(fis);

				WordExtractor we = new WordExtractor(doc);

				String[] paragraphs = we.getParagraphText();
				
				System.out.println("Total no of paragraph "+paragraphs.length);
				for (String para : paragraphs) {
					System.out.println(para.toString());
				}
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
