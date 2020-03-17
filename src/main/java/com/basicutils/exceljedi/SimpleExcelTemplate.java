package com.basicutils.exceljedi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic implementation of Excel Template
 * 
 * @author Bharat
 *
 */
public class SimpleExcelTemplate<T> extends ExcelTemplate<T> {

	private static Logger logger = LoggerFactory.getLogger(SimpleExcelTemplate.class);
	
	@Override
	protected void writeFile(List<T> list, Metadata md) throws ExcelException {

		logger.info("Writing file. md={}", md);
		SXSSFWorkbook workbook = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE); // 100 rows in memory
		
		try {
			
			SXSSFSheet sheet = workbook.createSheet(md.getSheetName());

			SXSSFRow headers = sheet.createRow(0);
			int headerColIndex = 0;
			for (Metadata.Col col : md.getColumns()) {
				headers.createCell(headerColIndex++).setCellValue(col.getColumnHeaderName());
			}

			int rowNum = 1;
			for (Object object : list) {

				SXSSFRow row = sheet.createRow(rowNum++);

				int coluNum = 0;
				for (Metadata.Col col : md.getColumns()) {
					Cell cell = row.createCell(coluNum);

					Field field = object.getClass().getDeclaredField(col.getFieldName());
					field.setAccessible(true);
					Object object2 = field.get(object); // get value
					if (object2 == null) {
						cell.setBlank();
					} else if ("String".equalsIgnoreCase(col.getDatatType())) {
						cell.setCellValue((String) object2);
					} else if ("int".equalsIgnoreCase(col.getDatatType())) {
						cell.setCellValue((int) object2);
					} else if ("Double".equalsIgnoreCase(col.getDatatType())) {
						cell.setCellValue((Double) object2);
					} else {
						logger.error("Datatype not found : {}", col.getDatatType());
					}
					coluNum++;
				}
			}

			Path path = Paths.get(md.getFilePath());
			Files.deleteIfExists(path);
			workbook.write(Files.newOutputStream(path, StandardOpenOption.CREATE_NEW));
		} catch (Exception e) {
			throw new ExcelException("Error while Writing excel file.", e);
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				throw new ExcelException("Encountered error while closing workbook.", e);
			}
		}
	}
}
