package de.phyberapex.diaryoflegends.model;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Matchup extends Model{

	private Game game;
	private Champion myChamp;
	private List<Item> myStartItems;
	private Champion enemyChamp;
	private List<Item> enemyStartItems;
	private MatchupResult result;
	private String notes;
	transient private static Logger logger = LogManager.getLogger(Matchup.class.getName());
}
