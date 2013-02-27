package de.phyberapex.diaryoflegends.controller;

import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.GameStatistic;
import de.phyberapex.diaryoflegends.model.StatisticTypes;
import de.phyberapex.diaryoflegends.model.Statistics;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.view.StatsView;
import de.phyberapex.diaryoflegends.view.panel.DefaultStatsPanel;

public class StatsController extends Controller {

	transient static private Logger logger = LogManager
			.getLogger(StatsController.class.getName());

	/**
	 * @param mainController
	 */
	public StatsController(MainController mainController) {
		super(mainController);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.phyberapex.diaryoflegends.controller.Controller#loadGUI()
	 */
	@Override
	public void loadGUI() {
		logger.trace("loadGUI() - Entering");
		this.view = new StatsView(this);
		logger.trace("loadGUI() - Leaving");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.phyberapex.diaryoflegends.controller.Controller#loadData()
	 */
	@Override
	public void loadData() {
		getView().refresh();
	}

	/**
	 * @param itemAt
	 */
	public void createStats(StatisticTypes itemAt, String parameter) {
		JPanel result = new JPanel();
		Statistics stats = new Statistics();
		stats.setType(itemAt);
		result.add(new JLabel("Comming soon(TM)"));
		switch (itemAt) {
		case TOP_3_HARDEST_ENEMY_MULTIPLE_CHAMPS:
			break;
		case TOP_3_HARDEST_ENEMY_PER_CHAMPS:
			break;
		case DEFAULT_STATS:
		default:
			HashMap<String, GameStatistic> mostXXX = new HashMap<>();
			mostXXX = ChampionUtil.getMostXXXStat(parameter);
			stats.addStats("mostXXX", mostXXX);
			result = new DefaultStatsPanel(stats);
			break;
		}
		((StatsView) getView()).setStatsPanel(result);
	}
}
