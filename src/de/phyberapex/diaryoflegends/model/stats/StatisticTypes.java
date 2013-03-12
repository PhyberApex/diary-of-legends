package de.phyberapex.diaryoflegends.model.stats;

public enum StatisticTypes {
	DEFAULT_STATS("Game statistics"), TOP_3_HARDEST_ENEMY_PER_CHAMPS(
			"Enemy TOP 3 per champion"), TOP_3_HARDEST_ENEMY_MULTIPLE_CHAMPS(
			"Enemy TOP 3 for multiple champions");

	private String desc;

	private StatisticTypes(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return this.desc;
	}
}