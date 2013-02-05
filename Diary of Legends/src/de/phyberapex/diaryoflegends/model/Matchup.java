package de.phyberapex.diaryoflegends.model;

import java.util.List;

public class Matchup extends Model{

	private Game game;
	private Champion myChamp;
	private List<Item> myStartItems;
	private Champion enemyChamp;
	private List<Item> enemyStartItems;
	private MatchupResult result;
	private String notes;
}
