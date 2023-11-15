package com.gduranti.sourcedownloader;

public class Main {

    public static void main(String... args) throws Exception {

        UnisysSourceDownloader downloader = new UnisysSourceDownloader();
        downloader.downloadSistema("VSIS", null);
        // downloader.downloadSistema("GSIS");
        // downloader.downloadSistema("SSIS");

    }


}
