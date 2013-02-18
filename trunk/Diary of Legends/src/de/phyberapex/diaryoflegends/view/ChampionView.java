package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
	private JButton clearSearchButton;
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

	/**
	 * Creates the GUI
	 */
	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		logger.debug("Adding searchTextfield to panel with constraints: {}",
				constraints);
		this.add(getSearchTextfield(), constraints);
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 2;
		constraints.gridy = 0;
		logger.debug("Adding searchButton to panel with constraints: {}",
				constraints);
		this.add(getSearchButton(), constraints);
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 3;
		constraints.gridy = 0;
		logger.debug("Adding clearSearchButton to panel with constraints: {}",
				constraints);
		this.add(getClearSearchButton(), constraints);
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 4;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		logger.debug("Adding championTable to panel with constraints: {}",
				constraints);
		this.add(getChampTablePane(), constraints);
		logger.trace("createGUI() - Leaving");
	}

	/**
	 * Returns the searchTextfield
	 * 
	 * @return {@link JTextField} The searchTextfield
	 */
	private JTextField getSearchTextfield() {
		logger.trace("getSearchTextfield() - Entering");
		if (searchTextField == null) {
			searchTextField = new JTextField();
			searchTextField.setPreferredSize(new Dimension(150, 30));
			searchTextField.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getSearchButton().doClick();
				}
			});
		}
		logger.trace("getSearchTextfield() - Returning");
		logger.debug("getSearchTextfield() - Returning: {}", searchTextField);
		return searchTextField;
	}

	/**
	 * Returns the searchButton
	 * 
	 * @return {@link JButton} The searchButton
	 */
	private JButton getSearchButton() {
		logger.trace("getSearchButton() - Entering");
		if (searchButton == null) {
			searchButton = new JButton("Search");
			searchButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					List<Champion> champs = controller.searchChampions(searchTextField.getText());
					MainView.getInstance().setStatusText(
							champs.size() + " Search results");
					((ChampionTableModel) championTable.getModel())
							.setChamps(champs);
				}
			});
		}
		logger.trace("getSearchButton() - Returning");
		logger.debug("getSearchButton() - Returning: {}", searchButton);
		return searchButton;
	}

	/**
	 * Returns the clearSearchButton
	 * 
	 * @return {@link JButton} The clearSearchButton
	 */
	private JButton getClearSearchButton() {
		logger.trace("getClearSearchButton() - Entering");
		if (clearSearchButton == null) {
			clearSearchButton = new JButton("Clear Filter");
			clearSearchButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					((ChampionTableModel) championTable.getModel())
							.setChamps(controller.getChampions());
				}
			});
		}
		logger.trace("getClearSearchButton() - Returning");
		logger.debug("getClearSearchButton() - Returning: {}",
				clearSearchButton);
		return clearSearchButton;
	}

	/**
	 * Returns the champTablePane
	 * 
	 * @return {@link JScrollPane} The champTablePane
	 */
	private JScrollPane getChampTablePane() {
		logger.trace("getChampTablePane() - Entering");
		if (champTablePane == null) {
			champTablePane = new JScrollPane(getChampionTable());
		}
		logger.trace("getChampTablePane() - Returning");
		logger.debug("getChampTablePane() - Returning: {}", champTablePane);
		return champTablePane;
	}

	/**
	 * Returns the championTable
	 * 
	 * @return {@link JTable} The championTable
	 */
	private JTable getChampionTable() {
		logger.trace("getChampionTable() - Entering");
		if (championTable == null) {
			championTable = new JTable();
			ChampionTableModel m = new ChampionTableModel(
					controller.getChampions());
			championTable.setModel(m);
			championTable.setDefaultRenderer(Object.class,
					new ChampionTableRenderer());
		}
		logger.trace("getChampionTable() - Returning");
		logger.debug("getChampionTable() - Returning: {}", championTable);
		return championTable;
	}

	@Override
	public void refresh() {
		((ChampionTableModel) championTable.getModel()).fireTableDataChanged();
	}
}