package de.phyberapex.diaryoflegends.test;

import java.util.Observable;
import java.util.Observer;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.config.TNull;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.base.Constants;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Summoner;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;

public class Test {

	public static void main(String[] args){
		EmbeddedConfiguration configuration = Db4oEmbedded
				.newConfiguration();
		configuration.common().objectClass(Observer.class).translate(new TNull());
		configuration.common().objectClass(Observable.class).translate(new TNull());
		ObjectContainer dbHandle = Db4oEmbedded.openFile(configuration,
				"db\\test.tb");
		
		Champion c = new Champion();
		c.setName("Hello");
		dbHandle.store(c);
		
		Item i = new Item();
		i.setPrice(200);
		dbHandle.store(i);
		
		Summoner s = new Summoner("Hi", null);
		dbHandle.store(s);
		dbHandle.close();
	}
}
