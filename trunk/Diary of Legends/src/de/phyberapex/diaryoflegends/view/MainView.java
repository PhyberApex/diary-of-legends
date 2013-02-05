package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.JFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.base.Constants;
import de.phyberapex.diaryoflegends.controller.MainController;

public class MainView extends JFrame implements View, Runnable {

	private static final long serialVersionUID = -3382358595179093567L;

	private MenuBarView menu;
	private MainController controller;
	private static Logger logger = LogManager.getLogger(MainView.class
			.getName());

	public MainView(MainController mainController) {
		super();
		logger.trace("MainView() - Entering");
		logger.debug("Parameter {}", mainController);
		this.controller = mainController;
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
		this.menu = new MenuBarView();
		this.setJMenuBar(menu);
		this.setTitle(Constants.getAppName()+" - v"+Constants.getAppVersion());
		logger.trace("createGui() - Leaving");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
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
