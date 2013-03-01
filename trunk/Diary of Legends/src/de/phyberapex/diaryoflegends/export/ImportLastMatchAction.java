package de.phyberapex.diaryoflegends.export;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.exception.ChampionNotFoundException;
import de.phyberapex.diaryoflegends.exception.ItemNotFoundException;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.GameResult;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.view.MainView;
import de.phyberapex.diaryoflegends.view.dialoge.NewEntryDialoge;

public class ImportLastMatchAction implements Runnable {

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
				JSONObject gameStats = jo.getJSONObject("data")
						.getJSONArray("gameStatistics").getJSONObject(0);
				Game game = new Game();
				Matchup matchup = new Matchup();
				matchup.setGame(game);
				game.setMatchup(matchup);
				int teamId = gameStats.getInt("teamId");
				// int spell1 = gameStats.getInt("spell1");
				// int spell2 = gameStats.getInt("spell2");
				List<Champion> myTeam = new ArrayList<>();
				List<Champion> enemyTeam = new ArrayList<>();
				Champion myChamp = ChampionUtil.getChampionById(gameStats
						.getInt("championId"));
				if (myChamp == null) {
					logger.error("Champion with id {} not found",
							gameStats.getInt("championId"));
					throw new ChampionNotFoundException(
							"Champion not found. Maybe your champions are outdated.");
				}
				matchup.setMyChamp(myChamp);
				myTeam.add(matchup.getMyChamp());
				JSONArray partsJoAr = gameStats.getJSONArray("fellowPlayers");
				for (int i = 0; i < partsJoAr.length(); i++) {
					logger.debug(
							"Looping through all participants. Currently {}", i);
					if (partsJoAr.getJSONObject(i).getInt("teamId") == teamId) {
						logger.debug("Participant {} is in users team", i);
						Champion champ = ChampionUtil.getChampionById(partsJoAr
								.getJSONObject(i).getInt("championId"));
						if (champ == null) {
							logger.error(
									"Champion with id {} not found",
									partsJoAr.getJSONObject(i).getInt(
											"championId"));
							throw new ChampionNotFoundException(
									"Champion not found. Maybe your champions are outdated.");
						}
						myTeam.add(champ);
					} else {
						logger.debug("Participant {} is in enemy team", i);
						Champion champ = ChampionUtil.getChampionById(partsJoAr
								.getJSONObject(i).getInt("championId"));
						if (champ == null) {
							logger.error(
									"Champion with id {} not found",
									partsJoAr.getJSONObject(i).getInt(
											"championId"));
							throw new ChampionNotFoundException(
									"Champion not found. Maybe your champions are outdated.");
						}
						enemyTeam.add(champ);
					}
				}
				game.setEnemyTeam(enemyTeam);
				game.setMyTeam(myTeam);
				JSONArray statArray = gameStats.getJSONArray("statistics");
				List<MatchupItem> myEndItems = new ArrayList<>();
				for (int i = 0; i < statArray.length(); i++) {
					JSONObject statsObject = statArray.getJSONObject(i);
					String statType = statsObject.getString("statType");
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
						Item item = ItemUtil.getItemById(value);
						if (item == null) {
							logger.error("Item with id {} not found", value);
							throw new ItemNotFoundException(
									"Item not found. Maybe your champions are outdated.");
						}
						myEndItems.add(new MatchupItem(item, 1));
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
				String timestamp = gameStats.getString("createDate");
				timestamp = timestamp.substring(6);
				timestamp = timestamp.substring(0, timestamp.length() - 2);
				game.setDate(new Date(Long.parseLong(timestamp)));
				JComboBox<Champion> enemyChmps = new JComboBox<Champion>(
						new DefaultComboBoxModel<Champion>(
								enemyTeam.toArray(new Champion[] {})));
				int answer = JOptionPane.CANCEL_OPTION;
				while (answer != JOptionPane.OK_OPTION) {
					answer = JOptionPane.showConfirmDialog(null, enemyChmps,
							"Please choose your lane opponent",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
				Champion enemyChamp = enemyChmps.getItemAt(enemyChmps
						.getSelectedIndex());
				logger.debug("Enemy champion choosen it is {}", enemyChamp);
				matchup.setEnemyChamp(enemyChamp);
				game.setLength(0);
				MainView.getInstance().setStatusText("Import complete");
				NewEntryDialoge nd = NewEntryDialoge.getInstance();
				nd.setToEdit(game, true);
				SwingUtilities.invokeLater(nd);
				MainView.getInstance().getMatchupPanel().addMatchup(matchup);
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
