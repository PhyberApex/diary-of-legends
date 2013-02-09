package de.phyberapex.diaryoflegends.test;

import java.io.File;
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
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Summoner;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;

public class Test {

	public static void main(String[] args){
		EmbeddedConfiguration configuration = Db4oEmbedded
				.newConfiguration();
		ObjectContainer dbHandle = Db4oEmbedded.openFile(configuration,
				"db\\test.tb");
		
		Champion c = new Champion();
		c.setName("Hello");
		c.setIcon(new File("C:\\test.png"));
		dbHandle.store(c);
		
		Item i = new Item();
		i.setPrice(200);
		dbHandle.store(i);
		
		Summoner s = new Summoner("Hi", null);
		dbHandle.store(s);
		
		ObjectSet<Champion> set = dbHandle.query(new Predicate<Champion>(){

			@Override
			public boolean match(Champion arg0) {
				return true;
			}
			
		});
		Iterator<Champion> it = set.iterator(); 
		while (it.hasNext()) {
			System.out.println(it.next().getName());
		}
		
		dbHandle.close();
	}
}