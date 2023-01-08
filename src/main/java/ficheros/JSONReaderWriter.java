package ficheros;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.*;

import interfaz.ServletAccesoDA;

public class JSONReaderWriter {
	
	private static final String PATH = "DatosAbiertosJSON.json";
	
	public static ArrayList<Dato> read() throws Exception {
		ArrayList<Dato> objetos = new ArrayList<>();
		
		File file = new File(PATH);
		
		String jsonString = Files.readString(file.toPath());
		
		JSONArray jsonArray = new JSONArray(jsonString);
		for(int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			
			Dato o = new Dato(
					String.valueOf(jsonObj.getInt("Año")),
					String.valueOf(jsonObj.getInt("Número de plazas")),
					String.valueOf(jsonObj.getInt("Número de alumnos 0-1 años")),
					String.valueOf(jsonObj.getInt("Número de alumnos 1-2 años")),
					String.valueOf(jsonObj.getInt("Número de alumnos 2-3 años")),
					String.valueOf(jsonObj.getInt("Número total de alumnos")));
			
			objetos.add(o);
		}
						
		return objetos;
	}

	public static void write(Dato objeto) throws Exception{
		File file = new File(PATH);
		
		String jsonString = Files.readString(file.toPath());
		JSONArray jsonArray = new JSONArray(jsonString);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Año", objeto.getAnio());
		jsonObj.put("Número de plazas", objeto.getPlazas());
		jsonObj.put("Número de alumnos 0-1 años", objeto.getAlumnosCeroUno());
		jsonObj.put("Número de alumnos 1-2 años", objeto.getAlumnosUnoDos());
		jsonObj.put("Número de alumnos 2-3 años", objeto.getAlumnosDosTres());
		jsonObj.put("Número total de alumnos", objeto.getAlumnosTotal());
		jsonArray.put(jsonObj);
		
		String finalJson = jsonArray.toString(4);

		Files.writeString(file.toPath(), finalJson.toString());
	}
}
