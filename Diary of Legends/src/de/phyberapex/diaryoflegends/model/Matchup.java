package de.phyberapex.diaryoflegends.model;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Matchup extends Model {

	private Game game;
	private Champion myChamp;
	private List<MatchupItem> myStartItems;
	private List<MatchupItem> myEndItems;
	private Champion enemyChamp;
	private List<MatchupItem> enemyStartItems;
	private List<MatchupItem> enemyEndItems;
	private MatchupResult result;
	private Role lane;
	private MatchupDifficulty difficulty;
	private String notes;
	transient private static Logger logger = LogManager.getLogger(Matchup.class
			.getName());

	public Matchup() {
	}

	public Matchup(Game game, Champion myChamp, Champion enemyChamp,
			List<MatchupItem> myStartItems, List<MatchupItem> myEndItems,
			List<MatchupItem> enemyStartItems, List<MatchupItem> enemyEndItems,
			MatchupResult result, Role role, MatchupDifficulty difficulty,
			String notes) {
		this.game = game;
		this.myChamp = myChamp;
		this.enemyChamp = enemyChamp;
		this.myStartItems = myStartItems;
		this.myEndItems = myEndItems;
		this.enemyStartItems = enemyStartItems;
		this.enemyEndItems = enemyEndItems;
		this.result = result;
		this.lane = role;
		this.difficulty = difficulty;
		this.notes = notes;

	}

	public Game getGame() {
		logger.trace("getGame() - Entering");
		logger.trace("getGame() - Returning");
		logger.debug("getGame() - Returning: {}", game);
		return game;
	}

	public void setGame(Game game) {
		logger.trace("setGame() - Entering");
		logger.debug("setGame() - Parameter: {}", game);
		this.game = game;
		logger.trace("setGame() - Leaving");
	}

	public Champion getMyChamp() {
		logger.trace("getMyChamp() - Entering");
		logger.trace("getMyChamp() - Returning");
		logger.debug("getMyChamp() - Returning: {}", myChamp);
		return myChamp;
	}

	public void setMyChamp(Champion myChamp) {
		logger.trace("setMyChamp() - Entering");
		logger.debug("setMyChamp() - Parameter: {}", myChamp);
		this.myChamp = myChamp;
		logger.trace("setMyChamp() - Leaving");
	}

	public List<MatchupItem> getMyStartItems() {
		logger.trace("getMyStartItems() - Entering");
		logger.trace("getMyStartItems() - Returning");
		logger.debug("getMyStartItems() - Returning: {}", myStartItems);
		return myStartItems;
	}

	public void setMyStartItems(List<MatchupItem> myStartItems) {
		logger.trace("setMyStartItems() - Entering");
		logger.debug("setMyStartItems() - Parameter: {}", myStartItems);
		this.myStartItems = myStartItems;
		logger.trace("setMyStartItems() - Leaving");
	}
	
	public List<MatchupItem> getMyEndItems() {
		logger.trace("getMyEndItems() - Entering");
		logger.trace("getMyEndItems() - Returning");
		logger.debug("getMyEndItems() - Returning: {}", myEndItems);
		return myEndItems;
	}

	public void setMyEndItems(List<MatchupItem> myEndItems) {
		logger.trace("setMyEndItems() - Entering");
		logger.debug("setMyEndItems() - Parameter: {}", myEndItems);
		this.myEndItems = myEndItems;
		logger.trace("setMyEndItems() - Leaving");
	}

	public Champion getEnemyChamp() {
		logger.trace("getEnemyChamp() - Entering");
		logger.trace("getEnemyChamp() - Returning");
		logger.debug("getEnemyChamp() - Returning: {}", enemyChamp);
		return enemyChamp;
	}

	public void setEnemyChamp(Champion enemyChamp) {
		logger.trace("setEnemyChamp() - Entering");
		logger.debug("setEnemyChamp() - Parameter: {}", enemyChamp);
		this.enemyChamp = enemyChamp;
		logger.trace("setEnemyChamp() - Leaving");
	}

	public List<MatchupItem> getEnemyStartItems() {
		logger.trace("getEnemyStartItems() - Entering");
		logger.trace("getEnemyStartItems() - Returning");
		logger.debug("getEnemyStartItems() - Returning: {}", enemyStartItems);
		return enemyStartItems;
	}

	public void setEnemyStartItems(List<MatchupItem> enemyStartItems) {
		logger.trace("setEnemyStartItems() - Entering");
		logger.debug("setEnemyStartItems() - Parameter: {}", enemyStartItems);
		this.enemyStartItems = enemyStartItems;
		logger.trace("setEnemyStartItems() - Leaving");
	}
	
	public List<MatchupItem> getEnemyEndItems() {
		logger.trace("getEnemyEndItems() - Entering");
		logger.trace("getEnemyEndItems() - Returning");
		logger.debug("getEnemyEndItems() - Returning: {}", enemyEndItems);
		return enemyEndItems;
	}

	public void setEnemyEndItems(List<MatchupItem> enemyEndItems) {
		logger.trace("setEnemyEndItems() - Entering");
		logger.debug("setEnemyEndItems() - Parameter: {}", enemyEndItems);
		this.enemyEndItems = enemyEndItems;
		logger.trace("setEnemyEndItems() - Leaving");
	}

	public MatchupResult getResult() {
		logger.trace("getResult() - Entering");
		logger.trace("getResult() - Returning");
		logger.debug("getResult() - Returning: {}", result);
		return result;
	}

	public void setResult(MatchupResult result) {
		logger.trace("setResult() - Entering");
		logger.debug("setResult() - Parameter: {}", result);
		this.result = result;
		logger.trace("setResult() - Leaving");
	}

	public Role getLane() {
		logger.trace("getLane() - Entering");
		logger.trace("getLane() - Returning");
		logger.debug("getLane() - Returning: {}", lane);
		return lane;
	}

	public void setLane(Role lane) {
		logger.trace("setLane() - Entering");
		logger.debug("setLane() - Parameter: {}", lane);
		this.lane = lane;
		logger.trace("setLane() - Leaving");
	}

	public MatchupDifficulty getDifficulty() {
		logger.trace("getDifficulty() - Entering");
		logger.trace("getDifficulty() - Returning");
		logger.debug("getDifficulty() - Returning: {}", difficulty);
		return difficulty;
	}

	public void setDifficulty(MatchupDifficulty difficulty) {
		logger.trace("setDifficulty() - Entering");
		logger.debug("setDifficulty() - Parameter: {}", difficulty);
		this.difficulty = difficulty;
		logger.trace("setDifficulty() - Leaving");
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

	@Override
	public String toString() {
		logger.trace("toString() - Entering");
		String str = myChamp + " vs. " + enemyChamp;
		logger.trace("toString() - Returning");
		logger.debug("toString() - Returning: {}", str);
		return str;
	}
}
