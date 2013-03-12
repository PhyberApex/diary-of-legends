package de.phyberapex.diaryoflegends.model.stats;

import java.util.List;

import de.phyberapex.diaryoflegends.model.Champion;

public class GameStatistic {

	private Champion champ;
	private Integer value;
	private List<Champion> foundChamps;

	public GameStatistic(Champion chmp, Integer value) {
		this.champ = chmp;
		this.value = value;
	}

	public Champion getChampion() {
		return champ;
	}

	public Integer getValue() {
		return value;
	}

	public List<Champion> getFoundChamps() {
		return foundChamps;
	}

	public void setFoundChamps(List<Champion> foundChamps) {
		this.foundChamps = foundChamps;
	}
}
