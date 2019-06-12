package com.gduranti.pixelreplacer;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PicureHandler {

    public static void main(String[] args) throws IOException {

        Picture originalPicture = new Picture("C:\\Users\\gabriel-duranti\\Desktop\\ponto.png");
        int width = originalPicture.getWidth();
        int height = originalPicture.getHeight();

        Picture newPicture = new Picture(width, height);

        // separate colors
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = originalPicture.getColor(col, row);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                if (r == 153
                        && g == 180
                        && b == 209) {

                    newPicture.setColor(col, row, Color.BLACK);
                } else {
                    newPicture.setColor(col, row, color);

                }
            }
        }

        File tempFile = newPicture.save("C:\\temp\\" + new Random().nextInt() + ".png");
        Desktop.getDesktop().open(tempFile);
    }

}
