package com.gduranti.pixelreplacer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Picture {

    private BufferedImage buf;
    private int width;
    private int height;

    public Picture(File file) throws IOException {
        this.buf = ImageIO.read(file);
        this.width = buf.getWidth();
        this.height = buf.getHeight();
    }

    public Picture(int w, int h) {
        this.width = w;
        this.height = h;
        this.buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor(int col, int row) {
        return new Color(buf.getRGB(col, row));
    }

    public void setColor(int col, int row, Color color) {
        buf.setRGB(col, row, color.getRGB());
    }

    public InputStream toInputStream() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(buf, "png", os);
        return new ByteArrayInputStream(os.toByteArray());
    }

    public File save(String destPath) throws IOException {
        File tempFile = new File(destPath);
        ImageIO.write(buf, "png", tempFile);
        return tempFile;
    }

}