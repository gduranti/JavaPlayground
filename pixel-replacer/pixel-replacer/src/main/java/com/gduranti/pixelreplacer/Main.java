package com.gduranti.pixelreplacer;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;

public class Main {

    public static void main(String... args) throws Exception {

        PixelReplacer pixelReplacer = new PixelReplacer();
        
        Picture newPicture = pixelReplacer.mapColor(new Color(153, 180, 209), Color.BLACK)
                                          .replace(new File("C:\\Users\\gabriel-duranti\\Desktop\\pont.png"));

        String destPath = "C:\\temp\\" + new Random().nextInt() + ".png";
        File tempFile = newPicture.save(destPath);

        buildPdf(destPath);

        // Desktop.getDesktop().open(tempFile);

        System.out.println("Concluído");
    }

    private static void buildPdf(String destPath) throws Exception {

        Document document = new Document();
        document.setPageSize(PageSize.A4.rotate());

        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\gabriel-duranti\\Desktop\\pont.pdf"));
        document.open();

        Image img = PngImage.getImage(destPath);
        img.scaleToFit(800, 520);

        document.add(img);

        document.close();
    }

}
