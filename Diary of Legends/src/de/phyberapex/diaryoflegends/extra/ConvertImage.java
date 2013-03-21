package de.phyberapex.diaryoflegends.extra;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConvertImage {

	private static Logger logger = LogManager.getLogger(ConvertImage.class
			.getName());

	public static byte[] convertFileToByteArray(File file) {
		byte[] bytes = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			bytes = bos.toByteArray();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public static ImageIcon convertByteArrayToImageIcon(byte[] b) {
		ImageIcon img = new ImageIcon(b);
		return img;
	}

	/**
	 * @param url
	 * @return
	 */
	public static byte[] convertUrlToByteArray(URL url) {
		byte[] bytes = null;
		try {
			URLConnection conn = url.openConnection();
			int length = conn.getContentLength();
			if (length != -1) {
				InputStream in = conn.getInputStream();
				ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
				byte[] buf = new byte[1024];
				for (int readNum; (readNum = in.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}
				bytes = bos.toByteArray();
				in.close();
			} else {
				logger.error("Couldn't open the icon file at: {}",
						url.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
}