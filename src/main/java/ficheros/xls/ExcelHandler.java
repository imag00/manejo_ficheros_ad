package ficheros.xls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import ficheros.Dato;

public class ExcelHandler {

	private Workbook workbook;
	private File file;

	public ExcelHandler(String file) {
		this.file = new File(file);
		try {
			workbook = WorkbookFactory.create(this.file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void append(Dato obj) throws Exception {
		Sheet page = workbook.getSheetAt(0);

		Row row = page.createRow(page.getLastRowNum() + 1);

		row.createCell(0).setCellValue(obj.getAnio());
		row.createCell(1).setCellValue(obj.getPlazas());
		row.createCell(2).setCellValue(obj.getAlumnosCeroUno());
		row.createCell(3).setCellValue(obj.getAlumnosUnoDos());
		row.createCell(4).setCellValue(obj.getAlumnosDosTres());
		row.createCell(5).setCellValue(obj.getAlumnosTotal());
	}

	public void write(DataRow[] data) throws Exception {

		Sheet page = workbook.createSheet();

		for (int i = 0; i < data.length; i++) {
			Row row = page.createRow(i);

			for (int d = 0; d < data[i].getData().length; d++) {
				row.createCell(d, CellType.STRING).setCellValue(data[i].getData()[d]);
			}
		}

		OutputStream output = new FileOutputStream(this.file);
		workbook.write(output);
		output.close();
		workbook.close();

	}

	public Dato[] read() throws Exception {
		List<DataRow> rows = new ArrayList<>();

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
						builder.append((cell.getNumericCellValue() + "").replace(".0", ""));
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
					builder.append((cell.getNumericCellValue() + "").replace(".0", ""));
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

		workbook.close();
		Dato[] output = new Dato[rows.size()];

		for (int i = 0; i < output.length; i++) {
			final String[] content = rows.get(i).getData();
			output[i] = new Dato(content[0], content[1], content[2], content[3], content[4], content[5]);
		}

		return output;
	}

	public String getJSPResultName() {
		// TODO Auto-generated method stub
		return "DatosAbiertosXLS.jsp";
	}
}
