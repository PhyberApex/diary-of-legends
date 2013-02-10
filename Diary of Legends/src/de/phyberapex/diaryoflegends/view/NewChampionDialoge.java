package de.phyberapex.diaryoflegends.view;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.phyberapex.diaryoflegends.model.Champion;

public class NewChampionDialoge extends JDialog{
	
	private Champion champ;
	private JLabel nameLabel;
	private JLabel iconLabel;
	private JTextField nameTextField;
	private JFileChooser iconFileChooser;
	
	public NewChampionDialoge(){
		super();
	}

	public Champion getChamp() {
		return champ;
	}

	public void setChamp(Champion champ) {
		this.champ = champ;
	}

	public JLabel getNameLabel() {
		if(nameLabel == null){
			
		}
		return nameLabel;
	}

	public JLabel getIconLabel() {
		return iconLabel;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JFileChooser getIconFileChooser() {
		return iconFileChooser;
	}

}
