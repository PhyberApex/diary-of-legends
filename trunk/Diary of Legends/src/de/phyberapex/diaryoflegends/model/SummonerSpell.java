package de.phyberapex.diaryoflegends.model;

import java.io.File;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.extra.ConvertImage;

public class SummonerSpell extends Model {

	private int id;
	private String name;
	private byte[] icon;
	transient private static Logger logger = LogManager
			.getLogger(SummonerSpell.class.getName());

	public SummonerSpell(int id, String name, File icon) {
		logger.trace("SummonerSpell() - Entering");
		logger.debug("SummonerSpell() - Parameter: {}, {}, {}", id, name, icon);
		this.id = id;
		this.setName(name);
		if (icon != null) {
			this.setIcon(icon);
		}
		logger.trace("SummonerSpell() - Leaving");
	}

	public SummonerSpell() {
	}

	/**
	 * Returns the id of this spell
	 * 
	 * @return {@link int}
	 */
	public int getId() {
		logger.trace("getId() - Entering");
		logger.trace("getId() - Returning");
		logger.debug("getId() - Returning: {}", id);
		return id;
	}

	/**
	 * Sets the id of this spell
	 * 
	 * @param name
	 *            {@link int} The spell to change to
	 */
	public void setId(int id) {
		logger.trace("setId() - Entering");
		logger.debug("setId() - Parameter: {}", name);
		this.id = id;
		logger.trace("setId() - Leaving");
	}

	/**
	 * Returns the name of this spell
	 * 
	 * @return {@link String}
	 */
	public String getName() {
		logger.trace("getName() - Entering");
		logger.trace("getName() - Returning");
		logger.debug("getName() - Returning: {}", name);
		return name;
	}

	/**
	 * Sets the name of this spell
	 * 
	 * @param name
	 *            {@link String} The name to change to
	 */
	public void setName(String name) {
		logger.trace("setName() - Entering");
		logger.debug("setName() - Parameter: {}", name);
		this.name = name;
		logger.trace("setName() - Leaving");
	}

	/**
	 * Returns the {@link ImageIcon} for this spell
	 * 
	 * @return {@link ImageIcon} The image for this spell
	 */
	public ImageIcon getIcon() {
		logger.trace("getName() - Entering");
		ImageIcon img = null;
		if (icon != null) {
			img = ConvertImage.convertByteArrayToImageIcon(icon);
		}
		logger.trace("getName() - Returning");
		logger.debug("getName() - Returning: {}", img);
		return img;
	}

	/**
	 * Returns the raw byte array of the icon
	 * 
	 * @return {@link byte[]}
	 */
	public byte[] getIconRaw() {
		logger.trace("getIconRaw() - Entering");
		logger.trace("getIconRaw() - Returning");
		logger.debug("getIconRaw() - Returning: {}", icon);
		return icon;
	}

	/**
	 * The file that represents the icon
	 * 
	 * @param file
	 *            {@link File} The file from which the image will be created
	 */
	public void setIcon(File file) {
		logger.trace("setIcon() - Entering");
		logger.debug("setIcon() - Parameter: {}", file);
		this.icon = ConvertImage.convertFileToByteArray(file);
		logger.trace("setIcon() - Leaving");
	}

	/**
	 * Returns a string representation of this spell
	 * 
	 * @return {@link String} The string representation
	 */
	@Override
	public String toString() {
		logger.trace("toString() - Entering");
		String str = this.getName();
		logger.trace("toString() - Returning");
		logger.debug("toString() - Returning: {}", str);
		return str;
	}

	@Override
	public boolean equals(Object o) {
		logger.trace("equals() - Entering");
		boolean returnValue = false;
		if (o instanceof SummonerSpell
				&& ((SummonerSpell) o).getId() == this.getId()) {
			return true;
		}
		logger.trace("equals() - Returning");
		logger.debug("equals() - Returning: {}", returnValue);
		return returnValue;
	}
}
