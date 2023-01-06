package dev.amrv.xls;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import ficheros.DataRow;
import ficheros.IOFichero;

public class ExcelOperator extends IOFichero {

	private Workbook workbook;

	public ExcelOperator(String file) {
		this(new File(file));
	}

	public ExcelOperator(File file) {
		super(file);
	}

	@Override
	public void write(DataRow[] data) throws Exception {
		workbook = WorkbookFactory.create(this.getFile());

		Sheet page = workbook.createSheet();

		for (int i = 0; i < data.length; i++) {
			Row row = page.createRow(i);

			for (int d = 0; d < data[i].getData().length; d++) {
				row.createCell(d, CellType.STRING).setCellValue(data[i].getData()[d]);
			}
		}

	}

	@Override
	public DataRow[] read() throws Exception {
		List<DataRow> rows = new ArrayList<>();

		workbook = WorkbookFactory.create(this.getFile());
		Sheet page = workbook.getSheetAt(0);

		page.rowIterator().forEachRemaining(row -> {

			DataRow.DataRowBuilder builder = new DataRow.DataRowBuilder();

			row.cellIterator().forEachRemaining(cell -> {

				switch (cell.getCellType()) {
				case BOOLEAN:
					builder.append(Boolean.toString(cell.getBooleanCellValue()));
					break;
				case FORMULA:
					switch (cell.getCachedFormulaResultType()) {
					case BOOLEAN:
						builder.append(Boolean.toString(cell.getBooleanCellValue()));
						break;
					case NUMERIC:
						builder.append(cell.getNumericCellValue() + "");
						break;
					case STRING:
						builder.append(cell.getStringCellValue());
						break;
					case ERROR:
					default:
						builder.append("ERROR");
						break;
					}
					break;
				case NUMERIC:
					builder.append(cell.getNumericCellValue() + "");
					break;
				case STRING:
					builder.append(cell.getStringCellValue());
					break;
				case _NONE:
				case ERROR:
				case BLANK:
				default:
					builder.append("");
					break;
				}

			});

			rows.add(builder.build());
		});

		return rows.toArray(new DataRow[0]);
	}

	@Override
	public String getJSPResultName() {
		// TODO Auto-generated method stub
		return "DatosAbiertosXLS";
	}
}
