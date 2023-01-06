package ficheros;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.*;

public class JSONReaderWriter {
	
	public static ArrayList<Objeto> read() throws Exception {
		ArrayList<Objeto> objetos = new ArrayList<>();
		
		File file = new File("prueba.json");
		
		String jsonString = Files.readString(file.toPath());
		
		JSONArray jsonArray = new JSONArray(jsonString);
		for(int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			
			Objeto o = new Objeto(
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
	
	public static String[] getKeys() throws Exception {
		File file = new File("prueba.json");
		
		String jsonString = Files.readString(file.toPath());
		
		JSONArray jsonArray = new JSONArray(jsonString);
		JSONObject jsonObj = jsonArray.getJSONObject(0);
		

		return (String[]) jsonObj.keySet().toArray();
	}

	public static void write(Objeto objeto) throws Exception{
		File file = new File("prueba.json");
		
		String jsonString = Files.readString(file.toPath());
		JSONArray jsonArray = new JSONArray(jsonString);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Año", objeto.getDato1());
		jsonObj.put("Número de plazas", objeto.getDato2());
		jsonObj.put("Número de alumnos 0-1 años", objeto.getDato3());
		jsonObj.put("Número de alumnos 1-2 años", objeto.getDato4());
		jsonObj.put("Número de alumnos 2-3 años", objeto.getDato5());
		jsonObj.put("Número total de alumnos", objeto.getDato6());
		jsonArray.put(jsonObj);
		
		String finalJson = jsonArray.toString(4);

		Files.writeString(file.toPath(), finalJson.toString());
	}
}
