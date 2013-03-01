package de.phyberapex.diaryoflegends.base;

/**
 * Class to hold the basic information for this application
 * @author Janis Walliser <walliser.janis@gmx.de>
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

	private static final String APP_NAME = "Diary of Legends powered by Elophant.com";
	private static final String APP_VERSION = "0.1";
	private static final String APP_AUTHOR = "PhyberApex";
	private static final String RESOURCE_PATH = "/resource/";
	transient private static Logger logger = LogManager
			.getLogger(Constants.class.getName());

	/**
	 * Returns the name of the application
	 * 
	 * @return {@link String} name of the application
	 */
	public static String getAppName() {
		logger.trace("getAppName() - Entering");
		logger.trace("getAppName() - Returning");
		logger.debug("getAppName() - Returning: {}", APP_NAME);
		return APP_NAME;
	}

	/**
	 * Returns the version of the application
	 * 
	 * @return {@link String} version of the application
	 */
	public static String getAppVersion() {
		logger.trace("getAppVersion() - Entering");
		logger.trace("getAppVersion() - Returning");
		logger.debug("getAppVersion() - Returning {}", APP_VERSION);
		return APP_VERSION;
	}

	/**
	 * Returns the author of the application
	 * 
	 * @return {@link String} author of the application
	 */
	public static String getAppAuthor() {
		logger.trace("getAppAuthor() - Entering");
		logger.trace("getAppAuthor() - Returning");
		logger.debug("getAppAuthor() - Returning: {}", APP_AUTHOR);
		return APP_AUTHOR;
	}

	/**
	 * Returns the path for using resource files within the jar
	 * 
	 * @return {@link String} path to the resource package
	 */
	public static String getResourcePath() {
		logger.trace("getResourcePath() - Entering");
		logger.trace("getResourcePath() - Returning");
		logger.debug("getResourcePath() - Returning {}", RESOURCE_PATH);
		return RESOURCE_PATH;
	}

	/**
	 * Returns the name of the application
	 * 
	 * @return {@link String} the current date
	 */
	public static String getDate() {
		logger.trace("getDate() - Entering");
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		logger.debug("Year: {}", year);
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		logger.debug("Month: {}", month);
		if (month.length() < 2) {
			month = "0" + month;
			logger.debug("New month: {}", month);
		}
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		logger.debug("Day: {}", year);
		if (day.length() < 2) {
			day = "0" + day;
			logger.debug("New day: {}", day);
		}
		String date = day + "." + month + "." + year;
		logger.trace("getDate() - Returning");
		logger.debug("getDate() - Returning: {}", date);
		return date;
	}

	/**
	 * Returns the content of a textfile within the jar
	 * 
	 * @param path
	 *            {@link String} path to file
	 * @return {@link String} content of the file
	 */
	public static String readInternTextFileToString(String path) {
		logger.trace("readInternFileToString() - Entering");
		logger.debug("readInternFileToString() - Parameter: {}", path);
		String toReturn = "";
		try {
			File f = new File(Constants.class.getResource(path).toURI());
			logger.debug("File: {}", f);
			FileReader fileReader = new FileReader(f);
			logger.debug("FileReader: {}", fileReader);
			BufferedReader in = new BufferedReader(fileReader);
			logger.debug("BufferedReader: {}", in);
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				toReturn += zeile + "\n";
				logger.debug("New returning string: {}", toReturn);
			}
			in.close();
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage());
		}
		logger.trace("readInternFileToString() - Returning");
		logger.debug("Returned {}", toReturn);
		return toReturn;
	}
}