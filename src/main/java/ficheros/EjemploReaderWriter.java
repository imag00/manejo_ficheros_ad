package ficheros;

import java.util.ArrayList;

public class EjemploReaderWriter {

	public static ArrayList<Objeto> read() throws Exception {
		// Hacer cosas de leer

		// Más cosas de leer...

		// try {
		// cosas...
		// catch(Exception e) {
		// Aquí te da un error y no se puede continuar? Lanza:
		// throw new Exception("Descripción del error");
		// Y te redirigirá automáticamente a la página de error
		// }

		// Ejemplo para probar el JSP
		ArrayList<Objeto> objetos = new ArrayList<>();
		objetos.add(new Objeto("Lorem", "ipsum", "dolor", "sit", "amet", "consectetur"));
		objetos.add(new Objeto("adipiscing", "elit", "sed", "do", "eiusmod", "tempor"));

		return objetos;
	}

	public static void write(Objeto objeto) throws Exception {
		// Hacer cosas de escribir

		// Más cosas de escribir...

		// try {
		// cosas...
		// catch(Exception e) {
		// Aquí te da un error y no se puede continuar? Lanza:
		// throw new Exception("Descripción del error");
		// Y te redirigirá automáticamente a la página de error
		// }
	}

}