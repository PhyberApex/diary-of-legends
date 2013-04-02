package de.phyberapex.diaryoflegends.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Matchup extends Model {

	private Game game;
	private Champion myChamp;
	private SummonerSpell mySpell1;
	private SummonerSpell mySpell2;
	private List<MatchupItem> myStartItems;
	private List<MatchupItem> myEndItems;
	private Champion enemyChamp;
	private SummonerSpell enemySpell1;
	private SummonerSpell enemySpell2;
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

	public Matchup(Game game, Champion myChamp, SummonerSpell mySpell1,
			SummonerSpell mySpell2, Champion enemyChamp,
			SummonerSpell enemySpell1, SummonerSpell enemySpell2,
			List<MatchupItem> myStartItems, List<MatchupItem> myEndItems,
			List<MatchupItem> enemyStartItems, List<MatchupItem> enemyEndItems,
			MatchupResult result, Role role, MatchupDifficulty difficulty,
			String notes) {
		this.game = game;
		this.myChamp = myChamp;
		this.mySpell1 = mySpell1;
		this.mySpell2 = mySpell2;
		this.enemyChamp = enemyChamp;
		this.enemySpell1 = enemySpell1;
		this.enemySpell2 = enemySpell2;
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

	public SummonerSpell getMySpell1() {
		logger.trace("getMySpell1() - Entering");
		logger.trace("getMySpell1() - Returning");
		logger.debug("getMySpell1() - Returning: {}", mySpell1);
		return mySpell1;
	}

	public void setMySpell1(SummonerSpell spell) {
		logger.trace("setMySpell1() - Entering");
		logger.debug("setMySpell1() - Parameter: {}", spell);
		this.mySpell1 = spell;
		logger.trace("setMySpell1() - Leaving");
	}

	public SummonerSpell getMySpell2() {
		logger.trace("getMySpell2() - Entering");
		logger.trace("getMySpell2() - Returning");
		logger.debug("getMySpell2() - Returning: {}", mySpell2);
		return mySpell2;
	}

	public void setMySpell2(SummonerSpell spell) {
		logger.trace("setMySpell2() - Entering");
		logger.debug("setMySpell2() - Parameter: {}", spell);
		this.mySpell2 = spell;
		logger.trace("setMySpell2() - Leaving");
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

	public SummonerSpell getEnemySpell1() {
		logger.trace("getEnemySpell1() - Entering");
		logger.trace("getEnemySpell1() - Returning");
		logger.debug("getEnemySpell1() - Returning: {}", enemySpell1);
		return enemySpell1;
	}

	public void setEnemySpell1(SummonerSpell spell) {
		logger.trace("setEnemySpell1() - Entering");
		logger.debug("setEnemySpell1() - Parameter: {}", spell);
		this.enemySpell1 = spell;
		logger.trace("setEnemySpell1() - Leaving");
	}

	public SummonerSpell getEnemySpell2() {
		logger.trace("getEnemySpell2() - Entering");
		logger.trace("getEnemySpell2() - Returning");
		logger.debug("getEnemySpell2() - Returning: {}", enemySpell2);
		return enemySpell2;
	}

	public void setEnemySpell2(SummonerSpell spell) {
		logger.trace("setEnemySpell2() - Entering");
		logger.debug("setEnemySpell2() - Parameter: {}", spell);
		this.enemySpell2 = spell;
		logger.trace("setEnemySpell2() - Leaving");
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
		Date d = getGame().getDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(d);
		logger.trace("toString() - Returning");
		logger.debug("toString() - Returning: {}", str);
		return str;
	}
}
