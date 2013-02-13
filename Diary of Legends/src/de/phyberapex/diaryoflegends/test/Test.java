package de.phyberapex.diaryoflegends.test;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.config.TNull;
import com.db4o.query.Predicate;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.base.Constants;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.Summoner;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;

public class Test {

	public static void main(String[] args) {
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		ObjectContainer dbHandle = Db4oEmbedded.openFile(configuration,
				"db\\test.db");

		Champion c = new Champion("Hallo", new File("C:\\test.png"));
		dbHandle.store(c);

		Item i = new Item();
		i.setIcon(new File("C:\\test.png"));
		i.setPrice(200);
		i.setName("test");
		dbHandle.store(i);

		Summoner s = new Summoner("Hi", null);
		dbHandle.store(s);

		Champion c1 = new Champion();
		c1.setName("Akali");

		Champion c2 = new Champion();
		c2.setName("Darius");

		Matchup matchup = new Matchup();
		matchup.setMyChamp(c1);
		matchup.setEnemyChamp(c2);
		
		Game g = new Game();
		g.setMatchup(matchup);
		g.setDate(new Date());
		dbHandle.store(g);
		ObjectSet<Champion> set = dbHandle.query(new Predicate<Champion>() {

			@Override
			public boolean match(Champion arg0) {
				return true;
			}

		});
		Iterator<Champion> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().getName());
		}

		ObjectSet<Item> set2 = dbHandle.query(new Predicate<Item>() {

			@Override
			public boolean match(Item arg0) {
				return true;
			}

		});
		Iterator<Item> it2 = set2.iterator();
		while (it2.hasNext()) {
			System.out.println(it2.next().getName());
		}
		dbHandle.close();
	}
}
