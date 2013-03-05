package de.phyberapex.diaryoflegends.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.exception.ChampionNotFoundException;
import de.phyberapex.diaryoflegends.exception.ItemNotFoundException;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.GameElophantImport;
import de.phyberapex.diaryoflegends.model.GameResult;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.view.MainView;
import de.phyberapex.diaryoflegends.view.dialoge.NewEntryDialoge;
import de.phyberapex.diaryoflegends.view.panel.ElophantImportPanel;

public class ImportLastMatchAction implements Runnable {

	private boolean[] toImport = { false, false, false, false, false, false,
			false, false, false, false };
	private List<Champion> allChampions = ChampionUtil.getAllChampions();
	private List<Item> allItems = ItemUtil.getAllItems();
	private List<GameElophantImport> impGames;
	private static Logger logger = LogManager
			.getLogger(ImportLastMatchAction.class.getName());

	public ImportLastMatchAction() {
	}

	private void doImport() {
		logger.trace("doImport() - Entering");
		MainView.getInstance().setStatusText("Importing...");
		try {
			JSONObject jo = getMostRecentMatchFromElophant();
			if (jo.getBoolean("success")) {
				MainView.getInstance().setStatusText(
						"Recent matches found analyzing...");
				checkMatches(jo);
				int y = 0;
				for (int x = 0; x < toImport.length; x++) {
					if (toImport[x]) {
						if (impGames.get(y).isSelected()) {
							Game game = new Game();
							Matchup matchup = new Matchup();
							matchup.setGame(game);
							game.setMatchup(matchup);
							matchup.setMyChamp(impGames.get(y).getMyChampion());
							game.setMyTeam(impGames.get(y).getMyTeam());
							game.setEnemyTeam(impGames.get(y).getEnemyTeam());
							game.setDate(impGames.get(y).getDate());
							JSONObject gameStats = jo.getJSONObject("data")
									.getJSONArray("gameStatistics")
									.getJSONObject(x);
							// int spell1 = gameStats.getInt("spell1");
							// int spell2 = gameStats.getInt("spell2");
							JSONArray statArray = gameStats
									.getJSONArray("statistics");
							List<MatchupItem> myEndItems = new ArrayList<>();
							for (int i = 0; i < statArray.length(); i++) {
								JSONObject statsObject = statArray
										.getJSONObject(i);
								String statType = statsObject
										.getString("statType");
								int value = statsObject.getInt("value");
								switch (statType) {
								case "ASSISTS":
									game.setOwnAssists(value);
									break;
								case "WIN":
									if (value == 1) {
										game.setResult(GameResult.WIN);
									} else {
										game.setResult(GameResult.LOSS);
									}
									break;
								case "ITEM0":
								case "ITEM1":
								case "ITEM2":
								case "ITEM3":
								case "ITEM4":
								case "ITEM5":
									if (value != 0) {
										Item item = null;
										Iterator<Item> it2 = allItems
												.iterator();
										while (it2.hasNext()) {
											Item tmp = it2.next();
											if (tmp.getId() == value) {
												item = tmp;
												break;
											}
										}
										if (item == null) {
											logger.error(
													"Item with id {} not found",
													value);
											throw new ItemNotFoundException(
													"Item not found. Maybe your items are outdated.");
										}
										myEndItems
												.add(new MatchupItem(item, 1));
									}
									break;
								case "NUM_DEATHS":
									game.setOwnDeaths(value);
									break;
								case "CHAMPIONS_KILLED":
									game.setOwnKills(value);
									break;
								case "MINIONS_KILLED":
									game.setOwnCS(value);
									break;
								}
							}
							matchup.setMyStartItems(new ArrayList<MatchupItem>());
							matchup.setMyEndItems(myEndItems);
							matchup.setEnemyStartItems(new ArrayList<MatchupItem>());
							matchup.setEnemyEndItems(new ArrayList<MatchupItem>());
							game.setLength(0);
							MainView.getInstance().setStatusText(
									"Import complete");
							NewEntryDialoge nd = NewEntryDialoge.getInstance();
							nd.setToEdit(game, true);
							nd.run();
						}
						y++;
					}
				}
			}else{
				MainView.getInstance().setStatusText("Import not possible, API may be under maintenance");
			}
		} catch (ChampionNotFoundException | ItemNotFoundException
				| IOException e) {
			logger.error(e.getMessage());
			MainView.getInstance().setStatusText(
					"Import failed. Please read the logfile.");
		}

		catch (Exception e) {
			logger.error("Unreadable format.");
			MainView.getInstance().setStatusText(
					"Import failed. Please read the logfile.");
		}
		logger.trace("doImport() - Leaving");
	}

