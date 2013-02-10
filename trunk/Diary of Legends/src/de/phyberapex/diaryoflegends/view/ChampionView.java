package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.controller.ChampionController;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.observer.Observable;

public class ChampionView extends JPanel implements View {

	private static final long serialVersionUID = -8456209180736169342L;
	private ChampionController controller;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton clearSearchButton;
	private JButton newButton;
	private JButton deleteButton;
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
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 1;
		logger.debug("Adding newButton to panel with constraints: {}",
				constraints);
		this.add(getNewButton(), constraints);
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 1;
		logger.debug("Adding deleteButton to panel with constraints: {}",
				constraints);
		this.add(getDeleteButton(), constraints);
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 4;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		logger.debug("Adding championTable to panel with constraints: {}",
				constraints);
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
	
	private JButton getClearSearchButton() {
		logger.trace("getClearSearchButton() - Entering");
		if (clearSearchButton == null) {
			clearSearchButton = new JButton("Clear Filter");
			clearSearchButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					((Champion)championTable.getValueAt(1, 1)).setName("BLA");
				}
			});
		}
		logger.trace("getClearSearchButton() - Returning");
		logger.debug("getClearSearchButton() - Returning: {}", clearSearchButton);
		return clearSearchButton;
	}

	private JButton getNewButton() {
		logger.trace("getNewButton() - Entering");
		if (newButton == null) {
			newButton = new JButton("new Champion");
			newButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Champion c = new Champion();
					c.setName("test");
					c.setIcon(new File("C:\\test.png"));
					ChampionUtil.saveChampion(c);
					((ChampionTableModel)championTable.getModel()).addChamp(c);
				}
			});
		}
		logger.trace("getNewButton() - Returning");
		logger.debug("getNewButton() - Returning: {}", newButton);
		return newButton;
	}
	
	private JButton getDeleteButton() {
		logger.trace("getDeleteButton() - Entering");
		if (deleteButton == null) {
			deleteButton = new JButton("delete Champion");
			deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO yes no option blablabal
					Champion champ = (Champion)championTable.getValueAt(championTable.getSelectedRow(), 1);
					String message = "Are you sure to delete: "+champ.getName()+"?";
					String title = "Information";
					int ok = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if(ok == JOptionPane.OK_OPTION){
						ChampionUtil.deleteChampion(champ);
						((ChampionTableModel)championTable.getModel()).removeChamp(champ);
					}
				}
			});
		}
		logger.trace("getDeleteButton() - Returning");
		logger.debug("getDeleteButton() - Returning: {}", deleteButton);
		return deleteButton;
	}

	private JScrollPane getChampTablePane() {
		logger.trace("getChampTablePane() - Entering");
		if (champTablePane == null) {
			champTablePane = new JScrollPane(getChampionTable());
		}
		logger.trace("getChampTablePane() - Returning");
		logger.debug("getChampTablePane() - Returning: {}", champTablePane);
		return champTablePane;
	}

	private JTable getChampionTable() {
		logger.trace("getChampionTable() - Entering");
		if (championTable == null) {
			championTable = new JTable();
			ChampionTableModel m = new ChampionTableModel(
					controller.getChampions());
			championTable.setModel(m);
			championTable.setDefaultRenderer(Object.class, new ChampionTableRenderer());
		}
		logger.trace("getChampionTable() - Returning");
		logger.debug("getChampionTable() - Returning: {}", championTable);
		return championTable;
	}

	@Override
	public void update(Observable o, Object arg) {
		((ChampionTableModel)championTable.getModel()).fireTableDataChanged();
	}

}
