package de.phyberapex.diaryoflegends.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.controller.ChampionController;
import de.phyberapex.diaryoflegends.model.Champion;

public class ChampionView extends JPanel implements View {

	private static final long serialVersionUID = -8456209180736169342L;
	private ChampionController controller;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton newButton;
	private JScrollPane champTablePane;
	private JTable championTable;
	private GridBagConstraints constraints;
	private static Logger logger = LogManager.getLogger(ChampionView.class
			.getName());

	public ChampionView(ChampionController controller) {
		logger.trace("ChampionView() - Entering");
		logger.debug("ChampionView() - Parameter: {}", controller);
		this.controller = controller;
		createGUI();
		logger.trace("ChampionView() - Leaving");
	}

	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		logger.debug("Adding searchTextfield to panel with constraints: {}", constraints);
		this.add(getSearchTextfield(), constraints);
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 0;
		logger.debug("Adding searchButton to panel with constraints: {}", constraints);
		this.add(getSearchButton(), constraints);
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 2;
		constraints.gridy = 0;
		logger.debug("Adding newButton to panel with constraints: {}", constraints);
		this.add(getNewButton(), constraints);
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 3;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		logger.debug("Adding championTable to panel with constraints: {}", constraints);
		this.add(getChampTablePane(), constraints);
		logger.trace("createGUI() - Leaving");
	}

	private JTextField getSearchTextfield() {
		logger.trace("getSearchTextfield() - Entering");
		if (searchTextField == null) {
			searchTextField = new JTextField();
			searchTextField.setPreferredSize(new Dimension(150, 30));
		}
		logger.trace("getSearchTextfield() - Returning");
		logger.debug("getSearchTextfield() - Returning: {}", searchTextField);
		return searchTextField;
	}

	private JButton getSearchButton() {
		logger.trace("getSearchButton() - Entering");
		if (searchButton == null) {
			searchButton = new JButton("Search");
		}
		logger.trace("getSearchButton() - Returning");
		logger.debug("getSearchButton() - Returning: {}", searchButton);
		return searchButton;
	}
	
	private JButton getNewButton() {
		logger.trace("getNewButton() - Entering");
		if (newButton == null) {
			newButton = new JButton("new Champion");
		}
		logger.trace("getNewButton() - Returning");
		logger.debug("getNewButton() - Returning: {}", newButton);
		return newButton;
	}
	
	private Component getChampTablePane() {
		logger.trace("getChampTablePane() - Entering");
		if (champTablePane == null) {
			champTablePane = new JScrollPane(getChampionTable());
		}
		logger.trace("getChampTablePane() - Returning");
		logger.debug("getChampTablePane() - Returning: {}", champTablePane);
		return champTablePane;
	}
	
	private Component getChampionTable() {
		logger.trace("getChampionTable() - Entering");
		if (championTable == null) {
			championTable = new JTable();
			ChampionTableModel m = new ChampionTableModel(controller.getChampions());
			championTable.setModel(m);
			championTable.setPreferredSize(new Dimension(200,300));
		}
		logger.trace("getChampionTable() - Returning");
		logger.debug("getChampionTable() - Returning: {}", championTable);
		return championTable;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Champion) {
			// Champ wurde geändert
		} else {
			// Champ liste wurde geändert
		}
	}

}
