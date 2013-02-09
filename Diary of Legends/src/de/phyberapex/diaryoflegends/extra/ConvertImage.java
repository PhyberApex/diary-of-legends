package de.phyberapex.diaryoflegends.extra;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

public class ConvertImage {

	public static byte[] convertFileToByteArray(File file) {
		byte[] bytes = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
				// no doubt here is 0
				/*
				 * Writes len bytes from the specified byte array starting at
				 * offset off to this byte array output stream.
				 */
				System.out.println("read " + readNum + " bytes,");
			}
			bytes = bos.toByteArray();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}

	public static ImageIcon convertByteArrayToImageIcon(byte[] b) {
		ImageIcon img = new ImageIcon(b);
		return img;
	}
}