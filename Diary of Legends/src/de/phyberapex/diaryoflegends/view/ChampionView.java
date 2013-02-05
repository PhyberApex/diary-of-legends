package de.phyberapex.diaryoflegends.view;

import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.phyberapex.diaryoflegends.controller.ChampionController;

public class ChampionView extends JPanel implements View{

	private static final long serialVersionUID = -8456209180736169342L;
	private ChampionController controller;

	public ChampionView(ChampionController controller){
		this.controller = controller;
		this.add(new JLabel("CHAMPIOBNS"));
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
