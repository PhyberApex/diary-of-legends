package de.phyberapex.diaryoflegends.model.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.Game;

/**
 * This class is used to save and retrive games
 * 
 * @author Janis Walliser <walliser.janis@gmx.de>
 * 
 */
public class GameUtil {

	private static ObjectContainer dbHandle = Config.getInstance()
			.getDBHandle();
	private static Logger logger = LogManager.getLogger(GameUtil.class
			.getName());

	/**
	 * Return all games currently stored in the database
	 * 
	 * @return {@link Iterator} Iterator for all games in the database
	 */
	public static List<Game> getAllGames() {
		logger.trace("getAllGames() - Entering");
		List<Game> returnValue = new ArrayList<Game>();
		ObjectSet<Game> set = dbHandle.query(new Predicate<Game>() {

			private static final long serialVersionUID = 1625150373402614414L;

			@Override
			public boolean match(Game arg0) {
				return true;
			}
		});
		while (set.iterator().hasNext()) {
			returnValue.add(set.iterator().next());
		}
		logger.trace("getAllGames() - Returning");
		logger.debug("Return {}", returnValue);
		return returnValue;
	}

	/**
	 * Saves the game given in the database
	 * 
	 * @param {@link Game} game
	 */
	public static void saveGame(Game game) {
		logger.trace("saveGame() - Entering");
		logger.debug("Parameter {}", game);
		dbHandle.store(game);
		logger.trace("getAllGames() - Leaving");
	}

}
