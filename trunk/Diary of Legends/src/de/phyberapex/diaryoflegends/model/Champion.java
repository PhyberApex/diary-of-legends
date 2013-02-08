package de.phyberapex.diaryoflegends.model;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Champion extends Model {

	private String name;
	private ImageIcon icon;
	private static Logger logger = LogManager.getLogger("");

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
}
