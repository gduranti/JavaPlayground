package com.gduranti.pixelreplacer;

import java.io.File;
import java.util.logging.Logger;

import com.gduranti.pixelreplacer.util.PdfBuilder;

public class Main {

    private static Logger LOGGER = Logger.getAnonymousLogger();

    public static void main(String... args) throws Exception {

        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");

        if (args.length != 5) {
            throw new RuntimeException("Invalid args.");
        }

        String sourceImagePath = args[0];
        String targetPdfPath = args[1];
        String originalColor = args[2];
        String newColor = args[3];
        boolean autoOpenPdf = Boolean.parseBoolean(args[4]);

        logArgs(sourceImagePath, targetPdfPath, originalColor, newColor);

        PixelReplacer pixelReplacer = new PixelReplacer();
        Picture newPicture = pixelReplacer.mapColor(originalColor, newColor).replace(new File(sourceImagePath));

        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.buildPdf(newPicture, targetPdfPath, autoOpenPdf);

        LOGGER.info("Concluído");
    }

    private static void logArgs(String sourceImagePath, String targetPdfPath, String originalColor, String newColor) {
        LOGGER.info("Iniciando...");
        LOGGER.info("sourceImagePath.: " + sourceImagePath);
        LOGGER.info("targetPdfPath...: " + targetPdfPath);
        LOGGER.info("originalColor...: " + originalColor);
        LOGGER.info("newColor........: " + newColor);
    }

}
