package de.phyberapex.diaryoflegends.model;

public enum MatchupDifficulty {

	SUPER_EASY("Big advantage"), EASY("Small advantage"), DRAW(
			"Pretty even"), HARD("Small disadvantage"), SUPER_HARD(
			"Big disadvantage");

	/**
	 * String representation of the difficulty
	 */
	private String description;

	private MatchupDifficulty(String desc) {
		this.description = desc;
	}

	/**
	 * 
	 * @return String representation of the difficulty
	 */
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
