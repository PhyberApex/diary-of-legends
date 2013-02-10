package de.phyberapex.diaryoflegends.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Summoner extends Model {

	private String name;
	private List<Game> games = new ArrayList<Game>();
	transient private static Logger logger = LogManager.getLogger(Summoner.class
			.getName());

	public Summoner(String name, List<Game> games){
		logger.trace("Summoner() - Entering");
		logger.debug("Parameter {}, {}", name, games);
		this.name = name;
		this.name = name;
		this.games = games;
		logger.trace("Summoner() - Leaving");
	}
	
	/**
	 * Returns the name of the summoner
	 * @return {@link String}
	 */
	public String getName() {
		logger.trace("getName() - Entering");
		logger.trace("getName() - Returning");
		logger.debug("Returned {}", name);
		return name;
	}

	/**
	 * Sets the name of the summoner
	 * @param name {@link String} new name to set
	 */
	public void setName(String name) {
		logger.trace("setName() - Entering");
		logger.debug("Parameter {}", name);
		this.name = name;
		logger.trace("setName() - Leaving");
	}

	/**
	 * Returns a {@link List} with all the games of the summoner
	 * @return {@link List}
	 */
	public List<Game> getGames() {
		logger.trace("getGames() - Entering");
		logger.trace("getGames() - Returning");
		logger.debug("Returned {}", games);
		return games;
	}

	/**
	 * Sets the games of the summoner
	 * @param games {@link List} the new list of games
	 */
	public void setGames(List<Game> games) {
		logger.trace("setGames() - Entering");
		logger.debug("Parameter {}", games);
		this.games = games;
		logger.trace("setGames() - Leaving");
	}
	
	/**
	 * Adds a new game to the Summoner
	 * @param game {@link Game} the game to add
	 */
	public void addGame(Game game){
		logger.trace("addGame() - Entering");
		logger.debug("Parameter {}", game);
		this.games.add(game);
		logger.trace("addGame() - Leaving");
	}

}
