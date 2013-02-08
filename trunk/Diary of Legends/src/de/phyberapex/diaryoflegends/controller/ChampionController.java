package de.phyberapex.diaryoflegends.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Model;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.view.ChampionView;

public class ChampionController extends Controller {

	private List<Champion> champions;
	private static Logger logger = LogManager
			.getLogger(ChampionController.class.getName());

	public ChampionController(MainController mainController) {
		super(mainController);
		logger.trace("MainController() - Entering");
		logger.debug("MainController() - Parameter: {}", mainController);
		champions = ChampionUtil.getAllChampions();
		this.models.addAll(champions);
		logger.trace("MainController() - Leaving");
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
		logger.trace("loadData() - Leaving");
	}

	public List<Champion> getChampions() {
		logger.trace("getChampions() - Entering");
		logger.trace("getChampions() - Returning");
		logger.debug("getChampions() - Returning: {}", champions);
		return this.champions;
	}

}
