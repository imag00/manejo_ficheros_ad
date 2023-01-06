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
				boolean dirs = file.getParentFile().mkdirs();

				if (!dirs)
					throw new IOException("Cannot create path " + file.getParentFile().getAbsolutePath());

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

	public abstract String getJSPResultName();

	public abstract void write(DataRow[] data) throws Exception;

	public void append(DataRow data) throws Exception {
		final DataRow[] dataset = read();
		final DataRow[] newDataset = Arrays.copyOf(dataset, dataset.length + 1);
		newDataset[newDataset.length - 1] = data;
		write(newDataset);
	}

	public abstract DataRow[] read() throws Exception;
}
