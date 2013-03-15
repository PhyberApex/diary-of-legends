package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.base.Constants;
import de.phyberapex.diaryoflegends.controller.MainController;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;

public class MainView extends JFrame implements View, Runnable {

	private static final long serialVersionUID = -3382358595179093567L;

	private static MainView instance;
	private MenuBarView menu;
	private JLabel statusLabel;
	private JLabel summonerInfoLabel;
	private JTabbedPane contentPane;
	private static Logger logger = LogManager.getLogger(MainView.class
			.getName());

	private MainView() {
		super();
		logger.trace("MainView() - Entering");
		createGui();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainController.exit();
			}
		});
		logger.trace("MainView() - Leaving");
	}

	private void createGui() {
		logger.trace("createGui() - Entering");
		this.setIconImage(ImageIconFactory
				.createImageIconFromResourePath("img/icon_128.png").getImage());
		this.setTitle(Constants.getAppName() + " - v"
				+ Constants.getAppVersion() + " powered by Elophant.com");
		this.setMinimumSize(new Dimension(420, 520));
		this.setJMenuBar(getMenuBarView());
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		this.add(getContentTabbedPane(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		this.add(getStatusLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.add(getSummonerInfoLabel(), constraints);
		logger.trace("createGui() - Leaving");
	}

	public void setMainController(MainController controller) {
		logger.trace("setMainController() - Entering");
		logger.debug("setMainController() - Parameter {}", controller);
		logger.trace("setMainController() - Leaving");
	}

	public static synchronized MainView getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("getInstance() - Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of MainView");
			instance = new MainView();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning {}", instance);
		return instance;
	}

	public MenuBarView getMenuBarView() {
		logger.trace("getMenuBarView() - Entering");
		if (menu == null) {
			menu = new MenuBarView();
		}
		logger.trace("getMenuBarView() - Returning");
		logger.debug("getMenuBarView() - Returning {}", menu);
		return menu;
	}

	public JTabbedPane getContentTabbedPane() {
		logger.trace("getContentTabbedPane() - Entering");
		if (contentPane == null) {
			contentPane = new JTabbedPane();
			contentPane.addTab("Matchups", null);
			contentPane.addTab("Stats", null);
		}
		logger.trace("getContentTabbedPane() - Returning");
		logger.debug("getContentTabbedPane() - Returning {}", contentPane);
		return contentPane;
	}

	public JLabel getStatusLabel() {
		logger.trace("getStatusLabel() - Entering");
		if (statusLabel == null) {
			statusLabel = new JLabel("Ready");
			statusLabel.setBorder(BorderFactory.createBevelBorder(1));
		}
		logger.trace("getStatusLabel() - Returning");
		logger.debug("getStatusLabel() - Returning {}", statusLabel);
		return statusLabel;
	}

	public JLabel getSummonerInfoLabel() {
		logger.trace("getSummonerInfoLabel() - Entering");
		if (summonerInfoLabel == null) {
			summonerInfoLabel = new JLabel(Config.getInstance()
					.getCurrentSummoner().getSummonerInfoString());
			summonerInfoLabel.setBorder(BorderFactory.createBevelBorder(1));
			summonerInfoLabel
					.setToolTipText("Summoner name | Ranked tier and devision | Winning/Losing streak(last 10 games ranked and normal)");
		}
		logger.trace("getSummonerInfoLabel() - Returning");
		logger.debug("getSummonerInfoLabel() - Returning: {}",
				summonerInfoLabel);
		return summonerInfoLabel;
	}

	public void setMatchupPanel(MatchupView comp) {
		logger.trace("setMatchupPanel() - Entering");
		logger.debug("setMatchupPanel() - Parameter: {}", comp);
		getContentTabbedPane().setComponentAt(0, comp);
		logger.trace("setMatchupPanel() - Leaving");
	}

	@Override
	public void run() {
		this.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - this.getSize().width) / 2,
				(d.height - this.getSize().height) / 2);
		this.setVisible(true);
	}

	public void setStatusText(String text) {
		logger.trace("setStatusText() - Entering");
		logger.debug("setStatusText() - Parameter: {}", text);
		statusLabel.setText(text);
		logger.trace("setStatusText() - Leaving");
	}

	@Override
	public void refresh() {
		menu.refresh();
	}

	/**
	 * @return
	 */
	public MatchupView getMatchupPanel() {
		logger.trace("getMatchupPanel() - Entering");
		MatchupView returnValue = (MatchupView) getContentTabbedPane()
				.getComponentAt(0);
		logger.trace("getMatchupPanel() - Returning");
		logger.debug("getMatchupPanel() - Returning: {}", returnValue);
		return returnValue;
	}

	public void setStatsPanel(StatsView view) {
		logger.trace("setStatsPanel() - Entering");
		logger.debug("setStatsPanel() - Parameter: {}", view);
		getContentTabbedPane().setComponentAt(1, view);
		logger.trace("setStatsPanel() - Leaving");
	}

	/**
	 * @param summonerInfoString
	 */
	public void setSummonerInfo(String summonerInfoString) {
		logger.trace("getSummonerInfoLabel() - Entering");
		logger.debug("getSummonerInfoLabel() - Parameter: {}",
				summonerInfoString);
		getSummonerInfoLabel().setText(summonerInfoString);
	}
}
