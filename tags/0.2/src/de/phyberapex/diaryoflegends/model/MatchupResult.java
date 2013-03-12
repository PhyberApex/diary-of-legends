package de.phyberapex.diaryoflegends.model;

import java.awt.Color;

public enum MatchupResult {
	WIN("Won the lane", Color.GREEN), LOSS("Lost the lane", Color.RED), DRAW(
			"Equal", Color.WHITE);

	/**
	 * String representation of the result
	 */
	private String description;
	private Color color;

	private MatchupResult(String desc, Color col) {
		this.description = desc;
		this.color = col;
	}

	/**
	 * 
	 * @return String representation of the result
	 */
	public String getDescription() {
		return this.description;
	}

	public Color getColor() {
		return this.color;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
