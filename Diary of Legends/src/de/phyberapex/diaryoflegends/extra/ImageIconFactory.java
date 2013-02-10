package de.phyberapex.diaryoflegends.extra;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.base.Constants;

public class ImageIconFactory {

	transient private static Logger logger = LogManager.getLogger(ImageIconFactory.class
			.getName());

	/**
	 * Returns a {@link ImageIcon} from the path given
	 * 
	 * @param path {@link String} path to the file e.g. C:\test.jpg
	 * @return {@link ImageIcon}
	 */
	public static ImageIcon createImageIcon(String path) {
		logger.trace("createImageIcon() - Entering");
		logger.debug("Trying to create ImageIcon from path {}", path);
		URL imgURL;
		ImageIcon icon = null;
		try {
			imgURL = new URL("file:"+path);
			icon = new ImageIcon(imgURL);
		} catch (MalformedURLException e) {
			logger.error("{} is not a valid URL", path);
			logger.error("Errormessage {}", e.getMessage());
		}
		logger.trace("createImageIcon() - Returning");
		logger.debug("Returned {}", icon);
		return icon;
	}
	
	/**
	 * Returns a {@link ImageIcon} from a file within the jar
	 * 
	 * {@link String} path to the file e.g.
	 * @return {@link ImageIcon}
	 */
	public static ImageIcon createImageIconFromResourePath(String path) {
		logger.trace("createImageIcon() - Entering");
		path = Constants.getResourcePath()+path;
		ImageIcon icon = createImageIcon(ImageIconFactory.class.getResource(path).getPath());
		logger.trace("createImageIcon() - Returning");
		logger.debug("Returned {}", icon);
		return icon;
	}
}