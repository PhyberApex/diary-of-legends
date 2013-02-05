package de.phyberapex.diaryoflegends.model;

public enum MatchupDifficulty {

	SUPER_EASY("I had a big advantage"), EASY("I had a small advantage"), DRAW(
			"It was pretty even"), HARD("He had a small advantage"), SUPER_HARD(
			"He had a big advantage");

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
}
