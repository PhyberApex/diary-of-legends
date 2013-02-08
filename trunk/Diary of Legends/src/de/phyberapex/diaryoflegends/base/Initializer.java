package de.phyberapex.diaryoflegends.base;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.controller.MainController;
import de.phyberapex.diaryoflegends.exception.InitializeException;
import de.phyberapex.diaryoflegends.model.Champion;

/**
 * Class to initialize the application on the start
 * @author Janis Walliser <walliser.janis@gmx.de>
 */
public class Initializer {

	private static Initializer instance;
	private static Logger logger = LogManager.getLogger(MainController.class
			.getName());

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
	 * @return {@link boolean} True if initilizing was successfull false if not
	 */
	public InitializeAction initializeApp() throws InitializeException{
		logger.trace("initializeApp() - Entering");
		InitializeAction returnValue = InitializeAction.NONE;
		changeLaF();
		Config conf = Config.getInstance();
		if(conf.getProperty("SUMMONER_NAME") == null){
			returnValue = InitializeAction.CREATE_SUMMONER;
		}
		logger.trace("initializeApp() - Returning");
		logger.debug("initializeApp() - Returning {}", returnValue);
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