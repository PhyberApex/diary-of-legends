package de.phyberapex.diaryoflegends.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameElophantImport {

	private List<Champion> myTeam;
	private List<Champion> enemyTeam;
	private Date date;
	private Champion myChampion;
	private boolean selected = false;
	private boolean ranked = false;
	transient private static Logger logger = LogManager
			.getLogger(GameElophantImport.class.getName());

	public GameElophantImport() {

	}

	public GameElophantImport(List<Champion> myTeam, List<Champion> enemyTeam,
			Date date, Champion myChampion, GameResult result) {
		this.myTeam = myTeam;
		this.enemyTeam = enemyTeam;
		this.date = date;
		this.myChampion = myChampion;
	}

	public List<Champion> getMyTeam() {
		logger.trace("getMyTeam() - Entering");
		logger.trace("getMyTeam() - Returning");
		logger.debug("getMyTeam() - Returning: {}", myTeam);
		return myTeam;
	}

	public void setMyTeam(List<Champion> myTeam) {
		logger.trace("setMyTeam() - Entering");
		logger.debug("setMyTeam() - Parameter: {}", myTeam);
		this.myTeam = myTeam;
		logger.trace("setMyTeam() - Leaving");
	}

	public List<Champion> getEnemyTeam() {
		logger.trace("getEnemyTeam() - Entering");
		logger.trace("getEnemyTeam() - Returning");
		logger.debug("getEnemyTeam() - Returning: {}", enemyTeam);
		return enemyTeam;
	}

	public void setEnemyTeam(List<Champion> enemyTeam) {
		logger.trace("setEnemyTeam() - Entering");
		logger.debug("setEnemyTeam() - Parameter: {}", enemyTeam);
		this.enemyTeam = enemyTeam;
		logger.trace("setEnemyTeam() - Leaving");
	}

	public Date getDate() {
		logger.trace("getDate() - Entering");
		logger.trace("getDate() - Returning");
		logger.debug("getDate() - Returning: {}", date);
		return date;
	}

	public void setDate(Date date) {
		logger.trace("setDate() - Entering");
		logger.debug("setDate() - Parameter: {}", date);
		this.date = date;
		logger.trace("setDate() - Leaving");
	}

	public Champion getMyChampion() {
		logger.trace("getMyChampion() - Entering");
		logger.trace("getMyChampion() - Returning");
		logger.debug("getMyChampion() - Returning: {}", myChampion);
		return myChampion;
	}

	public void setMyChampion(Champion myChampion) {
		logger.trace("setMyChampion() - Entering");
		logger.debug("setMyChampion() - Parameter: {}", myChampion);
		this.myChampion = myChampion;
		logger.trace("setMyChampion() - Leaving");
	}

	public void setSelected(boolean sel) {
		this.selected = sel;
	}

	/**
	 * @return
	 */
	public boolean isSelected() {
		return selected;
	}

	public void setRanked(boolean ran) {
		this.ranked = ran;
	}

	/**
	 * @return
	 */
	public boolean isRanked() {
		return ranked;
	}

	@Override
	public String toString() {
		logger.trace("toString() - Entering");
		Date d = getDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = df.format(d);
		logger.trace("toString() - Returning");
		logger.debug("toString() - Returning: {}", str);
		return str;
	}
}
