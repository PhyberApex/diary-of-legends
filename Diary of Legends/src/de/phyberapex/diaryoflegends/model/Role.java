package de.phyberapex.diaryoflegends.model;

import java.awt.Color;

public enum Role {
	TOP("Top-Lane", Color.decode("#116178")), SUPPORT("Support", Color
			.decode("#3f7811")), MID("Mid-Lane", Color.decode("#783611")), JUNGLE(
			"Jungle", Color.decode("#552266")), AD_CARRY("Ad-Carry", Color
			.decode("#771111"));

	/**
	 * String representation of the Role
	 */
	private String description;
	transient private Color color;

	private Role(String desc, Color rep) {
		this.description = desc;
		this.color = rep;
	}

	/**
	 * 
	 * @return String representation of the Role
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
