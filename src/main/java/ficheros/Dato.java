package ficheros;

public class Dato {
	private final String anio;
	private final String plazas;
	private final String alumnosCeroUno;
	private final String alumnosUnoDos;
	private final String alumnosDosTres;
	private final String alumnosTotal;
	
	public Dato(String anio, String plazas, String alumnosCeroUno, String alumnosUnoDos,
				String alumnosDosTres, String alumnosTotal) {
		super();
		this.anio = anio;
		this.plazas = plazas;
		this.alumnosCeroUno = alumnosCeroUno;
		this.alumnosUnoDos = alumnosUnoDos;
		this.alumnosDosTres = alumnosDosTres;
		this.alumnosTotal = alumnosTotal;
	}

	public String getAnio() {
		return anio;
	}

	public String getPlazas() {
		return plazas;
	}

	public String getAlumnosCeroUno() {
		return alumnosCeroUno;
	}

	public String getAlumnosUnoDos() {
		return alumnosUnoDos;
	}

	public String getAlumnosDosTres() {
		return alumnosDosTres;
	}

	public String getAlumnosTotal() {
		return alumnosTotal;
	}
	
	
}
