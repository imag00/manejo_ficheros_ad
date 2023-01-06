package interfaz;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.amrv.xls.ExcelOperator;
import ficheros.DataRow;
import ficheros.IOFichero;

public class ServletAccesoDA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, IOFichero> fileOperators = new HashMap<>();

	public ServletAccesoDA() {
		super();
	}

	/**
	 * Aqui se introducen las clases (que extienden de IOFichero) que leen/escriben
	 * los datos del programa.
	 * 
	 */
	public void loadFileHandlers() {
		fileOperators.put("xls", new ExcelOperator("DatosAbiertos.xls"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String fichero = request.getParameter("fichero") != null ? request.getParameter("fichero").trim().toLowerCase()
				: "";

		if (accion.equals("escritura")) {
			DataRow objeto;
			try {
				objeto = createObjeto(request);
			} catch (Exception e) {
				camposVacios(request, response);
				return;
			}

			try {
				if (fileOperators.containsKey(fichero))
					fileOperators.get(fichero).append(objeto);
				else
					throw new Exception("No se maneja el tipo de fichero");

			} catch (Exception e) {
				throwError(request, response, e.getMessage());
				return;
			}
		}

		lectura(request, response, fichero);
	}

	private void escritura(DataRow objeto, String fichero) throws Exception {

		switch (fichero) {
		case "xls":
			// new EscrituraXLS("escrituraExcel.xls").write(objeto);
			break;
		case "csv":
			// [Nombre de la clase de XLS].write(objeto);
			break;
		case "json":
			// [Nombre de la clase de XLS].write(objeto);
			break;
		case "xml":
			// [Nombre de la clase de XLS].write(objeto);
			break;
		default:
			throw new Exception("Tipo de fichero no válido");
		}
	}

	private void lectura(HttpServletRequest request, HttpServletResponse response, String fichero) {
		if (fileOperators.containsKey(fichero))
			throwError(request, response, "Tipo de fichero no válido");

		IOFichero handler = fileOperators.get(fichero);
		request.setAttribute("handler", handler);

		try {
			request.getRequestDispatcher(handler.getJSPResultName()).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	private DataRow createObjeto(HttpServletRequest request) throws Exception {
		String dato1 = request.getParameter("dato1");
		String dato2 = request.getParameter("dato2");
		String dato3 = request.getParameter("dato3");
		String dato4 = request.getParameter("dato4");
		String dato5 = request.getParameter("dato5");
		String dato6 = request.getParameter("dato6");

		if (dato1.isBlank() || dato2.isBlank() || dato3.isBlank() || dato4.isBlank() || dato5.isBlank()
				|| dato6.isBlank())
			throw new Exception("Campos vacios");

		return new DataRow(dato1, dato2, dato3, dato4, dato5, dato6);
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
