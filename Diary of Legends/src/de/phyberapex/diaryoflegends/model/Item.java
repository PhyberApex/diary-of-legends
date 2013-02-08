package de.phyberapex.diaryoflegends.model;

import javax.swing.ImageIcon;

public class Item extends Model{

	private int price;
	private ImageIcon icon;
	
	
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
