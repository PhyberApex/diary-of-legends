package de.phyberapex.diaryoflegends.view.panel;

import javax.swing.JLabel;

import de.phyberapex.diaryoflegends.model.Champion;

public class ChampionLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public ChampionLabel(Champion champion) {
		super(champion.getName());
		this.setIcon(champion.getResizeIcon(30));
	}

}
