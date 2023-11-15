package com.gduranti.pixelreplacer.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

import com.gduranti.pixelreplacer.Picture;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;

public class PdfBuilder {

    public void buildPdf(Picture newPicture, String targetPdfPath, boolean autoOpenPdf) throws Exception {

        Document document = new Document();
        document.setPageSize(PageSize.A4.rotate());

        PdfWriter.getInstance(document, new FileOutputStream(targetPdfPath));
        document.open();

        Image img = PngImage.getImage(newPicture.toInputStream());
        img.scaleToFit(800, 520);

        document.add(img);

        document.close();

        if (autoOpenPdf) {
            Desktop.getDesktop().open(new File(targetPdfPath));
        }
    }

}
