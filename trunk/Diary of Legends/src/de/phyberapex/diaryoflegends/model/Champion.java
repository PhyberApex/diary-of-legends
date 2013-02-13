package de.phyberapex.diaryoflegends.model;

import java.io.File;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.extra.ConvertImage;

public class Champion extends Model {

	private String name;
	private byte[] icon;
	transient private static Logger logger = LogManager
			.getLogger(Champion.class.getName());

	public Champion(String name, File icon) {
		logger.trace("Champion() - Entering");
		logger.debug("Champion() - Parameter: {}, {}", name, icon);
		this.setName(name);
		this.setIcon(icon);
		logger.trace("Champion() - Leaving");
	}

	public Champion() {
	}

	/**
	 * Returns the name of this champion
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
	 * Sets the name of this champion
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
	 * Returns the {@link ImageIcon} for this champion
	 * 
	 * @return {@link ImageIcon} The image for this champion
	 */
	public ImageIcon getIcon() {
		logger.trace("getName() - Entering");
		ImageIcon img = ConvertImage.convertByteArrayToImageIcon(icon);
		logger.trace("getName() - Returning");
		logger.debug("getName() - Returning: {}", img);
		return img;
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
	 * Returns a string representation of this Champion
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
}
