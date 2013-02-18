package de.phyberapex.diaryoflegends.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.view.MainView;

public class ImportAction {

	private static Logger logger = LogManager.getLogger(ExportAction.class
			.getName());

	private ImportAction() {

	}

	public static void doImport(File importFile) {
		logger.trace("doImport() - Entering");
		logger.debug("doImport() - Parameter: {}", importFile);
		MainView.getInstance().setStatusText("Importing...");
		FileInputStream fstream;
		BufferedReader br;
		try {
			fstream = new FileInputStream(importFile);
			br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				logger.debug("Reading line: {}", strLine);
				boolean doSkip = false;
				int x = 0;
				int first = strLine.indexOf("[");
				int second = strLine.indexOf("]");
				String[] attributes = strLine.substring(first + 1, second)
						.split("§");
				Game game = new Game();
				SimpleDateFormat sdfToDate = new SimpleDateFormat(
						"dd.MM.yyyy HH:mm:ss");
				logger.debug("Converting {} to date", attributes[x]);
				Date date = sdfToDate.parse(attributes[x]);
				game.setDate(date);
				x++;
				List<Champion> myTeam = new ArrayList<Champion>();
				int y = x + 5;
				for (int i = x; i < y; i++) {
					logger.debug("Trying to find a champion with the name {}",
							attributes[i]);
					List<Champion> tmpChmp = ChampionUtil
							.searchChampionByName(attributes[i]);
					if (tmpChmp.size() != 1) {
						logger.info(
								"Could not find champion {} skipping this line",
								attributes[i]);
						doSkip = true;
						break;
					} else {
						logger.debug("Champion found adding to the list of myTeam");
						myTeam.add(tmpChmp.get(0));
						x++;
					}
				}
				if (doSkip) {
					break;
				}
				game.setMyTeam(myTeam);
				x++;
				List<Champion> enemyTeam = new ArrayList<Champion>();
				y = x + 5;
				for (int i = x; i < y; i++) {
					logger.debug("Trying to find a champion with the name {}",
							attributes[i]);
					List<Champion> tmpChmp = ChampionUtil
							.searchChampionByName(attributes[i]);
					if (tmpChmp.size() != 1) {
						logger.info(
								"Could not find champion {} skipping this line",
								attributes[i]);
						doSkip = true;
						break;
					} else {
						logger.debug("Champion found adding to the list of enemyTeam");
						enemyTeam.add(tmpChmp.get(0));
						x++;
					}
				}
				if (doSkip) {
					break;
				}
				game.setEnemyTeam(enemyTeam);
				x++;
				Matchup matchup = new Matchup();
				logger.debug("Trying to find a champion with the name {}",
						attributes[x]);
				List<Champion> tmpChmp = ChampionUtil
						.searchChampionByName(attributes[x]);
				if (tmpChmp.size() != 1) {
					logger.info(
							"Could not find champion {} skipping this line",
							attributes[x]);
					break;
				}
				logger.debug("Champion found adding as own champion");
				matchup.setMyChamp(tmpChmp.get(0));
				x++;
				List<MatchupItem> myItems = new ArrayList<MatchupItem>();
				y = x + 6;
				for (int i = x; i < y; i += 2) {
					if (attributes[i].equals("MYITEMSEND")) {
						logger.debug("End of list of own starting items");
						break;
					} else {
						logger.debug("Trying to find a item with the name {}",
								attributes[i]);
						List<Item> items = ItemUtil
								.searchItemByName(attributes[i]);
						if (items.size() != 1) {
							logger.info(
									"Could not find item {} skipping this line",
									attributes[x]);
						} else {
							logger.debug("Item found adding to myStartItems");
							myItems.add(new MatchupItem(items.get(0), Integer
									.valueOf(attributes[i + 1])));
							x += 2;
						}
					}
				}
				if (doSkip) {
					break;
				}
				matchup.setMyStartItems(myItems);
				x++;
				logger.debug("Trying to find a champion with the name {}",
						attributes[x]);
				List<Champion> tmpEChmp = ChampionUtil
						.searchChampionByName(attributes[x]);
				if (tmpEChmp.size() != 1) {
					logger.info(
							"Could not find champion {} skipping this line",
							attributes[x]);
					break;
				}
				logger.debug("Champion found adding as enemy champion");
				matchup.setEnemyChamp(tmpEChmp.get(0));
				x++;
				List<MatchupItem> enemyItems = new ArrayList<MatchupItem>();
				y = x + 6;
				for (int i = x; i < y; i += 2) {
					if (attributes[i].equals("ENEMYITEMSEND")) {
						logger.debug("End of list of own starting items");
						break;
					} else {
						logger.debug("Trying to find a item with the name {}",
								attributes[x]);
						List<Item> items = ItemUtil
								.searchItemByName(attributes[i]);
						if (items.size() != 1) {
							logger.info(
									"Could not find item {} skipping this line",
									attributes[x]);
							doSkip = true;
						} else {
							logger.debug("Item found adding to enemyStartItems");
							enemyItems.add(new MatchupItem(items.get(0),
									Integer.valueOf(attributes[i + 1])));
							x += 2;
						}
					}
				}
				if (doSkip) {
					break;
				}
				matchup.setEnemyStartItems(enemyItems);
				x++;
				matchup.setResult(MatchupResult.valueOf(attributes[x]));
				x++;
				matchup.setLane(Role.valueOf(attributes[x]));
				x++;
				matchup.setDifficulty(MatchupDifficulty.valueOf(attributes[x]));
				x++;
				matchup.setNotes(attributes[x]);
				x++;
				matchup.setGame(game);
				game.setMatchup(matchup);
				game.setResult(GameResult.valueOf(attributes[x]));
				x++;
				game.setNotes(attributes[x]);
				x++;
				game.setOwnKills(Integer.valueOf(attributes[x]));
				x++;
				game.setOwnDeaths(Integer.valueOf(attributes[x]));
				x++;
				game.setOwnAssists(Integer.valueOf(attributes[x]));
				Config.getInstance().getDBHandle().store(game);
				MainView.getInstance().getGamePanel().addGame(game);
				MainView.getInstance().getMatchupPanel().addMatchup(matchup);
			}
			br.close();
			fstream.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			MainView.getInstance().setStatusText(
					"Import failed. Please read the logfile.");
		}
		MainView.getInstance().setStatusText("Import complete");
		logger.trace("doImport() - Leaving");
	}
}
