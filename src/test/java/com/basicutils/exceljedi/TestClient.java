package com.basicutils.exceljedi;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator.OfInt;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClient {

	private static final Logger logger = LoggerFactory.getLogger(TestClient.class);
	private static List<Employee> emps = new ArrayList<>();

	public static void main(String[] args) {
		
		ExcelTemplate<Employee> t = new SimpleExcelTemplate<>();
		populateTestData(1_00);
		
		long start = System.currentTimeMillis();
		
		t.writeExcel(emps, "C:\\temp\\test.xlsx", true);
		logger.info("took {} milliseconds", System.currentTimeMillis() - start);
	}

	public static void populateTestData(int count) {
		
		OfInt iterator = IntStream.range(0, count).iterator();     
		
		while(iterator.hasNext()) {
			Employee e1 = new Employee();
		
			e1.setName(RandomStringUtils.random(40, true, false));
			e1.setAge(new Random().nextInt(60));
			e1.setSalary(ThreadLocalRandom.current().nextDouble(100, 10000));
			
			emps.add(e1);
			int next = iterator.nextInt();
			if( next % 100 == 0) {
				logger.info("populated {} records", next);
			}
		}
		
	}
}
