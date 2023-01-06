package ficheros;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public abstract class IOFichero {

	private final File file;

	public IOFichero(String file) {
		this(new File(file));
	}

	public IOFichero(File file) {

		try {
			if (!file.exists()) {
				if (file.getParentFile() != null) {
					boolean dirs = file.getParentFile().mkdirs();

					if (!dirs)
						throw new IOException("Cannot create path " + file.getParentFile().getAbsolutePath());
				}
				file.createNewFile();
			} else if (!file.canWrite() || !file.canRead()) {
				file.setWritable(true);
				file.setReadable(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.file = file;
	}

	public File getFile() {
		return this.file;
	}

	/**
	 * Aqui debes de poner el nombre del JSP al que se llamará para escribir los
	 * datos que has leido.
	 * 
	 * @return el nombre del JSP sin la extension de archivo
	 */
	public abstract String getJSPResultName();

	/**
	 * Escribe todos los datos al fichero que se esta manejando desde la instancia
	 * de la implementacion del metodo.
	 * 
	 * @param data las diferentes filas de informacion.
	 * @throws Exception si algun error ocurre durnate la escritura.
	 */
	public abstract void write(DataRow[] data) throws Exception;

	/**
	 * Agrega un elemento a las filas ya existentes del fichero, la implementacion
	 * por defecto realiza una escritura y posteriormente amplia los datos para
	 * volver a escribirlos.
	 * 
	 * Se aconseja implementar de forma personalizada este metodo para mejorar su
	 * eficiencia.
	 * 
	 * @param data el dato que se va a añadir (al final de los datos ya existentes)
	 * @throws Exception si algun error ocurre durante el proceso de
	 *                   lectura/escritura
	 */
	public void append(DataRow data) throws Exception {
		final DataRow[] dataset = read();
		final DataRow[] newDataset = Arrays.copyOf(dataset, dataset.length + 1);
		newDataset[newDataset.length - 1] = data;
		write(newDataset);
	}

	/**
	 * Lee todos los datos del fichero que se esta manejando desde la instancia de
	 * la implementacion del metodo.
	 * 
	 * @return todos los datos leidos del fichero.
	 * @throws Exception si algun error ocurre durnate la lectura.
	 */
	public abstract DataRow[] read() throws Exception;
}
