package com.cwc.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelGeneratorService {
	public byte[] exportDataToExcel(List<Map<String, String>> data, String entityType) throws IOException {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet(entityType + " Data Sheet");

			// Create headers
			Row headerRow = sheet.createRow(0);
			int colNum = 0;
			for (String header : data.get(0).keySet()) {
				Cell cell = headerRow.createCell(colNum++);
				cell.setCellValue(header);
			}

			// Create data rows
			int rowNum = 1;
			for (Map<String, String> rowData : data) {
				Row row = sheet.createRow(rowNum++);
				colNum = 0;
				for (String header : rowData.keySet()) {
					Cell cell = row.createCell(colNum++);
					cell.setCellValue(rowData.get(header));
				}
			}
			
			workbook.write(outputStream);
			return outputStream.toByteArray();
		}
		
	}

}
