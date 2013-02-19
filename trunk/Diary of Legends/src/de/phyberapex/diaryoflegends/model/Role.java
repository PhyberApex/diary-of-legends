package de.phyberapex.diaryoflegends.model;

public enum Role {
	TOP("Top-Lane"), SUPPORT("Support"), MID("Mid-Lane"), JUNGLE("Jungle"), AD_CARRY(
			"Ad-Carry");

	/**
	 * String representation of the Role
	 */
	private String description;

	private Role(String desc) {
		this.description = desc;
	}

	/**
	 * 
	 * @return String representation of the Role
	 */
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
