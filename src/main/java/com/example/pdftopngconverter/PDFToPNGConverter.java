package com.example.pdftopngconverter;

/**
 * @author Nmh
 * @version 1.0
 * @description TODO
 * @date 3/16/2025
 */
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PDFToPNGConverter {
    public static void main(String[] args) {
        // Specify PDF file path directly
        String pdfFilePath = "your PDF file path";

        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            for (int page = 0; page < pageCount; page++) {
                // Render PDF to BufferedImage
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

                // Output PNG file path (same directory as PDF)
                String outputPath = pdfFilePath.replace(".pdf", "_page_" + (page + 1) + ".png");

                // Saving PNG Images
                ImageIOUtil.writeImage(bim, outputPath, 300);
                System.out.println("saved:" + outputPath);
            }
        } catch (IOException e) {
            System.err.println("Error processing PDF files:" + e.getMessage());
        }
    }
}


