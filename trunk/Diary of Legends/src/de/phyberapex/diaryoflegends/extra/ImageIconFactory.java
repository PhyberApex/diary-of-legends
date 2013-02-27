package de.phyberapex.diaryoflegends.extra;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.base.Constants;

public class ImageIconFactory {

	transient private static Logger logger = LogManager
			.getLogger(ImageIconFactory.class.getName());

	/**
	 * Returns a {@link ImageIcon} from the path given
	 * 
	 * @param path
	 *            {@link String} path to the file e.g. C:\test.jpg
	 * @return {@link ImageIcon}
	 */
	public static ImageIcon createImageIcon(String path) {
		logger.trace("createImageIcon() - Entering");
		logger.debug("createImageIcon() - Parameter: {}", path);
		logger.debug("Trying to create ImageIcon from path {}", path);
		URL imgURL;
		ImageIcon icon = null;
		try {
			imgURL = new URL("file:" + path);
			icon = new ImageIcon(imgURL);
		} catch (MalformedURLException e) {
			logger.error("{} is not a valid URL", path);
			logger.error("Errormessage {}", e.getMessage());
		}
		logger.trace("createImageIcon() - Returning");
		logger.debug("createImageIcon() - Returning: {}", icon);
		return icon;
	}

	/**
	 * Returns a {@link ImageIcon} from a file within the jar
	 * 
	 * {@link String} path to the file e.g.
	 * 
	 * @return {@link ImageIcon}
	 */
	public static ImageIcon createImageIconFromResourePath(String path) {
		logger.trace("createImageIconFromResourePath() - Entering");
		logger.debug("createImageIconFromResourePath() - Parameter: {}", path);
		path = Constants.getResourcePath() + path;
		logger.debug("New path is {}", path);
		ImageIcon icon = null;
		URL imgURL = ImageIconFactory.class.getResource(path);
		if (imgURL != null) {
			icon = new ImageIcon(imgURL);
		}
		logger.trace("createImageIconFromResourePath() - Returning");
		logger.debug("createImageIconFromResourePath() - Returning: {}", icon);
		return icon;
	}

	public static ImageIcon resizeImageIcon(ImageIcon icon, int height,
			int width) {
		logger.trace("resizeImageIcon() - Entering");
		logger.debug("resizeImageIcon() - Parameter: {}, {}, {}", icon, height,
				width);
		Image img = icon.getImage();
		ImageIcon newIcon = new ImageIcon(img.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH));
		logger.trace("resizeImageIcon() - Returning");
		logger.debug("resizeImageIcon() - Returning: {}", newIcon);
		return newIcon;
	}

	/**
	 * Returns 1 of 50 loading images
	 * @return
	 */
	public static ImageIcon getRandomLoadingIcon() {
		logger.trace("getRandomLoadingIcon() - Entering");
		ImageIcon icon = null;
		int rand = (int)(Math.random() * 20 + 1);
		String path = Constants
				.getResourcePath() + "img/splash/("+rand+").png";
		URL imgURL = ImageIconFactory.class.getResource(path);
		if (imgURL != null) {
			icon = new ImageIcon(imgURL);
		}
		logger.trace("getRandomLoadingIcon() - Returning");
		logger.debug("getRandomLoadingIcon() - Returning: {}", icon);
		return icon;
	}
}