package de.phyberapex.diaryoflegends.controller;

import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.GameStatistic;
import de.phyberapex.diaryoflegends.model.StatisticTypes;
import de.phyberapex.diaryoflegends.model.Statistics;
import de.phyberapex.diaryoflegends.model.Top3EnemyStatistics;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.view.StatsView;
import de.phyberapex.diaryoflegends.view.panel.stats.DefaultStatsPanel;
import de.phyberapex.diaryoflegends.view.panel.stats.Top3EnemiesPanel;

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
		result.add(new JLabel("Comming soon(TM)"));
		switch (itemAt) {
		case TOP_3_HARDEST_ENEMY_MULTIPLE_CHAMPS:
			Top3EnemyStatistics stats = ChampionUtil.getTop3Enemies(parameter);
			result = new Top3EnemiesPanel(stats);
			break;
		case TOP_3_HARDEST_ENEMY_PER_CHAMPS:
			break;
		case DEFAULT_STATS:
		default:
			Statistics statsGame = new Statistics();
			HashMap<String, GameStatistic> mostXXX = ChampionUtil
					.getGameStats(parameter);
			statsGame.addStats("mostXXX", mostXXX);
			result = new DefaultStatsPanel(statsGame);
			break;
		}
		((StatsView) getView()).setStatsPanel(result);
	}
}
