package de.phyberapex.diaryoflegends.model;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.extra.ConvertImage;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;

public class Champion extends Model {

	private int id;
	private String name;
	transient ImageIcon icon;
	transient private static Logger logger = LogManager
			.getLogger(Champion.class.getName());

	public Champion(int id, String name) {
		logger.trace("Champion() - Entering");
		logger.debug("Champion() - Parameter: {}, {}", id, name);
		this.id = id;
		this.setName(name);
		logger.trace("Champion() - Leaving");
	}

	public Champion() {
	}

	/**
	 * Returns the id of this champion
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
	 * Sets the id of this champion
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
		logger.trace("getIcon() - Entering");
		ImageIcon img = null;
		if (id != 0) {
			if (icon != null) {
				img = icon;
			} else {
				img = ImageIconFactory.createImageIcon(System
						.getProperty("user.dir")
						+ "/img/champions/"
						+ this.getId() + ".png");
			}
			if (img.getIconHeight() <= 0) {
				try {
					byte[] tmp = ConvertImage.convertUrlToByteArray(new URL(
							"http://img.lolking.net/shared/riot/images/champions/"
									+ getId() + "_104.png"));
					if (tmp != null) {
						File f = new File(System.getProperty("user.dir")
								+ "/img/champions/" + getId() + ".png");
						f.getParentFile().mkdirs();
						FileOutputStream fou = new FileOutputStream(f);
						fou.write(tmp);
						fou.close();
					}
				} catch (Exception e) {
					logger.error("Couln't write icon:" + e.getMessage());
				}
				img = ImageIconFactory.createImageIcon(System
						.getProperty("user.dir")
						+ "/img/champions/"
						+ this.getId() + ".png");
				if (img.getIconHeight() <= 0) {
					img = ImageIconFactory
							.createImageIconFromResourePath("img/empty_104x104.png");
				}
			}
		} else {
			img = ImageIconFactory
					.createImageIconFromResourePath("img/empty_104x104.png");
		}
		logger.trace("getIcon() - Returning");
		logger.debug("getIcon() - Returning: {}", img);
		return img;
	}

	public void setIcon(URL url) {
		logger.trace("setIcon() - Entering");
		logger.debug("setIcon() - Parameter: {}", url);
		if (id != 0) {
			byte[] tmp = ConvertImage.convertUrlToByteArray(url);
			File f = new File(System.getProperty("user.dir")
					+ "/img/champions/" + getId() + ".png");
			f.getParentFile().mkdirs();
			try {
				FileOutputStream fou = new FileOutputStream(f);
				fou.write(tmp);
				fou.close();
			} catch (Exception e) {
				logger.error("Couln't write icon:" + e.getMessage());
			}
		}
		this.icon = new ImageIcon(url);
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

	@Override
	public boolean equals(Object o) {
		logger.trace("equals() - Entering");
		boolean returnValue = false;
		if (o != null && ((Champion) o).getId() == this.getId()) {
			return true;
		}
		logger.trace("equals() - Returning");
		logger.debug("equals() - Returning: {}", returnValue);
		return returnValue;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + id;
		hash = hash * 31 + name.hashCode();
		if (icon != null) {
			hash = hash * 13 + icon.hashCode();
		}
		return hash;
	}

	/**
	 * @return
	 */
	public Icon getResizeIcon(int res) {
		return ImageIconFactory.resizeImageIcon(getIcon(), res, res);
	}
}
