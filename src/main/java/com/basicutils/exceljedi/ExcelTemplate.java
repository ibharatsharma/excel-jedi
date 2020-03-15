package com.basicutils.exceljedi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	 * 
	 * @throws Exception
	 */
	public final void writeExcel(List<T> list, String filePathWithName, boolean overwriteTargetIfExists)
			throws ExcelException {
		validate(list);
		handleExistingTargetFile(filePathWithName, overwriteTargetIfExists);
		
		T t = list.get(0);
		md = mdExtractor.extractMetadata(t);
		md.setFilePath(filePathWithName);
		
		// write file
		writeFile(list, md);
	}

	public final void writeExcel(List<T> list, String filePathWithName) throws ExcelException {
		writeExcel(list, filePathWithName, false);
	}

	protected abstract void writeFile(List<T> list, Metadata md) throws ExcelException;

	private void validate(List<T> list) {
		if (list == null || list.size() == 0) {
			throw new ExcelException("Input list cannot be empty.");
		}
	}

	private void handleExistingTargetFile(String filePathWithName, boolean overwriteTargetIfExists) {

		Path path = Paths.get(filePathWithName);
		if (overwriteTargetIfExists) {
			try {
				Files.deleteIfExists(path);
			} catch (IOException e) {
				throw new ExcelException("Could not delete existing file at -> "+ filePathWithName +". Cause:"+ e.getMessage());
			}
		} else if (Files.exists(path) && !overwriteTargetIfExists) {
			throw new ExcelException("File [" + filePathWithName + "] already exists");
		}
	}

}
