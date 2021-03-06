package de.phyberapex.diaryoflegends.model.util;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.Role;

/**
 * This class is used to save and retrive matchups
 * 
 * @author Janis Walliser <walliser.janis@gmx.de>
 * 
 */
public class MatchupUtil {

	private static ObjectContainer dbHandle = Config.getInstance()
			.getDBHandle();
	transient private static Logger logger = LogManager
			.getLogger(ItemUtil.class.getName());

	/**
	 * Return all items currently stored in the database
	 * 
	 * @return {@link List<Item>} A list containing all items in the database
	 */
	public static List<Matchup> getAllMatchups() {
		logger.trace("getAllMatchups() - Entering");
		ObjectSet<Matchup> returnValue = dbHandle
				.query(new Predicate<Matchup>() {

					private static final long serialVersionUID = -6535736734146443615L;

					@Override
					public boolean match(Matchup arg0) {
						return true;
					}
				});
		logger.trace("getAllMatchups() - Returning");
		logger.debug("getAllMatchups() - Returning: {}", returnValue);
		return returnValue;
	}

	/**
	 * Saves the matchup given in the database
	 * 
	 * @param {@link Matchup matchup}
	 */
	public static void saveMatchup(Matchup matchup) {
		logger.trace("saveMatchup() - Entering");
		logger.debug("saveMatchup() - Parameter {}", matchup);
		dbHandle.store(matchup);
		logger.trace("saveMatchup() - Leaving");
	}

	/**
	 * Saves the item given in the database
	 * 
	 * @param {@link Matchup matchup}
	 */
	public static void deleteMatchup(Matchup matchup) {
		logger.trace("deleteMatchup() - Entering");
		logger.debug("deleteMatchup() - Parameter {}", matchup);
		dbHandle.delete(matchup);
		if (matchup.getGame() != null) {
			matchup.getGame().setMatchup(null);
			GameUtil.deleteGame(matchup.getGame());
		}
		logger.trace("deleteMatchup() - Leaving");
	}

	/**
	 * @param lane
	 * @return
	 */
	public static List<Matchup> getMatchupsByLane(final Role lane) {
		logger.trace("getMatchupsByLane() - Entering");
		ObjectSet<Matchup> returnValue = dbHandle
				.query(new Predicate<Matchup>() {

					private static final long serialVersionUID = -6535736734146443615L;

					@Override
					public boolean match(Matchup arg0) {
						if (arg0.getLane() == lane) {
							return true;
						}
						return false;
					}
				});
		logger.trace("getMatchupsByLane() - Returning");
		logger.debug("getMatchupsByLane() - Returning: {}", returnValue);
		return returnValue;
	}
}
