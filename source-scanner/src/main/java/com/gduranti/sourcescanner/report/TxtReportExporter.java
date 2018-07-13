package com.gduranti.sourcescanner.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.gduranti.sourcescanner.ScannerReport;
import com.gduranti.sourcescanner.analyzer.AnalyzedFile;
import com.gduranti.sourcescanner.analyzer.SouceFragment;
import com.gduranti.sourcescanner.analyzer.SourceLine;

class TxtReportExporter implements ReportExporter {

    @Override
    public File export(ScannerReport report, String destinationPath) {
        String content = buildText(report);
        return writeFile(destinationPath, content);
    }

    public String buildText(ScannerReport report) {
        int fragments = 0;
        int matchingFiles = 0;

        StringBuilder builder = new StringBuilder();

        for (AnalyzedFile analyzedFile : report.getSourceFiles()) {

            if (!analyzedFile.getFragments().isEmpty()) {
                matchingFiles++;

                builder.append("\nFile: " + analyzedFile.getName());
                builder.append("\n\n##############################################################################\n\n");

                for (SouceFragment fragment : analyzedFile.getFragments()) {
                    fragments++;
                    for (SourceLine sourceLine : fragment.getLines()) {
                        builder.append(sourceLine.getContent()).append("\n");
                    }
                    builder.append("\n\n##############################################################################\n\n");
                }
            }
        }

        builder.append("\nMatching files: " + matchingFiles);
        builder.append("Fragments: " + fragments);

        return builder.toString();
    }

    private File writeFile(String destinationPath, String content) {
        FileOutputStream fos = null;
        try {
            File file = new File(destinationPath);
            fos = new FileOutputStream(file);
            IOUtils.write(content, fos);
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
