package de.phyberapex.diaryoflegends.controller;

import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.view.View;

public abstract class Controller {

	protected View view;
	protected MainController mainController;
	transient private static Logger logger = LogManager
			.getLogger(Controller.class.getName());

	public Controller(MainController mainController) {
		this.mainController = mainController;
	}

	/**
	 * Loads the GUI elements
	 */
	public abstract void loadGUI();

	/**
	 * Loads the model into the view
	 */
	public abstract void loadData();

	/**
	 * Returns the view connected to this controller
	 * 
	 * @return {@link Iterator}
	 */
	public View getView() {
		logger.trace("getView() - Entering");
		logger.trace("getView() - Returning");
		logger.debug("Returned {}", view);
		return view;
	}

}