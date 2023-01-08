package interfaz;

import java.io.IOException;

import ficheros.CSVReaderWriter;
import ficheros.Dato;
import ficheros.JSONReaderWriter;
import ficheros.ResourceExporter;
import ficheros.XMLReaderWriter;
import ficheros.xls.ExcelHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletAccesoDA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletAccesoDA() {
		super();
		excelHandler = new ExcelHandler(ResourceExporter.ROOT_LOCATION + "DatosAbiertosXLS.xls");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("AccesoDA.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String fichero = request.getParameter("fichero");

		if (accion.equals("escritura")) {
			Dato objeto;
			try {
				objeto = createObjeto(request);
			} catch (Exception e) {
				camposVacios(request, response);
				return;
			}

			try {
				escritura(objeto, fichero);
			} catch (Exception e) {
				throwError(request, response, e.getMessage());
				return;
			}
		}

		lectura(request, response, fichero);
	}

	private ExcelHandler excelHandler;

	private void escritura(Dato objeto, String fichero) throws Exception {

		switch (fichero) {
		case "xls":
			excelHandler.append(objeto);
			break;
		case "csv":
			CSVReaderWriter.write(objeto);
			break;
		case "json":
			JSONReaderWriter.write(objeto);
			break;
		case "xml":
			XMLReaderWriter.write(objeto);
			break;
		default:
			throw new Exception("Tipo de fichero no válido");
		}
	}

	private void lectura(HttpServletRequest request, HttpServletResponse response, String fichero) {
		String path = null;

		switch (fichero) {
		case "xls":
			request.setAttribute("handler", excelHandler);
			path = excelHandler.getJSPResultName();
			break;
		case "csv":
			path = "DatosAbiertosCSV";
			break;
		case "json":
			path = "DatosAbiertosJSON";
			break;
		case "xml":
			path = "DatosAbiertosXML";
			break;
		default:
			throwError(request, response, "Tipo de fichero no válido");
			return;
		}

		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private Dato createObjeto(HttpServletRequest request) throws Exception {
		String dato1 = request.getParameter("dato1");
		String dato2 = request.getParameter("dato2");
		String dato3 = request.getParameter("dato3");
		String dato4 = request.getParameter("dato4");
		String dato5 = request.getParameter("dato5");
		String dato6 = request.getParameter("dato6");

		if (dato1.isBlank() || dato2.isBlank() || dato3.isBlank() || dato4.isBlank() || dato5.isBlank()
				|| dato6.isBlank())
			throw new Exception("Campos vacios");

		return new Dato(dato1, dato2, dato3, dato4, dato5, dato6);
	}

	public static void throwError(HttpServletRequest request, HttpServletResponse response, String error) {
		request.setAttribute("error", error);

		try {
			request.getRequestDispatcher("ErrorDA").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void camposVacios(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("camposVacios", true);

		try {
			request.getRequestDispatcher("AccesoDA").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
