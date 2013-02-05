package de.phyberapex.diaryoflegends.model;

import java.util.Date;
import java.util.List;

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
}
