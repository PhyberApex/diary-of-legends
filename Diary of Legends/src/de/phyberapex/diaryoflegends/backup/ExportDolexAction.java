package de.phyberapex.diaryoflegends.backup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONWriter;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.util.GameUtil;
import de.phyberapex.diaryoflegends.view.MainView;

public class ExportDolexAction implements Runnable {

	private static Logger logger = LogManager.getLogger(ExportDolexAction.class
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
			FileWriter fw = new FileWriter(f, false);
			JSONWriter jw = new JSONWriter(fw);
			jw.object();
			jw.key("games");
			jw.array();
			for (Game game : games) {
				logger.debug("Exporting ", game);
				jw.object();
				jw.key("date");
				Date date = game.getDate();
				SimpleDateFormat sdfToDate = new SimpleDateFormat(
						"dd.MM.yyyy HH:mm:ss");
				String strDate = sdfToDate.format(date);
				jw.value(strDate);
				jw.key("myTeam");
				jw.array();
				jw.value(game.getMyTeam().get(0).getId());
				jw.value(game.getMyTeam().get(1).getId());
				jw.value(game.getMyTeam().get(2).getId());
				jw.value(game.getMyTeam().get(3).getId());
				jw.value(game.getMyTeam().get(4).getId());
				jw.endArray();
				jw.key("enemyTeam");
				jw.array();
				jw.value(game.getEnemyTeam().get(0).getId());
				jw.value(game.getEnemyTeam().get(1).getId());
				jw.value(game.getEnemyTeam().get(2).getId());
				jw.value(game.getEnemyTeam().get(3).getId());
				jw.value(game.getEnemyTeam().get(4).getId());
				jw.endArray();
				jw.key("matchup");
				jw.object();
				jw.key("myChampion");
				jw.value(game.getMatchup().getMyChamp().getId());
				jw.key("myStartItems");
				jw.array();
				for (MatchupItem mi : game.getMatchup().getMyStartItems()) {
					jw.object();
					jw.key("amount");
					jw.value(mi.getAmount());
					jw.key("item");
					jw.value(mi.getItem().getId());
					jw.endObject();
				}
				jw.endArray();
				jw.key("myEndItems");
				jw.array();
				for (MatchupItem mi : game.getMatchup().getMyEndItems()) {
					jw.object();
					jw.key("amount");
					jw.value(mi.getAmount());
					jw.key("item");
					jw.value(mi.getItem().getId());
					jw.endObject();
				}
				jw.endArray();
				jw.key("mySpell1");
				jw.value(game.getMatchup().getMySpell1().getId());
				jw.key("mySpell2");
				jw.value(game.getMatchup().getMySpell2().getId());
				jw.key("enemyChampion");
				jw.value(game.getMatchup().getEnemyChamp().getId());
				jw.key("enemyStartItems");
				jw.array();
				for (MatchupItem mi : game.getMatchup().getEnemyStartItems()) {
					jw.object();
					jw.key("amount");
					jw.value(mi.getAmount());
					jw.key("item");
					jw.value(mi.getItem().getId());
					jw.endObject();
				}
				jw.endArray();
				jw.key("enemyEndItems");
				jw.array();
				for (MatchupItem mi : game.getMatchup().getEnemyEndItems()) {
					jw.object();
					jw.key("amount");
					jw.value(mi.getAmount());
					jw.key("item");
					jw.value(mi.getItem().getId());
					jw.endObject();
				}
				jw.endArray();
				jw.key("enemySpell1");
				jw.value(game.getMatchup().getMySpell1().getId());
				jw.key("enemySpell2");
				jw.value(game.getMatchup().getMySpell2().getId());
				jw.key("matchupResult");
				jw.value(game.getMatchup().getResult().name());
				jw.key("lane");
				jw.value(game.getMatchup().getLane().name());
				jw.key("difficulty");
				jw.value(game.getMatchup().getDifficulty().name());
				jw.key("matchupNotes");
				jw.value(game.getMatchup().getNotes());
				jw.endObject();
				jw.key("gameNotes");
				jw.value(game.getNotes());
				jw.key("ownKills");
				jw.value(game.getOwnKills());
				jw.key("ownDeaths");
				jw.value(game.getOwnDeaths());
				jw.key("ownAssists");
				jw.value(game.getOwnAssists());
				jw.key("ownCS");
				jw.value(game.getOwnCS());
				jw.key("gameResult");
				jw.value(game.getResult().name());
				jw.key("length");
				jw.value(game.getLenght());
				jw.endObject();
				logger.debug("Finished with this game.");
			}
			jw.endArray();
			jw.endObject();
			fw.close();
			logger.debug("All games exported.");
			MainView.getInstance().setStatusText("Export complete");
		} catch (IOException e) {
			logger.error("Export failed. Reason" + e.getMessage());
			MainView.getInstance().setStatusText(
					"Export failed. Please read the logfile");
		}
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