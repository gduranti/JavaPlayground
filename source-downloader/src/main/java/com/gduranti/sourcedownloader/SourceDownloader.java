package com.gduranti.sourcedownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class SourceDownloader {

    private String url;
    private String folderpath;

    public SourceDownloader(String url, String folderpath) {
        this.url = url;
        this.folderpath = folderpath;
    }

    public void downloadFile(String sistema, String arquivo) {
        try {
            URL url = new URL(this.url + sistema + "/" + arquivo);

            String systemFolderPath = this.folderpath + sistema;

            File folder = new File(systemFolderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(systemFolderPath + "\\" + arquivo + ".cbl");
            if (file.exists()) {
                file.delete();
            }

            ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);

            outputStream.close();
            System.out.println(sistema + " " + arquivo + " downloaded");
        } catch (java.io.FileNotFoundException e) {
            System.out.println(sistema + " " + arquivo + " file not found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
