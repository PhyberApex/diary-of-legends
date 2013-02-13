package de.phyberapex.diaryoflegends.model;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Matchup extends Model {

	private Game game;
	private Champion myChamp;
	private List<Item> myStartItems;
	private Champion enemyChamp;
	private List<Item> enemyStartItems;
	private MatchupResult result;
	private String notes;
	transient private static Logger logger = LogManager.getLogger(Matchup.class
			.getName());

	public Matchup() {
	}

	public Matchup(Game game, Champion myChamp, Champion enemyChamp,
			List<Item> myStartItems, List<Item> enemyStartItems,
			MatchupResult result, String notes) {
		this.game = game;
		this.myChamp = myChamp;
		this.enemyChamp = enemyChamp;
		this.myStartItems = myStartItems;
		this.enemyStartItems = enemyStartItems;
		this.result = result;
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

	public List<Item> getMyStartItems() {
		logger.trace("getMyStartItems() - Entering");
		logger.trace("getMyStartItems() - Returning");
		logger.debug("getMyStartItems() - Returning: {}", myStartItems);
		return myStartItems;
	}

	public void setMyStartItems(List<Item> myStartItems) {
		logger.trace("setMyStartItems() - Entering");
		logger.debug("setMyStartItems() - Parameter: {}", myStartItems);
		this.myStartItems = myStartItems;
		logger.trace("setMyStartItems() - Leaving");
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

	public List<Item> getEnemyStartItems() {
		logger.trace("getEnemyStartItems() - Entering");
		logger.trace("getEnemyStartItems() - Returning");
		logger.debug("getEnemyStartItems() - Returning: {}", enemyStartItems);
		return enemyStartItems;
	}

	public void setEnemyStartItems(List<Item> enemyStartItems) {
		logger.trace("setEnemyStartItems() - Entering");
		logger.debug("setEnemyStartItems() - Parameter: {}", enemyStartItems);
		this.enemyStartItems = enemyStartItems;
		logger.trace("setEnemyStartItems() - Leaving");
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
