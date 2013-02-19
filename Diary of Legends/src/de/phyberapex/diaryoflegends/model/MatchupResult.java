package de.phyberapex.diaryoflegends.model;

public enum MatchupResult {
	WIN("Won the lane"), LOSS("Lost the lane"), DRAW("Equal");

	/**
	 * String representation of the result
	 */
	private String description;

	private MatchupResult(String desc) {
		this.description = desc;
	}

	/**
	 * 
	 * @return String representation of the result
	 */
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
