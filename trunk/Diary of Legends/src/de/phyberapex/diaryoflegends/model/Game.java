package de.phyberapex.diaryoflegends.model;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game extends Model {

	private Date date;
	private List<Champion> myTeam;
	private List<Champion> enemyTeam;
	private Matchup matchup;
	private GameResult result;
	private String notes;
	private int ownKills;
	private int ownDeaths;
	private int ownAssists;
	transient private static Logger logger = LogManager.getLogger(Game.class
			.getName());

	public Game() {
	}

	public Game(Date date, List<Champion> myTeam, List<Champion> enemyTeam,
			Matchup matchup, GameResult result, String notes, int ownKills,
			int ownDeaths, int ownAssists) {
		this.date = date;
		this.myTeam = myTeam;
		this.enemyTeam = enemyTeam;
		this.matchup = matchup;
		this.matchup.setGame(this);
		this.result = result;
		this.notes = notes;
		this.ownKills = ownKills;
		this.ownDeaths = ownDeaths;
		this.ownAssists = ownAssists;
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

	public Matchup getMatchup() {
		logger.trace("getMatchup() - Entering");
		logger.trace("getMatchup() - Returning");
		logger.debug("getMatchup() - Returning: {}", matchup);
		return matchup;
	}

	public void setMatchup(Matchup matchup) {
		logger.trace("setMatchup() - Entering");
		logger.debug("setMatchup() - Parameter: {}", matchup);
		this.matchup = matchup;
		logger.trace("setMatchup() - Leaving");
	}

	public GameResult getResult() {
		logger.trace("getResult() - Entering");
		logger.trace("getResult() - Returning");
		logger.debug("getResult() - Returning: {}", result);
		return result;
	}

	public void setResult(GameResult result) {
		logger.trace("setResult() - Entering");
		logger.debug("setResult() - Parameter: {}", result);
		this.result = result;
		logger.trace("setResult() - Leaving");
	}

	public String getNotes() {
		logger.trace("getNotes() - Entering");
		logger.trace("getNotes() - Returning");
		logger.debug("getNotes() - Returning: {}", notes);
		return notes;
	}

	public void setNotes(String notes) {
		logger.trace("setNotes() - Entering");
		logger.debug("setNotes() - Parameter: {}", notes);
		this.notes = notes;
		logger.trace("setNotes() - Leaving");
	}

	public int getOwnKills() {
		logger.trace("getOwnKills() - Entering");
		logger.trace("getOwnKills() - Returning");
		logger.debug("getOwnKills() - Returning: {}", ownKills);
		return ownKills;
	}

	public void setOwnKills(int ownKills) {
		logger.trace("setOwnKills() - Entering");
		logger.debug("setOwnKills() - Parameter: {}", ownKills);
		this.ownKills = ownKills;
		logger.trace("setOwnKills() - Leaving");
	}

	public int getOwnDeaths() {
		logger.trace("getOwnDeaths() - Entering");
		logger.trace("getOwnDeaths() - Returning");
		logger.debug("getOwnDeaths() - Returning: {}", ownDeaths);
		return ownDeaths;
	}

	public void setOwnDeaths(int ownDeaths) {
		logger.trace("setOwnDeaths() - Entering");
		logger.debug("setOwnDeaths() - Parameter: {}", ownDeaths);
		this.ownDeaths = ownDeaths;
		logger.trace("setOwnDeaths() - Leaving");
	}

	public int getOwnAssists() {
		logger.trace("getOwnAssists() - Entering");
		logger.trace("getOwnAssists() - Returning");
		logger.debug("getOwnAssists() - Returning: {}", ownAssists);
		return ownAssists;
	}

	public void setOwnAssists(int ownAssists) {
		logger.trace("setOwnAssists() - Entering");
		logger.debug("setOwnAssists() - Parameter: {}", ownAssists);
		this.ownAssists = ownAssists;
		logger.trace("setOwnAssists() - Leaving");
	}
	
	@Override
	public String toString(){
		return this.notes;
	}
}
