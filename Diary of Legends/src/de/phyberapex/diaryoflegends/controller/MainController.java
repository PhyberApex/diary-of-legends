package de.phyberapex.diaryoflegends.controller;

import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.ExitAction;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.base.InitializeAction;
import de.phyberapex.diaryoflegends.base.Initializer;
import de.phyberapex.diaryoflegends.exception.InitializeException;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.extra.Splash;
import de.phyberapex.diaryoflegends.view.ChampionView;
import de.phyberapex.diaryoflegends.view.CurrentSummonerNameView;
import de.phyberapex.diaryoflegends.view.ItemView;
import de.phyberapex.diaryoflegends.view.MainView;
import de.phyberapex.diaryoflegends.view.MatchupView;

public class MainController {

	private Splash splash;
	private MainView mainView;
	transient private Logger logger = LogManager.getLogger(MainController.class
			.getName());

	public MainController() {
		logger.trace("MainController() - Entering");
		try {
			splash = new Splash(ImageIconFactory
					.createImageIconFromResourePath("img/splash.png")
					.getImage(), "Checking resources");
			splash.setVisible(true);
			splash.showStatus("Initializing application", 10);
			InitializeAction initAction = Initializer.getInstance()
					.initializeApp();
			logger.debug("Value of initAction {}", initAction);
			splash.showStatus("Preparing controller", 20);
			ChampionController champController = new ChampionController(this);
			ItemController itemController = new ItemController(this);
			MatchupController matchupController = new MatchupController(this);
			splash.showStatus("Creating user interface", 30);
			mainView = MainView.getInstance();
			mainView.setMainController(this);
			champController.loadGUI();
			itemController.loadGUI();
			matchupController.loadGUI();
			splash.showStatus("Loading data", 40);
			champController.loadData();
			itemController.loadData();
			matchupController.loadData();
			mainView.setChampPanel((ChampionView) champController.getView());
			mainView.setItemPanel((ItemView) itemController.getView());
			mainView.setMatchupPanel((MatchupView) matchupController.getView());
			splash.showStatus("Preparing to start", 100);
			splash.close();
			CurrentSummonerNameView currSumView = CurrentSummonerNameView
					.getInstance();
			if (initAction == InitializeAction.CREATE_SUMMONER) {
				Object[] options = { "OK" };
				int ok = JOptionPane.showOptionDialog(null, currSumView,
						"Enter your Summoner name",
						JOptionPane.WARNING_MESSAGE,
						JOptionPane.INFORMATION_MESSAGE,
						ImageIconFactory.createImageIcon("C:\\1.png"), options,
						"OK");
				logger.debug("Entered text was '{}'",
						currSumView.getSummonerName());
				logger.debug("Button pressed was '{}' ({} = OK, {} = CLOSED)",
						ok, JOptionPane.OK_OPTION, JOptionPane.CLOSED_OPTION);
				if (ok == JOptionPane.CLOSED_OPTION) {
					JOptionPane
							.showMessageDialog(
									null,
									"Appliaction can not operate without a summoner name.",
									"Error", JOptionPane.ERROR_MESSAGE);
					logger.info("Appliaction can not operate a without summoner name.");
					exit();
				}
				Config.getInstance().setProperty("SUMMONER_NAME",
						currSumView.getSummonerName());
				Config.getInstance().saveChanges();
			} else {
				currSumView.setSummonerName(Config.getInstance().getProperty(
						"SUMMONER_NAME"));
			}
			SwingUtilities.invokeLater(mainView);

		} catch (InitializeException e) {
			splash.close();
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Exiting application", JOptionPane.ERROR_MESSAGE, null);
			ExitAction.getInstance().actionPerformed(null);
		} finally {
			logger.trace("MainController() - Leaving");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties prop = new Properties(System.getProperties());
		prop.put("log4j.configurationFile", "logconfig.xml");
		System.setProperties(prop);
		Logger logger = LogManager.getLogger(MainController.class.getName());
		logger.trace("main() - Entering");
		new MainController();
		logger.trace("main() - Leaving");
	}

	public void setStatus(String text) {
		logger.trace("setStatus() - Entering");
		logger.debug("setStatus() - Parameter: {}", text);
		mainView.setStatusText(text);
		logger.trace("setStatus() - Leaving");
	}

	/**
	 * Exits the appliaction
	 */
	public static void exit() {
		ExitAction.getInstance().actionPerformed(null);
	}

}