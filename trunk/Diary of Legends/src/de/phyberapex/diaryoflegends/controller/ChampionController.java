package de.phyberapex.diaryoflegends.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.view.ChampionView;

public class ChampionController extends Controller {

	private List<Champion> champions = new ArrayList<Champion>();
	transient private static Logger logger = LogManager
			.getLogger(ChampionController.class.getName());

	public ChampionController(MainController mainController) {
		super(mainController);
		logger.trace("ChampionController() - Entering");
		logger.debug("ChampionController() - Parameter: {}", mainController);
		champions = ChampionUtil.getAllChampions();
		logger.trace("ChampionController() - Leaving");
	}

	@Override
	public void loadGUI() {
		logger.trace("loadGUI() - Entering");
		this.view = new ChampionView(this);
		logger.trace("loadGUI() - Leaving");
	}

	@Override
	public void loadData() {
		logger.trace("loadData() - Entering");
		view.refresh();
		logger.trace("loadData() - Leaving");
	}

	/**
	 * Returns all champions
	 * 
	 * @return {@link List<Champion>} A list with all champions
	 */
	public List<Champion> getChampions() {
		logger.trace("getChampions() - Entering");
		logger.trace("getChampions() - Returning");
		logger.debug("getChampions() - Returning: {}", champions);
		return this.champions;
	}

	/**
	 * Returns all champions which have the searchString in their name
	 * @param text {@link String} searchString
	 * @return {@link List<Champion>} List of champions which have the searchString in their name
	 */
	public List<Champion> searchChampions(String text) {
		logger.trace("searchChampions() - Entering");
		logger.debug("searchChampions() - Parameter: {}", text);
		List<Champion> returnValue = ChampionUtil.searchChampionByName(text);
		logger.trace("searchChampions() - Returning");
		logger.debug("searchChampions() - Returning: {}", returnValue);
		return returnValue;
	}

}
