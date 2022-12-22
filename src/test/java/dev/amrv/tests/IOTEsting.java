package dev.amrv.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class IOTEsting {

    public static void main(String[] args) {
	final String doc = "terrenos-cinegeticos.xls";
	Workbook document;
	try {
	    document = WorkbookFactory.create(IOTEsting.class.getClassLoader().getResourceAsStream(doc));
	    for (int i = 0; i < document.getNumberOfSheets(); i++) {
		viewSheet(document.getSheetAt(i));
	    }
	} catch (EncryptedDocumentException | IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    public static void viewSheet(Sheet sheet) {
	if (sheet == null) {
	    System.err.println("Unable to load sheet");
	    return;
	}

	for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
	    viewRow(sheet.getRow(i));
	}
    }

    public static void viewRow(Row row) {
	if (row == null) {
	    System.err.println("Unable to load row");
	    return;
	}

	System.out.print("| ");
	for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
	    viewCell(row.getCell(i));
	    System.out.print(" | ");
	}
	System.out.println();
    }

    public static void viewCell(Cell cell) {
	if (cell == null) {
	    System.err.println("Unable to read cell");
	    return;
	}

	System.out.print(cell.getStringCellValue());
    }

}
