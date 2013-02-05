package de.phyberapex.diaryoflegends.model.util;

import java.util.Iterator;

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
	private static Logger logger = LogManager.getLogger(GameUtil.class
			.getName());

	/**
	 * Return all champions currently stored in the database
	 * 
	 * @return {@link Iterator} Iterator for all champions in the database
	 */
	public static Iterator<Champion> getAllGames() {
		logger.trace("getAllGames() - Entering");
		Iterator<Champion> returnValue;
		ObjectSet<Champion> set = dbHandle.query(new Predicate<Champion>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Champion arg0) {
				return true;
			}
		});
		returnValue = set.iterator();
		logger.trace("getAllGames() - Returning");
		logger.debug("Return {}", returnValue);
		return returnValue;
	}

	/**
	 * Saves the champion given in the database
	 * 
	 * @param {@link Champion} champ
	 */
	public static void saveChampion(Champion champ) {
		logger.trace("saveChampion() - Entering");
		logger.debug("Parameter {}", champ);
		dbHandle.store(champ);
		logger.trace("saveChampion() - Leaving");
	}

}