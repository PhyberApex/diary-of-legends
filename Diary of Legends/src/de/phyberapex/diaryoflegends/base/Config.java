package de.phyberapex.diaryoflegends.base;

/**
 * Class for handling with the configuration file
 * @author Janis Walliser <walliser.janis@gmx.de>
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;

import de.phyberapex.diaryoflegends.model.Region;
import de.phyberapex.diaryoflegends.model.Summoner;

public class Config {

	private static Config instance;
	private Properties prop;
	private ObjectContainer dbHandle;
	private Summoner currentSummoner = new Summoner("");
	private ObjectServer server;
	private static boolean internetAvailable;
	private static long lastCheck = 0;
	transient private static Logger logger = LogManager.getLogger(Config.class
			.getName());

	private Config() {
		logger.trace("Config() - Entering");
		FileReader reader = null;
		this.prop = new Properties();
		// Read file if exist, if not create it
		try {
			reader = new FileReader("diary.cfg");
			logger.debug("File diary: {}", reader);
		} catch (FileNotFoundException e) {
			logger.info("Configuration file not found. Creating a new one.");
			File prop = new File("diary.cfg");
			try {
				// Create file and set default values
				prop.createNewFile();
				reader = new FileReader("diary.cfg");
				logger.info("Setting databasename to {}",
						Constants.getAppName() + ".db");
				new File("db").mkdir();
				this.setProperty("DATABASENAME",
						"db\\" + Constants.getAppName() + ".db");
				this.setProperty("AUTO_UPDATE", "0");
				saveChanges();
			} catch (IOException e1) {
				logger.error(e1.getMessage());
			}
		}
		try {
			logger.info("Configuration file found loading it");
			this.prop.load(reader);
			if (this.getProperty("DATABASENAME") == null) {
				this.setProperty("DATABASENAME",
						"db\\" + Constants.getAppName() + ".db");
				saveChanges();
			}
			if (this.getProperty("AUTO_UPDATE") == null) {
				this.setProperty("AUTO_UPDATE", "0");
				saveChanges();
			}
			if (getProperty("SUMMONER_NAME") != null) {
				this.currentSummoner.setName(getProperty("SUMMONER_NAME"));
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		logger.trace("Config() - Leaving");
	}

	/**
	 * Sets a property
	 * 
	 * @param key
	 *            {@link String} unique key for the property
	 * @param value
	 *            {@link String} value of the poperty
	 */
	public void setProperty(String key, String value) {
		logger.trace("setProperty() - Entering");
		logger.debug("setProperty() - Parameter: key = {}, value = {}", key,
				value);
		if (key == "SUMMONER_NAME") {
			this.currentSummoner.setName(value);
		}
		prop.setProperty(key, value);
		logger.trace("setProperty() - Leaving");
	}

	/**
	 * Returns the value of the property with the given key
	 * 
	 * @param key
	 *            Unique key to get the value of
	 * @return {@link String} value of the given key
	 */
	public String getProperty(String key) {
		logger.trace("getProperty() - Entering");
		logger.trace("getProperty() - Returning");
		logger.debug("getProperty() - Returning: {}",
				this.prop.getProperty(key));
		return this.prop.getProperty(key);
	}

	/**
	 * Saves the changes made to the configuration to the file
	 */
	public void saveChanges() {
		logger.trace("saveChanges() - Entering");
		FileWriter writer = null;
		try {
			writer = new FileWriter("diary.cfg");
			logger.debug("writing file {}", writer);
			this.prop.store(writer, null);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		logger.trace("saveChanges() - Leaving");
	}

	/**
	 * Returns an instance of this class if the static attribute instance is<br>
	 * null it will be created.
	 * 
	 * @return {@link Config} an instance of this class
	 */
	public static synchronized Config getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("getInstance() - Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of Config");
			instance = new Config();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning: {}", instance);
		return instance;
	}

	/**
	 * Returns a handle to make queries to the database
	 * 
	 * @return {@link ObjectContainer} handle for the database
	 */
	public synchronized ObjectContainer getDBHandle() {
		logger.trace("getDBHandle() - Entering");
		if (server == null) {
			server = Db4oClientServer.openServer(
					prop.getProperty("DATABASENAME"), 0);
		}
		if (dbHandle == null) {
			logger.debug("Creating a new instance of ObjectContainer");
			dbHandle = server.openClient();
		}
		logger.trace("getDBHandle() - Returning");
		logger.debug("getDBHandle() - Returning: {}", dbHandle);
		return dbHandle;
	}

	public static boolean isInternetReachable() {
		logger.trace("isInternetReachable() - Entering");
		boolean returnValue = true;
		try {
			if (lastCheck != 0
					&& System.currentTimeMillis() - lastCheck < 100000) {
				returnValue = internetAvailable;
			} else {
				// make a URL to a known source
				URL url = new URL("http://api.elophant.com/v2/");
				// open a connection to that source
				HttpURLConnection urlConnect = (HttpURLConnection) url
						.openConnection();
				// trying to retrieve data from the source. If there
				// is no connection, this line will fail
				int timeout = 300;
				urlConnect.setConnectTimeout(timeout);
				urlConnect.setReadTimeout(timeout);
				urlConnect.getContent();
				lastCheck = System.currentTimeMillis();
			}
		} catch (IOException e) {
			lastCheck = System.currentTimeMillis();
			logger.debug("No internet connection");
			returnValue = false;
		}
		internetAvailable = returnValue;
		logger.trace("isInternetReachable() - Returning");
		logger.debug("isInternetReachable() - Returning: {}", returnValue);
		return returnValue;
	}

	public Summoner getCurrentSummoner() {
		logger.trace("getCurrentSummoner() - Entering");
		logger.trace("getCurrentSummoner() - Returning");
		logger.debug("getCurrentSummoner() - Returning: {}", currentSummoner);
		return currentSummoner;
	}

	public void setCurrentSummoner(Summoner currentSummoner) {
		logger.trace("setCurrentSummoner() - Entering");
		logger.debug("setCurrentSummoner() - Parameter: {}", currentSummoner);
		this.currentSummoner = currentSummoner;
		logger.trace("setCurrentSummoner() - Leaving");
	}

	public void closeDatabase() {
		getDBHandle().close();
		server.close();
	}

	/**
	 * @param summonerName
	 * @param region
	 * @return
	 */
	public int[] getSummonerIdByNameAndRegion(String summonerName, Region region) {
		logger.trace("getSummonerIdByNameAndRegion() - Entering");
		logger.debug("getSummonerIdByNameAndRegion() - Parameter: {}, {}",
				summonerName, region);
		int[] returnValue = { 0, 0 };
		if (isInternetReachable()) {
			try {
				URLConnection con = new URL("http://api.elophant.com/v2/"
						+ region.name() + "/summoner/" + summonerName + "?key="
						+ Config.getInstance().getProperty("API_KEY"))
						.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				String json = "";
				while ((inputLine = in.readLine()) != null)
					json += inputLine;
				in.close();
				JSONObject data = new JSONObject(json);
				if (data.getBoolean("success")) {
					returnValue[0] = data.getJSONObject("data").getInt(
							"summonerId");
					returnValue[1] = data.getJSONObject("data")
							.getInt("acctId");
				}
			} catch (IOException e) {
				logger.debug("Error: {}" + e.getMessage());
				logger.error("Couldn't connect to api.elophant.com summonerID and accountID not set");
			}
		}
		logger.trace("getSummonerIdByNameAndRegion() - Returning");
		logger.debug("getSummonerIdByNameAndRegion() - Returning: {}",
				returnValue);
		return returnValue;
	}
}