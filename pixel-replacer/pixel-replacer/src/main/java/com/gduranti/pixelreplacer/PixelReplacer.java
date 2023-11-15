package com.gduranti.pixelreplacer;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gduranti.pixelreplacer.util.ColorConverter;

public class PixelReplacer {

    private class RGB {
        int r, g, b;

        public RGB(Color color) {
            this.r = color.getRed();
            this.g = color.getGreen();
            this.b = color.getBlue();
        }
        
        @Override
        public int hashCode() {
            return r + g + b;
        }

        @Override
        public boolean equals(Object obj) {
            RGB other = (RGB) obj;
            return other.r == this.r && other.g == this.g && other.b == this.b;
        }
    }

    private Map<RGB, Color> colorMap = new HashMap<>();

    public PixelReplacer mapColor(String originalHexColor, String newHexColor) {
        ColorConverter colorConverter = new ColorConverter();
        return mapColor(colorConverter.converteRGB(originalHexColor), colorConverter.converteRGB(newHexColor));
    }

    public PixelReplacer mapColor(Color originalColor, Color newColor) {
        colorMap.put(new RGB(originalColor), newColor);
        return this;
    }

    public Picture replace(File pictureFile) throws IOException {

        Picture originalPicture = new Picture(pictureFile);
        int width = originalPicture.getWidth();
        int height = originalPicture.getHeight();

        Picture newPicture = new Picture(width, height);

        // separate colors
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = originalPicture.getColor(col, row);
                if (colorMap.containsKey(new RGB(color))) {
                    newPicture.setColor(col, row, colorMap.get(new RGB(color)));
                } else {
                    newPicture.setColor(col, row, color);

                }
            }
        }

        return newPicture;
    }

}
