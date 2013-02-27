package de.phyberapex.diaryoflegends.controller;

import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.ExitAction;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.base.InitializeAction;
import de.phyberapex.diaryoflegends.base.Initializer;
import de.phyberapex.diaryoflegends.base.Update;
import de.phyberapex.diaryoflegends.exception.InitializeException;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.extra.Splash;
import de.phyberapex.diaryoflegends.view.CurrentSummonerNameView;
import de.phyberapex.diaryoflegends.view.MainView;
import de.phyberapex.diaryoflegends.view.MatchupView;
import de.phyberapex.diaryoflegends.view.StatsView;
import de.phyberapex.diaryoflegends.view.dialoge.MatchupDetailDialoge;
import de.phyberapex.diaryoflegends.view.dialoge.NewEntryDialoge;

public class MainController {

	private Splash splash;
	private MainView mainView;
	private MatchupController matchupController;
	private StatsController statsController;

	private static MainController instance;
	transient private Logger logger = LogManager.getLogger(MainController.class
			.getName());

	public MainController() {
		logger.trace("MainController() - Entering");
		try {
			splash = new Splash(ImageIconFactory.getRandomLoadingIcon()
					.getImage(), "Checking resources");
			splash.setVisible(true);
			splash.showStatus("Initializing application", 10);
			List<InitializeAction> initAction = Initializer.getInstance()
					.initializeApp();
			logger.debug("Value of initAction {}", initAction);
			splash.showStatus("Updating", 20);
			if (initAction.contains(InitializeAction.UPDATE)) {
				Update.update();
			}
			splash.showStatus("Preparing controller", 25);
			matchupController = new MatchupController(this);
			statsController = new StatsController(this);
			splash.showStatus("Creating user interface", 30);
			mainView = MainView.getInstance();
			mainView.setMainController(this);
			matchupController.loadGUI();
			statsController.loadGUI();
			NewEntryDialoge.getInstance();
			MatchupDetailDialoge.getInstance();
			splash.showStatus("Loading data", 40);
			matchupController.loadData();
			statsController.loadData();
			mainView.setMatchupPanel((MatchupView) matchupController.getView());
			mainView.setStatsPanel((StatsView)statsController.getView());
			splash.showStatus("Preparing to start", 100);
			splash.close();
			if (initAction.contains(InitializeAction.CREATE_SUMMONER)) {
				CurrentSummonerNameView currSumView = CurrentSummonerNameView
						.getInstance();
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
		MainController.getInstance();
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

	public static synchronized MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	public void updated() {
		((MatchupView) matchupController.getView()).refresh();
	}
}