package de.phyberapex.diaryoflegends.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.phyberapex.diaryoflegends.Observable;
import de.phyberapex.diaryoflegends.controller.ItemController;

public class ItemView extends JPanel implements View{

	private static final long serialVersionUID = -8456209180736169342L;
	private ItemController controller;

	public ItemView(ItemController controller){
		this.controller = controller;
		this.add(new JLabel("Items"));
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
