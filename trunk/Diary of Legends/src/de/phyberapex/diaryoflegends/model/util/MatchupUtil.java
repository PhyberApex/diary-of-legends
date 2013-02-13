package de.phyberapex.diaryoflegends.model.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.Matchup;

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
		List<Matchup> returnValue = new ArrayList<Matchup>();
		ObjectSet<Matchup> set = dbHandle.query(new Predicate<Matchup>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Matchup arg0) {
				return true;
			}
		});
		Iterator<Matchup> i = set.iterator();
		while (i.hasNext()) {
			returnValue.add(i.next());
		}
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
		logger.trace("deleteMatchup() - Leaving");
	}
}
