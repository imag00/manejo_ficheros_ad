package dev.amrv.xls;

import java.util.ArrayList;
import java.util.List;

public class DataRow {

	private final String[] data;

	public DataRow(String... args) {
		this.data = args;
	}

	public String[] getData() {
		return data;
	}

	public static class DataRowBuilder {

		private final List<String> compose = new ArrayList<>();

		public DataRowBuilder append(String str) {
			compose.add(str);
			return this;
		}

		public DataRow build() {
			return new DataRow(compose.toArray(new String[0]));
		}

	}

}
