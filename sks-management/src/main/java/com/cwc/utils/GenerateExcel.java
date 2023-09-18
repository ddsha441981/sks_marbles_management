package com.cwc.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cwc.model.Category;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

//@AllArgsConstructor
public class GenerateExcel {
	
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Category> catlist;

    
public GenerateExcel(List<Category> catlist) {
		workbook = new XSSFWorkbook();
		this.catlist = catlist;
	}        

    private void writeHeaderLine() {
        sheet = workbook.createSheet("category");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();

        //set color of heading background
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        //Set Color of heading
        font.setColor(IndexedColors.BLACK1.getIndex());
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Code", style);
        createCell(row, 3, "Description", style);
        createCell(row, 4, "Status", style);
    }
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Category catListDetails : catlist) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, catListDetails.getCategoryId(), style);
            createCell(row, columnCount++, catListDetails.getCategoryName(), style);
            createCell(row, columnCount++, catListDetails.getCategoryCode(), style);
            createCell(row, columnCount++, catListDetails.getCategoryDescription(), style);
            createCell(row, columnCount++, catListDetails.isStatus(), style);
        }
    }
    public byte[] export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
		return null;
    }
    
//    public byte[] export() throws IOException {
//        writeHeaderLine();
//        writeDataLines();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//
//        return outputStream.toByteArray();
//    }


}
