package ficheros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReaderWriter {

	public static ArrayList<Objeto> read() throws Exception {

		ArrayList<Objeto> lineas = new ArrayList<>();
		lineas.add(new Objeto("Año", "Número de plazas", "Número de alumnos 0-1 años", "Número de alumnos 1-2 años",
				"Número de alumnos 2-3 años", "Número total de alumnos"));

		try {

			File archivoXML = new File("D:\\Users\\Sabri\\Documents\\Sabrii\\DAM2\\AD\\EclipseProyects\\ManejoFicheros\\src\\main\\java\\archivos\\DatosAbiertosXML.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document documentoXML = builder.parse(archivoXML);
			documentoXML.getDocumentElement().normalize();

			NodeList rows = documentoXML.getElementsByTagName("row");

			for (int i = 0; i < rows.getLength(); i++) {

				Node row = rows.item(i);
				NamedNodeMap attributes = row.getAttributes();

				String dato1 = attributes.item(0).getNodeValue();
				String dato2 = attributes.item(1).getNodeValue();
				String dato3 = attributes.item(2).getNodeValue();
				String dato4 = attributes.item(3).getNodeValue();
				String dato5 = attributes.item(4).getNodeValue();
				String dato6 = attributes.item(5).getNodeValue();

				lineas.add(new Objeto(dato1, dato2, dato3, dato4, dato5, dato6));
			}

		} catch (SAXException e) {
			throw new Exception(e.getMessage());
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new Exception(e.getMessage());
		}

		return lineas;
	}

	public static void write(Objeto objeto) throws Exception {

		try {

			File archivoXML = new File("D:\\Users\\Sabri\\Documents\\Sabrii\\DAM2\\AD\\EclipseProyects\\ManejoFicheros\\src\\main\\java\\archivos\\DatosAbiertosXML.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document documentoXML = builder.parse(archivoXML);
			documentoXML.getDocumentElement().normalize();

			Element row = documentoXML.createElement("row");

			row.setAttribute("Año", objeto.getDato1());
			row.setAttribute("Número_de_plazas", objeto.getDato2());
			row.setAttribute("Número_de_alumnos_0-1_años", objeto.getDato3());
			row.setAttribute("Número_de_alumnos_1-2_años", objeto.getDato4());
			row.setAttribute("Número_de_alumnos_2-3_años", objeto.getDato5());
			row.setAttribute("Número_total_de_alumnos", objeto.getDato6());

			documentoXML.getDocumentElement().appendChild(row);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(documentoXML);
			StreamResult result = new StreamResult(archivoXML);
			transformer.transform(source, result);

		} catch (SAXException e) {
			throw new Exception(e.getMessage());
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new Exception(e.getMessage());
		}

	}

}
