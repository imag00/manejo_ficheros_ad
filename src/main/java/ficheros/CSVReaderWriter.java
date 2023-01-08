package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import interfaz.ServletAccesoDA;

public class CSVReaderWriter {

	private static final String FILE_PATH = "DatosAbiertosCSV.csv";

	public static ArrayList<Dato> read() throws Exception {
		ArrayList<Dato> objs = new ArrayList<>();

		if (new File(FILE_PATH).isFile()) {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
			String row;
			while ((row = reader.readLine()) != null) {
				// En caso de que el archivo empiece con el BOM de UTF-8 lo saltamos
		        if (row.startsWith("\uFEFF"))
		            row = row.substring(1);
		        
		        row = row.replaceAll("\"", "");
		        
				String[] datos = row.split(",");
				System.out.println(Arrays.toString(datos));
				objs.add(new Dato(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]));
			}
			reader.close();

		} else {
			throw new Exception("Error al encontrar el archivo csv");
		}

		return objs;
	}

	public static void write(Dato obj) throws Exception {
		FileWriter writer = new FileWriter(FILE_PATH, true);
		writer.append(obj.getAnio() + ",");
		writer.append(obj.getPlazas() + ",");
		writer.append(obj.getAlumnosCeroUno() + ",");
		writer.append(obj.getAlumnosUnoDos() + ",");
		writer.append(obj.getAlumnosDosTres() + ",");
		writer.append(obj.getAlumnosTotal());
		writer.append("\n");

		writer.flush();
		writer.close();
	}
}
