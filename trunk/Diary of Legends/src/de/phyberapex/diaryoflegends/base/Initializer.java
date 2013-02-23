package de.phyberapex.diaryoflegends.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ext.DatabaseFileLockedException;
import de.phyberapex.diaryoflegends.controller.MainController;
import de.phyberapex.diaryoflegends.exception.InitializeException;
import de.phyberapex.diaryoflegends.model.Summoner;

/**
 * Class to initialize the application on the start
 * 
 * @author Janis Walliser <walliser.janis@gmx.de>
 */
public class Initializer {

	private static Initializer instance;
	private File updateFolder;
	transient private static Logger logger = LogManager
			.getLogger(MainController.class.getName());

	/**
	 * Consturctor of this class
	 */
	private Initializer() {
	}

	/**
	 * Returns an instance of this class if the static attribute instance is
	 * null it will be created.
	 * 
	 * @return {@link Initializer} an instance of this class
	 */
	public static synchronized Initializer getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of Initializer");
			instance = new Initializer();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning: {}", instance);
		return instance;
	}

	/**
	 * Initilizes the application
	 * 
	 * @return {@link List<InitializeAction>} List of action to be taken
	 */
	public List<InitializeAction> initializeApp() throws InitializeException {
		logger.trace("initializeApp() - Entering");
		List<InitializeAction> returnValue = new ArrayList<>();
		changeLaF();
		if (update()) {
			returnValue.add(InitializeAction.UPDATE);
		}
		Config conf = Config.getInstance();
		if (conf.getProperty("SUMMONER_NAME") == null) {
			returnValue.add(InitializeAction.CREATE_SUMMONER);
		} else {
			conf.setCurrentSummoner(new Summoner(conf
					.getProperty("SUMMONER_NAME"), null));
		}
		try {
			conf.getDBHandle();
		} catch (DatabaseFileLockedException e) {
			logger.fatal("Databasefile is locked");
			throw new InitializeException(
					"Can't open database. Databasefile is locked.");
		}
		logger.trace("initializeApp() - Returning");
		logger.debug("initializeApp() - Returning {}", returnValue);
		return returnValue;
	}

	private boolean update() {
		logger.trace("update() - Entering");
		boolean returnValue = false;
		updateFolder = new File(System.getProperty("user.dir") + "\\update");
		if (updateFolder.isDirectory()) {
			logger.debug("Update folder found. Searching for update file");
			File update = new File(updateFolder.getAbsolutePath()
					+ "\\update.dolup");
			if (update != null) {
				logger.debug("Update file found.");
				returnValue = true;
			}
		}
		logger.trace("update() - Returning");
		logger.debug("update() - Returning: {}", returnValue);
		return returnValue;
	}

	private void changeLaF() {
		logger.trace("changeLaF() - Entering");
		try {
			logger.debug("Iterating through LaFs");
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				logger.debug("Current LaF: {}", info.getName());
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					logger.debug("LaF {} set", info.getName());
					break;
				}
			}
		} catch (Exception e) {
			logger.info("Unable to find look and feel nimbus. Using default.");
		} finally {
			logger.trace("changeLaF() - Leaving");
		}
	}

}