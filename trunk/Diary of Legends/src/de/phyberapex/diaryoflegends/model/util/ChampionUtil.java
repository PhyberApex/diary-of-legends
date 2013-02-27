package de.phyberapex.diaryoflegends.model.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.GameStatistic;
import de.phyberapex.diaryoflegends.model.Game;

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
		ObjectSet<Champion> returnValue = dbHandle
				.query(new Predicate<Champion>() {

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
		ObjectSet<Champion> returnValue = dbHandle
				.query(new Predicate<Champion>() {

					private static final long serialVersionUID = -6535736734146443615L;

					@Override
					public boolean match(Champion arg0) {
						if (arg0.getName().toLowerCase()
								.contains(text.toLowerCase())) {
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
		logger.debug("getChampionById() - Returning: {}", returnValue);
		return returnValue;
	}

	/**
	 * @return
	 */
	public static HashMap<String, GameStatistic> getMostXXXStat(
			final String search) {
		logger.trace("getMostXXXStat() - Entering");
		logger.debug("getMostXXXStat() - Parameter: {}", search);
		HashMap<String, GameStatistic> returnValue = new HashMap<>();
		Champion chmpCS = new Champion(0, "no champion found", null);
		int cs = 0;
		Champion chmpKills = new Champion(0, "no champion found", null);
		int kills = 0;
		Champion chmpAssists = new Champion(0, "no champion found", null);
		int assists = 0;
		Champion chmpDeaths = new Champion(0, "no champion found", null);
		int deaths = 0;
		HashMap<Champion, Integer[]> avgCS = new HashMap<>();
		ObjectSet<Game> set = dbHandle.query(new Predicate<Game>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Game arg0) {
				for (String s : search.split(";")) {
					if (arg0.getMatchup().getMyChamp().getName().contains((s))) {
						return true;
					}
				}
				return false;
			}
		});
		for (Game g : set) {
			if (g.getOwnCS() > cs) {
				cs = g.getOwnCS();
				chmpCS = g.getMatchup().getMyChamp();
			}
			if (g.getOwnKills() > kills) {
				kills = g.getOwnKills();
				chmpKills = g.getMatchup().getMyChamp();
			}
			if (g.getOwnAssists() > assists) {
				assists = g.getOwnAssists();
				chmpAssists = g.getMatchup().getMyChamp();
			}
			if (g.getOwnDeaths() > deaths) {
				deaths = g.getOwnDeaths();
				chmpDeaths = g.getMatchup().getMyChamp();
			}
			if (avgCS.containsKey(g.getMatchup().getMyChamp())) {
				Integer[] i = avgCS.get(g.getMatchup().getMyChamp());
				i[0]++;
				i[1] = i[1] + g.getOwnKills();
				i[2] = i[2] + g.getOwnDeaths();
				i[3] = i[3] + g.getOwnAssists();
				i[4] = i[4] + g.getOwnCS();

			} else {
				avgCS.put(
						g.getMatchup().getMyChamp(),
						new Integer[] { 1, g.getOwnKills(), g.getOwnDeaths(),
								g.getOwnAssists(), g.getOwnCS() });
			}
		}
		returnValue.put("mostCS", new GameStatistic(chmpCS, cs));
		returnValue.put("mostKills", new GameStatistic(chmpKills, kills));
		returnValue.put("mostAssists", new GameStatistic(chmpAssists, assists));
		returnValue.put("mostDeaths", new GameStatistic(chmpDeaths, deaths));
		Champion chmpAvgKills = new Champion(0, "no champion found", null);
		Integer avgKillsValue = 0;
		Integer killsTotal = 0;
		Champion chmpAvgDeaths = new Champion(0, "no champion found", null);
		Integer avgDeathsValue = 0;
		Integer deathsTotal = 0;
		Champion chmpAvgAssists = new Champion(0, "no champion found", null);
		Integer avgAssistsValue = 0;
		Integer assistsTotal = 0;
		Champion chmpAvgCS = new Champion(0, "no champion found", null);
		Integer avgCsValue = 0;
		Integer minionsSlain = 0;
		Champion chmpGames = new Champion(0, "no champion found", null);
		Integer gamesValue = 0;
		Integer gamesTotal = 0;
		ArrayList<Champion> list = new ArrayList<>();
		for (Entry<Champion, Integer[]> e : avgCS.entrySet()) {
			list.add(e.getKey());
			gamesTotal += e.getValue()[0];
			killsTotal += e.getValue()[1];
			deathsTotal += e.getValue()[2];
			assistsTotal += e.getValue()[3];
			minionsSlain += e.getValue()[4];
			if (e.getValue()[0] > gamesValue) {
				gamesValue = e.getValue()[0];
				chmpGames = e.getKey();
			}
			if (e.getValue()[1] / e.getValue()[0] > avgKillsValue) {
				avgKillsValue = e.getValue()[1] / e.getValue()[0];
				chmpAvgKills = e.getKey();
			}
			if (e.getValue()[2] / e.getValue()[0] > avgDeathsValue) {
				avgDeathsValue = e.getValue()[2] / e.getValue()[0];
				chmpAvgDeaths = e.getKey();
			}
			if (e.getValue()[3] / e.getValue()[0] > avgAssistsValue) {
				avgAssistsValue = e.getValue()[3] / e.getValue()[0];
				chmpAvgAssists = e.getKey();
			}
			if (e.getValue()[4] / e.getValue()[0] > avgCsValue) {
				avgCsValue = e.getValue()[4] / e.getValue()[0];
				chmpAvgCS = e.getKey();
			}
		}
		returnValue.put("mostAvgKills", new GameStatistic(chmpAvgKills,
				avgKillsValue));
		returnValue.put("killsTotal", new GameStatistic(null, killsTotal));
		returnValue.put("mostAvgDeaths", new GameStatistic(chmpAvgDeaths,
				avgDeathsValue));
		returnValue.put("deathsTotal", new GameStatistic(null, deathsTotal));
		returnValue.put("mostAvgAssists", new GameStatistic(chmpAvgAssists,
				avgAssistsValue));
		returnValue.put("assistsTotal", new GameStatistic(null, assistsTotal));
		returnValue.put("mostAvgCS", new GameStatistic(chmpAvgCS, avgCsValue));
		returnValue.put("minionsSlain", new GameStatistic(null, minionsSlain));
		returnValue.put("mostGames", new GameStatistic(chmpGames, gamesValue));
		returnValue.put("gamesTotal", new GameStatistic(null, gamesTotal));
		GameStatistic stat = new GameStatistic(null, avgCS.size());
		stat.setFoundChamps(list);
		returnValue.put("champsFound", stat);
		logger.trace("getMostXXXStat() - Returning");
		logger.debug("getMostXXXStat() - Returning {}", returnValue);
		return returnValue;
	}
}
