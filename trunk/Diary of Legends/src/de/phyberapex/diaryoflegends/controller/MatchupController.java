/**
 * 
 */
package de.phyberapex.diaryoflegends.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;
import de.phyberapex.diaryoflegends.view.MatchupView;

/**
 * @author PhyberApex
 * 
 */
public class MatchupController extends Controller {

	private List<Matchup> matchups = new ArrayList<Matchup>();
	transient private static Logger logger = LogManager
			.getLogger(MatchupController.class.getName());

	public MatchupController(MainController mainController) {
		super(mainController);
		logger.trace("ItemController() - Entering");
		logger.debug("ItemController() - Parameter: {}", mainController);
		matchups = MatchupUtil.getAllMatchups();
		logger.trace("ItemController() - Leaving");
	}

	@Override
	public void loadGUI() {
		logger.trace("loadGUI() - Entering");
		this.view = new MatchupView(this);
		logger.trace("loadGUI() - Leaving");
	}

	@Override
	public void loadData() {
		logger.trace("loadData() - Entering");
		view.refresh();
		logger.trace("loadData() - Leaving");
	}

	/**
	 * Returns all matchups
	 * 
	 * @return {@link List<Matchup>} A list with all matchups
	 */
	public List<Matchup> getMatchups() {
		logger.trace("getMatchups() - Entering");
		logger.trace("getMatchups() - Returning");
		logger.debug("getMatchups() - Returning: {}", matchups);
		return this.matchups;
	}
}
