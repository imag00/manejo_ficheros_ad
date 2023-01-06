package ficheros;

public class DataRow {

	private final String[] content;

	public DataRow(String... content) {
		this.content = content;
	}

	public String[] getData() {
		return content;
	}

}
