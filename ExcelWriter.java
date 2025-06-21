
package EcomAutomation;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelWriter(String sheetName) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    public void writeHeader(String[] headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }

    public void writeMobileData(int rowNum, String mobileName, int price) {
        Row dataRow = sheet.createRow(rowNum);
        dataRow.createCell(0).setCellValue(mobileName);
        dataRow.createCell(1).setCellValue(price);
    }

    public void writeFirstMobilePriceStatus(int rowNum, String status) {
        Row dataRow = sheet.getRow(rowNum);
        if (dataRow == null) {
            dataRow = sheet.createRow(rowNum);
        }
        dataRow.createCell(2).setCellValue(status);
    }

    public void autoSizeColumns() {
        if (sheet.getRow(0) != null) {
            for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }
        }
    }

    public void save(String fileName) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
            System.out.println("Excel file generated successfully: " + fileName);
        } finally {
            close();
        }
    }

    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}


