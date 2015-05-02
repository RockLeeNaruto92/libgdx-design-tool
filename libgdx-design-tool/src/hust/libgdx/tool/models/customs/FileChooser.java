package hust.libgdx.tool.models.customs;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileChooser {
	private static FileChooser _instance;

	private JFileChooser fileChooser;
	private String resultPath;

	public FileChooser() {
		fileChooser = new JFileChooser();
	}

	public static FileChooser getInstance() {
		if (_instance == null)
			_instance = new FileChooser();

		return _instance;
	}

	public void show(boolean show, final String filter[]) {
		fileChooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				String desc = "";

				for (int i = 0; i < filter.length - 1; i++)
					desc = desc.concat("*." + filter[i]) + ",";
				desc = desc.concat("*." + filter[filter.length - 1]);

				return desc;
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;

				for (String string : filter)
					if (f.getName().endsWith("." + string))
						return true;
				return false;
			}
		});

		int resultVal = fileChooser.showOpenDialog(null);

		if (resultVal == JFileChooser.APPROVE_OPTION)
			resultPath = fileChooser.getSelectedFile().getPath();
	}

	public String getResultPath() {
		return resultPath;
	}
}