	/**
	 * @param jo
	 * @throws ChampionNotFoundException
	 * 
	 */
	private boolean checkMatches(JSONObject jo)
			throws ChampionNotFoundException {
		impGames = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			MainView.getInstance().setStatusText(
					"Currently analyzing " + (i + 1) + " of 10");
			GameElophantImport currGame = new GameElophantImport();
			JSONObject tryM = jo.getJSONObject("data")
					.getJSONArray("gameStatistics").getJSONObject(i);
			String type = tryM.getString("queueType");
			if (type.equals("NORMAL") || type.equals("RANKED_SOLO_5x5")) {
				toImport[i] = true;
				JSONObject gameStats = jo.getJSONObject("data")
						.getJSONArray("gameStatistics").getJSONObject(i);
				int teamId = gameStats.getInt("teamId");
				List<Champion> myTeam = new ArrayList<>();
				List<Champion> enemyTeam = new ArrayList<>();
				Champion myChamp = null;
				Iterator<Champion> it = allChampions.iterator();
				while (it.hasNext()) {
					Champion tmp = it.next();
					if (tmp.getId() == gameStats.getInt("championId")) {
						myChamp = tmp;
						break;
					}
				}
				if (myChamp == null) {
					logger.error("Champion with id {} not found",
							gameStats.getInt("championId"));
					throw new ChampionNotFoundException(
							"Champion not found. Maybe your champions are outdated.");
				}
				currGame.setMyChampion(myChamp);
				myTeam.add(currGame.getMyChampion());
				JSONArray partsJoAr = gameStats.getJSONArray("fellowPlayers");
				for (int j = 0; j < partsJoAr.length(); j++) {
					logger.debug(
							"Looping through all participants. Currently {}", j);
					if (partsJoAr.getJSONObject(j).getInt("teamId") == teamId) {
						logger.debug("Participant {} is in users team", j);
						Champion champ = null;
						it = allChampions.iterator();
						while (it.hasNext()) {
							Champion tmp = it.next();
							if (tmp.getId() == partsJoAr.getJSONObject(j)
									.getInt("championId")) {
								champ = tmp;
								break;
							}
						}
						if (champ == null) {
							logger.error(
									"Champion with id {} not found",
									partsJoAr.getJSONObject(j).getInt(
											"championId"));
							throw new ChampionNotFoundException(
									"Champion not found. Maybe your champions are outdated.");
						}
						myTeam.add(champ);
					} else {
						logger.debug("Participant {} is in enemy team", j);
						Champion champ = null;
						it = allChampions.iterator();
						while (it.hasNext()) {
							Champion tmp = it.next();
							if (tmp.getId() == partsJoAr.getJSONObject(j)
									.getInt("championId")) {
								champ = tmp;
								break;
							}
						}
						if (champ == null) {
							logger.error(
									"Champion with id {} not found",
									partsJoAr.getJSONObject(j).getInt(
											"championId"));
							throw new ChampionNotFoundException(
									"Champion not found. Maybe your champions are outdated.");
						}
						enemyTeam.add(champ);
					}
				}
				currGame.setEnemyTeam(enemyTeam);
				currGame.setMyTeam(myTeam);
				String timestamp = gameStats.getString("createDate");
				timestamp = timestamp.substring(6);
				timestamp = timestamp.substring(0, timestamp.length() - 2);
				currGame.setDate(new Date(Long.parseLong(timestamp)));
				impGames.add(currGame);
			}
		}
		ElophantImportPanel view = new ElophantImportPanel(impGames);
		int option = JOptionPane.showConfirmDialog(MainView.getInstance(),
				view, "Choose matches to import", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null);
		if (option == JOptionPane.OK_OPTION) {
			view.setSelection();
			return true;
		} else {
			return false;
		}
	}

	private JSONObject getMostRecentMatchFromElophant() throws IOException {
		logger.trace("getJSONStringFromRoflFile() - Entering");
		URLConnection con = new URL("http://api.elophant.com/v2/"
				+ Config.getInstance().getProperty("REGION") + "/recent_games/"
				+ Config.getInstance().getProperty("ACCOUNT_ID") + "?key="
				+ Config.getInstance().getProperty("API_KEY")).openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		String json = "";
		while ((inputLine = in.readLine()) != null)
			json += inputLine;
		in.close();
		JSONObject returnValue = new JSONObject(json);
		logger.trace("getJSONStringFromRoflFile() - Returning");
		logger.debug("getJSONStringFromRoflFile() - Returning: {}", returnValue);
		return returnValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		doImport();
	}
}
