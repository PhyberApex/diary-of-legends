package de.phyberapex.diaryoflegends.test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.GameResult;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupDifficulty;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.model.Role;

public class Test {

	public static File getPic(String str) {
		File f = null;
		try {

			f = new File(Test.class.getResource(
					"/de/phyberapex/diaryoflegends/test/img/" + str).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static void main(String[] args) {
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		ObjectContainer dbHandle = Db4oEmbedded.openFile(configuration,
				"db\\test.db");

		File f = new File("C:\\workspace\\Diary of Legends\\img\\champs");
		for (File f1 : f.listFiles()) {
			Champion c = new Champion(f1.getName(), f1);
			dbHandle.store(c);
		}

		File f2 = new File("C:\\workspace\\Diary of Legends\\img\\items");
		for (File f3 : f2.listFiles()) {
			Item c = new Item(f3.getName(), 0, f3);
			dbHandle.store(c);
		}

		// Champion ahri = new Champion("Ahri", getPic("ahri.png"));
		// Champion akali = new Champion("Akali", getPic("akali.png"));
		// Champion alistar = new Champion("Alistar", getPic("alistar.png"));
		// Champion amumu = new Champion("Amumu", getPic("amumu.png"));
		// Champion anivia = new Champion("Anivia", getPic("anivia.png"));
		//
		// List<Champion> team1 = new ArrayList<Champion>();
		// team1.add(ahri);
		// team1.add(akali);
		// team1.add(alistar);
		// team1.add(amumu);
		// team1.add(anivia);
		//
		// Champion annie = new Champion("Annie", getPic("annie.png"));
		// Champion ashe = new Champion("Ashe", getPic("ashe.png"));
		// Champion blitzcrank = new Champion("Blitzcrank",
		// getPic("blitzcrank.png"));
		// Champion brand = new Champion("Brand", getPic("brand.png"));
		// Champion caitlyn = new Champion("Caitlyn", getPic("caitlyn.png"));
		// List<Champion> team2 = new ArrayList<Champion>();
		// team2.add(annie);
		// team2.add(ashe);
		// team2.add(blitzcrank);
		// team2.add(brand);
		// team2.add(caitlyn);
		//
		// Item doransblade = new Item("Dorans blade", 475,
		// getPic("doransblade.png"));
		// Item healthpotion = new Item("Health potion", 35,
		// getPic("healthpotion.png"));
		// Item bootsofspeed = new Item("Boots of Speed", 350,
		// getPic("bootsofspeed.png"));
		//
		// List<MatchupItem> myItems = new ArrayList<>();
		// MatchupItem myItem1 = new MatchupItem(doransblade, 1);
		// myItems.add(myItem1);
		//
		// List<MatchupItem> enemyItems = new ArrayList<>();
		// MatchupItem enemyItem1 = new MatchupItem(bootsofspeed, 1);
		// enemyItems.add(enemyItem1);
		// MatchupItem enemyItem2 = new MatchupItem(healthpotion, 3);
		// enemyItems.add(enemyItem2);
		//
		// Matchup matchup = new Matchup(null, akali, brand, myItems,
		// enemyItems,
		// MatchupResult.WIN, Role.MID, MatchupDifficulty.SUPER_EASY,
		// "MATCHUP_NOTES");
		//
		// Game game = new Game(new Date(), team1, team2, matchup,
		// GameResult.WIN,
		// "GAmE NOTES", 0, 0, 0);
		// dbHandle.store(game);
		// try {
		// ObjectSet<Game> g = dbHandle.query(new Predicate<Game>() {
		//
		// private static final long serialVersionUID = -2610096847484155479L;
		//
		// @Override
		// public boolean match(Game arg0) {
		// return true;
		// }
		// });
		//
		// Iterator<Game> i = g.iterator();
		// while (i.hasNext()) {
		// Game gamo = i.next();
		// System.out.println(gamo.getMatchup().getResult());
		// System.out.println(gamo.getMatchup().getMyChamp() + " vs. "
		// + gamo.getMatchup().getEnemyChamp());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		dbHandle.close();
	}
}
