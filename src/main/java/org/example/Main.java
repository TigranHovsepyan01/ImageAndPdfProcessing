package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcoimg/360_F_532897269_oLGrBiPN5FnvUjnGf07onQQnduumZeYV.jpgns.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        String imagePath = "img/360_F_532897269_oLGrBiPN5FnvUjnGf07onQQnduumZeYV.jpg";
        String pdfPath = "/home/user/IdeaProjects/mvnProject/img/sample.pdf";
        try{
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            writeText();

            PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);

            try(PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.drawImage(image, 400, 0, image.getWidth() / 2, image.getHeight() / 2);
            }

            document.save(pdfPath);
            document.close();

            System.out.println("PDF created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeText() {
        String inputImagePath = "img/360_F_532897269_oLGrBiPN5FnvUjnGf07onQQnduumZeYV.jpg";
        String outputImagePath = "img/stampWithSignature.jpg";

        try{
            BufferedImage originalImage = ImageIO.read(new File(inputImagePath));

            Graphics2D g2d = originalImage.createGraphics();

            Font font = new Font("Arial", Font.BOLD, 24);
            g2d.setFont(font);
            g2d.setColor(Color.GREEN);

            int x = 150;
            int y = 200;

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a signature of up to 3 characters: - ");
            String text = scanner.nextLine();
            int size = text.length();
            if (size > 3) {
                throw new RuntimeException("Enter up to 3 characters: ");
            }

            g2d.drawString(text, x, y);

            ImageIO.write(originalImage, "jpg", new File(outputImagePath));

            System.out.println("Text added to the image successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}