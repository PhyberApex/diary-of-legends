package de.phyberapex.diaryoflegends.base;

import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ext.DatabaseFileLockedException;
import com.jgoodies.looks.windows.WindowsLookAndFeel;

import de.phyberapex.diaryoflegends.controller.MainController;
import de.phyberapex.diaryoflegends.exception.InitializeException;
import de.phyberapex.diaryoflegends.view.panel.SummonerNamePanel;

/**
 * Class to initialize the application on the start
 * 
 * @author Janis Walliser <walliser.janis@gmx.de>
 */
public class Initializer {

	private static Initializer instance;
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
		if (!idsSet()) {
			returnValue.add(InitializeAction.FETCH_IDS);
		}
		Config conf = Config.getInstance();
		if (conf.getProperty("SUMMONER_NAME") == null
				|| conf.getProperty("REGION") == null) {
			returnValue.add(InitializeAction.CREATE_SUMMONER);
			if (conf.getProperty("SUMMONER_NAME") != null) {
				SummonerNamePanel.getInstance().setSummonerName(
						conf.getProperty("SUMMONER_NAME"));
			}
		}
		if (conf.getProperty("API_KEY") == null) {
			returnValue.add(InitializeAction.API_KEY);
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

	/**
	 * @return
	 */
	private boolean idsSet() {
		logger.trace("idsSet() - Entering");
		boolean returnValue = true;
		if (Config.getInstance().getProperty("ACCOUNT_ID") == null
				|| Config.getInstance().getProperty("SUMMONER_ID") == null) {
			returnValue = false;
		}
		logger.trace("idsSet() - Returning");
		logger.debug("idsSet() - Returning: {}", returnValue);
		return returnValue;
	}

	private boolean update() {
		logger.trace("update() - Entering");
		boolean returnValue = !Config.getInstance().getProperty("AUTO_UPDATE")
				.equals("0");
		logger.trace("update() - Returning");
		logger.debug("update() - Returning: {}", returnValue);
		return returnValue;
	}

	private void changeLaF() {
		logger.trace("changeLaF() - Entering");
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
			// UIManager.setLookAndFeel(
			// UIManager.getSystemLookAndFeelClassName());
			logger.debug("Iterating through LaFs");
		} catch (Exception e) {
			logger.info("Unable to find look and feel nimbus. Using default.");
		} finally {
			logger.trace("changeLaF() - Leaving");
		}
	}

}