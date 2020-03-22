package com.basicutils.exceljedi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ExcelTemplateShould {

	@Test
	public void throwExceptionWhenInputListIsEmpty() {
		ExcelTemplate<Employee> template = new SimpleExcelTemplate<>();
		List<Employee> emptyList = new ArrayList<>();
		
		ExcelException excelException = assertThrows(ExcelException.class, () -> template.writeExcel(emptyList, "C:\\temp\test.xlsx"));
		assertEquals("Input list cannot be empty.", excelException.getMessage());
	}
	
}
