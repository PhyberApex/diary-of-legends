package de.phyberapex.diaryoflegends.model;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game extends Model{

	private Date date;
	private List<Champion> myTeam;
	private List<Champion> enemyTeam;
	private Matchup matchup;
	private GameResult result;
	private String notes;
	private int ownKills;
	private int ownDeaths;
	private int ownAssists;
	transient private static Logger logger = LogManager.getLogger(Game.class.getName());
}
