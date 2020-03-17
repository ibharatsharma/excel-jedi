package com.basicutils.exceljedi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bharat.exceljedi.annotations.Column;
import com.bharat.exceljedi.annotations.Sheet;

public class MetadataExtractor<T> {

	private static Logger logger = LoggerFactory.getLogger(MetadataExtractor.class);

	private Metadata md;

	public MetadataExtractor() {
		md = new Metadata();
	}

	public Metadata extractMetadata(T t) {
		try {
			extractSheetName(t);
			extractColumnNames(t);

			logger.info("metadata: {}", md);

		} catch (Exception e) {
			logger.error("Could not derive metadata.", e);
		}
		return md;
	}

	/**
	 * Takes sheet name from annotation if {@code @Sheet} annotation is present.
	 * Else takes it from
	 * 
	 * @param sheet
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	private void extractSheetName(T t) throws Exception {
		Sheet sheet = t.getClass().getAnnotation(Sheet.class);
		String sheetName = null;
		if (sheet != null) {
			Class<? extends Annotation> type = sheet.annotationType();
			Method method = type.getDeclaredMethod("name");
			sheetName = (String) method.invoke(sheet, (Object[]) null);
		} else {
			sheetName = t.getClass().getSimpleName();
		}
		// sheet name cannot be more than 32 chars
		if(sheetName.length() > 32) {
			sheetName = sheetName.substring(0, 31);
		}
		md.setSheetName(sheetName);
	}

	private void extractColumnNames(T t) throws Exception {
		logger.info("Extracting column names from {}", t.toString());
		Field[] fields = t.getClass().getDeclaredFields();

		for (Field f : fields) {
			Column c = f.getAnnotation(Column.class);

			if (c == null) {
				// the field doesn't have annotation, derive column name based on field name.
				f.setAccessible(true);
				md.getColumns().add(new Metadata.Col(f.getName(), f.getName(), f.getType().getSimpleName()));
				logger.info("got columnName: {}", f.getName());
			} else {
				Column col = f.getAnnotation(Column.class);
				Class<? extends Annotation> type = col.annotationType();
				Method method = type.getDeclaredMethod("name");
				String columnNameStr = (String) method.invoke(col, (Object[]) null);
				md.getColumns().add(new Metadata.Col(f.getName(), columnNameStr, f.getType().getSimpleName()));
				logger.info("got columnName: {} from annotation", columnNameStr);
			}
		}

	}
}
