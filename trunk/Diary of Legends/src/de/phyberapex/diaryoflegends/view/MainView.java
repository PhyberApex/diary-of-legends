package de.phyberapex.diaryoflegends.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.base.Constants;
import de.phyberapex.diaryoflegends.controller.MainController;

public class MainView extends JFrame implements View, Runnable {

	private static final long serialVersionUID = -3382358595179093567L;

	private static MainView instance;
	private MenuBarView menu;
	private MainController controller;
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
		this.setTitle(Constants.getAppName() + " - v"
				+ Constants.getAppVersion());
		this.setJMenuBar(getMenuBarView());
		this.setContentPane(getContentPane());
		logger.trace("createGui() - Leaving");
	}

	public void setMainController(MainController controller) {
		logger.trace("setMainController() - Entering");
		logger.debug("Parameter {}", controller);
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
		logger.debug("Returned {}", instance);
		return instance;
	}

	public MenuBarView getMenuBarView() {
		logger.trace("getMenuBarView() - Entering");
		if (menu == null) {
			menu = new MenuBarView();
		}
		logger.trace("getMenuBarView() - Returning");
		logger.debug("Returned {}", menu);
		return menu;
	}

	public JTabbedPane getContentPane() {
		logger.trace("getMenuBarView() - Entering");
		if (contentPane == null) {
			contentPane = new JTabbedPane();
			contentPane.addTab("Games", null);
			contentPane.addTab("Matchups", null);
			contentPane.addTab("Stats", null);
		}
		logger.trace("getMenuBarView() - Returning");
		logger.debug("Returned {}", contentPane);
		return contentPane;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}

	public void setGamesPanel(Component comp) {
		logger.trace("setGamesPanel() - Entering");
		logger.debug("Parameter: {}", comp);
		getContentPane().setComponentAt(0, comp);
		logger.trace("setGamesPanel() - Leaving");
	}

	@Override
	public void run() {
		this.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - this.getSize().width) / 2,
				(d.height - this.getSize().height) / 2);
		this.setVisible(true);
	}

}
