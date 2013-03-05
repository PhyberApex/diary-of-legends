package de.phyberapex.diaryoflegends.model;

public enum MatchupDifficulty {

	SUPER_EASY("Big advantage", 1), EASY("Small advantage", 2), DRAW(
			"Pretty even", 3), HARD("Small disadvantage", 4), SUPER_HARD(
			"Big disadvantage", 5);

	/**
	 * String representation of the difficulty
	 */
	private String description;
	private int value;

	private MatchupDifficulty(String desc, int value) {
		this.description = desc;
		this.value = value;
	}

	/**
	 * 
	 * @return String representation of the difficulty
	 */
	public String getDescription() {
		return this.description;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
