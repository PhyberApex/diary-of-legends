package de.phyberapex.diaryoflegends.action;

import java.io.File;
import java.io.FileInputStream;
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
import de.phyberapex.diaryoflegends.model.SummonerSpell;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.model.util.SummonerSpellUtil;
import de.phyberapex.diaryoflegends.view.MainView;
import de.phyberapex.diaryoflegends.view.dialoge.NewEntryDialog;

public class ImportRoflAction implements Runnable {

	private File file;
	private static Logger logger = LogManager.getLogger(ImportRoflAction.class
			.getName());

	public ImportRoflAction(File file) {
		this.file = file;
	}

	private void doImport(File importFile) {
		logger.trace("doImport() - Entering");
		logger.debug("doImport() - Parameter: {}", importFile);
		MainView.getInstance().setStatusText("Importing...");
		JSONObject jo = new JSONObject(getJSONStringFromRoflFile(importFile));
		Game game = new Game();
		Matchup matchup = new Matchup();
		matchup.setGame(game);
		game.setMatchup(matchup);
		try {
			JSONArray partsJoAr = jo.getJSONArray("participants");
			String sumName = Config.getInstance().getProperty("SUMMONER_NAME");
			logger.debug("Own summoner name set to {}", sumName);
			int myTeamId = 0;
			Champion myChamp = null;
			for (int i = 0; i < partsJoAr.length(); i++) {
				logger.debug(
						"Looping through all participants to find user. Currently {}",
						i);
				if (partsJoAr.getJSONObject(i).getString("summonerName")
						.equals(sumName)) {
					logger.debug("Participant {} is user", i);
					myTeamId = partsJoAr.getJSONObject(i).getInt("teamId");
					logger.debug("Users team id is {}", myTeamId);
					logger.debug("Gettin users champion");
					myChamp = ChampionUtil.getChampionById(partsJoAr
							.getJSONObject(i).getInt("championId"));
					logger.debug("Users champion is {}", myChamp);
					if (myChamp == null) {
						logger.error("Champion with id {} not found", partsJoAr
								.getJSONObject(i).getInt("championId"));
						throw new ChampionNotFoundException(
								"Champion not found. Maybe your champions are outdated.");
					}
					int mySpell1id = partsJoAr.getJSONObject(i).getInt(
							"spell1Id");
					logger.debug("Users spell1Id is {}", mySpell1id);
					SummonerSpell mySumm = SummonerSpellUtil
							.getSpellById(mySpell1id);
					logger.debug("Users spell1 is {}", mySumm);
					if (mySumm == null) {
						logger.error("Summoner spell with id {} not found",
								partsJoAr.getJSONObject(i).getInt("spell1Id"));
					}
					matchup.setMySpell1(mySumm);
					int mySpell2id = partsJoAr.getJSONObject(i).getInt(
							"spell2Id");
					logger.debug("Users spell2Id is {}", mySpell2id);
					mySumm = SummonerSpellUtil.getSpellById(mySpell2id);
					logger.debug("Users spell2 is {}", mySpell2id);
					if (mySumm == null) {
						logger.error("Summoner spell with id {} not found",
								partsJoAr.getJSONObject(i).getInt("spell2Id"));
					}
					matchup.setMySpell2(mySumm);
					break;
				}

			}
			if (myChamp == null) {
				logger.error("Own Champion not found");
				throw new ChampionNotFoundException(
						"Your champion was not found. Maybe you did not participate in that game.");
			}
			matchup.setMyChamp(myChamp);
			List<Champion> myTeam = new ArrayList<Champion>();
			List<Champion> enemyTeam = new ArrayList<Champion>();
			List<String> enemyTeamSumms = new ArrayList<String>();
			List<Integer> enemySpells1 = new ArrayList<>();
			List<Integer> enemySpells2 = new ArrayList<>();
			for (int i = 0; i < partsJoAr.length(); i++) {
				logger.debug(
						"Looping through all participants to divide teams. Currently {}",
						i);
				int championId = partsJoAr.getJSONObject(i)
						.getInt("championId");
				logger.debug("Current championId is {}", championId);
				Champion chmp = ChampionUtil.getChampionById(championId);
				logger.debug("Current champion is {}", chmp);
				if (chmp == null) {
					logger.error("Championt with id {} not found", partsJoAr
							.getJSONObject(i).getInt("championId"));
					throw new ChampionNotFoundException(
							"Champion not found. Maybe your champions are outdated.");
				}
				int teamId = partsJoAr.getJSONObject(i).getInt("teamId");
				logger.debug("Current teamId is {}", teamId);
				if (teamId == myTeamId) {
					myTeam.add(chmp);
				} else {
					enemyTeam.add(chmp);
					String enemySum = partsJoAr.getJSONObject(i).getString(
							"summonerName");
					logger.debug("Current enemy team summoner is {}", enemySum);
					enemyTeamSumms.add(enemySum);
					int enemySpell1 = partsJoAr.getJSONObject(i).getInt(
							"spell1Id");
					logger.debug("Current enemy summoner spell 1 id is {}",
							enemySpell1);
					enemySpells1.add(enemySpell1);
					int enemySpell2 = partsJoAr.getJSONObject(i).getInt(
							"spell2Id");
					logger.debug("Current enemy summoner spell 1 id is {}",
							enemySpell2);
					enemySpells2.add(enemySpell2);
				}
			}
			game.setDate(new Date(jo.getLong("gameStartTime")));
			game.setLength(jo.getLong("gameLength") / 1000);
			game.setMyTeam(myTeam);
			game.setEnemyTeam(enemyTeam);
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
			SummonerSpell enemySumm = SummonerSpellUtil
					.getSpellById(enemySpells1.get(enemyChmps
							.getSelectedIndex()));
			logger.debug("Enemy summoner spell 1 is {}", enemySumm);
			if (enemySumm == null) {
				logger.error("Summoner spell with id {} not found",
						enemySpells1.get(enemyChmps.getSelectedIndex()));
			}
			matchup.setEnemySpell1(enemySumm);
			enemySumm = SummonerSpellUtil.getSpellById(enemySpells2
					.get(enemyChmps.getSelectedIndex()));
			logger.debug("Enemy summoner spell 1 is {}", enemySumm);
			if (enemySumm == null) {
				logger.error("Summoner spell with id {} not found",
						enemySpells2.get(enemyChmps.getSelectedIndex()));
			}
			matchup.setEnemySpell2(enemySumm);
			String enemySummName = enemyTeamSumms.get(enemyChmps
					.getSelectedIndex());
			logger.debug("Enemy summoner name is {}", enemySummName);
			String innerJSONStr = jo.getString("statsJSON");
			JSONArray gameStatsArray = new JSONArray(innerJSONStr);
			List<MatchupItem> myItems = new ArrayList<MatchupItem>();
			List<MatchupItem> enemyItems = new ArrayList<MatchupItem>();
			for (int i = 0; i < gameStatsArray.length(); i++) {
				logger.debug(
						"Looping through all stats to build matchup. Currently {}",
						i);
				JSONObject statJo = gameStatsArray.getJSONObject(i);
				String currSummName = statJo.getString("NAME");
				logger.debug("Current summoner name is {}", currSummName);
				if (currSummName.equals(Config.getInstance().getProperty(
						"SUMMONER_NAME"))) {
					logger.debug("Summoner is user");
					String win = statJo.getString("WIN");
					logger.debug("Win is {}", win);
					if (win.equals("Win")) {
						game.setResult(GameResult.WIN);
					} else {
						game.setResult(GameResult.LOSS);
					}
					game.setOwnKills(Integer.valueOf(statJo
							.getString("CHAMPIONS_KILLED")));
					game.setOwnAssists(Integer.valueOf(statJo
							.getString("ASSISTS")));
					game.setOwnDeaths(Integer.valueOf(statJo
							.getString("NUM_DEATHS")));
					game.setOwnCS(Integer.valueOf(statJo
							.getString("MINIONS_KILLED")));
					for (int j = 0; j <= 5; j++) {
						logger.debug(
								"Loop to check all items currently ITEM{}", j);
						int itemId = Integer.valueOf(statJo.getString("ITEM"
								+ j));
						logger.debug("Current itemId is {}", itemId);
						Item currItem = null;
						if (itemId != 0) {
							currItem = ItemUtil.getItemById(itemId);
							logger.debug("Current item is {}", currItem);
							if (currItem == null) {
								logger.error("Item with id {} not found",
										itemId);
								throw new ItemNotFoundException(
										"Item not found. Maybe your items are outdated.");
							}
							myItems.add(new MatchupItem(currItem, 1));
						}
					}
					matchup.setMyStartItems(new ArrayList<MatchupItem>());
					matchup.setMyEndItems(myItems);
				} else if (currSummName.equals(enemySummName)) {
					logger.debug("Summoner is enemy");
					for (int j = 0; j <= 5; j++) {
						logger.debug(
								"Loop to check all items currently ITEM{}", j);
						int itemId = Integer.valueOf(statJo.getString("ITEM"
								+ j));
						logger.debug("Current itemId is {}", itemId);
						Item currItem = null;
						if (itemId != 0) {
							currItem = ItemUtil.getItemById(itemId);
							logger.debug("Current item is {}", currItem);
							if (currItem == null) {
								logger.error("Item with id {} not found",
										itemId);
								throw new ItemNotFoundException(
										"Item not found. Maybe your items are outdated.");
							}
							enemyItems.add(new MatchupItem(currItem, 1));
						}
					}
					matchup.setEnemyStartItems(new ArrayList<MatchupItem>());
					matchup.setEnemyEndItems(enemyItems);
				}
				if (matchup.getMyEndItems() != null
						&& matchup.getEnemyEndItems() != null) {
					break;
				}
			}
			MainView.getInstance().setStatusText("Import complete");
			NewEntryDialog nd = NewEntryDialog.getInstance();
			nd.setToEdit(game, true);
			SwingUtilities.invokeLater(nd);
		} catch (ChampionNotFoundException | ItemNotFoundException e) {
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

	private String getJSONStringFromRoflFile(File importFile) {
		logger.trace("getJSONStringFromRoflFile() - Entering");
		logger.debug("getJSONStringFromRoflFile() - Parameter: {}", importFile);
		String returnValue = "";
		try {
			FileInputStream in = new FileInputStream(importFile);
			int length = (int) importFile.length();
			byte[] b = new byte[length];
			int c;
			int x = 0;
			while ((c = in.read()) != -1 && x < length) {
				b[x] = (byte) c;
				x++;
			}
			String bla = new String(b);
			int start = bla.indexOf("{\"gameId");
			int end = bla.indexOf("gameLength", start) + 22;
			returnValue = bla.substring(start, end);
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
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
		if (file != null) {
			doImport(file);
		}
	}
}
