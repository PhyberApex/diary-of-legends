package de.phyberapex.diaryoflegends.model;

import java.io.File;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.extra.ConvertImage;

public class Item extends Model {

	private int id;
	private String name;
	private int price;
	private byte[] icon;
	transient private static Logger logger = LogManager.getLogger(Item.class
			.getName());

	public Item(int id, String name, int price, File icon) {
		logger.trace("Champion() - Entering");
		logger.debug("Champion() - Parameter: {}, {}, {}, {}", id, name, price,
				icon);
		this.id = id;
		this.setName(name);
		if (icon != null) {
			this.setIcon(icon);
		}
		this.setPrice(price);
		logger.trace("Champion() - Leaving");
	}

	public Item() {

	}

	/**
	 * Returns the id of this item
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
	 * Sets the id of this item
	 * 
	 * @param name
	 *            {@link int} The name to change to
	 */
	public void setId(int id) {
		logger.trace("setId() - Entering");
		logger.debug("setId() - Parameter: {}", name);
		this.id = id;
		logger.trace("setId() - Leaving");
	}

	/**
	 * Returns the name of this item
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
	 * Sets the name of this item
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
	 * Returns the price of this item
	 * 
	 * @return {@link int}
	 */
	public int getPrice() {
		logger.trace("getPrice() - Entering");
		logger.trace("getPrice() - Returning");
		logger.debug("getPrice() - Returning: {}", price);
		return price;
	}

	/**
	 * Sets the price of this item
	 * 
	 * @param name
	 *            {@link int} The name to change to
	 */
	public void setPrice(int price) {
		logger.trace("setPrice() - Entering");
		logger.debug("setPrice() - Parameter: {}", price);
		this.price = price;
		logger.trace("setName() - Leaving");
	}

	/**
	 * Returns the {@link ImageIcon} for this item
	 * 
	 * @return {@link ImageIcon} The image for this item
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
	 * Returns a string representation of this item
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
		if (o instanceof Item && ((Item)o).getId() == this.getId()){
			return true;
		}
		logger.trace("equals() - Returning");
		logger.debug("equals() - Returning: {}", returnValue);
		return returnValue;
	}
}
