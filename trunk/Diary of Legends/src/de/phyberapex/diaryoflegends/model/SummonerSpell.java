package de.phyberapex.diaryoflegends.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.extra.ConvertImage;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;

public class SummonerSpell extends Model {

	private int id;
	private String name;
	transient ImageIcon icon;
	transient private static Logger logger = LogManager
			.getLogger(SummonerSpell.class.getName());

	public SummonerSpell(int id, String name) {
		logger.trace("SummonerSpell() - Entering");
		logger.debug("SummonerSpell() - Parameter: {}, {}, {}", id, name, icon);
		this.id = id;
		this.setName(name);
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
		logger.trace("getIcon() - Entering");
		ImageIcon img = null;
		if (id != 0) {
			if (icon != null) {
				img = icon;
			} else {
				img = ImageIconFactory.createImageIcon(System
						.getProperty("user.dir")
						+ "/img/summonerspells/"
						+ this.getId() + ".png");
			}
			if (img.getIconHeight() <= 0) {
				try {
					byte[] tmp = ConvertImage.convertUrlToByteArray(new URL(
							"http://img.lolking.net/images/spells/" + getId()
									+ ".png"));
					File f = new File(System.getProperty("user.dir")
							+ "/img/items/" + getId() + ".png");
					FileOutputStream fou = new FileOutputStream(f);
					fou.write(tmp);
					fou.close();
				} catch (IOException e) {
					logger.error("Couln't write icon:" + e.getMessage());
				}
				img = ImageIconFactory.createImageIcon(System
						.getProperty("user.dir")
						+ "/img/summonerspells/"
						+ this.getId() + ".png");
				if (img.getIconHeight() <= 0) {
					img = ImageIconFactory
							.createImageIconFromResourePath("img/empty_60x60.png");
				}
			}
		} else {
			img = ImageIconFactory
					.createImageIconFromResourePath("img/empty_60x60.png");
		}
		logger.trace("getIcon() - Returning");
		logger.debug("getIcon() - Returning: {}", img);
		return img;
	}

	/**
	 * The file that represents the icon
	 * 
	 * @param file
	 *            {@link File} The file from which the image will be created
	 */
	public void setIcon(URL url) {
		logger.trace("setIcon() - Entering");
		logger.debug("setIcon() - Parameter: {}", url);
		if (id != 0) {
			byte[] tmp = ConvertImage.convertUrlToByteArray(url);
			File f = new File(System.getProperty("user.dir")
					+ "/img/summonerspells/" + getId() + ".png");
			try {
				FileOutputStream fou = new FileOutputStream(f);
				fou.write(tmp);
				fou.close();
			} catch (IOException e) {
				logger.error("Couln't write icon:" + e.getMessage());
			}
		}
		this.icon = new ImageIcon(url);
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

	/**
	 * @return
	 */
	public Icon getResizeIcon(int res) {
		return ImageIconFactory.resizeImageIcon(getIcon(), res, res);
	}
}
