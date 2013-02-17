package de.phyberapex.diaryoflegends.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.util.GameUtil;
import de.phyberapex.diaryoflegends.view.GameView;

public class GameController extends Controller {

	private List<Game> games = new ArrayList<Game>();
	transient private static Logger logger = LogManager
			.getLogger(ItemController.class.getName());

	public GameController(MainController mainController) {
		super(mainController);
		logger.trace("ItemController() - Entering");
		logger.debug("ItemController() - Parameter: {}", mainController);
		logger.trace("ItemController() - Leaving");
	}

	@Override
	public void loadGUI() {
		logger.trace("loadGUI() - Entering");
		this.view = new GameView(this);
		logger.trace("loadGUI() - Leaving");
	}

	@Override
	public void loadData() {
		logger.trace("loadData() - Entering");
		games.addAll(GameUtil.getAllGames());
		view.refresh();
		logger.trace("loadData() - Leaving");
	}

	/**
	 * Returns all games
	 * 
	 * @return {@link List<Game>} A list with all games
	 */
	public List<Game> getGames() {
		logger.trace("getGames() - Entering");
		logger.trace("getGames() - Returning");
		logger.debug("getGames() - Returning: {}", games);
		return this.games;
	}
}
