package de.phyberapex.diaryoflegends.view;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.base.Initializer;
import de.phyberapex.diaryoflegends.model.Summoner;

public class CurrentSummonerNameView extends JDialog implements View{

	private static final long serialVersionUID = -3649794292915604043L;
	private static CurrentSummonerNameView instance;
	private static Logger logger = LogManager.getLogger(CurrentSummonerNameView.class.getName());
	private JLabel nameLabel;
	private JTextField nameTextfield;
	
	private CurrentSummonerNameView(){
		super();
		logger.trace("CurrentSummonerNameView() - Entering");
		createGUI();
		logger.trace("CurrentSummonerNameView() - Leaving");
	}
	
	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setLayout(new FlowLayout());
		this.add(getNameLabel());
		this.add(getNameTextfield());
		logger.trace("createGUI() - Leaving");
	}

	private JTextField getNameTextfield() {
		logger.trace("getNameTextfield() - Entering");
		if(nameTextfield == null){
			nameTextfield = new JTextField();
		}
		logger.trace("getNameTextfield() - Returning");
		logger.debug("Returned {}", nameTextfield);
		return nameTextfield;
	}

	private JLabel getNameLabel() {
		logger.trace("getNameLabel() - Entering");
		if(nameLabel == null){
			nameLabel = new JLabel("Summoner Name:");
		}
		logger.trace("getNameLabel() - Returning");
		logger.debug("Returned {}", nameLabel);
		return nameLabel;
	}

	/**
	 * Returns an instance of this class if the static attribute instance is null it will be created.
	 * @return {@link Initializer} an instance of this class
	 */
	public static synchronized CurrentSummonerNameView getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of Initializer");
			instance = new CurrentSummonerNameView();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("Returned {}", instance);
		return instance;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.nameTextfield.setText(((Summoner)arg1).getName());
	}

}