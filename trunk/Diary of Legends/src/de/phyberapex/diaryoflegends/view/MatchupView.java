package de.phyberapex.diaryoflegends.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.controller.MatchupController;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;

public class MatchupView extends JPanel implements View {
	private static final long serialVersionUID = -8456209180736169342L;
	private MatchupController controller;
	private JButton newButton;
	private JButton deleteButton;
	private JScrollPane matchupTablePane;
	private JTable matchupTable;
	//NewChampionDialoge newChampionDialoge = new NewChampionDialoge(this);
	private GridBagConstraints constraints;
	private static Logger logger = LogManager.getLogger(ChampionView.class
			.getName());

	public MatchupView(MatchupController controller) {
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
		logger.debug("Adding matchupTable to panel with constraints: {}",
				constraints);
		this.add(getMatchupTablePane(), constraints);
		logger.trace("createGUI() - Leaving");
	}

	/**
	 * Returns the newButton
	 * 
	 * @return {@link JButton} The newButton
	 */
	private JButton getNewButton() {
		logger.trace("getNewButton() - Entering");
		if (newButton == null) {
			newButton = new JButton("new Champion");
			newButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					//TODO
					//newChampionDialoge.setVisible(true);
				}
			});
		}
		logger.trace("getNewButton() - Returning");
		logger.debug("getNewButton() - Returning: {}", newButton);
		return newButton;
	}

	/**
	 * Returns the deleteButton
	 * 
	 * @return {@link JButton} The deleteButton
	 */
	private JButton getDeleteButton() {
		logger.trace("getDeleteButton() - Entering");
		if (deleteButton == null) {
			deleteButton = new JButton("delete Champion");
			deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (matchupTable.getSelectedRow() != -1) {
						Matchup matchup = (Matchup) matchupTable.getValueAt(
								matchupTable.getSelectedRow(), 1);
						String message = "Are you sure to delete: "
								+ matchup + "?";
						String title = "Information";
						int ok = JOptionPane.showConfirmDialog(null, message,
								title, JOptionPane.YES_NO_OPTION);
						if (ok == JOptionPane.OK_OPTION) {
							MatchupUtil.deleteMatchup(matchup);
							((MatchupTableModel) matchupTable.getModel())
									.removeMatchup(matchup);
							MainView.getInstance().setStatusText(
									"Matchup " + matchup+ " removed");
						}
					} else {
						// TODO schöner!
						JOptionPane.showMessageDialog(MainView.getInstance(),
								"No Champion selected");
					}
				}
			});
		}
		logger.trace("getDeleteButton() - Returning");
		logger.debug("getDeleteButton() - Returning: {}", deleteButton);
		return deleteButton;
	}

	/**
	 * Returns the matchupTablePane
	 * 
	 * @return {@link JScrollPane} The champTablePane
	 */
	private JScrollPane getMatchupTablePane() {
		logger.trace("getMatchupTablePane() - Entering");
		if (matchupTablePane == null) {
			matchupTablePane = new JScrollPane(getMatchupTable());
		}
		logger.trace("getMatchupTablePane() - Returning");
		logger.debug("getMatchupTablePane() - Returning: {}", matchupTablePane);
		return matchupTablePane;
	}

	/**
	 * Returns the matchupTable
	 * 
	 * @return {@link JTable} The championTable
	 */
	private JTable getMatchupTable() {
		logger.trace("getMatchupTable() - Entering");
		if (matchupTable == null) {
			matchupTable = new JTable();
			MatchupTableModel m = new MatchupTableModel(
					controller.getMatchups());
			matchupTable.setModel(m);
			matchupTable.setDefaultRenderer(Object.class,
					new MatchupTableRenderer());
		}
		logger.trace("getMatchupTable() - Returning");
		logger.debug("getMatchupTable() - Returning: {}", matchupTable);
		return matchupTable;
	}

	/**
	 * Adds a new matchup
	 * 
	 * @param matchup
	 *            The {@link Matchup matchup} to add
	 */
	public void addMatchup(Matchup matchup) {
		logger.trace("addChamp() - Entering");
		logger.debug("addChamp() - Parameter: {}", matchup);
		((MatchupTableModel) matchupTable.getModel()).addMatchup(matchup);
		logger.trace("addChamp() - Leaving");
	}

	@Override
	public void refresh() {
		((MatchupTableModel) matchupTable.getModel()).fireTableDataChanged();
	}
}