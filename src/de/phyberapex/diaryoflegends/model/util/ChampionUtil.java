package de.phyberapex.diaryoflegends.model.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.stats.GameStatistic;
import de.phyberapex.diaryoflegends.model.stats.Top3EnemyStatistics;
import de.phyberapex.diaryoflegends.view.MainView;

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
		ObjectSet<Champion> returnValue = dbHandle.query(
				new Predicate<Champion>() {

					private static final long serialVersionUID = -6535736734146443615L;

					@Override
					public boolean match(Champion arg0) {
						return true;
					}
				}, new Comparator<Champion>() {

					@Override
					public int compare(Champion o1, Champion o2) {
						return o1.getName().compareTo(o2.getName());
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
	public static HashMap<String, GameStatistic> getGameStats(
			final String search) {
		logger.trace("getGameStats() - Entering");
		logger.debug("getGameStats() - Parameter: {}", search);
		HashMap<String, GameStatistic> returnValue = new HashMap<>();
		Champion chmpCS = new Champion(0, "no champion found");
		int cs = 0;
		Champion chmpKills = new Champion(0, "no champion found");
		int kills = 0;
		Champion chmpAssists = new Champion(0, "no champion found");
		int assists = 0;
		Champion chmpDeaths = new Champion(0, "no champion found");
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
		Champion chmpAvgKills = new Champion(0, "no champion found");
		Integer avgKillsValue = 0;
		Integer killsTotal = 0;
		Champion chmpAvgDeaths = new Champion(0, "no champion found");
		Integer avgDeathsValue = 0;
		Integer deathsTotal = 0;
		Champion chmpAvgAssists = new Champion(0, "no champion found");
		Integer avgAssistsValue = 0;
		Integer assistsTotal = 0;
		Champion chmpAvgCS = new Champion(0, "no champion found");
		Integer avgCsValue = 0;
		Integer minionsSlain = 0;
		Champion chmpGames = new Champion(0, "no champion found");
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
		logger.trace("getGameStats() - Returning");
		logger.debug("getGameStats() - Returning {}", returnValue);
		return returnValue;
	}

	/**
	 * @param parameter
	 * @return
	 */
	public static Top3EnemyStatistics getTop3Enemies(final String parameter) {
		logger.trace("getTop3Enemies() - Entering");
		logger.debug("getTop3Enemies() - Parameter: {}", parameter);
		ObjectSet<Matchup> set = dbHandle.query(new Predicate<Matchup>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Matchup arg0) {
				for (String s : parameter.split(";")) {
					if (arg0.getMyChamp().getName().contains((s))) {
						return true;
					}
				}
				return false;
			}
		});
		HashMap<Champion, String> myChamps = new HashMap<>();
		HashMap<Champion, double[]> champsWithDiff = new HashMap<>();
		while (set.hasNext()) {
			Matchup matchup = set.next();
			myChamps.put(matchup.getMyChamp(), "");
			logger.debug("Current own champion {} added to list",
					matchup.getMyChamp());
			if (champsWithDiff.containsKey(matchup.getEnemyChamp())) {
				logger.debug(
						"Enemy champion {} already in list calculating new avg difficulty",
						matchup.getEnemyChamp());
				double currentDifficulty = champsWithDiff.get(matchup
						.getEnemyChamp())[0];
				logger.debug("Current avg difficulty of this champ {}",
						currentDifficulty);
				double matches = champsWithDiff.get(matchup.getEnemyChamp())[1];
				logger.debug("Current amount of matches of this champ {}",
						matches);
				currentDifficulty = (currentDifficulty * matches + matchup
						.getDifficulty().getValue()) / ++matches;
				logger.debug("New difficulty of this champ {}",
						currentDifficulty);
				logger.debug("New amount of matches of this champ {}", matches);
				champsWithDiff.put(matchup.getEnemyChamp(), new double[] {
						currentDifficulty, matches });
			} else {
				logger.debug(
						"Enemy champion {} not in list adding with 1 match and difficulty {}",
						matchup.getEnemyChamp(), matchup.getDifficulty()
								.getValue());
				champsWithDiff.put(matchup.getEnemyChamp(), new double[] {
						matchup.getDifficulty().getValue(), 1 });
			}
		}
		List<Champion> top3 = new ArrayList<>();
		for (Entry<Champion, double[]> e : champsWithDiff.entrySet()) {
			if (top3.size() < 3) {
				logger.debug("Top3 smaller then 3");
				if (top3.size() < 2) {
					logger.debug("Top3 smaller then 2");
					if (top3.size() < 1) {
						logger.debug("Top3 smaller then 1 adding");
						top3.add(e.getKey());
					} else {
						if (champsWithDiff.get(top3.get(0))[0] < e.getValue()[0]) {
							logger.debug("new champions difficulty is higher then the one of currently number 1. Switching places");
							top3.add(top3.get(0));
							top3.set(0, e.getKey());
						} else {
							logger.debug("new champions difficulty is lower then the one of currently number 1. Adding");
							top3.add(e.getKey());
						}
					}
				} else {
					if (champsWithDiff.get(top3.get(1))[0] < e.getValue()[0]) {
						logger.debug("new champions difficulty is higher then the one of currently number 2. Adding number 2 as number 3");
						top3.add(top3.get(1));
						if (champsWithDiff.get(top3.get(0))[0] < e.getValue()[0]) {
							logger.debug("new champions difficulty is higher then the one of currently number 1. Setting currently number 1 as number 2 and the new one as number 1");
							top3.set(1, top3.get(0));
							top3.set(0, e.getKey());
						} else {
							logger.debug("new champions difficulty is lower then the one of currently number 1. Setting the new one as 2");
							top3.set(1, e.getKey());
						}
					} else {
						logger.debug("new champions difficulty is lower then the one of currently number 2. Adding");
						top3.add(e.getKey());
					}
				}
			} else if (e.getValue()[0] > champsWithDiff.get(top3.get(2))[0]) {
				logger.debug("new champions difficulty is higher then the one of currently number 3");
				if (e.getValue()[0] > champsWithDiff.get(top3.get(1))[0]) {
					logger.debug("new champions difficulty is higher then the one of currently number 2");
					if (e.getValue()[0] > champsWithDiff.get(top3.get(0))[0]) {
						logger.debug("new champions difficulty is higher then the one of currently number 1. Setting number 2 as 3, 1 as 2 and the new one as 1");
						top3.set(2, top3.get(1));
						top3.set(1, top3.get(0));
						top3.set(0, e.getKey());
					} else {
						logger.debug("new champions difficulty is lower then the one of currently number 1. Setting number 2 as 3 and the new one as 2");
						top3.set(2, top3.get(1));
						top3.set(1, e.getKey());
					}
				} else {
					logger.debug("new champions difficulty is lower then the one of currently number 2. Setting the new one as 3");
					top3.set(2, e.getKey());
				}
			}
		}
		List<Champion> myChampsList = new ArrayList<>();
		for (Entry<Champion, String> e : myChamps.entrySet()) {
			myChampsList.add(e.getKey());
		}
		Top3EnemyStatistics returnValue = null;
		if (top3.size() == 3) {
			returnValue = new Top3EnemyStatistics(myChampsList, top3.get(0),
					champsWithDiff.get(top3.get(0))[0], top3.get(1),
					champsWithDiff.get(top3.get(1))[0], top3.get(2),
					champsWithDiff.get(top3.get(2))[0]);
		}
		logger.trace("getTop3Enemies() - Returning");
		logger.debug("getTop3Enemies() - Returning {}", returnValue);
		return returnValue;
	}

	/**
	 * @param parameter
	 * @return
	 */
	public static List<Top3EnemyStatistics> getTop3EnemiesPerChamp(
			final String parameter) {
		logger.trace("getTop3EnemiesPerChamp() - Entering");
		logger.debug("getTop3EnemiesPerChamp() - Parameter: {}", parameter);
		ObjectSet<Matchup> set = dbHandle.query(new Predicate<Matchup>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Matchup arg0) {
				for (String s : parameter.split(";")) {
					if (arg0.getMyChamp().getName().contains((s))) {
						return true;
					}
				}
				return false;
			}
		});
		List<Top3EnemyStatistics> returnValue = new ArrayList<>();
		Set<Champion> champs = new HashSet<Champion>();
		while (set.hasNext()) {
			champs.add(set.next().getMyChamp());
		}
		MainView.getInstance().setStatusText(
				"Found " + champs.size() + " champions");
		for (Champion e : champs) {
			MainView.getInstance().setStatusText(
					"Calculating stats for " + e.getName());
			Top3EnemyStatistics tmp = getTop3Enemies(e.getName());
			if (tmp != null) {
				returnValue.add(tmp);
			}
		}
		logger.trace("getTop3EnemiesPerChamp() - Returning");
		logger.debug("getTop3EnemiesPerChamp() - Returning {}", returnValue);
		return returnValue;
	}
}
