package de.phyberapex.diaryoflegends.model;

import java.awt.Color;

public enum Role {
	TOP("Top-Lane", Color.ORANGE), SUPPORT("Support", Color.YELLOW), MID("Mid-Lane", Color.BLUE), JUNGLE("Jungle", Color.GREEN), AD_CARRY(
			"Ad-Carry",Color.RED);

	/**
	 * String representation of the Role
	 */
	private String description;
	private Color color;

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

	public Color getColor(){
		return this.color;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
