package com.gduranti.sourcescanner.report;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.gduranti.sourcescanner.ScannerReport;
import com.gduranti.sourcescanner.analyzer.AnalyzedFile;
import com.gduranti.sourcescanner.analyzer.SouceFragment;
import com.gduranti.sourcescanner.analyzer.SourceLine;

public class XlsReportExporter implements ReportExporter {

    @Override
    public File export(ScannerReport report, String destinationPath) {

        System.out.println("Creating file...");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Result");

        buildSheet(report, workbook, sheet);

        return writeFile(destinationPath, workbook);
    }

    private void buildSheet(ScannerReport report, HSSFWorkbook workbook, HSSFSheet sheet) {
        CellStyle defaultStyle = createDefaultStyle(workbook);
        CellStyle fileNameStyle = createFileNameStyle(workbook);
        CellStyle highlightStyle = createHighlightStyle(workbook);
        Font highlightFont = createHighlightFont(workbook);

        int i = 0;

        for (AnalyzedFile analyzedFile : report.getSourceFiles()) {

            if (!analyzedFile.getFragments().isEmpty()) {

                sheet.createRow(i++);
                sheet.createRow(i++);
                sheet.createRow(i++);
                sheet.createRow(i++);

                HSSFRow fileRow = sheet.createRow(i++);
                HSSFCell fileCell = fileRow.createCell(0);
                fileCell.setCellStyle(fileNameStyle);

                HSSFCell fileCell2 = fileRow.createCell(1);
                fileCell2.setCellStyle(fileNameStyle);
                fileCell2.setCellValue(analyzedFile.getName());

                sheet.createRow(i++);

                for (SouceFragment fragment : analyzedFile.getFragments()) {

                    sheet.createRow(i++);
                    sheet.createRow(i++);

                    for (SourceLine sourceLine : fragment.getLines()) {
                        HSSFRow row = sheet.createRow(i++);

                        HSSFCell fileNameCell = row.createCell(0);
                        fileNameCell.setCellStyle(fileNameStyle);
                        fileNameCell.setCellValue(analyzedFile.getName());

                        HSSFCell cell = row.createCell(1);

                        if (sourceLine.hasMatches()) {
                            HSSFRichTextString richText = new HSSFRichTextString(sourceLine.getContent());
                            for (Pair<Integer, Integer> match : sourceLine.getMatchingExpressions()) {

                                Integer end = Math.max(sourceLine.getContent().indexOf(" ", match.getRight()), match.getRight());

                                richText.applyFont(match.getLeft(), end, highlightFont);
                                richText.applyFont(end, sourceLine.getContent().length(), defaultStyle.getFontIndex());
                            }
                            cell.setCellValue(richText);
                            cell.setCellStyle(highlightStyle);
                        } else {
                            cell.setCellValue(sourceLine.getContent());
                            cell.setCellStyle(defaultStyle);
                        }
                    }
                }
            }

        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }

    private CellStyle createFileNameStyle(HSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFont(createFileNameFont(workbook));
        style.setFillForegroundColor(HSSFColorPredefined.BLACK.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private Font createFileNameFont(HSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Consolas");
        font.setColor(HSSFColorPredefined.WHITE.getIndex());
        font.setBold(true);
        return font;
    }

    private CellStyle createHighlightStyle(HSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFont(createDefaultFont(workbook));
        style.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private Font createHighlightFont(HSSFWorkbook workbook) {
        Font font = createDefaultFont(workbook);
        font.setColor(HSSFColorPredefined.RED.getIndex());
        font.setBold(true);
        return font;
    }

    private CellStyle createDefaultStyle(HSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFont(createDefaultFont(workbook));
        return style;
    }

    private Font createDefaultFont(HSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Consolas");
        return font;
    }

    private File writeFile(String destinationPath, HSSFWorkbook workbook) {
        FileOutputStream fos = null;
        try {
            File file = new File(destinationPath);
            file.createNewFile();
            fos = new FileOutputStream(file);
            workbook.write(fos);
            System.out.println("File " + file.getName() + " created successfully");
            return file;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
                workbook.close();
            } catch (Exception e) {
            }
        }
    }

}
