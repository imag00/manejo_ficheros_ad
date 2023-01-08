package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReaderWriter {

	private static final String FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\archivos\\DatosAbiertosCSV.csv";

	public static ArrayList<Objeto> read() throws Exception {
		ArrayList<Objeto> objs = new ArrayList<>();

		if (new File(FILE_PATH).isFile()) {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
			String row;
			while ((row = reader.readLine()) != null) {
				String[] datos = row.split(",");
				System.out.println(Arrays.toString(datos));
				objs.add(new Objeto(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]));
			}
			reader.close();

		} else {
			System.out.println(System.getProperty("user.dir"));
			throw new Exception("Error al encontrar el archivo csv");
		}

		return objs;
	}

	public static void write(Objeto obj) throws Exception {
		FileWriter writer = new FileWriter(FILE_PATH, true);
		writer.append(obj.getDato1() + ",");
		writer.append(obj.getDato2() + ",");
		writer.append(obj.getDato3() + ",");
		writer.append(obj.getDato4() + ",");
		writer.append(obj.getDato5() + ",");
		writer.append(obj.getDato6());
		writer.append("\n");

		writer.flush();
		writer.close();
	}
}
