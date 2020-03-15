package com.basicutils.exceljedi;

import java.util.ArrayList;
import java.util.List;

class Metadata {

	private String filePath;

	private String sheetName;
	private int sheetIndex;
	private List<Col> columns;

	public Metadata() {
		columns = new ArrayList<>();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public List<Col> getColumns() {
		return columns;
	}

	public void setColumn(List<Col> columns) {
		this.columns = columns;
	}
	
	

	@Override
	public String toString() {
		return "Metadata [filePath=" + filePath + ", sheetName=" + sheetName + ", sheetIndex=" + sheetIndex
				+ ", columns=" + columns + "]";
	}



	public static class Col {

		private String fieldName;
		private String columnHeaderName;
		private String datatType;

		public Col(String fieldName, String columnHeaderName, String dataType) {
			this.fieldName = fieldName;
			this.columnHeaderName = columnHeaderName;
			this.datatType = dataType;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getColumnHeaderName() {
			return columnHeaderName;
		}

		public void setColumnHeaderName(String columnHeaderName) {
			this.columnHeaderName = columnHeaderName;
		}

		public String getDatatType() {
			return datatType;
		}

		public void setDatatType(String datatType) {
			this.datatType = datatType;
		}

		@Override
		public String toString() {
			return "Col [fieldName=" + fieldName + ", columnHeaderName=" + columnHeaderName + ", datatType=" + datatType
					+ "]";
		}

	}
}
