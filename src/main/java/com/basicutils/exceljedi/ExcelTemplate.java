package com.basicutils.exceljedi;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExcelTemplate<T> {

	private static Logger logger = LoggerFactory.getLogger(ExcelTemplate.class);

	MetadataExtractor<T> mdExtractor;
	private Metadata md;
	
	public ExcelTemplate() {
		mdExtractor = new MetadataExtractor<>();
	}
	
	/**
	 * The template method for writing excel
	 * @throws Exception 
	 */
	public final void writeExcel(List<T> list, String filePathWithName) throws Exception {
		validate(list);

		T t = list.get(0);
		md = mdExtractor.extractMetadata(t);
		md.setFilePath(filePathWithName);
		// check if file exists.
		
		// write file
		writeFile(list, md);
	}

	protected abstract void writeFile(List<T> list, Metadata md)throws Exception ;

	private void validate(List<T> list) {
		if (list == null || list.size() == 0) {
			throw new ExcelException("Input list cannot be empty.");
		}
	}

	
}
