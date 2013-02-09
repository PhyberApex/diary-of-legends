package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.base.Initializer;
import de.phyberapex.diaryoflegends.model.Summoner;
import de.phyberapex.diaryoflegends.observer.Observable;

public class CurrentSummonerNameView extends JPanel implements View {

	private static final long serialVersionUID = -3649794292915604043L;
	private static CurrentSummonerNameView instance;
	private static Logger logger = LogManager
			.getLogger(CurrentSummonerNameView.class.getName());
	private JLabel nameLabel;
	private JTextField nameTextfield;

	private CurrentSummonerNameView() {
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
		if (nameTextfield == null) {
			nameTextfield = new JTextField("...");
			nameTextfield.setToolTipText("The name you want to be highlighted");
			nameTextfield.setPreferredSize(new Dimension(150, 30));
		}
		logger.trace("getNameTextfield() - Returning");
		logger.debug("getNameTextfield() - Returning: {}", nameTextfield);
		return nameTextfield;
	}

	private JLabel getNameLabel() {
		logger.trace("getNameLabel() - Entering");
		if (nameLabel == null) {
			nameLabel = new JLabel("Summoner Name:");
		}
		logger.trace("getNameLabel() - Returning");
		logger.debug("getNameLabel() - Returning: {}", nameLabel);
		return nameLabel;
	}

	/**
	 * Returns an instance of this class if the static attribute instance is
	 * null it will be created.
	 * 
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
		logger.debug("getInstance() - Returning: {}", instance);
		return instance;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.nameTextfield.setText(((Summoner) arg1).getName());
	}

	/**
	 * Sets the current summoners name
	 * 
	 * @param name
	 *            {@link String} the new name
	 */

	public void setSummonerName(String name) {
		logger.trace("setSummonerName() - Entering");
		logger.debug("setSummonerName() - Parameter: {}", name);
		nameTextfield.setText(name);
		logger.trace("setSummonerName() - Leaving");
	}

	/**
	 * Returns the current summoners name
	 * 
	 * @return {@link String}
	 */
	public String getSummonerName() {
		logger.trace("getSummonerName() - Entering");
		logger.trace("getSummonerName() - Returning");
		logger.debug("getSummonerName() - Returning: {}", nameTextfield.getText());
		return nameTextfield.getText();
	}

}