package de.phyberapex.diaryoflegends.controller;

import de.phyberapex.diaryoflegends.view.ChampionView;

public class ChampionController extends Controller{

	public ChampionController(MainController mainController) {
		super(mainController);
		this.view = new ChampionView(this);
	}

	@Override
	public void loadGUI() {
		
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		
	}

}
