package de.phyberapex.diaryoflegends.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import de.phyberapex.diaryoflegends.exception.ChampionNotFoundException;
import de.phyberapex.diaryoflegends.exception.ItemNotFoundException;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.GameResult;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupDifficulty;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.model.Role;
import de.phyberapex.diaryoflegends.model.SummonerSpell;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.GameUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;
import de.phyberapex.diaryoflegends.model.util.SummonerSpellUtil;
import de.phyberapex.diaryoflegends.view.MainView;

public class ImportDolexAction implements Runnable {

	private File file;
	private ImportDolexDouble doWithDouble = ImportDolexDouble.NONE;
	private static Logger logger = LogManager.getLogger(ImportDolexAction.class
			.getName());

	public ImportDolexAction(File file) {
		this.file = file;
	}

	private void doImport(File importFile) {
		logger.trace("doImport() - Entering");
		logger.debug("doImport() - Parameter: {}", importFile);
		MainView.getInstance().setStatusText("Importing...");
		FileInputStream fstream;
		BufferedReader br;
		List<Champion> allChampions = ChampionUtil.getAllChampions();
		List<Item> allItems = ItemUtil.getAllItems();
		try {
			fstream = new FileInputStream(importFile);
			br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			String imp = "";
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				imp += strLine;
			}
			br.close();
			fstream.close();
			JSONArray gArray = new JSONArray(imp);
			for (int i = 0; i < gArray.length(); i++) {
				MainView.getInstance().setStatusText(
						"Importing game " + (int) (i + 1) + " of "
								+ gArray.length() + "...");
				JSONObject gJo = gArray.getJSONObject(i);

				Game game = new Game();
				Date date = new Date(gJo.getLong("date"));
				game.setDate(date);
				List<Champion> teams = new ArrayList<Champion>();
				JSONArray myTeamArray = gJo.getJSONArray("myTeam");
				Champion chmp;
				for (int j = 0; j < myTeamArray.length(); j++) {
					chmp = null;
					Iterator<Champion> it = allChampions.iterator();
					while (it.hasNext()) {
						Champion tmp = it.next();
						if (tmp.getId() == myTeamArray.getInt(j)) {
							chmp = tmp;
							break;
						}
					}
					if (chmp == null) {
						logger.error("Championt with id {} not found",
								myTeamArray.getInt(j));
						throw new ChampionNotFoundException(
								"Champion not found. Maybe your champions are outdated.");
					} else {
						teams.add(chmp);
					}
				}
				game.setMyTeam(teams);
				teams = new ArrayList<Champion>();
				JSONArray enemyTeamArray = gJo.getJSONArray("enemyTeam");
				for (int j = 0; j < enemyTeamArray.length(); j++) {
					chmp = null;
					Iterator<Champion> it = allChampions.iterator();
					while (it.hasNext()) {
						Champion tmp = it.next();
						if (tmp.getId() == enemyTeamArray.getInt(j)) {
							chmp = tmp;
							break;
						}
					}
					if (chmp == null) {
						logger.error("Champion with id {} not found",
								enemyTeamArray.getInt(j));
						throw new ChampionNotFoundException(
								"Champion not found. Maybe your champions are outdated.");
					} else {
						teams.add(chmp);
					}
				}
				game.setEnemyTeam(teams);
				Matchup matchup = new Matchup();
				matchup.setGame(game);
				JSONObject mJo = gJo.getJSONObject("matchup");
				chmp = null;
				Iterator<Champion> chmpit = allChampions.iterator();
				while (chmpit.hasNext()) {
					Champion tmp = chmpit.next();
					if (tmp.getId() == mJo.getInt("myChampion")) {
						chmp = tmp;
						break;
					}
				}
				if (!game.getMyTeam().contains(chmp)) {
					logger.error("Champion with id {} not found in your team",
							mJo.getInt("myChampion"));
					throw new ChampionNotFoundException(
							"You champion is not in your team.");
				}
				matchup.setMyChamp(chmp);
				JSONArray myStartItemsArray = mJo.getJSONArray("myStartItems");
				List<MatchupItem> matchupItems = new ArrayList<MatchupItem>();
				Item item = null;
				for (int j = 0; j < myStartItemsArray.length(); j++) {
					JSONObject maItJo = myStartItemsArray.getJSONObject(j);
					Iterator<Item> it = allItems.iterator();
					while (it.hasNext()) {
						Item tmp = it.next();
						if (tmp.getId() == maItJo.getInt("item")) {
							item = tmp;
							break;
						}
					}
					if (item == null) {
						logger.error("Item with id {} not found",
								maItJo.getInt("item"));
						throw new ItemNotFoundException(
								"Item not found. Maybe your champions are outdated.");
					} else {
						matchupItems.add(new MatchupItem(item, maItJo
								.getInt("amount")));
					}
				}
				matchup.setMyStartItems(matchupItems);
				JSONArray myEndItemsArray = mJo.getJSONArray("myEndItems");
				matchupItems = new ArrayList<MatchupItem>();
				item = null;
				for (int j = 0; j < myEndItemsArray.length(); j++) {
					JSONObject maItJo = myEndItemsArray.getJSONObject(j);
					Iterator<Item> it = allItems.iterator();
					while (it.hasNext()) {
						Item tmp = it.next();
						if (tmp.getId() == maItJo.getInt("item")) {
							item = tmp;
							break;
						}
					}
					if (item == null) {
						logger.error("Item with id {} not found",
								maItJo.getInt("item"));
						throw new ItemNotFoundException(
								"Item not found. Maybe your champions are outdated.");
					} else {
						matchupItems.add(new MatchupItem(item, maItJo
								.getInt("amount")));
					}
				}
				matchup.setMyEndItems(matchupItems);

				SummonerSpell spell1 = SummonerSpellUtil.getSpellById(mJo
						.optInt("mySpell1"));
				if (spell1 == null) {
					logger.error("Summoner spell with id {} not found",
							mJo.optInt("mySpell1"));
				}
				matchup.setMySpell1(spell1);
				SummonerSpell spell2 = SummonerSpellUtil.getSpellById(mJo
						.optInt("mySpell2"));
				if (spell2 == null) {
					logger.error("Summoner spell with id {} not found",
							mJo.optInt("mySpell2"));
				}
				matchup.setMySpell2(spell2);
				chmp = null;
				Iterator<Champion> chmspit = allChampions.iterator();
				while (chmspit.hasNext()) {
					Champion tmp = chmspit.next();
					if (tmp.getId() == mJo.getInt("enemyChampion")) {
						chmp = tmp;
						break;
					}
				}
				if (!game.getEnemyTeam().contains(chmp)) {
					logger.error("Champion with id {} not found in enemy team",
							mJo.getInt("enemyChampion"));
					throw new ChampionNotFoundException(
							"Enemy champion is not in enemy team.");
				}
				matchup.setEnemyChamp(chmp);
				JSONArray enemyStartItemsArray = mJo
						.getJSONArray("enemyStartItems");
				matchupItems = new ArrayList<MatchupItem>();
				item = null;
				for (int j = 0; j < enemyStartItemsArray.length(); j++) {
					JSONObject maItJo = enemyStartItemsArray.getJSONObject(j);
					Iterator<Item> it = allItems.iterator();
					while (it.hasNext()) {
						Item tmp = it.next();
						if (tmp.getId() == maItJo.getInt("item")) {
							item = tmp;
							break;
						}
					}
					if (item == null) {
						logger.error("Item with id {} not found",
								maItJo.getInt("item"));
						throw new ItemNotFoundException(
								"Item not found. Maybe your champions are outdated.");
					} else {
						matchupItems.add(new MatchupItem(item, maItJo
								.getInt("amount")));
					}
				}
				matchup.setEnemyStartItems(matchupItems);
				JSONArray enemyEndItemsArray = mJo
						.getJSONArray("enemyEndItems");
				matchupItems = new ArrayList<MatchupItem>();
				item = null;
				for (int j = 0; j < enemyEndItemsArray.length(); j++) {
					JSONObject maItJo = enemyEndItemsArray.getJSONObject(j);
					Iterator<Item> it = allItems.iterator();
					while (it.hasNext()) {
						Item tmp = it.next();
						if (tmp.getId() == maItJo.getInt("item")) {
							item = tmp;
							break;
						}
					}
					if (item == null) {
						logger.error("Item with id {} not found",
								maItJo.getInt("item"));
						throw new ItemNotFoundException(
								"Item not found. Maybe your champions are outdated.");
					} else {
						matchupItems.add(new MatchupItem(item, maItJo
								.getInt("amount")));
					}
				}
				matchup.setEnemyEndItems(matchupItems);
				SummonerSpell enemySpell1 = SummonerSpellUtil.getSpellById(mJo
						.optInt("enemySpell1"));
				if (enemySpell1 == null) {
					logger.error("Summoner spell with id {} not found",
							mJo.optInt("enemySpell1"));
				}
				matchup.setEnemySpell1(enemySpell1);
				SummonerSpell enemySpell2 = SummonerSpellUtil.getSpellById(mJo
						.optInt("enemySpell2"));
				if (enemySpell2 == null) {
					logger.error("Summoner spell with id {} not found",
							mJo.optInt("enemySpell2"));
				}
				matchup.setEnemySpell2(enemySpell2);
				matchup.setResult(MatchupResult.valueOf(mJo
						.getString("matchupResult")));
				matchup.setLane(Role.valueOf(mJo.getString("lane")));
				matchup.setDifficulty(MatchupDifficulty.valueOf(mJo
						.getString("difficulty")));
				matchup.setNotes(mJo.getString("matchupNotes"));
				game.setMatchup(matchup);
				game.setNotes(gJo.getString("gameNotes"));
				game.setOwnKills(gJo.getInt("ownKills"));
				game.setOwnDeaths(gJo.getInt("ownDeaths"));
				game.setOwnAssists(gJo.getInt("ownAssists"));
				game.setOwnCS(gJo.getInt("ownCS"));
				game.setResult(GameResult.valueOf(gJo.getString("gameResult")));
				game.setLength(gJo.getLong("length"));
				boolean reallyImport = true;
				if (doWithDouble != ImportDolexDouble.YES_TO_ALL && checkDouble(matchup)) {
					if (doWithDouble == ImportDolexDouble.NO_TO_ALL) {
						reallyImport = false;
					} else if (doWithDouble == ImportDolexDouble.NONE) {
						String[] options = { "Yes to all", "Yes", "No",
								"No to all" };
						int ok = JOptionPane
								.showOptionDialog(
										MainView.getInstance(),
										"There is already a match like this. Do you want to import it anyway?",
										"Enter your Summoner name",
										JOptionPane.WARNING_MESSAGE,
										JOptionPane.INFORMATION_MESSAGE, null,
										options, "No");
						if (ok == JOptionPane.CLOSED_OPTION || ok == 2) {
							reallyImport = false;
						} else if (ok == 0) {
							reallyImport = true;
							doWithDouble = ImportDolexDouble.YES_TO_ALL;
						} else if (ok == 3) {
							reallyImport = false;
							doWithDouble = ImportDolexDouble.NO_TO_ALL;
						}
					}
				}
				if (reallyImport) {
					MatchupUtil.saveMatchup(matchup);
					GameUtil.saveGame(game);
					MainView.getInstance().getMatchupPanel()
							.addMatchup(matchup);
				}
			}
			MainView.getInstance().setStatusText("Import complete");
		} catch (Exception e) {
			logger.error(e.getMessage());
			MainView.getInstance().setStatusText(
					"Import failed. Please read the logfile.");
		}
		logger.trace("doImport() - Leaving");
	}

	/**
	 * @param matchup
	 * @return
	 */
	private boolean checkDouble(Matchup matchup) {
		logger.trace("checkDouble() - Entering");
		logger.debug("checkDouble() - Parameter: {}", matchup);
		boolean returnValue = false;
		for (Matchup m : MatchupUtil.getAllMatchups()) {
			if (m.getGame().getDate().equals(matchup.getGame().getDate())) {
				if (m.getMyChamp() == matchup.getMyChamp()) {
					returnValue = true;
					break;
				}
			}
		}
		logger.trace("checkDouble() - Returning");
		logger.debug("checkDouble() - Returning: {}", returnValue);
		return returnValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (file != null) {
			doImport(file);
		}
	}
}