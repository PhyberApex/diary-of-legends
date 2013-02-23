package de.phyberapex.diaryoflegends.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.controller.GameController;
import de.phyberapex.diaryoflegends.extra.LoadingSplash;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.view.dialoge.MatchupDetailDialoge;
import de.phyberapex.diaryoflegends.view.dialoge.NewEntryDialoge;
import de.phyberapex.diaryoflegends.view.model.GameTableModel;
import de.phyberapex.diaryoflegends.view.renderer.GameTableRenderer;

public class GameView extends JPanel implements View {

	private static final long serialVersionUID = 5737240943659740168L;
	private GameController controller;
	private JButton newButton;
	private JButton deleteButton;
	private JScrollPane gameTablePane;
	private JTable gameTable;
	private GridBagConstraints constraints;
	private static Logger logger = LogManager.getLogger(ChampionView.class
			.getName());

	public GameView(GameController controller) {
		logger.trace("GameView() - Entering");
		logger.debug("GameView() - Parameter: {}", controller);
		this.controller = controller;
		createGUI();
		logger.trace("GameView() - Leaving");
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
		constraints.gridy = 0;
		logger.debug("Adding newButton to panel with constraints: {}",
				constraints);
		this.add(getNewButton(), constraints);

		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 0;
		logger.debug("Adding deleteButton to panel with constraints: {}",
				constraints);
		this.add(getDeleteButton(), constraints);

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 4;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		logger.debug("Adding championTable to panel with constraints: {}",
				constraints);
		this.add(getGameTablePane(), constraints);

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
			newButton = new JButton("new Game & Matchup");
			newButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					LoadingSplash spl = new LoadingSplash();
					spl.setVisible(true);
					SwingUtilities.invokeLater(new NewEntryDialoge());
					spl.close();
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
			deleteButton = new JButton("delete Game & Matchup");
			deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (gameTable.getSelectedRow() != -1) {
						Game game = (Game) gameTable.getValueAt(
								gameTable.getSelectedRow(), 1);
						String message = "Are you sure to delete: " + game
								+ "?";
						String title = "Information";
						int ok = JOptionPane.showConfirmDialog(null, message,
								title, JOptionPane.YES_NO_OPTION);
						if (ok == JOptionPane.OK_OPTION) {
							controller.deleteGame(game, true);
							((GameTableModel) gameTable.getModel())
									.removeGame(game);
							MainView.getInstance().setStatusText(
									"Game and matchup " + game.getMatchup()
											+ " removed");
						}
					} else {
						// TODO schöner!
						JOptionPane.showMessageDialog(MainView.getInstance(),
								"No Item selected");
					}
				}
			});
		}
		logger.trace("getDeleteButton() - Returning");
		logger.debug("getDeleteButton() - Returning: {}", deleteButton);
		return deleteButton;
	}

	/**
	 * Returns the gameTablePane
	 * 
	 * @return {@link JScrollPane} The itemTablePane
	 */
	private JScrollPane getGameTablePane() {
		logger.trace("getItemTablePane() - Entering");
		if (gameTablePane == null) {
			gameTablePane = new JScrollPane(getGameTable());
		}
		logger.trace("getItemTablePane() - Returning");
		logger.debug("getItemTablePane() - Returning: {}", gameTablePane);
		return gameTablePane;
	}

	/**
	 * Returns the itemTable
	 * 
	 * @return {@link JTable} The itemTable
	 */
	private JTable getGameTable() {
		logger.trace("getItemTable() - Entering");
		if (gameTable == null) {
			gameTable = new JTable();
			GameTableModel m = new GameTableModel(controller.getGames());
			gameTable.setModel(m);
			gameTable.setDefaultRenderer(Object.class, new GameTableRenderer());
			gameTable.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					final int row = gameTable.rowAtPoint(e.getPoint());
					if (e.getButton() == MouseEvent.BUTTON3) {
						JPopupMenu menu = new JPopupMenu();
						JMenuItem edit = new JMenuItem("Edit");
						edit.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								LoadingSplash spl = new LoadingSplash();
								spl.setVisible(true);
								NewEntryDialoge nd = new NewEntryDialoge();
								nd.setToEdit(((Game) gameTable.getValueAt(row,
										1)), false);
								SwingUtilities.invokeLater(nd);
								spl.close();
							}
						});
						JMenuItem view = new JMenuItem("View");
						view.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new MatchupDetailDialoge()
										.showDetails((Matchup) gameTable
												.getValueAt(row, 1));
							}
						});
						menu.add(edit);
						menu.add(view);
						menu.show(gameTable, e.getX(), e.getY());
					} else if (e.getClickCount() == 2) {
						// MatchupDetailDialoge.getInstance().showDetails(
						// (Game) gameTable.getValueAt(row, 1));
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {

				}
			});
		}
		logger.trace("getItemTable() - Returning");
		logger.debug("getItemTable() - Returning: {}", gameTable);
		return gameTable;
	}

	/**
	 * Adds a new Game
	 * 
	 * @param game
	 *            The {@link Game game} to add
	 */
	public void addGame(Game game) {
		logger.trace("addItem() - Entering");
		logger.debug("addItem() - Parameter: {}", game);
		((GameTableModel) gameTable.getModel()).addGame(game);
		logger.trace("addItem() - Leaving");
	}

	@Override
	public void refresh() {
		((GameTableModel) gameTable.getModel()).fireTableDataChanged();
	}

	/**
	 * @param game
	 */
	public void removeGame(Game game) {
		logger.trace("removeGame() - Entering");
		logger.debug("removeGame() - Parameter: {}", game);
		((GameTableModel) gameTable.getModel()).removeGame(game);
		logger.trace("removeGame() - Leaving");
	}
}