package de.phyberapex.diaryoflegends.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.view.MainView;

public class RefreshSummonerInfoAction implements Runnable {

	private boolean destroy = false;
	private static RefreshSummonerInfoAction instance;
	private static Logger logger = LogManager
			.getLogger(RefreshSummonerInfoAction.class.getName());

	private RefreshSummonerInfoAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!destroy) {
			try {
				Thread.sleep(300000);
				MainView.getInstance().setSummonerInfo(
						Config.getInstance().getCurrentSummoner()
								.getSummonerInfoString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns an instance of this class if the static attribute instance is<br>
	 * null it will be created.
	 * 
	 * @return {@link RefreshSummonerInfoAction} an instance of this class
	 */
	public static synchronized RefreshSummonerInfoAction getInstance() {
		logger.trace("getInstance() - Entering");
		if (instance == null) {
			instance = new RefreshSummonerInfoAction();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning: {}", instance);
		return instance;
	}

	/**
	 * 
	 */
	public void destroy() {
		this.destroy = true;
	}

}
