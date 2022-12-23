package dev.amrv.tests;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class IOTEsting {

	private static final int[] STRING_SIZE = { 40, 40, 30, 20, 20, 11, 35, 40, 40, 12, 12, 30 };

	public static void main(String[] args) {
		final String doc = "comercios.xlsx";
		StringBuilder builder = new StringBuilder();

		try {
			Workbook document = WorkbookFactory.create(IOTEsting.class.getClassLoader().getResourceAsStream(doc));
			for (int i = 0; i < document.getNumberOfSheets(); i++) {
				builder.append(viewSheet(document.getSheetAt(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(builder.toString());

	}

	public static String viewSheet(Sheet sheet) {
		if (sheet == null) {
			System.err.println("Unable to load sheet");
			return "";
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			builder.append(viewRow(sheet.getRow(i)));
		}
		return builder.toString();
	}

	public static String viewRow(Row row) {
		if (row == null)
			return "| Empty";

		StringBuilder builder = new StringBuilder("| ");

		for (int i = 0; i < row.getLastCellNum(); i++) {
			builder.append(viewCell(row.getCell(i), i)).append(" | ");
		}
		return builder.toString() + System.lineSeparator();
	}

	public static String viewCell(Cell cell, int col) {
		if (cell == null) {
			return " ".repeat(STRING_SIZE[col]);
		}

		return formatString(cell.getStringCellValue(), col);
	}

	public static String formatString(String str, int col) {
		if (str.length() > STRING_SIZE[Math.min(col, STRING_SIZE.length - 1)] - 3) {
			return str.substring(0, STRING_SIZE[Math.min(col, STRING_SIZE.length - 1)] - 3) + "...";

		} else if (str.length() < STRING_SIZE[Math.min(col, STRING_SIZE.length - 1)]) {
			return str + " ".repeat(STRING_SIZE[Math.min(col, STRING_SIZE.length - 1)] - str.length());

		} else
			return str;

	}

}
