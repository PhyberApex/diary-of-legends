package de.phyberapex.diaryoflegends.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.util.GameUtil;
import de.phyberapex.diaryoflegends.view.MainView;

public class ExportAction implements Runnable {

	private static Logger logger = LogManager.getLogger(ExportAction.class
			.getName());

	private void doExport() {
		logger.trace("doExport() - Entering");
		List<Game> games = GameUtil.getAllGames();
		logger.debug("Trying to export {} games and matches", games.size());
		MainView.getInstance().setStatusText(
				"Exporting " + games.size() + " games and matchups");
		try {
			File f = new File(System.getProperty("user.dir") + "\\backup.dolex");
			logger.debug("Exporting into {}", f);
			BufferedWriter bw = new BufferedWriter(new FileWriter(f, false));
			for (Game game : games) {
				logger.debug("Exporting ", game);
				String newLine = "[";
				Date date = game.getDate();
				SimpleDateFormat sdfToDate = new SimpleDateFormat(
						"dd.MM.yyyy HH:mm:ss");
				String strDate = sdfToDate.format(date);
				newLine += strDate + "§";
				logger.debug("Exporting champions of my team...");
				for (Champion champ : game.getMyTeam()) {
					logger.debug("Exporting {}", champ.getName());
					newLine += champ.getName() + "§";
				}
				newLine += "MYTEAMEND§";
				logger.debug("Exporting champions of enemy team...");
				for (Champion champ : game.getEnemyTeam()) {
					logger.debug("Exporting {}", champ.getName());
					newLine += champ.getName() + "§";
				}
				newLine += "ENEMYTEAMEND§";
				Matchup m = game.getMatchup();
				newLine += m.getMyChamp() + "§";
				logger.debug("Exporting my start items...");
				for (MatchupItem item : m.getMyStartItems()) {
					logger.debug("Exporting {} Amount: {}", item.getItem(),
							item.getAmount());
					newLine += item.getItem().getName() + "§"
							+ item.getAmount() + "§";
				}
				newLine += "MYITEMSEND§";
				newLine += m.getEnemyChamp() + "§";
				logger.debug("Exporting enemy start items...");
				for (MatchupItem item : m.getEnemyStartItems()) {
					logger.debug("Exporting {} Amount: {}", item.getItem(),
							item.getAmount());
					newLine += item.getItem().getName() + "§"
							+ item.getAmount() + "§";
				}
				newLine += "ENEMYITEMSEND§";
				newLine += m.getResult().name() + "§";
				newLine += m.getLane().name() + "§";
				newLine += m.getDifficulty().name() + "§";
				newLine += m.getNotes() + "§";
				newLine += game.getResult().name() + "§";
				newLine += game.getNotes() + "§";
				newLine += game.getOwnKills() + "§";
				newLine += game.getOwnDeaths() + "§";
				newLine += game.getOwnAssists();
				newLine += "]";
				bw.write(newLine);
				bw.newLine();
				logger.debug("Finished with this game.");
			}
			logger.debug("All games exported.");
			bw.close();
		} catch (IOException e) {
			logger.error("Export failed. Reason" + e.getMessage());
			MainView.getInstance().setStatusText(
					"Export failed. Please read the logfile");
		}
		MainView.getInstance().setStatusText("Export complete");
		logger.trace("doExport() - Leaving");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		doExport();
	}
}