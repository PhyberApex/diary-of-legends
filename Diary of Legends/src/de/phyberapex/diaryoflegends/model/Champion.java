package de.phyberapex.diaryoflegends.model;

import java.io.File;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.extra.ConvertImage;

public class Champion extends Model {

	private String name;
	private byte[] icon;
	private static Logger logger = LogManager.getLogger("");

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageIcon getIcon() {
		return ConvertImage.convertByteArrayToImageIcon(icon);
	}
	public void setIcon(File file) {
		this.icon = ConvertImage.convertFileToByteArray(file);
	}
}
