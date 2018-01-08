package com.gduranti.sourcedownloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Main {

    public static void main(String... args) throws Exception {

        List<String> files = buildFileList();
        for (String file : files) {
            downloadFile(file);
        }

    }

    private static List<String> buildFileList() {
        List<String> files = new ArrayList<>();

        for (int i = 0; i < 2100; i++) {
            files.add(StringUtils.leftPad(Integer.toString(i), 3, "0"));
        }

        files.add("outro-arquivo");
        files.add("outro-arquivo2");
        return files;
    }

    private static void downloadFile(String arquivo) throws MalformedURLException, IOException, FileNotFoundException {
        
        try {
            URL url = new URL("http://url.../" + arquivo);

            File file = new File("C:\\caminho.....\\" + arquivo + ".cbl");
            if (file.exists()) {
                file.delete();
            }

            ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);

            outputStream.close();
            System.out.println(arquivo + " downloaded");
        } catch (java.io.FileNotFoundException e) {
            System.out.println(arquivo + " file not found");
        }
    }
    
}
