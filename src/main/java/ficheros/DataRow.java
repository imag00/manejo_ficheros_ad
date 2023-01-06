package ficheros;

import java.util.ArrayList;

public class DataRow {

	private final String[] content;

	public DataRow(String... content) {
		this.content = content;
	}

	public String[] getData() {
		return content;
	}

	public static class DataRowBuilder {

		private final ArrayList<String> data = new ArrayList<>();

		public DataRowBuilder append(String data) {
			this.data.add(data);
			return this;
		}

		public DataRow build() {
			return new DataRow(data.toArray(new String[0]));
		}

	}

}
