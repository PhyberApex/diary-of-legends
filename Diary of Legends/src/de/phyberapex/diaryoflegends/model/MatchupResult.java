package de.phyberapex.diaryoflegends.model;

import java.awt.Color;

public enum MatchupResult {
	WIN("Won the lane", new Color(0, 204, 51)), LOSS("Lost the lane",
			new Color(204, 0, 0)), DRAW("Equal", Color.ORANGE);

	/**
	 * String representation of the result
	 */
	transient private String description;
	transient private Color color;

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
