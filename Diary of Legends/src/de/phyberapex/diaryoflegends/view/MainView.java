package de.phyberapex.diaryoflegends.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.base.Constants;
import de.phyberapex.diaryoflegends.controller.MainController;

public class MainView extends JFrame implements View, Runnable {

	private static final long serialVersionUID = -3382358595179093567L;

	private static MainView instance;
	private MenuBarView menu;
	private JLabel statusLabel;
	private JTabbedPane contentPane;
	private MainController controller;
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
		this.setTitle(Constants.getAppName() + " - v"
				+ Constants.getAppVersion());
		this.setJMenuBar(getMenuBarView());
		this.setLayout(new BorderLayout());
		this.add(getContentTabbedPane(), BorderLayout.CENTER);
		this.add(getStatusLabel(), BorderLayout.SOUTH);
		logger.trace("createGui() - Leaving");
	}

	public void setMainController(MainController controller) {
		logger.trace("setMainController() - Entering");
		logger.debug("setMainController() - Parameter {}", controller);
		this.controller = controller;
		logger.trace("setMainController() - Leaving");
	}

	public static synchronized MainView getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("Instance is {}", instance);
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
			menu = new MenuBarView(this);
		}
		logger.trace("getMenuBarView() - Returning");
		logger.debug("getMenuBarView() - Returning {}", menu);
		return menu;
	}

	public JTabbedPane getContentTabbedPane() {
		logger.trace("getContentTabbedPane() - Entering");
		if (contentPane == null) {
			contentPane = new JTabbedPane();
			contentPane.addTab("Games", null);
			contentPane.addTab("Matchups", null);
			contentPane.addTab("Stats", null);
			contentPane.addTab("Champions", null);
			contentPane.addTab("Items", null);
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

	public void setGamesPanel(Component comp) {
		logger.trace("setGamesPanel() - Entering");
		logger.debug("Parameter: {}", comp);
		getContentTabbedPane().setComponentAt(0, comp);
		logger.trace("setGamesPanel() - Leaving");
	}

	public void setChampPanel(ChampionView comp) {
		logger.trace("setChampPanel() - Entering");
		logger.debug("setChampPanel() - Parameter: {}", comp);
		getContentTabbedPane().setComponentAt(3, comp);
		logger.trace("setChampPanel() - Leaving");
	}

	public ChampionView getChampPanel() {
		logger.trace("setChampPanel() - Entering");
		ChampionView returnValue = (ChampionView) getContentTabbedPane()
				.getComponentAt(3);
		logger.trace("setChampPanel() - Returning");
		logger.debug("setChampPanel() - Returning: {}", returnValue);
		return returnValue;
	}

	public void setItemPanel(ItemView comp) {
		logger.trace("setItemPanel() - Entering");
		logger.debug("setItemPanel() - Parameter: {}", comp);
		getContentTabbedPane().setComponentAt(4, comp);
		logger.trace("setItemPanel() - Leaving");
	}
	
	public void setMatchupPanel(MatchupView comp) {
		logger.trace("setMatchupPanel() - Entering");
		logger.debug("setMatchupPanel() - Parameter: {}", comp);
		getContentTabbedPane().setComponentAt(1, comp);
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
		// TODO Auto-generated method stub
		
	}
}
