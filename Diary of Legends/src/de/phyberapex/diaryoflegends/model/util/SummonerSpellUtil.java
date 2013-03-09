package de.phyberapex.diaryoflegends.model.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.SummonerSpell;

/**
 * This class is used to save and retrive summoner spells
 * 
 * @author Janis Walliser <walliser.janis@gmx.de>
 * 
 */
public class SummonerSpellUtil {

	private static ObjectContainer dbHandle = Config.getInstance()
			.getDBHandle();
	// Fix as there is no SummonerSpell API
	transient private static Logger logger = LogManager
			.getLogger(SummonerSpellUtil.class.getName());

	/**
	 * Return all summoner spells currently stored in the database
	 * 
	 * @return {@link Iterator} Iterator for all games in the database
	 */
	public static List<SummonerSpell> getAllSpells() {
		logger.trace("getAllSpells() - Entering");
		List<SummonerSpell> returnValue = dbHandle
				.query(new Predicate<SummonerSpell>() {

					private static final long serialVersionUID = -6535736734146443615L;

					@Override
					public boolean match(SummonerSpell arg0) {
						return true;
					}
				});
		if (returnValue.size() == 0) {
			returnValue = createSummoners();
		}
		logger.trace("getAllSpells() - Returning");
		logger.debug("getAllSpells {}", returnValue);
		return returnValue;
	}

	/**
	 * @return
	 */
	private static List<SummonerSpell> createSummoners() {
		List<SummonerSpell> tmp = new ArrayList<>();
		SummonerSpell newSpell = new SummonerSpell(1, "Cleanse");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(2, "Clairvoyance");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(3, "Exhaust");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(4, "Flash");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(6, "Ghost");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(7, "Heal");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(10, "Revive");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(11, "Smite");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(12, "Teleport");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(13, "Clearity");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(14, "Ignite");
		tmp.add(newSpell);
		saveSpell(newSpell);
		newSpell = new SummonerSpell(21, "Barrier");
		tmp.add(newSpell);
		saveSpell(newSpell);
		return tmp;
	}

	/**
	 * Saves the spell given in the database
	 * 
	 * @param {@link SummonerSpell} spell
	 */
	public static void saveSpell(SummonerSpell spell) {
		logger.trace("saveSpell() - Entering");
		logger.debug("saveSpell() - Parameter {}", spell);
		dbHandle.store(spell);
		logger.trace("saveSpell() - Leaving");
	}

	/**
	 * Saves the game given in the database
	 * 
	 * @param {@link SummonerSpell} spell
	 */
	public static void deleteSpell(SummonerSpell spell) {
		logger.trace("deleteItem() - Entering");
		logger.debug("deleteItem() - Parameter {}", spell);
		dbHandle.delete(spell);
		logger.trace("deleteItem() - Leaving");
	}

	/**
	 * Returns the {@link SummonerSpell} with the given id or if not found<br>
	 * null
	 * 
	 * @param id
	 *            {@link int}
	 * @return {@link SummonerSpell} or null
	 */
	public static SummonerSpell getSpellById(final int id) {
		logger.trace("getSpellById() - Entering");
		logger.debug("getSpellById() - Parameter: {}", id);
		SummonerSpell returnValue = null;
		ObjectSet<SummonerSpell> set = dbHandle
				.query(new Predicate<SummonerSpell>() {

					private static final long serialVersionUID = -6535736734146443615L;

					@Override
					public boolean match(SummonerSpell arg0) {
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
		logger.trace("getSpellById() - Returning");
		logger.debug("getSpellById() - Returning {}", returnValue);
		return returnValue;
	}

}
