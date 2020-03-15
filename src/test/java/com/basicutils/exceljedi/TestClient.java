package com.basicutils.exceljedi;

import java.util.ArrayList;
import java.util.List;

import com.basicutils.exceljedi.ExcelTemplate;
import com.basicutils.exceljedi.SimpleExcelTemplate;

public class TestClient {

	
	public static void main(String[] args) throws Exception {
		
		List<Employee> emps = new ArrayList<>();
		Employee e1 = new Employee();
		e1.setName("Bharat");
		e1.setAge(25);
		e1.setSalary(1000.00);
		emps.add(e1);
		
		ExcelTemplate<Employee> t = new SimpleExcelTemplate<>();
		t.writeExcel(emps, "C:\\temp\\test.xlsx");
	}
}
