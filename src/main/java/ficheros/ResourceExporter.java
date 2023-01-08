package ficheros;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.filechooser.FileSystemView;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ResourceExporter
 *
 */
public class ResourceExporter implements ServletContextListener {

	public static String ROOT_LOCATION = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath()
			+ File.separator + "AccesoDatosDAM" + File.separator;

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Resources stored at >> " + ROOT_LOCATION);

		try {
			createPhysicalResource(ROOT_LOCATION + "DatosAbiertosCSV.csv", "DatosAbiertosCSV.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			createPhysicalResource(ROOT_LOCATION + "DatosAbiertosJSON.json", "DatosAbiertosJSON.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			createPhysicalResource(ROOT_LOCATION + "DatosAbiertosXLS.xls", "DatosAbiertosXLS.xls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			createPhysicalResource(ROOT_LOCATION + "DatosAbiertosXML.xml", "DatosAbiertosXML.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	private void createPhysicalResource(String destinationPath, String resourceName) throws IOException {
		final InputStream stream = ResourceExporter.class.getClassLoader().getResourceAsStream("DatosAbiertosCSV.csv");

		if (stream == null)
			throw new RuntimeException(
					"Can't access the resources required to start the server (" + resourceName + ")");

		final File file = new File(destinationPath);

		if (!file.exists()) {
			final File parent = file.getParentFile();

			if (parent != null)
				parent.mkdirs();

			file.createNewFile();

		} else if (!file.canWrite()) {
			file.setWritable(true);
		}

		FileOutputStream output = new FileOutputStream(file);

		int nextByte = stream.read();
		do {
			output.write(nextByte);
			nextByte = stream.read();

		} while (nextByte > -1);

		stream.close();
		output.close();
	}

}
