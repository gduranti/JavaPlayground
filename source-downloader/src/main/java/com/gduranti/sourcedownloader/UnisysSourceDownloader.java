package com.gduranti.sourcedownloader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class UnisysSourceDownloader {

    public void downloadSistema(String sistema, Integer programa) {
        System.out.println("\n\n--------------------------------------------");
        System.out.println("Downloading sources of " + sistema + "...");
        SourceDownloader downloader = new SourceDownloader(
            "http://{{url-here}}/source/xref/2_UNISYS_DJUS/",
             "C:\\dev\\projetos\\Unisys\\");
        getFileList(programa).forEach(file -> downloader.downloadFile(sistema, file));
    }

    private static List<String> getFileList(Integer programa) {

        if (programa != null) {
            return Arrays.asList(programa.toString());
        }

        List<String> files = new ArrayList<>();

        files.add("DASDLSYMBOL");
        files.add("2000");
        files.add("2001");
        files.add("2002");
        files.add("2003");
        files.add("2004");
        files.add("2005");
        files.add("2006");
        files.add("2999");

        for (int i = 1000; i > 0; i--) {
            files.add(StringUtils.leftPad(Integer.toString(i), 3, "0"));
        }

        return files;
    }

}
