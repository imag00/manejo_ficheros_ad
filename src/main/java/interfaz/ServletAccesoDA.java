package interfaz;

import java.io.IOException;

import dev.amrv.xls.ExcelHandler;
import ficheros.Objeto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletAccesoDA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ExcelHandler excelHandler;

	public ServletAccesoDA() {
		super();
		excelHandler = new ExcelHandler("C:\\Users\\Usuario\\Downloads\\educacion-infantil.-numero-de-plazas-y-alumnos-desde-2012.xls");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String fichero = request.getParameter("fichero");

		if (accion.equals("escritura")) {
			Objeto objeto;
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

	private void escritura(Objeto objeto, String fichero) throws Exception {

		switch (fichero) {
		case "xls":
			excelHandler.append(objeto);
			// [Nombre de la clase de XLS].write(objeto);
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

	private Objeto createObjeto(HttpServletRequest request) throws Exception {
		String dato1 = request.getParameter("dato1");
		String dato2 = request.getParameter("dato2");
		String dato3 = request.getParameter("dato3");
		String dato4 = request.getParameter("dato4");
		String dato5 = request.getParameter("dato5");
		String dato6 = request.getParameter("dato6");

		if (dato1.isBlank() || dato2.isBlank() || dato3.isBlank() || dato4.isBlank() || dato5.isBlank()
				|| dato6.isBlank())
			throw new Exception("Campos vacios");

		return new Objeto(dato1, dato2, dato3, dato4, dato5, dato6);
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
