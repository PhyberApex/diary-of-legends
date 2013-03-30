package de.phyberapex.diaryoflegends.model.stats;

import java.util.List;

import de.phyberapex.diaryoflegends.model.Champion;

public class Top3EnemyStatistics {
	private List<Champion> myChamp;
	private Champion enemy1Champ;
	private double enemy1diff;
	private Champion enemy2Champ;
	private double enemy2diff;
	private Champion enemy3Champ;
	private double enemy3diff;

	public Top3EnemyStatistics(List<Champion> myChamp, Champion enemyChamp1,
			double diff1, Champion enemyChamp2, double diff2,
			Champion enemyChamp3, double diff3) {
		this.myChamp = myChamp;
		this.enemy1Champ = enemyChamp1;
		this.enemy1diff = diff1;
		this.enemy2Champ = enemyChamp2;
		this.enemy2diff = diff2;
		this.enemy3Champ = enemyChamp3;
		this.enemy3diff = diff3;
	}

	public List<Champion> getMyChamp() {
		return myChamp;
	}

	public Champion getEnemy1Champ() {
		return enemy1Champ;
	}

	public double getEnemy1diff() {
		return Math.round(enemy1diff * 1000) / 1000.0;
	}

	public Champion getEnemy2Champ() {
		return enemy2Champ;
	}

	public double getEnemy2diff() {
		return Math.round(enemy2diff * 1000) / 1000.0;
	}

	public Champion getEnemy3Champ() {
		return enemy3Champ;
	}

	public double getEnemy3diff() {
		return Math.round(enemy3diff * 1000) / 1000.0;
	}
}