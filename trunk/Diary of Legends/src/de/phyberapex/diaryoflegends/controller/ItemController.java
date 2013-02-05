package de.phyberapex.diaryoflegends.controller;

import de.phyberapex.diaryoflegends.view.ItemView;

public class ItemController extends Controller{
	
	public ItemController(MainController mainController) {
		super(mainController);
		this.view = new ItemView(this);
	}

	@Override
	public void loadGUI() {
		
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		
	}
}
