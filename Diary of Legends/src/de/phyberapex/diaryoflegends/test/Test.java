package de.phyberapex.diaryoflegends.test;

import java.io.File;
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

	public static void main(String[] args) {
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		ObjectContainer dbHandle = Db4oEmbedded.openFile(configuration,
				"db\\test.db");

		Champion ahri = new Champion("Ahri", new File("C:\\test\\ahri.png"));
		Champion akali = new Champion("Akali", new File("C:\\test\\akali.png"));
		Champion alistar = new Champion("Alistar", new File(
				"C:\\test\\alistar.png"));
		Champion amumu = new Champion("Amumu", new File("C:\\test\\amumu.png"));
		Champion anivia = new Champion("Anivia", new File(
				"C:\\test\\anivia.png"));

		List<Champion> team1 = new ArrayList<Champion>();
		team1.add(ahri);
		team1.add(akali);
		team1.add(alistar);
		team1.add(amumu);
		team1.add(anivia);

		Champion annie = new Champion("Annie", new File("C:\\test\\annie.png"));
		Champion ashe = new Champion("Ashe", new File("C:\\test\\ashe.png"));
		Champion blitzcrank = new Champion("Blitzcrank", new File(
				"C:\\test\\blitzcrank.png"));
		Champion brand = new Champion("Brand", new File("C:\\test\\brand.png"));
		Champion caitlyn = new Champion("Caitlyn", new File(
				"C:\\test\\caitlyn.png"));
		List<Champion> team2 = new ArrayList<Champion>();
		team1.add(annie);
		team1.add(ashe);
		team1.add(blitzcrank);
		team1.add(brand);
		team1.add(caitlyn);

		Item doransblade = new Item("Dorans blade", 475, new File(
				"C:\\test\\doransblade.png"));
		Item healthpotion = new Item("Health potion", 475, new File(
				"C:\\test\\healthpotion.png"));
		Item bootsofspeed = new Item("Boots of Speed", 475, new File(
				"C:\\test\\bootsofspeed.png"));

		List<MatchupItem> myItems = new ArrayList<>();
		MatchupItem myItem1 = new MatchupItem(doransblade, 1);
		myItems.add(myItem1);

		List<MatchupItem> enemyItems = new ArrayList<>();
		MatchupItem enemyItem1 = new MatchupItem(bootsofspeed, 1);
		enemyItems.add(enemyItem1);
		MatchupItem enemyItem2 = new MatchupItem(healthpotion, 3);
		enemyItems.add(enemyItem2);

		Matchup matchup = new Matchup(null, akali, brand, myItems, enemyItems,
				MatchupResult.WIN, Role.MID, MatchupDifficulty.SUPER_EASY,
				"MATCHUP_NOTES");

		Game game = new Game(new Date(), team1, team2, matchup, GameResult.WIN,
				"GAmE NOTES", 0, 0, 0);
		dbHandle.store(game);
		try {
			ObjectSet<Game> g = dbHandle.query(new Predicate<Game>() {

				@Override
				public boolean match(Game arg0) {
					return true;
				}
			});

			Iterator<Game> i = g.iterator();
			while (i.hasNext()) {
				Game gamo = i.next();
				System.out.println(gamo.getMatchup().getResult());
				System.out.println(gamo.getMatchup().getMyChamp() + " vs. "+gamo.getMatchup().getEnemyChamp());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbHandle.close();
	}
}
