package de.phyberapex.diaryoflegends.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Model;
import de.phyberapex.diaryoflegends.view.View;

public abstract class Controller {

	protected List<Model> models = new ArrayList<Model>();
	protected View view;
	private static Logger logger = LogManager.getLogger(Controller.class
			.getName());

	
	public Controller(View view){
		this.view = view;
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
	 * Adds all views as observers to all views
	 */
	public void addObservers() {
		logger.trace("addObservers() - Entering");
		for (Model model : models) {
			logger.debug("Current model = {}", model);
			model.addObserver(view);
		}
		logger.trace("addObservers() - Leaving");
	}

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

	/**
	 * Returns all models currently connected to this controller
	 * 
	 * @return {@link Iterator}
	 */
	public Iterator<Model> getModels() {
		logger.trace("getModels() - Entering");
		logger.trace("getModels() - Returning");
		logger.debug("Returned {}", models.iterator());
		return models.iterator();
	}
}