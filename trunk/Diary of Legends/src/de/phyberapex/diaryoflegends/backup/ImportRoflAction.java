package de.phyberapex.diaryoflegends.backup;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.exception.ChampionNotFoundException;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.view.MainView;

public class ImportRoflAction implements Runnable {

	private File file;
	private static Logger logger = LogManager.getLogger(ExportDolexAction.class
			.getName());

	public ImportRoflAction(File file) {
		this.file = file;
	}

	private void doImport(File importFile) {
		logger.trace("doImport() - Entering");
		logger.debug("doImport() - Parameter: {}", importFile);
		MainView.getInstance().setStatusText("Importing...");
		JSONObject jo = new JSONObject(getJSONStringFromRoflFile(importFile));
		try {
			JSONArray partsJoAr = jo.getJSONArray("participants");
			String sumName = Config.getInstance().getProperty("SUMMONER_NAME");
			int myTeamId = 0;
			Champion myChamp = null;
			for (int i = 0; i <= partsJoAr.length(); i++) {
				if (partsJoAr.getJSONObject(i).getString("summonerName")
						.equals(sumName)) {
					myTeamId = partsJoAr.getJSONObject(i).getInt("teamId");
					myChamp = ChampionUtil.getChampionById(partsJoAr
							.getJSONObject(i).getInt("championId"));
					if (myChamp == null) {
						logger.error("Championt with id {} not found",
								partsJoAr.getJSONObject(i).getInt("championId"));
						throw new ChampionNotFoundException(
								"Champion not found. Maybe your champions are outdated.");
					}
					break;
				}

			}
			List<Champion> myTeam = new ArrayList<Champion>();
			List<Champion> enemyTeam = new ArrayList<Champion>();
			for (int i = 0; i < partsJoAr.length(); i++) {
				Champion chmp = ChampionUtil.getChampionById(partsJoAr
						.getJSONObject(i).getInt("championId"));
				if (chmp == null) {
					logger.error("Championt with id {} not found", partsJoAr
							.getJSONObject(i).getInt("championId"));
					throw new ChampionNotFoundException(
							"Champion not found. Maybe your champions are outdated.");
				} else if (partsJoAr.getJSONObject(i).getInt("teamId") == myTeamId) {
					myTeam.add(chmp);
				} else {
					enemyTeam.add(chmp);
				}
			}
			Game game = new Game();
			// TODO datum rausfinden
			game.setDate(new Date());
			game.setMyTeam(myTeam);
			game.setEnemyTeam(enemyTeam);
			Matchup matchup = new Matchup();
			matchup.setMyChamp(myChamp);

			JComboBox<Champion> enemyChmps = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							enemyTeam.toArray(new Champion[] {})));
			int i = JOptionPane.CANCEL_OPTION;
			while (i != JOptionPane.OK_OPTION) {
				i = JOptionPane.showConfirmDialog(null, enemyChmps,
						"Please choose your lane opponent",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
			}
			matchup.setEnemyChamp(enemyChmps.getItemAt(enemyChmps
					.getSelectedIndex()));
			

			// TODO längerausfinden
			game.setLength(0);
			MainView.getInstance().setStatusText("Import complete");
		} catch (Exception e) {
			logger.error(e.getMessage());
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
			int start = bla.indexOf("{");
			int end = bla.indexOf("©", start);
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
