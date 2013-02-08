package de.phyberapex.diaryoflegends.test;

import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.base.Constants;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Summoner;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;

public class Test {

	public static void main(String[] args){
		Champion c = new Champion();
		c.setName("Hello");
		ChampionUtil.saveChampion(c);
		
		Item i = new Item();
		i.setPrice(200);
		Config.getInstance().getDBHandle().store(i);
		
		Summoner s = new Summoner("Hi", null);
		Config.getInstance().getDBHandle().store(s);
	}
}
