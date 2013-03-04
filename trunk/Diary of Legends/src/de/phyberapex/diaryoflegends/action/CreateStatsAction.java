package de.phyberapex.diaryoflegends.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.controller.StatsController;
import de.phyberapex.diaryoflegends.model.StatisticTypes;
import de.phyberapex.diaryoflegends.view.MainView;

public class CreateStatsAction implements Runnable {

	private static Logger logger = LogManager.getLogger(CreateStatsAction.class
			.getName());
	private StatsController controller;
	private StatisticTypes stat;
	private String parameter;

	public CreateStatsAction(StatsController cont, StatisticTypes stat,
			String parameter) {
		logger.trace("CreateStatsAction() - Entering");
		this.controller = cont;
		this.stat = stat;
		this.parameter = parameter;
		logger.trace("CreateStatsAction() - Leaving");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		MainView.getInstance().setStatusText("Generating statistics...");
		controller.createStats(stat, parameter);
		MainView.getInstance().setStatusText("Statistics generated");
	}
}