package de.phyberapex.diaryoflegends.model.util;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.Champion;

/**
 * This class is used to save and retrive champions
 * 
 * @author Janis Walliser <walliser.janis@gmx.de>
 * 
 */
public class ChampionUtil {

	private static ObjectContainer dbHandle = Config.getInstance()
			.getDBHandle();
	transient private static Logger logger = LogManager
			.getLogger(GameUtil.class.getName());

	/**
	 * Return all champions currently stored in the database
	 * 
	 * @return {@link List<Champion>} A list containing all champions in the
	 *         database
	 */
	public static List<Champion> getAllChampions() {
		logger.trace("getAllChampions() - Entering");
		ObjectSet<Champion> returnValue = dbHandle.query(new Predicate<Champion>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Champion arg0) {
				return true;
			}
		});
		logger.trace("getAllChampions() - Returning");
		logger.debug("getAllChampions() - Returning {}", returnValue);
		return returnValue;
	}

	/**
	 * Saves the champion given in the database
	 * 
	 * @param {@link Champion} champ
	 */
	public static void saveChampion(Champion champ) {
		logger.trace("saveChampion() - Entering");
		logger.debug("saveChampion() - Parameter {}", champ);
		dbHandle.store(champ);
		logger.trace("saveChampion() - Leaving");
	}

	/**
	 * Saves the champion given in the database
	 * 
	 * @param {@link Champion} champ
	 */
	public static void deleteChampion(Champion champ) {
		logger.trace("deleteChampion() - Entering");
		logger.debug("deleteChampion() - Parameter {}", champ);
		dbHandle.delete(champ);
		logger.trace("deleteChampion() - Leaving");
	}

	/**
	 * Returns a {@link List} with champions that have the given string in their
	 * name
	 * 
	 * @param text
	 *            {@link String}
	 * @return {@link List<Champion>}
	 */
	public static List<Champion> searchChampionByName(final String text) {
		logger.trace("searchChampionByName() - Entering");
		logger.debug("searchChampionByName() - Parameter: {}", text);
		ObjectSet<Champion> returnValue = dbHandle.query(new Predicate<Champion>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Champion arg0) {
				if (arg0.getName().toLowerCase().contains(text.toLowerCase())) {
					return true;
				} else {
					return false;
				}
			}
		});
		logger.trace("searchChampionByName() - Returning");
		logger.debug("searchChampionByName() - Returning {}", returnValue);
		return returnValue;
	}

	/**
	 * Returns the {@link Champion} with the given id or if not found<br>
	 * null
	 * 
	 * @param id
	 *            {@link int}
	 * @return {@link Champion} or null
	 */
	public static Champion getChampionById(final int id) {
		logger.trace("getChampionById() - Entering");
		logger.debug("getChampionById() - Parameter: {}", id);
		Champion returnValue = null;
		ObjectSet<Champion> set = dbHandle.query(new Predicate<Champion>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Champion arg0) {
				if (arg0.getId() == id) {
					return true;
				} else {
					return false;
				}
			}
		});
		if (set.hasNext()) {
			returnValue = set.next();
		}
		logger.trace("getChampionById() - Returning");
		logger.debug("getChampionById() - Returning {}", returnValue);
		return returnValue;
	}
}
