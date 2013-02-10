package de.phyberapex.diaryoflegends.model;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Item extends Model{

	private int price;
	private ImageIcon icon;
	transient private static Logger logger = LogManager.getLogger(Item.class.getName());
	
	
 	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
}
