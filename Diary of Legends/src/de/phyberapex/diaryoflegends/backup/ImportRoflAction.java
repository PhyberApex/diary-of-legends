package de.phyberapex.diaryoflegends.backup;

import java.io.File;
import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import de.phyberapex.diaryoflegends.view.MainView;

public class ImportRoflAction implements Runnable {

	private File file;
	private static Logger logger = LogManager.getLogger(ExportDolexAction.class
			.getName());

	public ImportRoflAction(File file) {
		this.file = file;
	}

	private void doImport(File importFile) {
		logger.trace("doImport() - Entering");
		logger.debug("doImport() - Parameter: {}", importFile);
		MainView.getInstance().setStatusText("Importing...");
		JSONObject jo = new JSONObject(getJSONStringFromRoflFile(importFile));
		try {
		} catch (Exception e) {
			logger.error(e.getMessage());
			MainView.getInstance().setStatusText(
					"Import failed. Please read the logfile.");
		}
		MainView.getInstance().setStatusText("Import complete");
		logger.trace("doImport() - Leaving");
	}

	private String getJSONStringFromRoflFile(File importFile) {
		logger.trace("getJSONStringFromRoflFile() - Entering");
		logger.debug("getJSONStringFromRoflFile() - Parameter: {}", importFile);
		String returnValue = "";
		try {
			FileInputStream in = new FileInputStream(importFile);
			int length = (int) importFile.length();
			byte[] b = new byte[length];
			int c;
			int x = 0;
			while ((c = in.read()) != -1 && x < length) {
				b[x] = (byte) c;
				x++;
			}
			String bla = new String(b);
			int start = bla.indexOf("{");
			int end = bla.indexOf("©", start);
			returnValue = bla.substring(start, end);
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		logger.trace("getJSONStringFromRoflFile() - Returning");
		logger.debug("getJSONStringFromRoflFile() - Returning: {}", returnValue);
		return returnValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (file != null) {
			doImport(file);
		}
	}
}
