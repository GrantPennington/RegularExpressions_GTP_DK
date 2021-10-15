package Two;
/**
 * Extract Text from the Bellarmine Schedule PDF using PDFBox library
 * @author Grant Pennington, Dalton Kilner
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ExtractText {
    public static void main(String[] args){
        try (PDDocument document = PDDocument.load(new File("/home/cs322/Documents/RegularExpressions/Part_Two/Bellarmine_Schedule.pdf"))) { // input the PDF through the command line
            document.getClass();
            File plainText = new File("/home/cs322/Documents/RegularExpressions/Part_Two/Bellarmine_Schedule.txt");
            FileWriter writer = new FileWriter(plainText); // new FileWriter object being passed the plainText File object
            if (!document.isEncrypted()) {
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                String lines[] = pdfFileInText.split("\\r?\\n");
                
                for (String line : lines) {
                    writer.write(line); // write each line to a text file
                }
                writer.close(); //close the FileWriter
                System.out.println("Text file created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
